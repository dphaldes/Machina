package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.common.item.GuideBookItem;
import mod.machina.common.item.VoidArmorItem;
import mod.machina.common.item.components.ItemStackHolder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistrar {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.Items.createItems(Machina.ID);

    public static final DeferredItem<GuideBookItem> COMPENDIUM = ITEMS.registerItem("compendium", (props) -> new GuideBookItem(
            new Item.Properties().stacksTo(1)
                    .component(DataComponentRegistrar.STACK_HOLDER, ItemStackHolder.of(Items.IRON_SWORD))
                    .rarity(Rarity.EPIC)));

    public static final DeferredItem<VoidArmorItem> VOID_HELMET = ITEMS.registerItem("void_helmet", (props) -> new VoidArmorItem(ArmorItem.Type.HELMET));
    public static final DeferredItem<VoidArmorItem> VOID_CHESTPLATE = ITEMS.registerItem("void_chestplate", (props) -> new VoidArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final DeferredItem<VoidArmorItem> VOID_LEGGINGS = ITEMS.registerItem("void_leggings", (props) -> new VoidArmorItem(ArmorItem.Type.LEGGINGS));
    public static final DeferredItem<VoidArmorItem> VOID_BOOTS = ITEMS.registerItem("void_boots", (props) -> new VoidArmorItem(ArmorItem.Type.BOOTS));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
