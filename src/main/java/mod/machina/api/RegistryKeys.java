package mod.machina.api;

import mod.machina.Machina;
import mod.machina.api.gear.Gear;
import mod.machina.api.rune.Rune;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class RegistryKeys {
    public static final ResourceKey<Registry<Gear>> GEARS = ResourceKey.createRegistryKey(Machina.prefix("gears"));
    public static final ResourceKey<Registry<Rune>> RUNES = ResourceKey.createRegistryKey(Machina.prefix("runes"));
}
