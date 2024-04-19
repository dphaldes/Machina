package com.mystchonky.machina.common.event;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.armament.arsenal.ArsenalController;
import com.mystchonky.machina.common.item.ExosuitArmorItem;
import com.mystchonky.machina.common.network.NetworkedAttachments;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod.EventBusSubscriber(modid = Machina.MODID)
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
    public static void equipmentChange(final LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (event.getFrom().getItem() instanceof ExosuitArmorItem || event.getTo().getItem() instanceof ExosuitArmorItem) {
            if (ArsenalController.isWearingArmor(player)) {
                ArsenalController.activate(player);
            } else {
                ArsenalController.deactivate(player);
            }
        }
    }
}
