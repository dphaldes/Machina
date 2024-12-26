package com.mystchonky.machina.data.common;

import com.mystchonky.machina.common.registrar.BlockRegistrar;
import com.mystchonky.machina.common.registrar.GearRegistrar;
import com.mystchonky.machina.common.registrar.ItemRegistrar;
import com.mystchonky.machina.data.common.recipe.GearRecipeBuilder;
import com.mystchonky.machina.data.common.recipe.RiftRecipeBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
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
        riftRecipe(ItemRegistrar.GRIMOIRE, Items.BOOK, recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockRegistrar.CODEX.blockItem())
                .requires(ItemRegistrar.GRIMOIRE.asItem())
                .requires(Items.LECTERN)
                .unlockedBy("has_grimoire", has(ItemRegistrar.GRIMOIRE.asItem()))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.ARMOR.get())
                .addIngredient(Ingredient.of(Items.DIAMOND_CHESTPLATE))
                .addIngredient(Ingredient.of(Items.IRON_INGOT))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.HEALTH.get())
                .addIngredient(Ingredient.of(Tags.Items.ARMORS))
                .addIngredient(Ingredient.of(ItemTags.MEAT))
                .addIngredient(Ingredient.of(Tags.Items.CROPS))
                .addIngredient(Ingredient.of(Items.GOLDEN_APPLE))
                .addIngredient(Ingredient.of(Items.GHAST_TEAR))
                .save(recipeOutput);

    }

    private void riftRecipe(DeferredItem<? extends Item> item, Item ingredient, RecipeOutput recipeOutput) {
        new RiftRecipeBuilder(new ItemStack(item.get()), Ingredient.of(ingredient)).save(recipeOutput);
    }
}
