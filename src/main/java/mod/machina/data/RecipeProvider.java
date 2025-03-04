package mod.machina.data;

import mod.machina.common.recipe.CompendiumRecipe;
import mod.machina.data.recipe.GearRecipeProvider;
import mod.machina.data.recipe.ShapelessRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SpecialRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

    public RecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        GearRecipeProvider.build(recipeOutput);
        ShapelessRecipeProvider.build(recipeOutput);
        SpecialRecipeBuilder.special(CompendiumRecipe::new)
                .save(recipeOutput, "compendium_weapon_swap");
    }

}
