package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.PerkTrait;
import mod.machina.common.perk.PerkLibrary;

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
