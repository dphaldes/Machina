package com.mystchonky.machina.data.client;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.registrar.GearRegistrar;
import com.mystchonky.machina.common.registrar.ItemRegistrar;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Machina.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistrar.EXO_HELMET.get());
        basicItem(ItemRegistrar.EXO_PLATE.get());
        basicItem(ItemRegistrar.EXO_LEGGINGS.get());
        basicItem(ItemRegistrar.EXO_BOOTS.get());

        GearRegistrar.GEAR_ITEMS.getEntries().forEach(gear -> basicGear(gear.get()));
    }

    private ItemModelBuilder basicGear(Item item) {
        return basicGear(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)));
    }

    private ItemModelBuilder basicGear(ResourceLocation item) {
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), "item/gear/" + item.getPath()));
    }
}
