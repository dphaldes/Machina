package mod.machina.data;

import mod.machina.data.recipe.GearRecipeProvider;
import mod.machina.data.recipe.ShapelessRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

    public RecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        GearRecipeProvider.build(recipeOutput);
        ShapelessRecipeProvider.build(recipeOutput);
    }

}
