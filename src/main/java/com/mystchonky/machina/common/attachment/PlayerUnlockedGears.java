package com.mystchonky.machina.common.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public record PlayerUnlockedGears(Map<ResourceLocation, Integer> gears) {

    public static PlayerUnlockedGears create() {
        return new PlayerUnlockedGears(new HashMap<>());
    }

    public static final Codec<PlayerUnlockedGears> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Codec.unboundedMap(ResourceLocation.CODEC, Codec.INT).fieldOf("gears").forGetter(PlayerUnlockedGears::gears))
                    .apply(instance, PlayerUnlockedGears::new));

}
