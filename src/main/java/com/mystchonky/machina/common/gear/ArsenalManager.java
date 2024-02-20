package com.mystchonky.machina.common.gear;

import com.mystchonky.machina.common.item.ExosuitArmorItem;
import com.mystchonky.machina.common.registrar.AttachmentRegistrar;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.Map;
import java.util.stream.StreamSupport;

public class ArsenalManager {

    public static void openArsenalScreen(Player player) {
        boolean armorMatch = StreamSupport.stream(player.getArmorSlots().spliterator(), false)
                .allMatch(itemStack -> itemStack.getItem() instanceof ExosuitArmorItem);
        if (!armorMatch) {
            player.displayClientMessage(Component.literal("Not wearing the full set"), true);
            return;
        }
        player.displayClientMessage(Component.literal("Showing Screen now"), true);
    }

    public static Map<ResourceLocation, Integer> getAllUnlockedGears(Player player) {
        return player.getData(AttachmentRegistrar.PLAYER_UNLOCKED_GEARS).gears();
    }
}
