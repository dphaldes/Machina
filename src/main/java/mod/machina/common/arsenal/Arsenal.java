package mod.machina.common.arsenal;

import com.mojang.serialization.Codec;
import mod.machina.api.gear.Gear;
import mod.machina.common.registrar.AttachmentRegistrar;
import mod.machina.common.util.SizedList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public record Arsenal(SizedList<Gear> gears) {
    public static final int ARSENAL_SIZE = 8;

    public static final Codec<Arsenal> CODEC = Gear.CODEC
            .listOf(ARSENAL_SIZE, ARSENAL_SIZE)
            .xmap(SizedList::new, Function.identity())
            .xmap(Arsenal::new, Arsenal::gears);

    public static final StreamCodec<RegistryFriendlyByteBuf, Arsenal> STREAM_CODEC = Gear.STREAM_CODEC
            .apply(ByteBufCodecs.list(ARSENAL_SIZE))
            .map(SizedList::new, Function.identity())
            .map(Arsenal::new, Arsenal::gears);

    public static Arsenal create() {
        List<Gear> slots = new ArrayList<>(Collections.nCopies(ARSENAL_SIZE, Gear.EMPTY));
        return new Arsenal(new SizedList<>(slots));
    }

    public static Arsenal get(Player player) {
        return player.getData(AttachmentRegistrar.ARSENAL);
    }

    public static void set(Player player, Arsenal arsenal) {
        player.setData(AttachmentRegistrar.ARSENAL, arsenal);
    }

}
