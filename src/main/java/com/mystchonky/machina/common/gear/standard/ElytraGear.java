package com.mystchonky.machina.common.gear.standard;

import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.api.gear.trait.PerkTrait;
import com.mystchonky.machina.common.perk.PerkLibrary;

public class ElytraGear extends Gear {

    public ElytraGear() {
        super("elytra");

        addTrait(new PerkTrait(PerkLibrary.GLIDE));
    }

    @Override
    public String displayName() {
        return "Elytra";
    }
}
