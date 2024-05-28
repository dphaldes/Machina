package com.mystchonky.machina.api.armament;

import com.mystchonky.machina.api.armament.traits.AttributeTrait;
import com.mystchonky.machina.api.armament.traits.PerkTrait;
import com.mystchonky.machina.api.armament.traits.PotionTrait;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class ExampleGear extends AbstractGear {
    public static final UUID GEAR_UUID = UUID.fromString("ac0be5f9-6906-4d06-98a7-1114f11d46ff");
    private static final MobEffectInstance REGENERATION = new MobEffectInstance(MobEffects.REGENERATION, -1, 0, true, true);

    public ExampleGear() {
        super("example");

        var attribute = new AttributeTrait(GEAR_UUID);
        attribute.addModifier(this, Attributes.MOVEMENT_SPEED, 0.6, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        attribute.addModifier(this, Attributes.GRAVITY, -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addTrait(attribute);

        addTrait(new PotionTrait(REGENERATION));

        addTrait(new PerkTrait(PerkLibrary.GLIDE));
    }

    @Override
    public String displayName() {
        return "Example Gear";
    }
}
