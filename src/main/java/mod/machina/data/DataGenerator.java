package mod.machina.data;

import mod.machina.api.RegistryKeys;
import mod.machina.data.client.BlockStateProvider;
import mod.machina.data.client.ItemModelProvider;
import mod.machina.data.client.LanguageProvider;
import mod.machina.data.content.AugmentsProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class DataGenerator {

    public static void generate(GatherDataEvent event) {
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
        event.createDatapackRegistryObjects(new RegistrySetBuilder()
                .add(RegistryKeys.AUGMENT, AugmentsProvider::bootstrap)
        );

        // client
        generator.addProvider(event.includeClient(), new BlockStateProvider(packOutput, helper));
        generator.addProvider(event.includeClient(), new ItemModelProvider(packOutput, helper));
        generator.addProvider(event.includeClient(), new LanguageProvider(packOutput, "en_us"));


    }

}
