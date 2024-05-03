package com.mystchonky.machina.client.screen;

import com.mystchonky.machina.common.armament.Armament;
import com.mystchonky.machina.common.registrar.LangRegistrar;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class ScreenManager {

    public static void openArsenalScreen(Player player) {
        if (!Armament.isWearingArmor(player)) {
            player.displayClientMessage(LangRegistrar.ARMOR_MISSING.component(), true);
            return;
        }
        Minecraft.getInstance().setScreen(new ArsenalScreen(player));
    }

}
