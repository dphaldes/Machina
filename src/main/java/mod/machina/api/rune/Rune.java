package mod.machina.api.rune;

import com.mojang.serialization.Codec;
import mod.machina.Machina;
import mod.machina.common.registrar.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public record Rune(ResourceLocation id) {
    public static final Rune EMPTY = new Rune(Machina.prefix("null"));
    public static final Codec<Rune> CODEC = Registries.RUNES.byNameCodec();
    public static final StreamCodec<RegistryFriendlyByteBuf, Rune> STREAM_CODEC = ByteBufCodecs.registry(Registries.RUNES.key());
}
