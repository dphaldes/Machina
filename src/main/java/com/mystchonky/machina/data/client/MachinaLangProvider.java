package com.mystchonky.machina.data.client;

import com.mystchonky.machina.Machina;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MachinaLangProvider extends LanguageProvider {
    public MachinaLangProvider(PackOutput output, String locale) {
        super(output, Machina.MODID, locale);
    }

    @Override
    protected void addTranslations() {
    }
}
