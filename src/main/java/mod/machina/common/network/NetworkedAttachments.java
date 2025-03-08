package mod.machina.common.network;


import mod.machina.api.gear.Gear;
import mod.machina.common.network.messages.MessageSyncArsenalEquipped;
import mod.machina.common.network.messages.MessageSyncArsenalUnlocked;
import mod.machina.common.network.messages.MessageUpdateArsenalEquipped;
import mod.machina.common.util.SizedList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class NetworkedAttachments {
    public static void syncArsenal(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            MessageRegistrar.sendTo(serverPlayer, MessageSyncArsenalEquipped.create(serverPlayer));
        }
    }

    public static void syncGears(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            MessageRegistrar.sendTo(serverPlayer, MessageSyncArsenalUnlocked.create(serverPlayer));
        }
    }

    public static void syncAllAttachments(Player player) {
        syncArsenal(player);
        syncGears(player);
    }

    public static void updateArsenal(SizedList<Gear> equipped) {
        MessageRegistrar.sendToServer(new MessageUpdateArsenalEquipped(equipped));
    }
}
