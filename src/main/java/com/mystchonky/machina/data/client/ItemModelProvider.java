package com.mystchonky.machina.data.client;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.registrar.BlockRegistrar;
import com.mystchonky.machina.common.registrar.GearRegistrar;
import com.mystchonky.machina.common.registrar.ItemRegistrar;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Machina.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistrar.VOID_HELMET.get());
        basicItem(ItemRegistrar.VOID_CHESTPLATE.get());
        basicItem(ItemRegistrar.VOID_LEGGINGS.get());
        basicItem(ItemRegistrar.VOID_BOOTS.get());

        GearRegistrar.GEAR_ITEMS.getEntries().forEach(gear -> basicGear(gear.get()));

        BlockRegistrar.BLOCK_ITEMS.getEntries().forEach(block ->
                this.withExistingParent(block.getId().toString(), Machina.prefix("block/" + block.getId().getPath())));
    }

    private ItemModelBuilder basicGear(Item item) {
        var resource = BuiltInRegistries.ITEM.getKey(item);
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(resource.getNamespace(), "item/gear/" + resource.getPath()));
    }
}
