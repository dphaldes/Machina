package com.mystchonky.machina.common.attachment;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import com.mystchonky.machina.common.arsenal.ArsenalManager;
import com.mystchonky.machina.common.registrar.AttachmentRegistrar;
import com.mystchonky.machina.common.util.LimitedList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record Arsenal(LimitedList<ArsenalGearSlot> slots) {
    private static final int ARSENAL_SIZE = 8;

    public static final Codec<Arsenal> CODEC = ArsenalGearSlot.CODEC.sizeLimitedListOf(ARSENAL_SIZE)
            .xmap(LimitedList::new, List::copyOf)
            .xmap(Arsenal::new, Arsenal::slots);

    public static final StreamCodec<RegistryFriendlyByteBuf, Arsenal> STREAM_CODEC = ArsenalGearSlot.STREAM_CODEC.apply(ByteBufCodecs.list(ARSENAL_SIZE))
            .map(LimitedList::new, List::copyOf)
            .map(Arsenal::new, Arsenal::slots);

    public static Arsenal empty() {
        List<ArsenalGearSlot> slots = Collections.nCopies(ARSENAL_SIZE, ArsenalGearSlot.empty());
        return new Arsenal(new LimitedList<>(slots));
    }

    public static Arsenal get(Player player) {
        return player.getData(AttachmentRegistrar.ARSENAL);
    }

    public static void set(Player player, Arsenal arsenal) {
        ArsenalManager.removeArsenalEffects(player, get(player));
        player.setData(AttachmentRegistrar.ARSENAL, arsenal);
        ArsenalManager.applyArsenalEffects(player, arsenal);
    }

    public List<AbstractGear> gears() {
        return slots().stream().map(ArsenalGearSlot::equippedGear).collect(Collectors.toList());
    }

}
