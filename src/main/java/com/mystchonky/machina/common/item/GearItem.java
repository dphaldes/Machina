package com.mystchonky.machina.common.item;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.common.armament.gear.UnlockedGears;
import com.mystchonky.machina.common.network.NetworkedAttachments;
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
        super(new Properties().stacksTo(1));
        this.gear = gear;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide())
            return super.use(level, player, hand);

        var unlockedGears = UnlockedGears.get(player);
        if (!unlockedGears.contains(gear)) {
            unlockedGears.add(gear);
        } else {
            unlockedGears.remove(gear);
        }
        NetworkedAttachments.syncGears(player);
        player.sendSystemMessage(Component.literal("WOWOWOA"));
        return super.use(level, player, hand);
    }
}
