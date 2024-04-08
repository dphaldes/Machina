package com.mystchonky.machina.common.arsenal.gear;

import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import com.mystchonky.machina.common.arsenal.GearLibrary;

public class GlideGear extends AbstractGear {
    public static final GlideGear INSTANCE = new GlideGear();

    protected GlideGear() {
        super(GearLibrary.GLIDE);
    }

    @Override
    public String getDisplayName() {
        return "Glide Gear";
    }
}
