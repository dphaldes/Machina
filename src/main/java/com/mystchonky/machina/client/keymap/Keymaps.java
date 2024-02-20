package com.mystchonky.machina.client.keymap;

import com.mojang.blaze3d.platform.InputConstants;
import com.mystchonky.machina.Machina;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = Machina.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Keymaps {

    public static KeyMapping KEY_PLAYER_ARSENAL = new KeyMapping("key.machina.key_arsenal",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            "key.categories.machina");

    @SubscribeEvent
    public static void registerKeymaps(RegisterKeyMappingsEvent event) {
        event.register(KEY_PLAYER_ARSENAL);
    }
}
