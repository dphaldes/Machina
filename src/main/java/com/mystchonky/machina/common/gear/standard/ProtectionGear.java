package com.mystchonky.machina.common.gear.standard;

import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.api.gear.trait.PerkTrait;
import com.mystchonky.machina.common.perk.PerkLibrary;

public class ProtectionGear extends Gear {
    public ProtectionGear() {
        super("protection");

        addTrait(new PerkTrait(PerkLibrary.PROTECTION));
    }

    @Override
    public String displayName() {
        return "Protection";
    }
}
