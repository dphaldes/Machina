package mod.machina.api.augment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.machina.api.RegistryKeys;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public record Augment(Component name, List<Trait> traits, HolderSet<Augment> exclusiveSet) {

    public static final Codec<Augment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    ComponentSerialization.CODEC.fieldOf("name").forGetter(Augment::name),
                    Trait.CODEC.listOf().fieldOf("traits").forGetter(Augment::traits),
                    RegistryCodecs.homogeneousList(RegistryKeys.AUGMENT)
                            .optionalFieldOf("exclusive_set", HolderSet.direct())
                            .forGetter(Augment::exclusiveSet)
            ).apply(instance, Augment::new)
    );

    public static final Codec<Holder<Augment>> HOLDER_CODEC = RegistryFixedCodec.create(RegistryKeys.AUGMENT);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<Augment>> HOLDER_STREAM_CODEC = ByteBufCodecs.holderRegistry(RegistryKeys.AUGMENT);

    public void onEquip(Player player) {
        if (player.level().isClientSide()) return;

        traits.forEach(it -> it.onEquip(player));
    }

    public void onRemove(Player player) {
        if (player.level().isClientSide()) return;

        traits.forEach(it -> it.onRemove(player));
    }

    public static boolean areCompatible(Holder<Augment> first, Holder<Augment> second) {
        return !first.equals(second) && !first.value().exclusiveSet.contains(second) && !second.value().exclusiveSet.contains(first);
    }

}
