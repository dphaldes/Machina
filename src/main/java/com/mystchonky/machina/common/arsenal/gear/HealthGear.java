package com.mystchonky.machina.common.arsenal.gear;

import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import com.mystchonky.machina.common.arsenal.GearLibrary;

public class HealthGear extends AbstractGear {
    public static final HealthGear INSTANCE = new HealthGear();

    protected HealthGear() {
        super(GearLibrary.HEALTH);
    }

    @Override
    public String getDisplayName() {
        return "Health Gear";
    }
}
