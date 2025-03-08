package mod.machina.common.arsenal;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.PerkTrait;
import mod.machina.api.perk.Perk;
import mod.machina.common.item.VoidArmorItem;
import mod.machina.common.perk.Perks;
import mod.machina.common.registrar.AttachmentRegistrar;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ArsenalManager {

    public static boolean active(Player player) {
        return StreamSupport.stream(player.getArmorSlots().spliterator(), false)
                .allMatch(itemStack -> itemStack.getItem() instanceof VoidArmorItem);
    }

    public static void update(Player player, EquippedGears gears) {
        deactivate(player);
        setEquippedGears(player, gears);
        activate(player);
    }

    public static void activate(Player player) {
        // activate gears
        getEquippedGears(player).gears().forEach(gear -> gear.onEquip(player));

        // calculate perks
        List<Perk> perks = getEquippedGears(player)
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
        getEquippedGears(player).gears().forEach(gear -> gear.onRemove(player));

        // remove perks
        Perks.set(player, List.of());
    }

    public static EquippedGears getEquippedGears(Player player) {
        return player.getData(AttachmentRegistrar.ARSENAL);
    }

    public static void setEquippedGears(Player player, EquippedGears gears) {
        player.setData(AttachmentRegistrar.ARSENAL, gears);
    }

    public static ArrayList<Gear> getUnlockedGears(Player player) {
        return player.getData(AttachmentRegistrar.UNLOCKED_GEARS);
    }

    public static void setUnlockedGears(Player player, ArrayList<Gear> gears) {
        gears.remove(Gear.EMPTY);
        player.setData(AttachmentRegistrar.UNLOCKED_GEARS, gears);
    }
}
