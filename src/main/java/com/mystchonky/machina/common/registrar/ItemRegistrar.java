package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.item.ExosuitArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistrar {

    public static DeferredRegister.Items ITEMS = DeferredRegister.createItems(Machina.MODID);

    public static DeferredItem<ExosuitArmorItem> EXO_HELMET = ITEMS.register("exo_helmet", () -> new ExosuitArmorItem(ArmorItem.Type.HELMET));
    public static DeferredItem<ExosuitArmorItem> EXO_PLATE = ITEMS.register("exo_plate", () -> new ExosuitArmorItem(ArmorItem.Type.CHESTPLATE));
    public static DeferredItem<ExosuitArmorItem> EXO_LEGGINGS = ITEMS.register("exo_leggings", () -> new ExosuitArmorItem(ArmorItem.Type.LEGGINGS));
    public static DeferredItem<ExosuitArmorItem> EXO_BOOTS = ITEMS.register("exo_boots", () -> new ExosuitArmorItem(ArmorItem.Type.BOOTS));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
