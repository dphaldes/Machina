package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.api.Perk;

import java.util.HashMap;

public class PerkRegistrar {
    private static final HashMap<String, String> entries = new HashMap<>();

    public static final Perk GLIDE = perk("glide", "Glide");
    public static final Perk GILDED = perk("gilded", "Gilded");
    public static final Perk PROTECTION = perk("protection", "Protection");
    public static final Perk PROTECTION_FIRE = perk("protection_fire", "Fire Protection");
    public static final Perk PROTECTION_BLAST = perk("protection_blast", "Blast Protection");
    public static final Perk PROTECTION_WATER = perk("protection_water", "Aqua Protection");
    public static final Perk PROTECTION_FREEZE = perk("protection_freeze", "Ice Protection");
    public static final Perk PROTECTION_PROJECTILE = perk("protection_projectile", "Projectile Protection");


    private static Perk perk(String key, String displayName) {
        var perk = new Perk(key);
        entries.put(localizationKey(key), displayName);
        return perk;
    }

    public static String localizationKey(String key) {
        return "perk." + Machina.ID + "." + key;
    }

    public static HashMap<String, String> entries() {
        return entries;
    }

}
