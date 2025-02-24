package mod.machina.api;

import mod.machina.Machina;
import mod.machina.api.gear.Gear;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class RegistryKeys {
    public static final ResourceKey<Registry<Gear>> GEARS = ResourceKey.createRegistryKey(Machina.prefix("gears"));
}
