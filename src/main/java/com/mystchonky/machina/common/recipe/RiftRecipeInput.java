package com.mystchonky.machina.common.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record RiftRecipeInput(ItemStack stack) implements RecipeInput {

    @Override
    public ItemStack getItem(int slot) {
        if (slot != 0) throw new IllegalArgumentException("No item for index: "+ slot);
        return this.stack();
    }

    @Override
    public int size() {
        return 1;
    }
}
