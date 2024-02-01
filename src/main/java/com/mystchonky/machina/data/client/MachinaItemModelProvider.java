package com.mystchonky.machina.data.client;

import com.mystchonky.machina.Machina;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MachinaItemModelProvider extends ItemModelProvider {
    public MachinaItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Machina.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }
}
