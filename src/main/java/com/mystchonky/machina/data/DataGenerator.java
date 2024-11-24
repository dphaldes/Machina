package com.mystchonky.machina.data;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.data.client.ItemModelProvider;
import com.mystchonky.machina.data.client.LanguageProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Machina.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerator {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        var generator = event.getGenerator();
        var packOutput = generator.getPackOutput();
        var helper = event.getExistingFileHelper();

        //client
        generator.addProvider(event.includeClient(), new ItemModelProvider(packOutput, helper));
        generator.addProvider(event.includeClient(), new LanguageProvider(packOutput, "en_us"));
    }

}
