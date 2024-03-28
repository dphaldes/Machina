package com.mystchonky.machina.common.network;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.network.messages.MessageSyncPlayerAttachments;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

public class NetworkManager {
    public static void registerMessages(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(Machina.MODID);

        registrar.play(MessageSyncPlayerAttachments.TYPE, MessageSyncPlayerAttachments.STREAM_CODEC, MessageHandler::handle);
    }

    public static <T extends Message> void sendTo(ServerPlayer player, T message) {
        PacketDistributor.PLAYER.with(player).send(message);
    }

    public static <T extends Message> void sendToServer(T message) {
        PacketDistributor.SERVER.noArg().send(message);
    }

    public static <T extends Message> void sendToTracking(Entity entity, T message) {
        PacketDistributor.TRACKING_ENTITY.with(entity).send(message);
    }

    public static <T extends Message> void sendToTracking(LevelChunk chunk, T message) {
        PacketDistributor.TRACKING_CHUNK.with(chunk).send(message);
    }

    public static <T extends Message> void sendToDimension(ResourceKey<Level> dimensionKey, T message) {
        PacketDistributor.DIMENSION.with(dimensionKey).send(message);
    }
}
