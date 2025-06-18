package mod.machina.common.registrar;

import mod.machina.api.RegistryKeys;
import mod.machina.api.gear.Gear;
import mod.machina.api.rune.Rune;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;


public class Registries {
    public static final Registry<Gear> GEARS = new RegistryBuilder<>(RegistryKeys.GEARS)
            .sync(true)
            .create();

    public static final Registry<Rune> RUNES = new RegistryBuilder<>(RegistryKeys.RUNES)
            .sync(true)
            .create();

    public static void register(NewRegistryEvent event) {
        event.register(GEARS);
        event.register(RUNES);
    }
}
