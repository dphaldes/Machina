package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.PerkTrait;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;

public class AqueousGear extends AbstractGear {

    public AqueousGear() {
        super("aqueous");

        addTrait(new PerkTrait(PerkLibrary.AQUA_AFFINITY));
        addTrait(new PerkTrait(PerkLibrary.DEPTH_STRIDER));
    }

    @Override
    public String displayName() {
        return "Aqueous Gear";
    }
}
