package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.AttributeTrait;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SpeedGear extends AbstractGear {

    public SpeedGear() {
        super("speed");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.MOVEMENT_SPEED, 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        attribute.addModifier(Attributes.STEP_HEIGHT, 0.4, AttributeModifier.Operation.ADD_VALUE);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Speed Gear";
    }
}
