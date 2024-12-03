package com.mystchonky.machina.common.recipe;

import com.mystchonky.machina.common.registrar.RecipeRegistrar;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record RiftRecipe(Ingredient ingredient, ItemStack result) implements Recipe<RiftRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(ingredient);
    }


    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public boolean matches(RiftRecipeInput input, Level level) {
        return this.ingredient.test(input.stack());
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }

    @Override
    public ItemStack assemble(RiftRecipeInput input, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistrar.Serializers.RIFT.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistrar.Types.RIFT.get();
    }
}


