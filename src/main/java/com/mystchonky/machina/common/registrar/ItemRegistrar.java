package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.item.CodexItem;
import com.mystchonky.machina.common.item.RiftPearlItem;
import com.mystchonky.machina.common.item.VoidArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistrar {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.Items.createItems(Machina.ID);

    public static final DeferredItem<VoidArmorItem> VOID_HELMET = ITEMS.registerItem("void_helmet", (props) -> new VoidArmorItem(ArmorItem.Type.HELMET));
    public static final DeferredItem<VoidArmorItem> VOID_CHESTPLATE = ITEMS.registerItem("void_chestplate", (props) -> new VoidArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final DeferredItem<VoidArmorItem> VOID_LEGGINGS = ITEMS.registerItem("void_leggings", (props) -> new VoidArmorItem(ArmorItem.Type.LEGGINGS));
    public static final DeferredItem<VoidArmorItem> VOID_BOOTS = ITEMS.registerItem("void_boots", (props) -> new VoidArmorItem(ArmorItem.Type.BOOTS));

    public static final DeferredItem<RiftPearlItem> RIFT_PEARL = ITEMS.registerItem("rift_pearl", RiftPearlItem::new, new Item.Properties().stacksTo(16));
    public static final DeferredItem<CodexItem> CODEX = ITEMS.registerItem("codex", CodexItem::new, new Item.Properties().stacksTo(1));
    public static final DeferredItem<Item> GRIMOIRE = ITEMS.registerItem("grimoire", Item::new, new Item.Properties().stacksTo(1));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
