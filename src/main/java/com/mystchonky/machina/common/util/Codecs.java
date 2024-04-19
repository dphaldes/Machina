package com.mystchonky.machina.common.util;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Unit;
import com.mojang.serialization.Codec;

import java.util.Optional;

public class Codecs {
    public static <O> Codec<Optional<O>> optional(Codec<O> codec) {
        return Codec.either(
                codec,
                Codec.unit(Unit.INSTANCE)
        ).xmap(
                e -> e.map(Optional::of, u -> Optional.empty()),
                o -> o.map(Either::<O, Unit>left).orElse(Either.right(Unit.INSTANCE))
        );
    }
}
