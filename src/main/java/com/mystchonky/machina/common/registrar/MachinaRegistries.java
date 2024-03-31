package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class MachinaRegistries {
    public static final Registry<AbstractGear> GEAR_REGISTRY = new RegistryBuilder<AbstractGear>(ResourceKey.createRegistryKey(Machina.prefix("gears"))).create();

    public static void register(final NewRegistryEvent event) {
        event.register(GEAR_REGISTRY);
    }
}
