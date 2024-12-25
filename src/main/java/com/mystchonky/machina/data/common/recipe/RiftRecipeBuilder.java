package com.mystchonky.machina.data.common.recipe;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.recipe.RiftRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class RiftRecipeBuilder extends SimpleRecipeBuilder {
    private final Ingredient input;

    public RiftRecipeBuilder(ItemStack result, Ingredient input) {
        super(result);
        this.input = input;
    }

    @Override
    public void save(RecipeOutput recipeOutput) {
        var id = RecipeBuilder.getDefaultRecipeId(this.getResult()).getPath();
        this.save(recipeOutput, Machina.prefix("rift/" + id));
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        var recipe = new RiftRecipe(this.result, this.input);
        recipeOutput.accept(id, recipe, null);
    }
}
