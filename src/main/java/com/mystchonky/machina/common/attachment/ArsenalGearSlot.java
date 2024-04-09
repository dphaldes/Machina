package com.mystchonky.machina.common.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import javax.annotation.Nullable;
import java.util.Optional;

public record ArsenalGearSlot(@Nullable AbstractGear equippedGear) {

    public static final Codec<ArsenalGearSlot> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AbstractGear.CODEC.optionalFieldOf("equippedGear")
                    .forGetter(ArsenalGearSlot::equippedGearOptional)).apply(instance, ArsenalGearSlot::fromOptional));

    public static final StreamCodec<RegistryFriendlyByteBuf, ArsenalGearSlot> STREAM_CODEC = StreamCodec.composite(
            AbstractGear.STREAM_CODEC.apply(ByteBufCodecs::optional),
            ArsenalGearSlot::equippedGearOptional,
            ArsenalGearSlot::fromOptional
    );

    public static ArsenalGearSlot fromOptional(Optional<AbstractGear> gear) {
        return new ArsenalGearSlot(gear.orElse(null));
    }

    public Optional<AbstractGear> equippedGearOptional() {
        return Optional.ofNullable(equippedGear);
    }

    public static ArsenalGearSlot empty() {
        return new ArsenalGearSlot(null);
    }

}

