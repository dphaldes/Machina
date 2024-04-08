package com.mystchonky.machina.common.registrar;


import com.mystchonky.machina.Machina;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class LangRegistrar {

    public static final Map<String, String> entries = new HashMap<>();

    public static final Component ARSENAL_SCREEN = addTranslation("title", Machina.prefix("arsenal"), "Arsenal Screen");

    private static MutableComponent addTranslation(String key, String translation) {
        entries.put(key, translation);
        return Component.translatable(key);
    }

    private static MutableComponent addTranslation(String prefix, ResourceLocation id, String translation) {
        return addTranslation(prefix + "." + id, translation);
    }

    private static MutableComponent addTranslation(String prefix, ResourceLocation path, String name, String translation) {
        return addTranslation(prefix, new ResourceLocation(path.getNamespace(), path.getPath() + "/" + name), translation);
    }

    public static void load() {
    }
}
