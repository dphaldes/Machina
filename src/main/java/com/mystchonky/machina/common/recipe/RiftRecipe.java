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

public record RiftRecipe(Ingredient ingredient, ItemStack result) implements Recipe<RiftRecipe.Input> {

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
        public ItemStack getItem(int slot) {
            if (slot != 0) throw new IllegalArgumentException("No item for index: " + slot);
            return this.stack();
        }

        @Override
        public int size() {
            return 1;
        }
    }

    public static class Serializer implements RecipeSerializer<RiftRecipe> {

        public static final MapCodec<RiftRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC.fieldOf("input").forGetter(RiftRecipe::ingredient),
                ItemStack.CODEC.fieldOf("result").forGetter(RiftRecipe::result)
        ).apply(instance, RiftRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, RiftRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, RiftRecipe::ingredient,
                        ItemStack.STREAM_CODEC, RiftRecipe::result,
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


