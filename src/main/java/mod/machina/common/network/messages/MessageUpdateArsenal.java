package mod.machina.common.network.messages;

import mod.machina.Machina;
import mod.machina.common.arsenal.ArsenalManager;
import mod.machina.common.arsenal.EquippedGears;
import mod.machina.common.network.NetworkedAttachments;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public record MessageUpdateArsenal(EquippedGears gears) implements Message.Server {

    public static final Type<MessageUpdateArsenal> TYPE = new Type<>(Machina.prefix("update_arsenal"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageUpdateArsenal> STREAM_CODEC =
            EquippedGears.STREAM_CODEC.map(MessageUpdateArsenal::new, MessageUpdateArsenal::gears);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void onServerReceived(ServerPlayer player) {
        ArsenalManager.update(player, gears());
        NetworkedAttachments.syncArsenal(player);
    }
}
