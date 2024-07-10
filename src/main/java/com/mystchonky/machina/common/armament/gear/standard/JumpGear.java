package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.AttributeTrait;
import com.mystchonky.machina.api.armament.traits.PerkTrait;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class JumpGear extends AbstractGear {

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
