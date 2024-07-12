package com.mystchonky.machina.common.armament.perk;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.armament.Perk;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import oshi.util.tuples.Pair;

import java.util.HashMap;
import java.util.Map;

public class PerkLibrary {
    public static final HashMap<String, String> entries = new HashMap<>();
    public static final Map<Perk, Pair<ResourceKey<Enchantment>, Integer>> enchantmentMap = new HashMap<>();

    public static final Perk GLIDE = newPerk("glide", "Glide");
    public static final Perk GILDED = newPerk("gilded", "Gilded");
    public static final Perk PROTECTION = newPerk("protection", "Protection");
    public static final Perk PROTECTION_FIRE = newPerk("protection_fire", "Fire Protection");
    public static final Perk PROTECTION_FALL = newPerk("protection_fall", "Fall Protection");
    public static final Perk PROTECTION_BLAST = newPerk("protection_blast", "Blast Protection");
    public static final Perk PROTECTION_WATER = newPerk("protection_water", "Aqua Protection");
    public static final Perk PROTECTION_FREEZE = newPerk("protection_freeze", "Ice Protection");
    public static final Perk PROTECTION_PROJECTILE = newPerk("protection_projectile", "Projectile Protection");

    //enchantmentPerks
    public static final Perk AQUA_AFFINITY = enchantedPerk("aqua_affinity", "Aqua Affinity", Enchantments.AQUA_AFFINITY, 1);
    public static final Perk DEPTH_STRIDER = enchantedPerk("depth_strider", "Depth Strider", Enchantments.DEPTH_STRIDER, 2);
    public static final Perk FROST_WALKER = enchantedPerk("frost_walker", "Frost Walker", Enchantments.FROST_WALKER, 2);
    public static final Perk RESPIRATION = enchantedPerk("respiration", "Respiration", Enchantments.RESPIRATION, 2);


    private static Perk newPerk(String key, String displayName) {
        var perk = new Perk(key);
        entries.put(localizationKey(key), displayName);
        return perk;
    }

    private static Perk enchantedPerk(String key, String displayName, ResourceKey<Enchantment> enchantment, int defaultLevel) {
        var perk = newPerk(key, displayName);
        enchantmentMap.put(perk, new Pair<>(enchantment, defaultLevel));
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

    public static void applyPerkEnchants(Player player, ItemEnchantments.Mutable enchants) {
        Perks.get(player).forEach(perk -> {
            var registry = Minecraft.getInstance().level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
            if (enchantmentMap.containsKey(perk)) {
                var data = enchantmentMap.get(perk);
                enchants.upgrade(registry.getHolderOrThrow(data.getA()), data.getB());
            }
        });
    }
}
