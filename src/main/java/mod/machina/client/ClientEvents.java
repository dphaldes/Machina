package mod.machina.client;

import mod.machina.Machina;
import mod.machina.client.keymap.KeymapManager;
import mod.machina.common.registrar.DataComponentRegistrar;
import mod.machina.common.registrar.LangRegistrar;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;


@EventBusSubscriber(modid = Machina.ID)
public class ClientEvents {
    @SubscribeEvent
    public static void clientTickEnd(ClientTickEvent.Post event) {
        ClientData.ticks += 1;

        KeymapManager.handleKeymaps();
    }

    @SubscribeEvent
    public static void itemTooltip(ItemTooltipEvent event) { // TODO: investigate if client only or not
        var itemstack = event.getItemStack();
        var tooltip = event.getToolTip();
        if (itemstack.has(DataComponentRegistrar.STACK_HOLDER)) {
            var held = itemstack.get(DataComponentRegistrar.STACK_HOLDER).stack().getDisplayName();
            tooltip.add(1, Component.translatable(LangRegistrar.STACK_HOLDER.key(), held));
        }

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
    }
}
