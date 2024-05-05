package com.mystchonky.machina.common.armament.perk;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.armament.Perk;

import java.util.HashMap;

public class PerkLibrary {
    public static final HashMap<String, String> entries = new HashMap<>();

    public static final Perk GLIDE = newPerk("glide", "Glide");
    public static final Perk GILDED = newPerk("gilded", "Gilded");


    private static Perk newPerk(String key, String displayName) {
        var perk = new Perk(key);
        entries.put(localizationKey(key), displayName);
        return perk;
    }

    public static String localizationKey(String key) {
        return "perk." + Machina.MODID + "." + key;
    }
}
