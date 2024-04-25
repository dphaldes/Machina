package com.mystchonky.machina.common.network.messages;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.armament.gear.UnlockedGears;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

public record MessageSyncGears(UnlockedGears gears) implements Message.Client {

    public static final Type<MessageSyncGears> TYPE = new Type<>(Machina.prefix("sync_player_gears"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageSyncGears> STREAM_CODEC = StreamCodec.composite(
            UnlockedGears.STREAM_CODEC,
            MessageSyncGears::gears,
            MessageSyncGears::new
    );

    public static MessageSyncGears create(Player player) {
        UnlockedGears gears = UnlockedGears.get(player);
        return new MessageSyncGears(gears);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void onClientReceived(Player player) {
        UnlockedGears.set(player, gears());
    }
}
