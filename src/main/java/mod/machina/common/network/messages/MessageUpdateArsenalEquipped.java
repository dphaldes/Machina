package mod.machina.common.network.messages;

import mod.machina.Machina;
import mod.machina.api.gear.Gear;
import mod.machina.common.arsenal.Arsenal;
import mod.machina.common.arsenal.ArsenalManager;
import mod.machina.common.network.NetworkedAttachments;
import mod.machina.common.util.SizedList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public record MessageUpdateArsenalEquipped(SizedList<Gear> equipped) implements Message.Server {

    public static final Type<MessageUpdateArsenalEquipped> TYPE = new Type<>(Machina.prefix("update_arsenal_equipped"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageUpdateArsenalEquipped> STREAM_CODEC =
            Arsenal.Equipped.STREAM_CODEC.map(MessageUpdateArsenalEquipped::new, MessageUpdateArsenalEquipped::equipped);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void onServerReceived(ServerPlayer player) {
        ArsenalManager.setEquippedGears(player, equipped());
        NetworkedAttachments.syncArsenal(player);
    }
}
