package com.mystchonky.machina.data;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.data.client.ItemModelProvider;
import com.mystchonky.machina.data.client.LanguageProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Machina.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MachinaDataGenerator {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        Machina.LOGGER.info("DATAGENNING");
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();

        //client
        dataGenerator.addProvider(event.includeClient(), new ItemModelProvider(packOutput, helper));
        dataGenerator.addProvider(event.includeClient(), new LanguageProvider(packOutput, "en_us"));
    }

}
