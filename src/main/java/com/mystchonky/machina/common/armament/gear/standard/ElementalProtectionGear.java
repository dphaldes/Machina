package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.PerkTrait;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;

public class ElementalProtectionGear extends AbstractGear {
    public ElementalProtectionGear() {
        super("elemental_protection");

        addTrait(new PerkTrait(PerkLibrary.PROTECTION_FIRE));
        addTrait(new PerkTrait(PerkLibrary.PROTECTION_WATER));
        addTrait(new PerkTrait(PerkLibrary.PROTECTION_BLAST));
        addTrait(new PerkTrait(PerkLibrary.PROTECTION_FREEZE));
    }

    @Override
    public String displayName() {
        return "Elemental Protection Gear";
    }
}
