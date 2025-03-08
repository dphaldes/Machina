package mod.machina.common.network.messages;

import mod.machina.Machina;
import mod.machina.api.gear.Gear;
import mod.machina.common.arsenal.Arsenal;
import mod.machina.common.arsenal.ArsenalManager;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public record MessageSyncArsenalUnlocked(List<Gear> unlocked) implements Message.Client {

    public static final Type<MessageSyncArsenalUnlocked> TYPE = new Type<>(Machina.prefix("sync_arsenal_unlocked"));
    public static final StreamCodec<RegistryFriendlyByteBuf, MessageSyncArsenalUnlocked> STREAM_CODEC =
            Arsenal.Unlocked.STREAM_CODEC.map(MessageSyncArsenalUnlocked::new, MessageSyncArsenalUnlocked::unlocked);

    public static MessageSyncArsenalUnlocked create(Player player) {
        var gears = ArsenalManager.getArsenal(player).unlocked();
        return new MessageSyncArsenalUnlocked(gears);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Override
    public void onClientReceived(Player player) {
        ArsenalManager.setUnlockedGears(player, unlocked());
    }
}
