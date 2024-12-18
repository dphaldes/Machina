package com.mystchonky.machina.common.item;

import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.common.gear.UnlockedGears;
import com.mystchonky.machina.common.network.NetworkedAttachments;
import com.mystchonky.machina.common.registrar.LangRegistrar;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class GearItem extends Item {
    public final Gear gear;

    public GearItem(Gear gear) {
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
            if (!player.isCreative())
                player.getItemInHand(hand).shrink(1);
            player.sendSystemMessage(Component.translatable(LangRegistrar.GEAR_UNLOCK.key(), Component.translatable(gear.localizationKey())));

        } else if (player.isCreative() && player.isSecondaryUseActive()) {
            unlockedGears.remove(gear);
            player.sendSystemMessage(Component.translatable(LangRegistrar.GEAR_REMOVE.key(), Component.translatable(gear.localizationKey())));
        }

        NetworkedAttachments.syncGears(player);
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, List<Component> components, TooltipFlag flag) {
        this.gear.getTooltip(components);
    }
}
