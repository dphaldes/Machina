package com.mystchonky.machina.common.registrar;

import com.example.regilite.registry.ItemRegistry;
import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.item.ExosuitArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.registries.DeferredItem;

public class ItemRegistrar {

    public static ItemRegistry ITEMS = ItemRegistry.createRegistry(Machina.MODID);

    public static DeferredItem<ExosuitArmorItem> EXO_HELMET = ITEMS.registerItem("exo_helmet", (props) -> new ExosuitArmorItem(ArmorItem.Type.HELMET))
            .setModelProvider(ItemModelProvider::basicItem);
    public static DeferredItem<ExosuitArmorItem> EXO_PLATE = ITEMS.registerItem("exo_plate", (props) -> new ExosuitArmorItem(ArmorItem.Type.CHESTPLATE))
            .setModelProvider(ItemModelProvider::basicItem);
    public static DeferredItem<ExosuitArmorItem> EXO_LEGGINGS = ITEMS.register("exo_leggings", () -> new ExosuitArmorItem(ArmorItem.Type.LEGGINGS))
            .setModelProvider(ItemModelProvider::basicItem);
    public static DeferredItem<ExosuitArmorItem> EXO_BOOTS = ITEMS.register("exo_boots", () -> new ExosuitArmorItem(ArmorItem.Type.BOOTS))
            .setModelProvider(ItemModelProvider::basicItem);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
