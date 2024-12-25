package com.mystchonky.machina.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mystchonky.machina.common.registrar.RecipeRegistrar;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record RiftRecipe(ItemStack result, Ingredient ingredient) implements Recipe<RiftRecipe.Input> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(ingredient);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 1;
    }

    @Override
    public boolean matches(Input input, Level level) {
        return this.ingredient.test(input.stack());
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }

    @Override
    public ItemStack assemble(Input input, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistrar.Serializers.RIFT.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistrar.Types.RIFT.get();
    }

    public record Input(ItemStack stack) implements RecipeInput {

        @Override
        public ItemStack getItem(int index) {
            if (index != 0) throw new IllegalArgumentException("No item for index: " + index);
            return this.stack();
        }

        @Override
        public int size() {
            return 1;
        }
    }

    public static class Serializer implements RecipeSerializer<RiftRecipe> {

        private static final MapCodec<RiftRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(RiftRecipe::result),
                Ingredient.CODEC_NONEMPTY.fieldOf("input").forGetter(RiftRecipe::ingredient)
        ).apply(instance, RiftRecipe::new));

        private static final StreamCodec<RegistryFriendlyByteBuf, RiftRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        ItemStack.STREAM_CODEC, RiftRecipe::result,
                        Ingredient.CONTENTS_STREAM_CODEC, RiftRecipe::ingredient,
                        RiftRecipe::new
                );

        @Override
        public MapCodec<RiftRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, RiftRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}


