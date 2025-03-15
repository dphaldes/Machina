package mod.machina.common.item.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.machina.api.gear.trait.EnchantmentLevel;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.List;
import java.util.function.Consumer;

public record ArmorTraits(List<EnchantmentLevel> enchantments) implements TooltipProvider {
    public static Codec<ArmorTraits> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    EnchantmentLevel.CODEC.listOf().fieldOf("enchantments").orElse(List.of()).forGetter(ArmorTraits::enchantments)
            ).apply(instance, ArmorTraits::new)
    );
    public static StreamCodec<RegistryFriendlyByteBuf, ArmorTraits> STREAM_CODEC = StreamCodec.composite(
            EnchantmentLevel.STREAM_CODEC.apply(ByteBufCodecs.list()),
            ArmorTraits::enchantments,
            ArmorTraits::new
    );

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
        var provider = context.registries();
        if (provider != null) {
            var registry = provider.lookupOrThrow(Registries.ENCHANTMENT);
            for (var pair : enchantments) {
                var holder = registry.get(pair.enchant());
                holder.ifPresent(enchant -> tooltipAdder.accept(Enchantment.getFullname(enchant, pair.level())));
            }
        }
    }
}
