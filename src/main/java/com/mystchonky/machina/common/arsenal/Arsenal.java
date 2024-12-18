package com.mystchonky.machina.common.arsenal;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.common.registrar.AttachmentRegistrar;
import com.mystchonky.machina.common.util.Codecs;
import com.mystchonky.machina.common.util.Optionals;
import com.mystchonky.machina.common.util.SizedList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public record Arsenal(SizedList<Gear> gears) {
    public static final int ARSENAL_SIZE = 8;

    public static final Codec<Arsenal> CODEC = Codecs.optional(Gear.CODEC)
            .sizeLimitedListOf(Arsenal.ARSENAL_SIZE)
            .xmap(Optionals::fromOptional, Optionals::toOptional)
            .xmap(SizedList::new, Function.identity())
            .xmap(Arsenal::new, Arsenal::gears);

    public static final StreamCodec<RegistryFriendlyByteBuf, Arsenal> STREAM_CODEC =
            ByteBufCodecs.optional(Gear.STREAM_CODEC)
                    .apply(ByteBufCodecs.list(Arsenal.ARSENAL_SIZE))
                    .map(Optionals::fromOptional, Optionals::toOptional)
                    .map(SizedList::new, Function.identity())
                    .map(Arsenal::new, Arsenal::gears);

    public static Arsenal create() {
        List<Gear> slots = Collections.nCopies(ARSENAL_SIZE, null);
        return new Arsenal(new SizedList<>(slots));
    }

    public static Arsenal get(Player player) {
        return player.getData(AttachmentRegistrar.ARSENAL);
    }

    public static void set(Player player, Arsenal arsenal) {
        player.setData(AttachmentRegistrar.ARSENAL, arsenal);
    }

}
