package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.armament.AbstractGear;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class Registries {
    public static final ResourceKey<Registry<AbstractGear>> GEARS = ResourceKey.createRegistryKey(Machina.prefix("gears"));
    public static final Registry<AbstractGear> GEARS_REGISTRY = new RegistryBuilder<>(GEARS)
            .sync(true)
            .create();

    public static void register(final NewRegistryEvent event) {
        event.register(GEARS_REGISTRY);
    }
}
