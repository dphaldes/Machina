package com.mystchonky.machina.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public class CodexItem extends Item {
    public CodexItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return itemStack.copy();
    }

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
//        ItemStack itemstack = player.getItemInHand(hand);
//        if (level.isClientSide()) {
//            ScreenManager.openCodexScreen(player);
//        }
//        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
//    }
}
