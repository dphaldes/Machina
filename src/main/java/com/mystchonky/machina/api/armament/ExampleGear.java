package com.mystchonky.machina.api.armament;

import com.mystchonky.machina.api.armament.traits.AttributeTrait;
import com.mystchonky.machina.api.armament.traits.EnchantmentTrait;
import com.mystchonky.machina.api.armament.traits.PerkTrait;
import com.mystchonky.machina.api.armament.traits.PotionTrait;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.Enchantments;

public class ExampleGear extends AbstractGear {
    private static final MobEffectInstance REGENERATION = new MobEffectInstance(MobEffects.REGENERATION, -1, 0, true, true);

    public ExampleGear() {
        super("example");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.MOVEMENT_SPEED, 0.6, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        attribute.addModifier(Attributes.GRAVITY, -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addTrait(attribute);

        addTrait(new PotionTrait(REGENERATION));

        addTrait(new PerkTrait(PerkLibrary.GLIDE));

        addTrait(new EnchantmentTrait(Enchantments.AQUA_AFFINITY, 1, EquipmentSlot.HEAD));
    }

    @Override
    public String displayName() {
        return "Example Gear";
    }
}
