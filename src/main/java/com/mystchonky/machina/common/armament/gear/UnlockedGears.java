package com.mystchonky.machina.common.armament.gear;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.api.armament.gear.AbstractGear;
import com.mystchonky.machina.common.registrar.AttachmentRegistrar;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public record UnlockedGears(ArrayList<AbstractGear> gears) {
    public static final Codec<UnlockedGears> CODEC = AbstractGear.CODEC.listOf()
            .xmap(ArrayList::new, List::copyOf)
            .xmap(UnlockedGears::new, UnlockedGears::gears);

    public static final StreamCodec<RegistryFriendlyByteBuf, UnlockedGears> STREAM_CODEC = AbstractGear.STREAM_CODEC.apply(ByteBufCodecs.list())
            .map(ArrayList::new, List::copyOf)
            .map(UnlockedGears::new, UnlockedGears::gears);

    public static UnlockedGears create() {
        return new UnlockedGears(new ArrayList<>());
    }

    public static UnlockedGears get(Player player) {
        return player.getData(AttachmentRegistrar.UNLOCKED_GEARS);
    }

    public static void set(Player player, UnlockedGears gears) {
        player.setData(AttachmentRegistrar.UNLOCKED_GEARS, gears);
    }
}
