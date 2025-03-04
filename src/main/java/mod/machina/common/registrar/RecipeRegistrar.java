package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.common.recipe.CompendiumRecipe;
import mod.machina.common.recipe.GearRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RecipeRegistrar {

    public static void register(IEventBus bus) {
        Types.register(bus);
        Serializers.register(bus);
    }

    public static class Types {
        public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Machina.ID);

        public static final Supplier<RecipeType<GearRecipe>> GEAR = TYPES.register("gear", () -> RecipeType.simple(Machina.prefix("gear")));

        public static void register(IEventBus bus) {
            TYPES.register(bus);
        }
    }

    public static class Serializers {
        public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Machina.ID);

        public static final Supplier<RecipeSerializer<GearRecipe>> GEAR = SERIALIZERS.register("gear", GearRecipe.Serializer::new);
        public static final Supplier<RecipeSerializer<?>> COMPENDIUM = SERIALIZERS.register("compendium", () -> new SimpleCraftingRecipeSerializer<>(CompendiumRecipe::new));

        public static void register(IEventBus bus) {
            SERIALIZERS.register(bus);
        }
    }
}
