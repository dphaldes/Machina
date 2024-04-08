package com.mystchonky.machina.common.arsenal.gear;

import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import com.mystchonky.machina.common.arsenal.GearLibrary;

public class BreathGear extends AbstractGear {
    public static final BreathGear INSTANCE = new BreathGear();

    protected BreathGear() {
        super(GearLibrary.WATER_BREATH);
    }

    @Override
    public String getDisplayName() {
        return "Water Breathing Gear";
    }
}
