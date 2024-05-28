package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.AttributeTrait;
import com.mystchonky.machina.api.armament.traits.PerkTrait;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class JumpGear extends AbstractGear {
    private static final UUID GEAR_SPEED_UUID = UUID.fromString("09ac1295-2f7d-4bf1-aa8a-9ad81d304b48");

    public JumpGear() {
        super("jump");

        var attribute = new AttributeTrait(GEAR_SPEED_UUID);
        attribute.addModifier(this, Attributes.JUMP_STRENGTH, 0.2, AttributeModifier.Operation.ADD_VALUE);
        addTrait(attribute);

        addTrait(new PerkTrait(PerkLibrary.PROTECTION_FALL));
    }

    @Override
    public String displayName() {
        return "Jump Gear";
    }
}
