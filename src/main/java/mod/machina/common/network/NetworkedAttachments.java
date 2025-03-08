package mod.machina.common.network;


import mod.machina.common.arsenal.EquippedGears;
import mod.machina.common.network.messages.MessageSyncEquippedGears;
import mod.machina.common.network.messages.MessageSyncUnlockedGears;
import mod.machina.common.network.messages.MessageUpdateArsenal;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class NetworkedAttachments {
    public static void syncArsenal(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            MessageRegistrar.sendTo(serverPlayer, MessageSyncEquippedGears.create(serverPlayer));
        }
    }

    public static void syncGears(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            MessageRegistrar.sendTo(serverPlayer, MessageSyncUnlockedGears.create(serverPlayer));
        }
    }

    public static void syncAllAttachments(Player player) {
        syncArsenal(player);
        syncGears(player);
    }

    public static void updateArsenal(EquippedGears arsenal) {
        MessageRegistrar.sendToServer(new MessageUpdateArsenal(arsenal));
    }
}
