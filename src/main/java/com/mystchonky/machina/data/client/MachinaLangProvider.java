package com.mystchonky.machina.data.client;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.registrar.ItemRegistrar;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MachinaLangProvider extends LanguageProvider {
    public MachinaLangProvider(PackOutput output, String locale) {
        super(output, Machina.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(ItemRegistrar.EXO_HELMET.get(), "Exo Helmet");
        add(ItemRegistrar.EXO_PLATE.get(), "Exo Plate");
        add(ItemRegistrar.EXO_LEGGINGS.get(), "Exo Leggings");
        add(ItemRegistrar.EXO_BOOTS.get(), "Exo Boots");
    }
}
