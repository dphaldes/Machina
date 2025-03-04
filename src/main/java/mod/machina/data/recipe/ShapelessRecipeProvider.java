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
    }
}
