package com.mystchonky.machina.data.common;

import com.mystchonky.machina.common.registrar.ItemRegistrar;
import com.mystchonky.machina.data.common.recipe.RiftRecipeBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

    public RecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        riftRecipe(ItemRegistrar.VOID_HELMET, Items.IRON_HELMET, recipeOutput);
        riftRecipe(ItemRegistrar.VOID_CHESTPLATE, Items.IRON_CHESTPLATE, recipeOutput);
        riftRecipe(ItemRegistrar.VOID_LEGGINGS, Items.IRON_LEGGINGS, recipeOutput);
        riftRecipe(ItemRegistrar.VOID_BOOTS, Items.IRON_BOOTS, recipeOutput);
    }

    private void riftRecipe(DeferredItem<? extends Item> item, Item ingredient, RecipeOutput recipeOutput) {
        new RiftRecipeBuilder(new ItemStack(item.get()), Ingredient.of(ingredient)).save(recipeOutput);
    }
}
