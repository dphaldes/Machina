package mod.machina.common.network.messages;

import mod.machina.Machina;
import mod.machina.api.augment.Augment;
import mod.machina.common.content.armor.Arsenal;
import mod.machina.common.content.armor.ArsenalManager;
import mod.machina.common.network.NetworkedAttachments;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;

public record MessageUpdateArsenalEquipped(List<Holder<Augment>> equipped) implements Message.Server {

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
