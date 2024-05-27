package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.PerkTrait;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;

public class GildedGear extends AbstractGear {
    public GildedGear() {
        super("gilded");

        addTrait(new PerkTrait(PerkLibrary.GILDED));
    }

    @Override
    public String displayName() {
        return "Gilded";
    }
}
