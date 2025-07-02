package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.api.augment.Perk;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class PerkRegistrar {
    public static final DeferredRegister<Perk> PERKS = DeferredRegister.create(Registries.PERKS, Machina.ID);

    public static final Supplier<Perk> GLIDE = register("glide");
    public static final Supplier<Perk> GILDED = register("gilded");

    private static Supplier<Perk> register(String id) {
        return PERKS.register(id, () -> new Perk(Machina.prefix(id)));
    }

    public static void register(IEventBus bus) {
        PERKS.register(bus);
    }
}
