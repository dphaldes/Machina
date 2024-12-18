package com.mystchonky.machina.common.gear;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.common.registrar.AttachmentRegistrar;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public final class UnlockedGears {
    public static final Codec<ArrayList<Gear>> CODEC =
            Gear.CODEC.listOf().xmap(ArrayList::new, List::copyOf);

    public static final StreamCodec<RegistryFriendlyByteBuf, ArrayList<Gear>> STREAM_CODEC =
            Gear.STREAM_CODEC.apply(ByteBufCodecs.list()).map(ArrayList::new, List::copyOf);

    public static ArrayList<Gear> create() {
        return new ArrayList<>();
    }

    public static ArrayList<Gear> get(Player player) {
        return player.getData(AttachmentRegistrar.UNLOCKED_GEARS);
    }

    public static void set(Player player, ArrayList<Gear> gears) {
        player.setData(AttachmentRegistrar.UNLOCKED_GEARS, gears);
    }
}