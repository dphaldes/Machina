package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.api.rune.Rune;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RuneRegistrar {

    public static final DeferredRegister<Rune> RUNES = DeferredRegister.create(Registries.RUNES, Machina.ID);

    public static final DeferredHolder<Rune, Rune> PHI = register("phi");
    public static final DeferredHolder<Rune, Rune> PSI = register("psi");
    public static final DeferredHolder<Rune, Rune> THETA = register("theta");

    private static DeferredHolder<Rune, Rune> register(String id) {
        return RUNES.register(id, () -> new Rune(Machina.prefix(id)));
    }

    public static void register(IEventBus bus) {
        RUNES.register(bus);
    }
}
