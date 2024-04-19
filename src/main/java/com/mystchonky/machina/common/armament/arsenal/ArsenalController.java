package com.mystchonky.machina.common.armament.arsenal;

import com.mystchonky.machina.common.item.ExosuitArmorItem;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.common.Mod;

import java.util.Objects;
import java.util.stream.StreamSupport;

@Mod.EventBusSubscriber
public class ArsenalController {

    public static boolean isWearingArmor(Player player) {
        return StreamSupport.stream(player.getArmorSlots().spliterator(), false)
                .allMatch(itemStack -> itemStack.getItem() instanceof ExosuitArmorItem);
    }

    public static void activate(Player player) {
        activate(player, Arsenal.get(player));
    }

    public static void activate(Player player, Arsenal arsenal) {
        arsenal.gears().stream().filter(Objects::nonNull).forEach(gear -> gear.onEquip(player));
    }

    public static void deactivate(Player player) {
        deactivate(player, Arsenal.get(player));
    }

    public static void deactivate(Player player, Arsenal arsenal) {
        arsenal.gears().stream().filter(Objects::nonNull).forEach(gear -> gear.onRemove(player));
    }

}
