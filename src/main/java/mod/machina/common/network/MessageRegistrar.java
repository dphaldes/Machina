package mod.machina.common.network;

import mod.machina.Machina;
import mod.machina.common.network.messages.Message;
import mod.machina.common.network.messages.MessageSetRiftRecipe;
import mod.machina.common.network.messages.MessageSyncArsenalEquipped;
import mod.machina.common.network.messages.MessageSyncArsenalUnlocked;
import mod.machina.common.network.messages.MessageUpdateArsenalEquipped;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class MessageRegistrar {
    public static void registerMessages(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Machina.ID);

        registrar.playToServer(MessageUpdateArsenalEquipped.TYPE, MessageUpdateArsenalEquipped.STREAM_CODEC, MessageHandler::server);
        registrar.playToServer(MessageSetRiftRecipe.TYPE, MessageSetRiftRecipe.STREAM_CODEC, MessageHandler::server);

        registrar.playToClient(MessageSyncArsenalEquipped.TYPE, MessageSyncArsenalEquipped.STREAM_CODEC, MessageHandler::client);
        registrar.playToClient(MessageSyncArsenalUnlocked.TYPE, MessageSyncArsenalUnlocked.STREAM_CODEC, MessageHandler::client);
    }

    public static <T extends Message> void sendTo(ServerPlayer player, T message) {
        PacketDistributor.sendToPlayer(player, message);
    }

    public static <T extends Message> void sendToServer(T message) {
        PacketDistributor.sendToServer(message);
    }

    public static <T extends Message> void sendToTracking(Entity entity, T message) {
        PacketDistributor.sendToPlayersTrackingEntity(entity, message);
    }

    public static <T extends Message> void sendToDimension(ServerLevel level, T message) {
        PacketDistributor.sendToPlayersInDimension(level, message);
    }
}
