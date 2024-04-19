package com.mystchonky.machina.client.screen;

import com.mystchonky.machina.common.armament.arsenal.ArsenalController;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ScreenManager {

    public static void openArsenalScreen(Player player) {
        if (!ArsenalController.isWearingArmor(player)) {
            player.displayClientMessage(Component.literal("Not wearing the full set"), true);
            return;
        }
        Minecraft.getInstance().setScreen(new ArsenalScreen(player));
    }

}
