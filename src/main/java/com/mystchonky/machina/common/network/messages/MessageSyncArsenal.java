package com.mystchonky.machina.common.network.messages;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.arsenal.Arsenal;
import com.mystchonky.machina.common.arsenal.ArsenalManager;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

public record MessageSyncArsenal(Arsenal arsenal) implements Message.Client {

    public static final Type<MessageSyncArsenal> TYPE = new Type<>(Machina.prefix("sync_arsenal"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageSyncArsenal> STREAM_CODEC =
            Arsenal.STREAM_CODEC.map(MessageSyncArsenal::new, MessageSyncArsenal::arsenal);

    public static MessageSyncArsenal create(Player player) {
        Arsenal arsenal = Arsenal.get(player);
        return new MessageSyncArsenal(arsenal);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void onClientReceived(Player player) {
        ArsenalManager.update(player, arsenal());
    }
}
