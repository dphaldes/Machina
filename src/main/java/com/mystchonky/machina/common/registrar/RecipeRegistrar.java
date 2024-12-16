package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.recipe.RiftRecipe;
import com.mystchonky.machina.common.recipe.RiftRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
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

        public static final Supplier<RecipeType<RiftRecipe>> RIFT = TYPES.register("rift", () -> RecipeType.simple(Machina.prefix("rift")));

        public static void register(IEventBus bus) {
            TYPES.register(bus);
        }
    }

    public static class Serializers {
        public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Machina.ID);

        public static final Supplier<RecipeSerializer<RiftRecipe>> RIFT = SERIALIZERS.register("rift", RiftRecipeSerializer::new);

        public static void register(IEventBus bus) {
            SERIALIZERS.register(bus);
        }
    }
}
