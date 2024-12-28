package com.mystchonky.machina.common.gear.standard;

import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.api.gear.trait.AttributeTrait;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class MiningGear extends Gear {
    public MiningGear() {
        super("mining");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.MINING_EFFICIENCY, 16, AttributeModifier.Operation.ADD_VALUE);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Mining";
    }
}
