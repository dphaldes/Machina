package com.mystchonky.machina.common.attachment;

import com.mojang.serialization.Codec;
import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import com.mystchonky.machina.common.registrar.AttachmentRegistrar;
import com.mystchonky.machina.common.util.LimitedList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.player.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record Arsenal(LimitedList<ArsenalGearSlot> slots) {
    private static final int ARSENAL_SIZE = 8;

    public static final Codec<Arsenal> CODEC = ExtraCodecs.sizeLimitedList(ArsenalGearSlot.CODEC.listOf(), ARSENAL_SIZE)
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

    public static void set(Player player, Arsenal updated) {
        var oldGears = get(player).gears();
        var newGears = updated.gears();
        var removed = oldGears.stream().filter(it -> !newGears.contains(it)).toList();
        var installed = newGears.stream().filter(it -> !oldGears.contains(it)).toList();

        player.setData(AttachmentRegistrar.ARSENAL, updated);
        removed.forEach(gear -> gear.onUnequip(player));
        installed.forEach(gear -> gear.onEquip(player));
    }

    public List<AbstractGear> gears() {
        return slots().stream().map(ArsenalGearSlot::equippedGear).collect(Collectors.toList());
    }

}
