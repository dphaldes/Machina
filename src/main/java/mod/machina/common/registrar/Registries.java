package mod.machina.common.registrar;

import com.mojang.serialization.MapCodec;
import mod.machina.api.RegistryKeys;
import mod.machina.api.augment.Augment;
import mod.machina.api.augment.Perk;
import mod.machina.api.augment.Trait;
import mod.machina.api.rune.Rune;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;


public class Registries {

    public static final Registry<Rune> RUNES = new RegistryBuilder<>(RegistryKeys.RUNE)
            .sync(true)
            .create();

    public static final Registry<MapCodec<? extends Trait>> TRAIT_CODECS = new RegistryBuilder<>(RegistryKeys.TRAIT_CODEC)
            .sync(true)
            .create();

    public static final Registry<Perk> PERKS = new RegistryBuilder<>(RegistryKeys.PERK)
            .sync(true)
            .create();

    public static void register(NewRegistryEvent event) {
        event.register(RUNES);
        event.register(TRAIT_CODECS);
        event.register(PERKS);
    }

    public static void datapackRegisty(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(RegistryKeys.AUGMENT, Augment.CODEC, Augment.CODEC);
    }
}
