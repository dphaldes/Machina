package mod.machina.common.registrar;

import mod.machina.api.RegistryKeys;
import mod.machina.api.gear.Gear;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;


public class Registries {
    public static final Registry<Gear> GEARS_REGISTRY = new RegistryBuilder<>(RegistryKeys.GEARS)
            .sync(true)
            .create();

    public static void register(final NewRegistryEvent event) {
        event.register(GEARS_REGISTRY);
    }
}
