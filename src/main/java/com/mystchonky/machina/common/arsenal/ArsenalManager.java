package com.mystchonky.machina.common.arsenal;

import com.mystchonky.machina.client.screen.PlayerArsenalScreen;
import com.mystchonky.machina.common.item.ExosuitArmorItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.stream.StreamSupport;

public class ArsenalManager {

    public static void openArsenalScreen(Player player) {
        boolean armorMatch = StreamSupport.stream(player.getArmorSlots().spliterator(), false)
                .allMatch(itemStack -> itemStack.getItem() instanceof ExosuitArmorItem);
        if (!armorMatch) {
            player.displayClientMessage(Component.literal("Not wearing the full set"), true);
            return;
        }
        Minecraft.getInstance().setScreen(new PlayerArsenalScreen(player));
    }

}
