package com.mystchonky.machina.common.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;

import java.util.Optional;

public record ArsenalGearSlot(AbstractGear.Polarity polarity, Optional<AbstractGear> equippedGear) {

    public static final Codec<ArsenalGearSlot> CODEC = RecordCodecBuilder.create(instance -> instance.group(AbstractGear.Polarity.CODEC.fieldOf("polarity").forGetter(ArsenalGearSlot::polarity),
            AbstractGear.CODEC.optionalFieldOf("equippedGear").forGetter(ArsenalGearSlot::equippedGear)).apply(instance, ArsenalGearSlot::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ArsenalGearSlot> STREAM_CODEC = StreamCodec.composite(
            NeoForgeStreamCodecs.enumCodec(AbstractGear.Polarity.class),
            ArsenalGearSlot::polarity,
            AbstractGear.STREAM_CODEC.apply(ByteBufCodecs::optional),
            ArsenalGearSlot::equippedGear,
            ArsenalGearSlot::new
    );

    public static ArsenalGearSlot empty() {
        return new ArsenalGearSlot(AbstractGear.Polarity.NONE, Optional.empty());
    }

    public State getSlotState() {
        return equippedGear.map(gear -> polarity == gear.getPolarity() ? State.MATCH : State.NOT_MATCH).orElse(State.EMPTY);
    }

    public enum State {
        EMPTY, MATCH, NOT_MATCH
    }
}

