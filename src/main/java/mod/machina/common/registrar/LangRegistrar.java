package mod.machina.common.registrar;


import mod.machina.Machina;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.Map;

public class LangRegistrar {

    private static final Map<String, String> entries = new HashMap<>();

    public static final LangEntry ARMOR_MISSING = addTranslation("text", "armor_missing", "Not wearing the full set");
    public static final LangEntry GEAR_UNLOCK = addTranslation("gear", "unlock", "Unlocked %s!");
    public static final LangEntry GEAR_LEARNT = addTranslation("gear", "learnt", "You have already learnt %s!");
    public static final LangEntry GEAR_REMOVE = addTranslation("gear", "remove", "Removed %s!");

    // TRAITS
    public static final LangEntry ATTRIBUTE_ADD = addTranslation("trait", "attribute_add", "+%s %s");
    public static final LangEntry ATTRIBUTE_ADD_N = addTranslation("trait", "attribute_add_n", "-%s %s");
    public static final LangEntry ATTRIBUTE_MULTIPLY_BASE = addTranslation("trait", "attribute_multiply_base", "+%s%% %s");
    public static final LangEntry ATTRIBUTE_MULTIPLY_BASE_N = addTranslation("trait", "attribute_multiply_base_n", "-%s%% %s");
    public static final LangEntry ATTRIBUTE_MULTIPLY_TOTAL = addTranslation("trait", "attribute_multiply_total", "+%s%% %s");
    public static final LangEntry ATTRIBUTE_MULTIPLY_TOTAL_N = addTranslation("trait", "attribute_multiply_total_n", "-%s%% %s");

    public static final LangEntry POTION = addTranslation("trait", "potion", "\uD83E\uDDEA %s");
    public static final LangEntry PERK = addTranslation("trait", "perk", "♦ %s");
    public static final LangEntry ENCHANT = addTranslation("trait", "enchant", "♠ %s");
    //

    // SCREENS
    public static final LangEntry ARSENAL_SCREEN = addTranslation("title", "screen.equipped", "Arsenal Screen");
    public static final LangEntry CODEX_SCREEN = addTranslation("title", "screen.codex", "Codex Screen");
    public static final LangEntry CRAFTING = addTranslation("text", "rift.crafting", "Crafting: %s");
    public static final LangEntry RIFT_READY = addTranslation("text", "rift.crafting_done", "Walk into the portal");
    public static final LangEntry RIFT_WAITING = addTranslation("text", "rift.waiting", "Waiting for remaining ingredients");
    //

    // KEYMAPS
    public static final LangEntry KEYMAP_CATEGORIES = addTranslation("key", "catergories", "Machina");
    public static final LangEntry ARSENAL_KEY = addTranslation("key", "equipped", "Open Arsenal Screen");
    //

    // ITEMS
    public static final LangEntry STACK_HOLDER = addTranslation("tooltip", "stack_holder", "Holding %s");

    private static LangEntry addTranslation(String key, String translation) {
        entries.put(key, translation);
        return new LangEntry(key, Component.translatable(key));
    }

    private static LangEntry addTranslation(String type, String key, String translation) {
        return addTranslation(type + "." + Machina.ID + "." + key, translation);
    }

    public static Map<String, String> entries() {
        return entries;
    }


    public static void load() {
    }

    public record LangEntry(String key, Component component) {
    }
}
