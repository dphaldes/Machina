package com.mystchonky.machina.common.gear.standard;

import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.api.gear.trait.PerkTrait;
import com.mystchonky.machina.common.perk.PerkLibrary;

public class GlideGear extends Gear {

    public GlideGear() {
        super("glide");

        addTrait(new PerkTrait(PerkLibrary.GLIDE));
    }

    @Override
    public String displayName() {
        return "Glide Gear";
    }
}
