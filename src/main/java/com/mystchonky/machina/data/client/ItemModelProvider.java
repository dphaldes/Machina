package com.mystchonky.machina.data.client;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.registrar.ItemRegistrar;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
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
