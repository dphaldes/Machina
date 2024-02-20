package com.mystchonky.machina.common.registrar;

import com.enderio.regilite.registry.ItemRegistry;
import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.item.ExosuitArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;

public class ItemRegistrar {

    public static ItemRegistry ITEMS = ItemRegistry.createRegistry(Machina.MODID);

    public static DeferredItem<ExosuitArmorItem> EXO_HELMET = ITEMS.registerItem("exo_helmet", (props) -> new ExosuitArmorItem(ArmorItem.Type.HELMET));
    public static DeferredItem<ExosuitArmorItem> EXO_PLATE = ITEMS.registerItem("exo_plate", (props) -> new ExosuitArmorItem(ArmorItem.Type.CHESTPLATE));
    public static DeferredItem<ExosuitArmorItem> EXO_LEGGINGS = ITEMS.registerItem("exo_leggings", (props) -> new ExosuitArmorItem(ArmorItem.Type.LEGGINGS));
    public static DeferredItem<ExosuitArmorItem> EXO_BOOTS = ITEMS.registerItem("exo_boots", (props) -> new ExosuitArmorItem(ArmorItem.Type.BOOTS));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
