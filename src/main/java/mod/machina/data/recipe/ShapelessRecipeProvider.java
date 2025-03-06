package mod.machina.data.recipe;

import mod.machina.common.registrar.ItemRegistrar;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

import static net.minecraft.data.recipes.RecipeProvider.has;

public class ShapelessRecipeProvider {

    public static void build(RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistrar.COMPENDIUM)
                .requires(Items.BOOK)
                .requires(Items.IRON_SWORD)
                .unlockedBy("has_iron_sword", has(Items.IRON_SWORD))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistrar.VOID_HELMET)
                .requires(ItemRegistrar.COMPENDIUM)
                .requires(Items.IRON_HELMET)
                .unlockedBy("has_compendium", has(ItemRegistrar.COMPENDIUM))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistrar.VOID_CHESTPLATE)
                .requires(ItemRegistrar.COMPENDIUM)
                .requires(Items.IRON_CHESTPLATE)
                .unlockedBy("has_compendium", has(ItemRegistrar.COMPENDIUM))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistrar.VOID_LEGGINGS)
                .requires(ItemRegistrar.COMPENDIUM)
                .requires(Items.IRON_LEGGINGS)
                .unlockedBy("has_compendium", has(ItemRegistrar.COMPENDIUM))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistrar.VOID_BOOTS)
                .requires(ItemRegistrar.COMPENDIUM)
                .requires(Items.IRON_BOOTS)
                .unlockedBy("has_compendium", has(ItemRegistrar.COMPENDIUM))
                .save(recipeOutput);
    }
}
