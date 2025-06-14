package mod.machina.common.event;

import mod.machina.Machina;
import mod.machina.common.arsenal.ArsenalManager;
import mod.machina.common.item.VoidArmorItem;
import mod.machina.common.item.components.ItemStackHolder;
import mod.machina.common.level.RiftShape;
import mod.machina.common.network.NetworkedAttachments;
import mod.machina.common.registrar.DataComponentRegistrar;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

@EventBusSubscriber(modid = Machina.ID)
public class EventHandler {
    @SubscribeEvent
    public static void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            NetworkedAttachments.syncAllAttachments(player);
        }
    }

    @SubscribeEvent
    public static void playerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            NetworkedAttachments.syncAllAttachments(player);
        }
    }

    @SubscribeEvent
    public static void playerDimChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            NetworkedAttachments.syncAllAttachments(player);
        }
    }

    @SubscribeEvent
    public static void equipmentChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player) || !event.getSlot().isArmor()) return;

        if (event.getFrom().getItem() instanceof VoidArmorItem || event.getTo().getItem() instanceof VoidArmorItem) {
            ArsenalManager.removeTraitsFromStack(event.getFrom());
            ArsenalManager.removeTraitsFromStack(event.getTo());
            ArsenalManager.respec(player);
        }
    }

    @SubscribeEvent
    public static void useItemOnBlock(UseItemOnBlockEvent event) {
        if (event.getUsePhase() == UseItemOnBlockEvent.UsePhase.ITEM_AFTER_BLOCK) {
            // place rifts
            if (event.getItemStack().is(Items.FLINT_AND_STEEL)) {
                var level = event.getLevel();
                var pos = event.getPos();
                var state = level.getBlockState(pos);
                if (RiftShape.frameBlock(state, level, pos)) {
                    var optional = RiftShape.findEmptyPortalShape(level, pos.above(), Direction.Axis.X);
                    if (optional.isPresent()) {
                        optional.get().createPortalBlocks();
                        event.setCancellationResult(ItemInteractionResult.sidedSuccess(level.isClientSide()));
                    }
                }
            }

        }
    }

    @SubscribeEvent
    public static void useItem(PlayerInteractEvent.RightClickItem event) {
        var itemStack = event.getItemStack();
        if (itemStack.has(DataComponentRegistrar.STACK_HOLDER) && event.getEntity().isSecondaryUseActive()) {
            var replaceStack = itemStack.get(DataComponentRegistrar.STACK_HOLDER).stack();
            itemStack.remove(DataComponentRegistrar.STACK_HOLDER);
            replaceStack.set(DataComponentRegistrar.STACK_HOLDER, ItemStackHolder.of(itemStack));

            event.getEntity().setItemInHand(event.getHand(), replaceStack);
        }
    }

    @SubscribeEvent
    public static void damageEvent(LivingIncomingDamageEvent event) {
        TraitEventHandler.damageEvent(event);
    }

//    @SubscribeEvent
//    public static void enchantmentEvent(GetEnchantmentLevelEvent event) {
//        TraitEventHandler.enchantmentEvent(event);
//    }
}
