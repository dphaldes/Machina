package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.gear.AbstractGear;
import com.mystchonky.machina.api.armament.gear.traits.PerkTrait;
import com.mystchonky.machina.common.armament.gear.GearLibrary;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;

public class GlideGear extends AbstractGear {
    public static final GlideGear INSTANCE = new GlideGear();

    protected GlideGear() {
        super(GearLibrary.GLIDE);

        addTrait(new PerkTrait(PerkLibrary.GLIDE));
    }

    @Override
    public String displayName() {
        return "Glide Gear";
    }
}
