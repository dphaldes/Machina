package com.mystchonky.machina.common.gear.standard;

import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.api.gear.trait.AttributeTrait;
import com.mystchonky.machina.api.gear.trait.PerkTrait;
import com.mystchonky.machina.common.perk.PerkLibrary;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class JumpGear extends Gear {

    public JumpGear() {
        super("jump");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.JUMP_STRENGTH, 0.2, AttributeModifier.Operation.ADD_VALUE);
        addTrait(attribute);

        addTrait(new PerkTrait(PerkLibrary.PROTECTION_FALL));
    }

    @Override
    public String displayName() {
        return "Jump Gear";
    }
}
