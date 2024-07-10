package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.AttributeTrait;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class HealthGear extends AbstractGear {

    public HealthGear() {
        super("health");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.MAX_HEALTH, 6, AttributeModifier.Operation.ADD_VALUE);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Health Gear";
    }
}
