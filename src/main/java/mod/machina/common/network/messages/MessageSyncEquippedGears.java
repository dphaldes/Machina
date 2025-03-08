package mod.machina.common.network.messages;

import mod.machina.Machina;
import mod.machina.common.arsenal.ArsenalManager;
import mod.machina.common.arsenal.EquippedGears;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

public record MessageSyncEquippedGears(EquippedGears gears) implements Message.Client {

    public static final Type<MessageSyncEquippedGears> TYPE = new Type<>(Machina.prefix("sync_equipped_gears"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageSyncEquippedGears> STREAM_CODEC =
            EquippedGears.STREAM_CODEC.map(MessageSyncEquippedGears::new, MessageSyncEquippedGears::gears);

    public static MessageSyncEquippedGears create(Player player) {
        EquippedGears gears = ArsenalManager.getEquippedGears(player);
        return new MessageSyncEquippedGears(gears);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void onClientReceived(Player player) {
        ArsenalManager.update(player, gears());
    }
}
