package com.mystchonky.machina.common.registrar;


import com.mystchonky.machina.Machina;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.Map;

public class LangRegistrar {

    public static final Map<String, String> entries = new HashMap<>();

    public static final LangEntry ARSENAL_SCREEN = addTranslation("title", "arsenal", "Arsenal Screen");
    public static final LangEntry ARMOR_MISSING = addTranslation("text", "armor_missing", "Not wearing the full set");
    public static final LangEntry GEAR_UNLOCK = addTranslation("gear", "unlock", "Unlocked %s!");
    public static final LangEntry GEAR_REMOVE = addTranslation("gear", "remove", "Removed %s!");

    // REGION TRAITS

    public static final LangEntry ATTRIBUTE_ADD = addTranslation("trait", "attribute_add", "+%s %s");
    public static final LangEntry ATTRIBUTE_ADD_N = addTranslation("trait", "attribute_add_n", "-%s %s");
    public static final LangEntry ATTRIBUTE_MULTIPLY_BASE = addTranslation("trait", "attribute_multiply_base", "+%s%% %s");
    public static final LangEntry ATTRIBUTE_MULTIPLY_BASE_N = addTranslation("trait", "attribute_multiply_base_n", "-%s%% %s");
    public static final LangEntry ATTRIBUTE_MULTIPLY_TOTAL = addTranslation("trait", "attribute_multiply_total", "+%s%% %s");
    public static final LangEntry ATTRIBUTE_MULTIPLY_TOTAL_N = addTranslation("trait", "attribute_multiply_total_n", "-%s%% %s");

    public static final LangEntry POTION = addTranslation("trait", "potion", "Applies %s");
    public static final LangEntry PERK = addTranslation("trait", "perk", "Grants %s");

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
