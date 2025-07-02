package mod.machina.common.network;


import mod.machina.api.augment.Augment;
import mod.machina.common.network.messages.MessageSyncArsenalEquipped;
import mod.machina.common.network.messages.MessageSyncArsenalUnlocked;
import mod.machina.common.network.messages.MessageUpdateArsenalEquipped;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.List;

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

    public static void updateArsenal(List<Holder<Augment>> equipped) {
        MessageRegistrar.sendToServer(new MessageUpdateArsenalEquipped(equipped));
    }
}
