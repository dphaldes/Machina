package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.api.Rune;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RuneRegistrar {

    public static final DeferredRegister<Rune> RUNES = DeferredRegister.create(Registries.RUNES, Machina.ID);

    public static final DeferredHolder<Rune, Rune> PHI = register("phi");

    private static DeferredHolder<Rune, Rune> register(String id) {
        return RUNES.register(id, () -> new Rune(Machina.prefix(id)));
    }

}
