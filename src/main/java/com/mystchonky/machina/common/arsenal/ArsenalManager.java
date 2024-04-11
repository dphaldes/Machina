package com.mystchonky.machina.common.arsenal;

import com.mystchonky.machina.common.attachment.Arsenal;
import com.mystchonky.machina.common.item.ExosuitArmorItem;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;

import java.util.Objects;
import java.util.stream.StreamSupport;

@Mod.EventBusSubscriber
public class ArsenalManager {

    public static boolean isWearingArmor(Player player) {
        return StreamSupport.stream(player.getArmorSlots().spliterator(), false).allMatch(itemStack -> itemStack.getItem() instanceof ExosuitArmorItem);
    }

    public static void applyArsenalEffects(Player player) {
        applyArsenalEffects(player, Arsenal.get(player));
    }

    public static void applyArsenalEffects(Player player, Arsenal arsenal) {
        arsenal.gears().stream().filter(Objects::nonNull).forEach(gear -> gear.onEquip(player));
    }

    public static void removeArsenalEffects(Player player) {
        removeArsenalEffects(player, Arsenal.get(player));
    }

    public static void removeArsenalEffects(Player player, Arsenal arsenal) {
        arsenal.gears().stream().filter(Objects::nonNull).forEach(gear -> gear.onUnequip(player));
    }

    @SubscribeEvent
    public static void equipmentChange(final LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        if (event.getFrom().getItem() instanceof ExosuitArmorItem || event.getTo().getItem() instanceof ExosuitArmorItem) {
            if (isWearingArmor(player)) {
                applyArsenalEffects(player);
            } else {
                removeArsenalEffects(player);
            }
        }
    }
}
