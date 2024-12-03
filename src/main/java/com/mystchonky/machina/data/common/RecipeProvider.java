package com.mystchonky.machina.data.common;

import com.mystchonky.machina.common.registrar.ItemRegistrar;
import com.mystchonky.machina.data.common.recipe.RiftRecipeBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

    public RecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        new RiftRecipeBuilder(
                new ItemStack(ItemRegistrar.VOID_BOOTS.get()),
                Ingredient.of(Items.IRON_BOOTS)
        ).save(recipeOutput);
    }
}
