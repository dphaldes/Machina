package com.mystchonky.machina.common.armament.gear;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.common.registrar.AttachmentRegistrar;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public final class UnlockedGears {
    public static final Codec<ArrayList<AbstractGear>> CODEC =
            AbstractGear.CODEC.listOf().xmap(ArrayList::new, List::copyOf);

    public static final StreamCodec<RegistryFriendlyByteBuf, ArrayList<AbstractGear>> STREAM_CODEC =
            AbstractGear.STREAM_CODEC.apply(ByteBufCodecs.list()).map(ArrayList::new, List::copyOf);

    public static ArrayList<AbstractGear> create() {
        return new ArrayList<>();
    }

    public static ArrayList<AbstractGear> get(Player player) {
        return player.getData(AttachmentRegistrar.UNLOCKED_GEARS);
    }

    public static void set(Player player, ArrayList<AbstractGear> gears) {
        player.setData(AttachmentRegistrar.UNLOCKED_GEARS, gears);
    }
}
