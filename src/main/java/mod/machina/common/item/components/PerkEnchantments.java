package mod.machina.common.item.components;

import com.mojang.serialization.Codec;
import mod.machina.api.gear.trait.EnchantmentLevel;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.List;

public record PerkEnchantments(List<EnchantmentLevel> enchantments) {
    public static Codec<PerkEnchantments> CODEC = EnchantmentLevel.CODEC.listOf().xmap(PerkEnchantments::new, PerkEnchantments::enchantments);
    public static StreamCodec<RegistryFriendlyByteBuf, PerkEnchantments> STREAM_CODEC =
            EnchantmentLevel.STREAM_CODEC
                    .apply(ByteBufCodecs.list())
                    .map(PerkEnchantments::new, PerkEnchantments::enchantments);
}
