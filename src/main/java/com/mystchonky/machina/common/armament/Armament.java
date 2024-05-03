package com.mystchonky.machina.common.armament;

import com.mystchonky.machina.api.armament.gear.AbstractGear;
import com.mystchonky.machina.api.armament.gear.traits.PerkTrait;
import com.mystchonky.machina.common.armament.arsenal.Arsenal;
import com.mystchonky.machina.common.armament.perk.Perks;
import com.mystchonky.machina.common.item.ExosuitArmorItem;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Armament {

    public static boolean isWearingArmor(Player player) {
        return StreamSupport.stream(player.getArmorSlots().spliterator(), false)
                .allMatch(itemStack -> itemStack.getItem() instanceof ExosuitArmorItem);
    }

    public static void updateArsenal(Player player, Arsenal arsenal) {
        deactivateArmor(player);
        Arsenal.set(player, arsenal);
        activateArmor(player);
    }

    public static void activateArmor(Player player) {
        activateArsenal(player);
        activatePerks(player);
    }

    public static void deactivateArmor(Player player) {
        deactivateArsenal(player);
        deactivatePerks(player);
    }

    private static void activateArsenal(Player player) {
        Arsenal.get(player).gears().stream().filter(Objects::nonNull).forEach(gear -> gear.onEquip(player));
    }

    private static void deactivateArsenal(Player player) {
        Arsenal.get(player).gears().stream().filter(Objects::nonNull).forEach(gear -> gear.onRemove(player));
    }

    private static void activatePerks(Player player) {
        Arsenal arsenal = Arsenal.get(player);
        var perks = Perks.get(player);
        var perkMap = perks.map();
        var perkSet = arsenal.gears().stream()
                .filter(Objects::nonNull)
                .map(AbstractGear::getTraits)
                .flatMap(Collection::stream)
                .filter(PerkTrait.class::isInstance)
                .map(PerkTrait.class::cast)
                .map(PerkTrait::perk)
                .collect(Collectors.toSet());

        perkSet.forEach(perk -> perkMap.put(perk, true));
        Perks.set(player, perks);
    }

    private static void deactivatePerks(Player player) {
        var perks = Perks.get(player);
        perks.map().clear();
        Perks.set(player, perks);
    }
}
