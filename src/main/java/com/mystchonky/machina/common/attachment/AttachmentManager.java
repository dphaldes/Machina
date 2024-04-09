package com.mystchonky.machina.common.attachment;


import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.network.MessageRegistrar;
import com.mystchonky.machina.common.network.messages.MessageSyncArsenal;
import com.mystchonky.machina.common.network.messages.MessageSyncGears;
import com.mystchonky.machina.common.network.messages.MessageUpdateArsenal;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class AttachmentManager {
    public static void syncArsenal(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            MessageRegistrar.sendTo(serverPlayer, MessageSyncArsenal.create(serverPlayer));
        }
    }

    public static void syncGears(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            MessageRegistrar.sendTo(serverPlayer, MessageSyncGears.create(serverPlayer));
        }
    }

    public static void syncAllAttachments(Player player) {
        syncArsenal(player);
        syncGears(player);
    }

    public static void updateArsenal(Arsenal arsenal) {
        MessageRegistrar.sendToServer(new MessageUpdateArsenal(arsenal));
    }

    @Mod.EventBusSubscriber(modid = Machina.MODID)
    public static class EventHandler {

        @SubscribeEvent
        public static void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
            if (event.getEntity() instanceof ServerPlayer player) {
                syncAllAttachments(player);
            }
        }

        @SubscribeEvent
        public static void playerRespawn(PlayerEvent.PlayerRespawnEvent event) {
            if (event.getEntity() instanceof ServerPlayer player) {
                syncAllAttachments(player);
            }
        }

        @SubscribeEvent
        public static void playerDimChange(PlayerEvent.PlayerChangedDimensionEvent event) {
            if (event.getEntity() instanceof ServerPlayer player) {
                syncAllAttachments(player);
            }
        }

    }
}
