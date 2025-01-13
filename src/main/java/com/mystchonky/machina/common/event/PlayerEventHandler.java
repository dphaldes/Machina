package com.mystchonky.machina.common.event;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.arsenal.ArsenalManager;
import com.mystchonky.machina.common.item.VoidArmorItem;
import com.mystchonky.machina.common.item.components.ItemStackReference;
import com.mystchonky.machina.common.level.RiftShape;
import com.mystchonky.machina.common.network.NetworkedAttachments;
import com.mystchonky.machina.common.registrar.DataComponentRegistrar;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

@EventBusSubscriber(modid = Machina.ID)
public class PlayerEventHandler {
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
            if (ArsenalManager.active(player)) {
                ArsenalManager.activate(player);
            } else {
                ArsenalManager.deactivate(player);
            }
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
        if (itemStack.has(DataComponentRegistrar.ITEM_STACK_REF) && event.getEntity().isSecondaryUseActive()) {
            var replaceStack = itemStack.get(DataComponentRegistrar.ITEM_STACK_REF).stack();
            itemStack.remove(DataComponentRegistrar.ITEM_STACK_REF);
            replaceStack.set(DataComponentRegistrar.ITEM_STACK_REF, ItemStackReference.of(itemStack));

            event.getEntity().setItemInHand(event.getHand(), replaceStack);
        }
    }
}
