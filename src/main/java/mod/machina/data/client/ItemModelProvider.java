package mod.machina.data.client;

import mod.machina.Machina;
import mod.machina.common.item.GearItem;
import mod.machina.common.registrar.BlockRegistrar;
import mod.machina.common.registrar.GearRegistrar;
import mod.machina.common.registrar.ItemRegistrar;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
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

        GearRegistrar.GEAR_ITEMS.getEntries()
                .forEach(gear -> basicGear(gear.get()));

        basicItem(ItemRegistrar.COMPENDIUM.asItem());
        simpleBlockItem(BlockRegistrar.RIFT_PORTAL.block());
    }

    private void basicGear(Item item) {
        if (item instanceof GearItem gearItem) {
            var resource = BuiltInRegistries.ITEM.getKey(item);
            getBuilder(item.toString())
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", ResourceLocation.fromNamespaceAndPath(resource.getNamespace(),
                            "item/gear/" + gearItem.gear().id()));
        }
    }
}
