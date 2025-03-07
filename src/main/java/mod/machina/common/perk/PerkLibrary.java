package mod.machina.common.perk;

import mod.machina.Machina;
import mod.machina.api.perk.Perk;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;

public class PerkLibrary {
    private static final HashMap<String, String> entries = new HashMap<>();

    public static final Perk GLIDE = newPerk("glide", "Glide");
    public static final Perk GILDED = newPerk("gilded", "Gilded");
    public static final Perk PROTECTION = newPerk("protection", "Protection");
    public static final Perk PROTECTION_FIRE = newPerk("protection_fire", "Fire Protection");
    public static final Perk PROTECTION_BLAST = newPerk("protection_blast", "Blast Protection");
    public static final Perk PROTECTION_WATER = newPerk("protection_water", "Aqua Protection");
    public static final Perk PROTECTION_FREEZE = newPerk("protection_freeze", "Ice Protection");
    public static final Perk PROTECTION_PROJECTILE = newPerk("protection_projectile", "Projectile Protection");


    private static Perk newPerk(String key, String displayName) {
        var perk = new Perk(key);
        entries.put(localizationKey(key), displayName);
        return perk;
    }

    public static String localizationKey(String key) {
        return "perk." + Machina.ID + "." + key;
    }

    public static boolean hasPerk(LivingEntity entity, Perk perk) {
        if (entity instanceof Player player) {
            return Perks.get(player).contains(perk);
        }
        return false;
    }

    public static HashMap<String, String> entries() {
        return entries;
    }

}
