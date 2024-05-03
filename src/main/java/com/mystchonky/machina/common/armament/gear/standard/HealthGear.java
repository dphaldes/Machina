package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.gear.AbstractGear;
import com.mystchonky.machina.api.armament.gear.traits.AttributeTrait;
import com.mystchonky.machina.common.armament.gear.GearLibrary;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class HealthGear extends AbstractGear {
    public static final UUID GEAR_HEALTH_UUID = UUID.fromString("ce8c612f-8a82-409d-b46d-3d27ffaa1c64");
    public static final HealthGear INSTANCE = new HealthGear();

    protected HealthGear() {
        super(GearLibrary.HEALTH);

        var attribute = new AttributeTrait(GEAR_HEALTH_UUID);
        attribute.addModifier(this, Attributes.MAX_HEALTH, 10, AttributeModifier.Operation.ADD_VALUE);
        attribute.addModifier(this, Attributes.SCALE, 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Health Gear";
    }
}
