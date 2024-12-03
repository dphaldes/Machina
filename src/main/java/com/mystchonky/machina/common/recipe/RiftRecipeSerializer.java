package com.mystchonky.machina.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class RiftRecipeSerializer implements RecipeSerializer<RiftRecipe> {

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
