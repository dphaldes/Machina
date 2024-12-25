package com.mystchonky.machina.client;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.client.keymap.KeymapManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;


@EventBusSubscriber(modid = Machina.ID)
public class ClientEvents {
    @SubscribeEvent
    public static void clientTickEnd(ClientTickEvent.Post event) {
        ClientData.ticks += 1;

        KeymapManager.handleKeymaps();
    }
}
