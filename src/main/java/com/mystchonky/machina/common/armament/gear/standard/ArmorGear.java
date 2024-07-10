package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.AttributeTrait;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ArmorGear extends AbstractGear {

    public ArmorGear() {
        super("armor");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.ARMOR, 5, AttributeModifier.Operation.ADD_VALUE);
        attribute.addModifier(Attributes.ARMOR_TOUGHNESS, 8, AttributeModifier.Operation.ADD_VALUE);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Armor Gear";
    }
}
