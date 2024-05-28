package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.AttributeTrait;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class HealthGear extends AbstractGear {
    private static final UUID GEAR_HEALTH_UUID = UUID.fromString("ce8c612f-8a82-409d-b46d-3d27ffaa1c64");

    public HealthGear() {
        super("health");

        var attribute = new AttributeTrait(GEAR_HEALTH_UUID);
        attribute.addModifier(this, Attributes.MAX_HEALTH, 6, AttributeModifier.Operation.ADD_VALUE);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Health Gear";
    }
}
