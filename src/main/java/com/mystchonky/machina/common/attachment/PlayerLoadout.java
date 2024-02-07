package com.mystchonky.machina.common.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mystchonky.machina.common.gear.GearSlot;

import java.util.Arrays;

public record PlayerLoadout(GearSlot[] gearSlots) {
    private static final int LOADOUT_SIZE = 8;
    public static Codec<PlayerLoadout> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    GearSlot.CODEC.listOf().comapFlatMap(data -> {
                        if (data.size() != LOADOUT_SIZE) {
                            return DataResult.error(() -> "Invalid number of slots in the loadout");
                        }
                        return DataResult.success(data.toArray(new GearSlot[0]));
                    }, Arrays::asList).fieldOf("gearSlots").forGetter(PlayerLoadout::gearSlots)).apply(instance, PlayerLoadout::new));

    public static PlayerLoadout empty() {
        GearSlot[] slots = new GearSlot[LOADOUT_SIZE];
        Arrays.setAll(slots, i -> GearSlot.empty());
        return new PlayerLoadout(slots);
    }


}
