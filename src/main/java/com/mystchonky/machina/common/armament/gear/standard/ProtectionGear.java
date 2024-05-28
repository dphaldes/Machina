package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.PerkTrait;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;

public class ProtectionGear extends AbstractGear {
    public ProtectionGear() {
        super("protection");

        addTrait(new PerkTrait(PerkLibrary.PROTECTION));
    }

    @Override
    public String displayName() {
        return "Protection Gear";
    }
}
