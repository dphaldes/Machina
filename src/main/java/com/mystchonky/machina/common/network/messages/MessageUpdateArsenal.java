package com.mystchonky.machina.common.network.messages;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.armament.Armament;
import com.mystchonky.machina.common.armament.arsenal.Arsenal;
import com.mystchonky.machina.common.network.NetworkedAttachments;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public record MessageUpdateArsenal(Arsenal arsenal) implements Message.Server {

    public static final Type<MessageUpdateArsenal> TYPE = new Type<>(Machina.prefix("update_arsenal"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageUpdateArsenal> STREAM_CODEC = StreamCodec.composite(
            Arsenal.STREAM_CODEC,
            MessageUpdateArsenal::arsenal,
            MessageUpdateArsenal::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void onServerReceived(ServerPlayer player) {
        Armament.updateArsenal(player, arsenal());
        NetworkedAttachments.syncArsenal(player);
    }
}
