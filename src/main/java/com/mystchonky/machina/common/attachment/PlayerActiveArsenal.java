package com.mystchonky.machina.common.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mystchonky.machina.common.gear.GearSlot;

import java.util.Arrays;

public record PlayerActiveArsenal(GearSlot[] gearSlots) {
    private static final int ARSENAL_SIZE = 8;
    public static Codec<PlayerActiveArsenal> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    GearSlot.CODEC.listOf().comapFlatMap(data -> {
                        if (data.size() != ARSENAL_SIZE) {
                            return DataResult.error(() -> "Invalid number of slots in the arsenal");
                        }
                        return DataResult.success(data.toArray(new GearSlot[0]));
                    }, Arrays::asList).fieldOf("gearSlots").forGetter(PlayerActiveArsenal::gearSlots)).apply(instance, PlayerActiveArsenal::new));

    public static PlayerActiveArsenal empty() {
        GearSlot[] slots = new GearSlot[ARSENAL_SIZE];
        Arrays.setAll(slots, i -> GearSlot.empty());
        return new PlayerActiveArsenal(slots);
    }


}
