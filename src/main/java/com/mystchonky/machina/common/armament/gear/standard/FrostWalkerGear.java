package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.PerkTrait;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;

public class FrostWalkerGear extends AbstractGear {

    public FrostWalkerGear() {
        super("frost_walker");

        addTrait(new PerkTrait(PerkLibrary.FROST_WALKER));
    }

    @Override
    public String displayName() {
        return "Frost Walker";
    }
}
