package mod.machina.data;

import com.klikli_dev.modonomicon.api.datagen.LanguageProviderCache;
import com.klikli_dev.modonomicon.api.datagen.NeoBookProvider;
import com.klikli_dev.modonomicon.datagen.EnUsProvider;
import mod.machina.Machina;
import mod.machina.data.book.GuideBook;
import mod.machina.data.book.MultiblockProvider;
import mod.machina.data.client.BlockStateProvider;
import mod.machina.data.client.ItemModelProvider;
import mod.machina.data.client.LanguageProvider;
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
        generator.addProvider(event.includeServer(), new MultiblockProvider(packOutput));

        // client
        generator.addProvider(event.includeClient(), new BlockStateProvider(packOutput, helper));
        generator.addProvider(event.includeClient(), new ItemModelProvider(packOutput, helper));
        generator.addProvider(event.includeClient(), new LanguageProvider(packOutput, "en_us"));

        // book
        var bookCache = new LanguageProviderCache("en_us");
        generator.addProvider(event.includeServer(), NeoBookProvider.of(event, new GuideBook(bookCache)));
        generator.addProvider(event.includeClient(), new EnUsProvider(packOutput, bookCache));

    }

}
