package mod.machina.common.network.messages;

import mod.machina.Machina;
import mod.machina.api.augment.Augment;
import mod.machina.common.armor.Arsenal;
import mod.machina.common.armor.ArsenalManager;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public record MessageSyncArsenalEquipped(List<Holder<Augment>> equipped) implements Message.Client {

    public static final Type<MessageSyncArsenalEquipped> TYPE = new Type<>(Machina.prefix("sync_arsenal_equipped"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageSyncArsenalEquipped> STREAM_CODEC =
            Arsenal.Equipped.STREAM_CODEC.map(MessageSyncArsenalEquipped::new, MessageSyncArsenalEquipped::equipped);

    public static MessageSyncArsenalEquipped create(Player player) {
        var gears = ArsenalManager.getArsenal(player).equipped();
        return new MessageSyncArsenalEquipped(gears);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void onClientReceived(Player player) {
        ArsenalManager.setEquippedGears(player, equipped());
    }
}
