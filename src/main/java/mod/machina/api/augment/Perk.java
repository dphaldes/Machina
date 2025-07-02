package mod.machina.api.augment;

import com.mojang.serialization.Codec;
import mod.machina.api.RegistryKeys;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceLocation;

public record Perk(ResourceLocation id) {
    public static final Codec<Holder<Perk>> CODEC = RegistryFixedCodec.create(RegistryKeys.PERK);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<Perk>> STREAM_CODEC = ByteBufCodecs.holderRegistry(RegistryKeys.PERK);
}
