package mod.machina.common.network;


import mod.machina.common.arsenal.Arsenal;
import mod.machina.common.network.messages.MessageSyncArsenal;
import mod.machina.common.network.messages.MessageSyncGears;
import mod.machina.common.network.messages.MessageUpdateArsenal;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class NetworkedAttachments {
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
}
