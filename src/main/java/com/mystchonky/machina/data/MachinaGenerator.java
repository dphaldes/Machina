package com.mystchonky.machina.data;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.data.client.MachinaItemModelProvider;
import com.mystchonky.machina.data.client.MachinaLangProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Machina.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MachinaGenerator {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();

        //client
        dataGenerator.addProvider(event.includeClient(), new MachinaItemModelProvider(packOutput, helper));
        dataGenerator.addProvider(event.includeClient(), new MachinaLangProvider(packOutput, "en_us"));
    }

}
