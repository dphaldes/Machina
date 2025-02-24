package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.PerkTrait;
import mod.machina.common.perk.PerkLibrary;

public class ElytraGear extends Gear {

    public ElytraGear() {
        super("elytra");

        addTrait(new PerkTrait(PerkLibrary.GLIDE));
    }

    @Override
    public String displayName() {
        return "Elytra";
    }
}
