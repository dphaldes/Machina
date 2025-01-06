package com.mystchonky.machina.client.screen;

import com.mystchonky.machina.common.arsenal.ArsenalManager;
import com.mystchonky.machina.common.registrar.LangRegistrar;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

public class ScreenManager {
    private static final Minecraft minecraft = Minecraft.getInstance();

    public static void openArsenalScreen(Player player) {
        if (!ArsenalManager.active(player)) {
            player.displayClientMessage(LangRegistrar.ARMOR_MISSING.component(), true);
            return;
        }
        minecraft.setScreen(new ArsenalScreen(player));
    }

    public static void openCodexScreen(Player player, BlockPos master) {
        minecraft.setScreen(new CodexScreen(player, master));
    }

}
