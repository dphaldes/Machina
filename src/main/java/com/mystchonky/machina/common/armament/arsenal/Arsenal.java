package com.mystchonky.machina.common.armament.arsenal;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.common.registrar.AttachmentRegistrar;
import com.mystchonky.machina.common.util.SizedList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

import java.util.Collections;
import java.util.List;

public record Arsenal(SizedList<AbstractGear> gears) {
    public static final int ARSENAL_SIZE = 8;

    public static final Codec<Arsenal> CODEC = ExtraSerializers.GEARS_CODEC.xmap(Arsenal::new, Arsenal::gears);

    public static final StreamCodec<RegistryFriendlyByteBuf, Arsenal> STREAM_CODEC =
            ExtraSerializers.GEARS_STREAM_CODEC.map(Arsenal::new, Arsenal::gears);

    public static Arsenal create() {
        List<AbstractGear> slots = Collections.nCopies(ARSENAL_SIZE, null);
        return new Arsenal(new SizedList<>(slots));
    }

    public static Arsenal get(Player player) {
        return player.getData(AttachmentRegistrar.ARSENAL);
    }

    public static void set(Player player, Arsenal arsenal) {
        player.setData(AttachmentRegistrar.ARSENAL, arsenal);
    }

}
