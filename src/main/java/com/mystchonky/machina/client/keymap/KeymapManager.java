package com.mystchonky.machina.client.keymap;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.arsenal.ArsenalManager;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;

@Mod.EventBusSubscriber(modid = Machina.MODID, value = Dist.CLIENT)
public class KeymapManager {
    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END || MINECRAFT.player == null)
            return;

        while (Keymaps.KEY_PLAYER_ARSENAL.consumeClick()) {
            ArsenalManager.openArsenalScreen(MINECRAFT.player);
        }
    }
}
