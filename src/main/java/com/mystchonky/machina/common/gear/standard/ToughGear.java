package com.mystchonky.machina.common.gear.standard;

import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.api.gear.trait.AttributeTrait;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ToughGear extends Gear {
    public ToughGear() {
        super("tough");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.ARMOR_TOUGHNESS, 5, AttributeModifier.Operation.ADD_VALUE);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Tough";
    }
}
