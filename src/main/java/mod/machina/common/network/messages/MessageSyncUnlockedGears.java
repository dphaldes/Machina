package mod.machina.common.network.messages;

import mod.machina.Machina;
import mod.machina.api.gear.Gear;
import mod.machina.common.arsenal.ArsenalManager;
import mod.machina.common.arsenal.UnlockedGears;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;

public record MessageSyncUnlockedGears(ArrayList<Gear> gears) implements Message.Client {

    public static final Type<MessageSyncUnlockedGears> TYPE = new Type<>(Machina.prefix("sync_player_gears"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageSyncUnlockedGears> STREAM_CODEC =
            UnlockedGears.STREAM_CODEC.map(MessageSyncUnlockedGears::new, MessageSyncUnlockedGears::gears);

    public static MessageSyncUnlockedGears create(Player player) {
        ArrayList<Gear> gears = ArsenalManager.getUnlockedGears(player);
        return new MessageSyncUnlockedGears(gears);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void onClientReceived(Player player) {
        ArsenalManager.setUnlockedGears(player, gears());
    }
}
