package com.mystchonky.machina.data.common;

import com.mystchonky.machina.common.registrar.GearRegistrar;
import com.mystchonky.machina.common.registrar.ItemRegistrar;
import com.mystchonky.machina.data.common.recipe.GearRecipeBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

    public RecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistrar.GRIMOIRE)
                .requires(ItemRegistrar.CODEX)
                .requires(Items.BOOK)
                .unlockedBy("has_codex", has(ItemRegistrar.CODEX))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.ARMOR)
                .addIngredient(Ingredient.of(Items.DIAMOND_CHESTPLATE))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.HEALTH)
                .addIngredient(Ingredient.of(Items.GOLDEN_APPLE))
                .addIngredient(Ingredient.of(Items.IRON_CHESTPLATE))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.AQUEOUS)
                .addIngredient(Ingredient.of(Items.PRISMARINE_CRYSTALS, Items.PRISMARINE_BRICKS, Items.DARK_PRISMARINE))
                .addIngredient(Ingredient.of(Items.TURTLE_SCUTE, Items.TURTLE_HELMET))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.FROST_WALKER)
                .addIngredient(Ingredient.of(Items.BLUE_ICE))
                .addIngredient(Ingredient.of(Items.IRON_BOOTS))
                .addIngredient(Ingredient.of(Items.LAPIS_LAZULI))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.GILDED)
                .addIngredient(Ingredient.of(Items.GOLD_BLOCK))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.ELYTRA)
                .addIngredient(Ingredient.of(Items.ELYTRA))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.PROTECTION)
                .addIngredient(Ingredient.of(Items.IRON_BLOCK))
                .addIngredient(Ingredient.of(Items.IRON_CHESTPLATE))
                .addIngredient(Ingredient.of(Items.LAPIS_LAZULI))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.ELMENTAL_PROTECTION)
                .addIngredient(Ingredient.of(Items.FEATHER))
                .addIngredient(Ingredient.of(Items.MAGMA_CREAM))
                .addIngredient(Ingredient.of(Items.SEA_LANTERN))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.SPEED)
                .addIngredient(Ingredient.of(Items.IRON_BOOTS))
                .addIngredient(Ingredient.of(Items.FEATHER))
                .addIngredient(Ingredient.of(Items.FEATHER))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.JUMP)
                .addIngredient(Ingredient.of(Items.IRON_BOOTS))
                .addIngredient(Ingredient.of(Items.SLIME_BALL))
                .addIngredient(Ingredient.of(Items.SLIME_BALL))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.TOUGH)
                .addIngredient(Ingredient.of(Items.NETHERITE_SCRAP))
                .addIngredient(Ingredient.of(Items.NETHERITE_SCRAP))
                .addIngredient(Ingredient.of(Items.NETHERITE_SCRAP))
                .addIngredient(Ingredient.of(Items.NETHERITE_SCRAP))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.MINING)
                .addIngredient(Ingredient.of(Items.DIAMOND_PICKAXE))
                .addIngredient(Ingredient.of(Items.LAPIS_LAZULI))
                .addIngredient(Ingredient.of(Items.REDSTONE_BLOCK))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.ATTACK)
                .addIngredient(Ingredient.of(Items.DIAMOND_SWORD))
                .addIngredient(Ingredient.of(Items.AMETHYST_SHARD))
                .addIngredient(Ingredient.of(Items.REDSTONE_BLOCK))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.SMALL)
                .addIngredient(Ingredient.of(Items.HONEY_BOTTLE))
                .addIngredient(Ingredient.of(Items.LAPIS_LAZULI))
                .save(recipeOutput);

        GearRecipeBuilder.builder(GearRegistrar.BIG)
                .addIngredient(Ingredient.of(Items.HONEY_BOTTLE))
                .addIngredient(Ingredient.of(Items.REDSTONE))
                .save(recipeOutput);
    }

}
