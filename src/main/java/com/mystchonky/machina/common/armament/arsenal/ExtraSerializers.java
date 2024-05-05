package com.mystchonky.machina.common.armament.arsenal;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.common.util.Codecs;
import com.mystchonky.machina.common.util.Optionals;
import com.mystchonky.machina.common.util.SizedList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.function.Function;

class ExtraSerializers {

    static final Codec<SizedList<AbstractGear>> GEARS_CODEC = Codecs.optional(AbstractGear.CODEC)
            .sizeLimitedListOf(Arsenal.ARSENAL_SIZE)
            .xmap(Optionals::fromOptional, Optionals::toOptional)
            .xmap(SizedList::new, Function.identity());

    static final StreamCodec<RegistryFriendlyByteBuf, SizedList<AbstractGear>> GEARS_STREAM_CODEC = ByteBufCodecs.optional(AbstractGear.STREAM_CODEC)
            .apply(ByteBufCodecs.list(Arsenal.ARSENAL_SIZE))
            .map(Optionals::fromOptional, Optionals::toOptional)
            .map(SizedList::new, Function.identity());

}
