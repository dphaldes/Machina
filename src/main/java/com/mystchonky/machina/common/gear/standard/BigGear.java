package com.mystchonky.machina.common.gear.standard;

import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.api.gear.trait.AttributeTrait;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class BigGear extends Gear {
    public BigGear() {
        super("big");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.SCALE, 1, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Big";
    }
}
