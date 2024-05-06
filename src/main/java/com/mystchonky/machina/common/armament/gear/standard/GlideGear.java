package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.PerkTrait;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;

public class GlideGear extends AbstractGear {

    public GlideGear() {
        super("glide");

        addTrait(new PerkTrait(PerkLibrary.GLIDE));
    }

    @Override
    public String displayName() {
        return "Glide Gear";
    }
}
