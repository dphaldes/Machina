package mod.machina.api.gear.trait;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public record EnchantmentLevel(ResourceKey<Enchantment> enchant, int level) {
    public static Codec<EnchantmentLevel> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ResourceKey.codec(Registries.ENCHANTMENT).fieldOf("enchantment").forGetter(EnchantmentLevel::enchant),
                    Codec.INT.fieldOf("level").forGetter(EnchantmentLevel::level)
            ).apply(instance, EnchantmentLevel::new)
    );

    public static StreamCodec<RegistryFriendlyByteBuf, EnchantmentLevel> STREAM_CODEC = StreamCodec.composite(
            ResourceKey.streamCodec(Registries.ENCHANTMENT),
            EnchantmentLevel::enchant,
            ByteBufCodecs.INT,
            EnchantmentLevel::level,
            EnchantmentLevel::new
    );
}
