package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.PerkTrait;
import mod.machina.common.perk.PerkLibrary;

public class ElementalProtectionGear extends Gear {
    public ElementalProtectionGear() {
        super("elemental_protection");

        addTrait(new PerkTrait(PerkLibrary.PROTECTION_FIRE));
        addTrait(new PerkTrait(PerkLibrary.PROTECTION_WATER));
        addTrait(new PerkTrait(PerkLibrary.PROTECTION_BLAST));
        addTrait(new PerkTrait(PerkLibrary.PROTECTION_FREEZE));
    }

    @Override
    public String displayName() {
        return "Elemental Protection";
    }
}
