package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.PerkTrait;
import mod.machina.common.perk.PerkLibrary;

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
