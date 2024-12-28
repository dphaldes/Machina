package com.mystchonky.machina.common.gear.standard;

import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.api.gear.trait.AttributeTrait;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class HealthGear extends Gear {

    public HealthGear() {
        super("health");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.MAX_HEALTH, 6, AttributeModifier.Operation.ADD_VALUE);
        attribute.addModifier(Attributes.KNOCKBACK_RESISTANCE, 0.3, AttributeModifier.Operation.ADD_VALUE);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Fortitude";
    }
}
