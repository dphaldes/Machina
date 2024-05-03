package com.mystchonky.machina.common.armament.perk;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.common.registrar.AttachmentRegistrar;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;

public record Perks(HashMap<Perk, Boolean> map) {

    public static final Codec<Perks> CODEC = Codec.unboundedMap(Perk.CODEC, Codec.BOOL).xmap(HashMap::new, it -> it).xmap(Perks::new, Perks::map);

    public static final StreamCodec<ByteBuf, Perks> STREAM_CODEC =
            ByteBufCodecs.map(HashMap::new, Perk.STREAM_CODEC, ByteBufCodecs.BOOL).map(Perks::new, Perks::map);

    public static Perks create() {
        return new Perks(new HashMap<>());
    }

    public static Perks get(Player player) {
        return player.getData(AttachmentRegistrar.PERKS);
    }

    public static void set(Player player, Perks perks) {
        player.setData(AttachmentRegistrar.PERKS, perks);
    }
}
