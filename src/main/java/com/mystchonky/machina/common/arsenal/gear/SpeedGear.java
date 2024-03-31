package com.mystchonky.machina.common.arsenal.gear;

import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import com.mystchonky.machina.common.arsenal.GearLibrary;

public class SpeedGear extends AbstractGear {
    public static final SpeedGear INSTANCE = new SpeedGear();

    protected SpeedGear() {
        super(GearLibrary.SPEED);
    }

}
