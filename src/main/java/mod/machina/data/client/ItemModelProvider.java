package mod.machina.data.client;

import mod.machina.Machina;
import mod.machina.client.ItemOverridePredicates;
import mod.machina.common.registrar.BlockRegistrar;
import mod.machina.common.registrar.ItemRegistrar;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Machina.ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistrar.VOID_HELMET.asItem());
        basicItem(ItemRegistrar.VOID_CHESTPLATE.asItem());
        basicItem(ItemRegistrar.VOID_LEGGINGS.asItem());
        basicItem(ItemRegistrar.VOID_BOOTS.asItem());

        simpleBlockItem(BlockRegistrar.RIFT_PORTAL.block());

        var guideId = ItemRegistrar.GUIDE.getId();
        getBuilder(guideId.toString())
                .override()
                .predicate(ItemOverridePredicates.GUIDE_SWORD_ACTIVE, 1f)
                .model(new ModelFile.ExistingModelFile(ResourceLocation.withDefaultNamespace("item/iron_sword"), existingFileHelper))
                .end()
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(guideId.getNamespace(), "item/" + guideId.getPath()));

    }

}
