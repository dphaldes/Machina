package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.AttributeTrait;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class ArmorGear extends AbstractGear {
    private static final UUID GEAR_ARMOR_UUID = UUID.fromString("22b6105b-9765-4262-888b-f564ab30f285");

    public ArmorGear() {
        super("armor");

        var attribute = new AttributeTrait(GEAR_ARMOR_UUID);
        attribute.addModifier(this, Attributes.ARMOR, 5, AttributeModifier.Operation.ADD_VALUE);
        attribute.addModifier(this, Attributes.ARMOR_TOUGHNESS, 8, AttributeModifier.Operation.ADD_VALUE);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Armor Gear";
    }
}
