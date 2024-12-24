package com.mystchonky.machina.api;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.gear.Gear;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class RegistryKeys {
    public static final ResourceKey<Registry<Gear>> GEARS = ResourceKey.createRegistryKey(Machina.prefix("gears"));
}
