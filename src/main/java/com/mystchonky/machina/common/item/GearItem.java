package com.mystchonky.machina.common.item;

import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import com.mystchonky.machina.common.attachment.AttachmentManager;
import com.mystchonky.machina.common.attachment.UnlockedGears;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GearItem extends Item {
    public final AbstractGear gear;

    public GearItem(AbstractGear gear) {
        super(new Properties());
        this.gear = gear;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide())
            return super.use(level, player, hand);

        UnlockedGears unlocked = UnlockedGears.get(player);
        if (!unlocked.gears().contains(gear)) {
            unlocked.gears().add(gear);
        } else {
            unlocked.gears().remove(gear);
        }
        AttachmentManager.syncGears(player);
        player.sendSystemMessage(Component.literal("WOWOWOA"));
        return super.use(level, player, hand);
    }
}
