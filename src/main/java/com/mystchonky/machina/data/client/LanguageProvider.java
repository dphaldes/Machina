package com.mystchonky.machina.data.client;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.common.perk.PerkLibrary;
import com.mystchonky.machina.common.registrar.GearRegistrar;
import com.mystchonky.machina.common.registrar.ItemRegistrar;
import com.mystchonky.machina.common.registrar.LangRegistrar;
import net.minecraft.data.PackOutput;

public class LanguageProvider extends net.neoforged.neoforge.common.data.LanguageProvider {
    public LanguageProvider(PackOutput output, String locale) {
        super(output, Machina.ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(ItemRegistrar.VOID_HELMET.get(), "Voidwalker Hood");
        add(ItemRegistrar.VOID_CHESTPLATE.get(), "Voidwalker Mantle");
        add(ItemRegistrar.VOID_LEGGINGS.get(), "Voidwalker Breeches");
        add(ItemRegistrar.VOID_BOOTS.get(), "Voidwalker Greaves");
        add(ItemRegistrar.CODEX.get(), "Codex");
        add(ItemRegistrar.GRIMOIRE.get(), "Grimoire");

        GearRegistrar.GEARS.getEntries().forEach(gear -> {
            if (gear.get() == Gear.EMPTY)
                return;
            add(gear.get().getGearItem(), gear.get().displayName());
            add(gear.get().localizationKey(), gear.get().displayName());
        });

        PerkLibrary.entries().forEach(this::add);
        LangRegistrar.entries().forEach(this::add);
    }
}
