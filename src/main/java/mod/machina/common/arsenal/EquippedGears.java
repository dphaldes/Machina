package mod.machina.common.arsenal;

import com.mojang.serialization.Codec;
import mod.machina.api.gear.Gear;
import mod.machina.common.util.SizedList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public record EquippedGears(SizedList<Gear> gears) {
    public static final int CAPACITY = 8;

    public static final Codec<EquippedGears> CODEC = Gear.CODEC
            .listOf(CAPACITY, CAPACITY)
            .xmap(SizedList::new, Function.identity())
            .xmap(EquippedGears::new, EquippedGears::gears);

    public static final StreamCodec<RegistryFriendlyByteBuf, EquippedGears> STREAM_CODEC = Gear.STREAM_CODEC
            .apply(ByteBufCodecs.list(CAPACITY))
            .map(SizedList::new, Function.identity())
            .map(EquippedGears::new, EquippedGears::gears);

    public static EquippedGears create() {
        List<Gear> slots = new ArrayList<>(Collections.nCopies(CAPACITY, Gear.EMPTY));
        return new EquippedGears(new SizedList<>(slots));
    }
}
