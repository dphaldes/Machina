package com.mystchonky.machina.common.gear.standard;

import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.api.gear.trait.PerkTrait;
import com.mystchonky.machina.common.perk.PerkLibrary;

public class GildedGear extends Gear {
    public GildedGear() {
        super("gilded");

        addTrait(new PerkTrait(PerkLibrary.GILDED));
    }

    @Override
    public String displayName() {
        return "Gilded";
    }
}
