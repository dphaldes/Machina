package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.gear.AbstractGear;
import com.mystchonky.machina.api.armament.gear.traits.AttributeTrait;
import com.mystchonky.machina.common.armament.gear.GearLibrary;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class SpeedGear extends AbstractGear {
    public static final UUID GEAR_SPEED_UUID = UUID.fromString("ac0be5f9-6906-4d06-98a7-1114f11d46ff");
    public static final SpeedGear INSTANCE = new SpeedGear();

    protected SpeedGear() {
        super(GearLibrary.SPEED);

        var attribute = new AttributeTrait(GEAR_SPEED_UUID);
        attribute.addModifier(this, Attributes.MOVEMENT_SPEED, 0.6, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        attribute.addModifier(this, Attributes.GRAVITY, -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Speed Gear";
    }
}
