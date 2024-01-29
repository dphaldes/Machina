package com.mystchonky.machina.data.client;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.registrar.ItemRegistrar;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MachinaItemModelProvider extends ItemModelProvider {
    public MachinaItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Machina.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistrar.EXO_HELMET.get());
        basicItem(ItemRegistrar.EXO_PLATE.get());
        basicItem(ItemRegistrar.EXO_LEGGINGS.get());
        basicItem(ItemRegistrar.EXO_BOOTS.get());
    }
}
