package com.mystchonky.machina.common.armament.perk;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.armament.Perk;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;

public class PerkLibrary {
    public static final HashMap<String, String> entries = new HashMap<>();

    public static final Perk AQUA_AFFINITY = newPerk("aqua_affinity", "Aqua Affinity");
    public static final Perk DEPTH_STRIDER = newPerk("depth_strider", "Depth Strider");
    public static final Perk FROST_WALKER = newPerk("frost_walker", "Frost Walker");
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

    public static boolean hasPerk(LivingEntity entity, Perk perk) {
        if (entity instanceof Player player) {
            return Perks.get(player).contains(perk);
        }
        return false;
    }
}
