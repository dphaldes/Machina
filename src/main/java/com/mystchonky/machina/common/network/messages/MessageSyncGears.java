package com.mystchonky.machina.common.network.messages;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.common.gear.UnlockedGears;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

public record MessageSyncGears(ArrayList<Gear> gears) implements Message.Client {

    public static final Type<MessageSyncGears> TYPE = new Type<>(Machina.prefix("sync_player_gears"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageSyncGears> STREAM_CODEC =
            UnlockedGears.STREAM_CODEC.map(MessageSyncGears::new, MessageSyncGears::gears);

    public static MessageSyncGears create(Player player) {
        ArrayList<Gear> gears = UnlockedGears.get(player);
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
