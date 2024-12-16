package com.mystchonky.machina.common.network;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.network.messages.Message;
import com.mystchonky.machina.common.network.messages.MessageSyncArsenal;
import com.mystchonky.machina.common.network.messages.MessageSyncGears;
import com.mystchonky.machina.common.network.messages.MessageUpdateArsenal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class MessageRegistrar {
    public static void registerMessages(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Machina.ID);

        registrar.playToServer(MessageUpdateArsenal.TYPE, MessageUpdateArsenal.STREAM_CODEC, MessageHandler::server);

        registrar.playToClient(MessageSyncArsenal.TYPE, MessageSyncArsenal.STREAM_CODEC, MessageHandler::client);
        registrar.playToClient(MessageSyncGears.TYPE, MessageSyncGears.STREAM_CODEC, MessageHandler::client);
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
