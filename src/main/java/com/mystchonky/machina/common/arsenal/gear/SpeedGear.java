package com.mystchonky.machina.common.arsenal.gear;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import com.mystchonky.machina.api.arsenal.gear.AttributeGear;
import com.mystchonky.machina.common.arsenal.GearLibrary;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class SpeedGear extends AbstractGear implements AttributeGear {
    public static final SpeedGear INSTANCE = new SpeedGear();

    public static final UUID GEAR_SPEED_UUID = UUID.fromString("ac0be5f9-6906-4d06-98a7-1114f11d46ff");

    protected SpeedGear() {
        super(GearLibrary.SPEED);
    }

    @Override
    public String getDisplayName() {
        return "Speed Gear";
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getModifiers() {
        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> modifiers = new ImmutableMultimap.Builder<>();
        modifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(GEAR_SPEED_UUID, "SpeedGear", 0.6, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return modifiers.build();
    }

}
