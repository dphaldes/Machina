package com.mystchonky.machina.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;

public class ComponentUtil {

    /**
     * Append arguments to a translatable component.
     * If you don't pass a translatable component, it will not be modified.
     */
    public static MutableComponent withArgs(MutableComponent component, Object... args) {
        // Translate with args.
        if (component.getContents() instanceof TranslatableContents translatableContents) {
            return Component.translatable(translatableContents.getKey(), args);
        }

        // Fallback.
        return component;
    }
}
