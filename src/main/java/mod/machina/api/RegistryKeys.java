package mod.machina.api;

import com.mojang.serialization.MapCodec;
import mod.machina.Machina;
import mod.machina.api.augment.Augment;
import mod.machina.api.augment.Perk;
import mod.machina.api.augment.Trait;
import mod.machina.api.rune.Rune;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class RegistryKeys {
    public static final ResourceKey<Registry<Rune>> RUNE = ResourceKey.createRegistryKey(Machina.prefix("runes"));
    public static final ResourceKey<Registry<Perk>> PERK = ResourceKey.createRegistryKey(Machina.prefix("perk"));
    public static final ResourceKey<Registry<Augment>> AUGMENT = ResourceKey.createRegistryKey(Machina.prefix("augment"));
    public static final ResourceKey<Registry<MapCodec<? extends Trait>>> TRAIT_CODEC = ResourceKey.createRegistryKey(Machina.prefix("trait_codec"));
}
