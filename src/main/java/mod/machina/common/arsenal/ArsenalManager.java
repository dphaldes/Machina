package mod.machina.common.arsenal;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.PerkTrait;
import mod.machina.api.perk.Perk;
import mod.machina.common.item.VoidArmorItem;
import mod.machina.common.perk.Perks;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ArsenalManager {

    public static boolean active(Player player) {
        return StreamSupport.stream(player.getArmorSlots().spliterator(), false)
                .allMatch(itemStack -> itemStack.getItem() instanceof VoidArmorItem);
    }

    public static void update(Player player, Arsenal arsenal) {
        deactivate(player);
        Arsenal.set(player, arsenal);
        activate(player);
    }

    public static void activate(Player player) {
        // activate gears
        Arsenal.get(player).gears().forEach(gear -> gear.onEquip(player));

        // recalculate perks
        List<Perk> perks = Arsenal.get(player)
                .gears().stream()
                .map(Gear::getTraits)
                .flatMap(Collection::stream)
                .filter(PerkTrait.class::isInstance)
                .map(PerkTrait.class::cast)
                .map(PerkTrait::perk)
                .collect(Collectors.toList());
        Perks.set(player, perks);
    }

    public static void deactivate(Player player) {
        // deactivate gears
        Arsenal.get(player).gears().forEach(gear -> gear.onRemove(player));

        // remove perks
        Perks.set(player, List.of());
    }

}
