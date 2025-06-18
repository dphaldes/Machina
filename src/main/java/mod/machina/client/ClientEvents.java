package mod.machina.client;

import mod.machina.client.keymap.KeymapManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;


public class ClientEvents {
    @SubscribeEvent
    public static void clientTickEnd(ClientTickEvent.Post event) {
        ClientData.ticks += 1;

        KeymapManager.handleKeymaps();
    }

//    @SubscribeEvent
//    public static void itemTooltip(ItemTooltipEvent event) {
//
//        if (itemstack.getItem() instanceof VoidArmorItem) {
//            var registry = Minecraft.getInstance().level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
//            if (itemstack.has(DataComponentRegistrar.ARMOR_TRAITS)) {
//                var data = itemstack.get(DataComponentRegistrar.ARMOR_TRAITS).enchantments();
//                for (var pair : data) {
//                    var enchant = registry.getHolderOrThrow(pair.enchant());
//                    tooltip.add(Component.translatable(LangRegistrar.ENCHANT.key(), Enchantment.getFullname(enchant, pair.level()))
//                            .withStyle(ChatFormatting.LIGHT_PURPLE));
//                }
//            }
//        }
//    }
}
