package com.mystchonky.machina.data;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.data.client.BlockStateProvider;
import com.mystchonky.machina.data.client.ItemModelProvider;
import com.mystchonky.machina.data.client.LanguageProvider;
import com.mystchonky.machina.data.common.BlockTagsProvider;
import com.mystchonky.machina.data.common.ItemTagsProvider;
import com.mystchonky.machina.data.common.LootTableProvider;
import com.mystchonky.machina.data.common.RecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Machina.ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerator {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        var lookupProvider = event.getLookupProvider();
        var helper = event.getExistingFileHelper();
        var generator = event.getGenerator();
        var packOutput = generator.getPackOutput();

        // common
        var blockTags = new BlockTagsProvider(packOutput, lookupProvider, helper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ItemTagsProvider(packOutput, lookupProvider, blockTags.contentsGetter(), helper));
        generator.addProvider(event.includeServer(), new RecipeProvider(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, lookupProvider));

        // client
        generator.addProvider(event.includeClient(), new BlockStateProvider(packOutput, helper));
        generator.addProvider(event.includeClient(), new ItemModelProvider(packOutput, helper));
        generator.addProvider(event.includeClient(), new LanguageProvider(packOutput, "en_us"));
    }

}
