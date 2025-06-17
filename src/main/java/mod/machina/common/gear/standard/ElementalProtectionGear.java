package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.PerkTrait;
import mod.machina.common.registrar.PerkRegistrar;

public class ElementalProtectionGear extends Gear {
    public ElementalProtectionGear() {
        super("elemental_protection");

        addTrait(new PerkTrait(PerkRegistrar.PROTECTION_FIRE));
        addTrait(new PerkTrait(PerkRegistrar.PROTECTION_WATER));
        addTrait(new PerkTrait(PerkRegistrar.PROTECTION_BLAST));
        addTrait(new PerkTrait(PerkRegistrar.PROTECTION_FREEZE));
    }

    @Override
    public String displayName() {
        return "Elemental Protection";
    }
}
