package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.PerkTrait;
import mod.machina.common.registrar.PerkRegistrar;

public class ElytraGear extends Gear {

    public ElytraGear() {
        super("elytra");

        addTrait(new PerkTrait(PerkRegistrar.GLIDE));
    }

    @Override
    public String displayName() {
        return "Elytra";
    }
}
