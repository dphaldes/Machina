package com.mystchonky.machina.common.util;

import java.util.List;
import java.util.Optional;

public class Optionals {
    public static <T> List<T> fromOptional(List<Optional<T>> optionals) {
        return optionals.stream().map(it -> it.orElse(null)).toList();
    }

    public static <T> List<Optional<T>> toOptional(List<T> list) {
        return list.stream().map(Optional::ofNullable).toList();
    }
}
