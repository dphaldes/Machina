package com.mystchonky.machina.common.registrar;


import com.mystchonky.machina.Machina;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.Map;

public class LangRegistrar {

    public static final Map<String, String> entries = new HashMap<>();

    public static final LangEntry ARSENAL_SCREEN = addTranslation("title", "arsenal", "Arsenal Screen");

    // REGION ATTRIBUTES

    public static final LangEntry ATTRIBUTE_ADD = addTranslation("attribute", "add", "+%s %s");
    public static final LangEntry ATTRIBUTE_ADD_N = addTranslation("attribute", "add_n", "-%s %s");
    public static final LangEntry ATTRIBUTE_MULTIPLY_BASE = addTranslation("attribute", "multiply_base", "+%s%% %s");
    public static final LangEntry ATTRIBUTE_MULTIPLY_BASE_N = addTranslation("attribute", "multiply_base_n", "-%s%% %s");
    public static final LangEntry ATTRIBUTE_MULTIPLY_TOTAL = addTranslation("attribute", "multiply_total", "+%s%% %s");
    public static final LangEntry ATTRIBUTE_MULTIPLY_TOTAL_N = addTranslation("attribute", "multiply_total_n", "-%s%% %s");

    // ENDREGION

    private static LangEntry addTranslation(String key, String translation) {
        entries.put(key, translation);
        return new LangEntry(key, Component.translatable(key));
    }

    private static LangEntry addTranslation(String type, String key, String translation) {
        return addTranslation(type + "." + Machina.MODID + "." + key, translation);
    }


    public static void load() {
    }

    public record LangEntry(String key, Component component) {
    }
}
