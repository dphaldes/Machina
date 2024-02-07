package com.mystchonky.machina.common.gear;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mystchonky.machina.common.registrar.GearRegistrar;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public record GearSlot(Gear.Polarity slotPolarity, Optional<GearHolder> equippedGear) {

    public static final Codec<GearSlot> CODEC = RecordCodecBuilder.create(instance -> instance.group(Gear.Polarity.CODEC.fieldOf("polarity").forGetter(GearSlot::slotPolarity), GearHolder.CODEC.optionalFieldOf("equippedGear").forGetter(GearSlot::equippedGear)).apply(instance, GearSlot::new));

    public static GearSlot empty() {
        return new GearSlot(Gear.Polarity.NONE, Optional.empty());
    }

    public State getSlotPolarityState() {
        return equippedGear.map(gearHolder -> slotPolarity == gearHolder.gear().getPolarity() ? State.MATCH : State.NOT_MATCH).orElse(State.EMPTY);
    }

    public enum State {
        EMPTY, MATCH, NOT_MATCH
    }

    public record GearHolder(Gear gear, int level) {

        public static final Codec<GearHolder> CODEC = RecordCodecBuilder.create(instance -> instance.group(ResourceLocation.CODEC.fieldOf("gear").forGetter(GearHolder::getGearRL), Codec.INT.fieldOf("level").forGetter(GearHolder::level)).apply(instance, GearHolder::create));

        public static GearHolder create(ResourceLocation gearRL, int level) {
            var registry = GearRegistrar.GEARS.getRegistry().get();
            return new GearHolder(registry.get(gearRL), level);
        }

        public ResourceLocation getGearRL() {
            var registry = GearRegistrar.GEARS.getRegistry().get();
            return registry.getKey(gear);
        }
    }
}

