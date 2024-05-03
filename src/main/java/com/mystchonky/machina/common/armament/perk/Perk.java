package com.mystchonky.machina.common.armament.perk;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record Perk(String key) {

    public static final Codec<Perk> CODEC = Codec.STRING.xmap(Perk::new, Perk::key);
    public static final StreamCodec<ByteBuf, Perk> STREAM_CODEC = ByteBufCodecs.STRING_UTF8.map(Perk::new, Perk::key);

}
