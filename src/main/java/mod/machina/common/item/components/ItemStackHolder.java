package mod.machina.common.item.components;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public record ItemStackHolder(ItemStack stack) {
    public static final Codec<ItemStackHolder> CODEC = ItemStack.SINGLE_ITEM_CODEC.xmap(ItemStackHolder::new, ItemStackHolder::stack);
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemStackHolder> STREAM_CODEC = ItemStack.STREAM_CODEC.map(ItemStackHolder::new, ItemStackHolder::stack);

    public static ItemStackHolder of(Item item) {
        return new ItemStackHolder(new ItemStack(item, 1));
    }

    public static ItemStackHolder of(ItemStack stack) {
        return new ItemStackHolder(stack);
    }
}
