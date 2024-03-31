package com.mystchonky.machina.common.network.messages;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.attachment.Arsenal;
import com.mystchonky.machina.common.network.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

public record MessageSyncArsenal(Arsenal arsenal) implements Message {

    public static final CustomPacketPayload.Type<MessageSyncArsenal> TYPE = new Type<>(Machina.prefix("sync_arsenal"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageSyncArsenal> STREAM_CODEC = StreamCodec.composite(
            Arsenal.STREAM_CODEC,
            MessageSyncArsenal::arsenal,
            MessageSyncArsenal::new
    );

    public static MessageSyncArsenal create(Player player) {
        Arsenal arsenal = Arsenal.get(player);
        return new MessageSyncArsenal(arsenal);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void onClientReceived(Minecraft minecraft, Player player) {
        Arsenal.set(player, arsenal());
    }
}
