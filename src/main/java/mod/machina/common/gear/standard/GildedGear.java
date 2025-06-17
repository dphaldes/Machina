package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.PerkTrait;
import mod.machina.common.registrar.PerkRegistrar;

public class GildedGear extends Gear {
    public GildedGear() {
        super("gilded");

        addTrait(new PerkTrait(PerkRegistrar.GILDED));
    }

    @Override
    public String displayName() {
        return "Gilded";
    }
}
