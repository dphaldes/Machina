package com.mystchonky.machina.client.keymap;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.client.screen.ScreenManager;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.TickEvent;

@EventBusSubscriber(modid = Machina.MODID, value = Dist.CLIENT)
public class KeymapManager {
    private static final Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END || minecraft.player == null)
            return;

        while (Keymaps.KEY_PLAYER_ARSENAL.consumeClick()) {
            ScreenManager.openArsenalScreen(minecraft.player);
        }
    }
}
