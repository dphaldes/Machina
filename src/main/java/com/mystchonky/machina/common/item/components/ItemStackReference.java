package com.mystchonky.machina.common.item.components;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public record ItemStackReference(ItemStack stack) {
    public static final Codec<ItemStackReference> CODEC = ItemStack.SINGLE_ITEM_CODEC.xmap(ItemStackReference::new, ItemStackReference::stack);
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemStackReference> STREAM_CODEC = ItemStack.STREAM_CODEC.map(ItemStackReference::new, ItemStackReference::stack);

    public static ItemStackReference of(Item item) {
        return new ItemStackReference(new ItemStack(item, 1));
    }

    public static ItemStackReference of(ItemStack stack) {
        return new ItemStackReference(stack);
    }
}
