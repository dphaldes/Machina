package mod.machina.data.recipe;

import mod.machina.Machina;
import mod.machina.api.gear.Gear;
import mod.machina.common.recipe.GearRecipe;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class GearRecipeBuilder implements RecipeBuilder {

    protected final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @Nullable
    protected String group;
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    protected final Gear result;

    protected GearRecipeBuilder(Gear result) {
        this.result = result;
    }

    public static GearRecipeBuilder builder(Supplier<? extends Gear> result) {
        return builder(result.get());
    }

    public static GearRecipeBuilder builder(Gear result) {
        return new GearRecipeBuilder(result);
    }

    public GearRecipeBuilder addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    @Override
    public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }


    @Nullable
    @Override
    public Item getResult() {
        return null;
    }

    @Override
    public void save(RecipeOutput recipeOutput) {
        this.save(recipeOutput, Machina.prefix("gear/" + this.result.id()));
    }


    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        var recipe = new GearRecipe(this.result, this.ingredients);
        recipeOutput.accept(id, recipe, null);
    }
}
