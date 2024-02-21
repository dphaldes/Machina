package com.mystchonky.machina.common.attachment;


import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.network.NetworkManager;
import com.mystchonky.machina.common.network.messages.MessageSyncPlayerAttachments;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.common.Mod;

public class AttachmentManager {
    public static void syncPlayerAttachments(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkManager.sendTo(serverPlayer, new MessageSyncPlayerAttachments(serverPlayer));
        }
    }

    @Mod.EventBusSubscriber(modid = Machina.MODID)
    public static class EventHandler {

    }
}
