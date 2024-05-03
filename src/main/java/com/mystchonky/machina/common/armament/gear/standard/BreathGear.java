package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.gear.AbstractGear;
import com.mystchonky.machina.api.armament.gear.traits.PotionTrait;
import com.mystchonky.machina.common.armament.gear.GearLibrary;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class BreathGear extends AbstractGear {
    private static final MobEffectInstance WATER_BREATHING = new MobEffectInstance(MobEffects.WATER_BREATHING, -1, 0, true, true);
    private static final MobEffectInstance REGENERATION = new MobEffectInstance(MobEffects.REGENERATION, -1, 0, true, true);
    public static final BreathGear INSTANCE = new BreathGear();

    protected BreathGear() {
        super(GearLibrary.WATER_BREATH);

        var water = new PotionTrait(WATER_BREATHING);
        addTrait(water);

        var regeneration = new PotionTrait(REGENERATION);
        addTrait(regeneration);
    }

    @Override
    public String displayName() {
        return "Water Breathing Gear";
    }
}
