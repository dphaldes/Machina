package com.mystchonky.machina.common.armament.arsenal;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.api.armament.gear.AbstractGear;
import com.mystchonky.machina.common.registrar.AttachmentRegistrar;
import com.mystchonky.machina.common.util.Codecs;
import com.mystchonky.machina.common.util.LimitedList;
import com.mystchonky.machina.common.util.Optionals;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public record Arsenal(LimitedList<AbstractGear> gears) {
    private static final int ARSENAL_SIZE = 8;

    public static final Codec<Arsenal> CODEC = Codecs.optional(AbstractGear.CODEC)
            .sizeLimitedListOf(ARSENAL_SIZE)
            .xmap(Optionals::fromOptional, Optionals::toOptional)
            .xmap(LimitedList::new, Function.identity())
            .xmap(Arsenal::new, Arsenal::gears);

    public static final StreamCodec<RegistryFriendlyByteBuf, Arsenal> STREAM_CODEC = ByteBufCodecs.optional(AbstractGear.STREAM_CODEC)
            .apply(ByteBufCodecs.list(ARSENAL_SIZE))
            .map(Optionals::fromOptional, Optionals::toOptional)
            .map(LimitedList::new, Function.identity())
            .map(Arsenal::new, Arsenal::gears);

    public static Arsenal create() {
        List<AbstractGear> slots = Collections.nCopies(ARSENAL_SIZE, null);
        return new Arsenal(new LimitedList<>(slots));
    }

    public static Arsenal get(Player player) {
        return player.getData(AttachmentRegistrar.ARSENAL);
    }

    public static void set(Player player, Arsenal arsenal) {
        ArsenalController.deactivate(player, get(player));
        player.setData(AttachmentRegistrar.ARSENAL, arsenal);
        ArsenalController.activate(player, arsenal);
    }


}
