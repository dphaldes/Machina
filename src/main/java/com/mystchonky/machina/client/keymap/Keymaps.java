package com.mystchonky.machina.client.keymap;

import com.mojang.blaze3d.platform.InputConstants;
import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.registrar.LangRegistrar;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Machina.ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class Keymaps {

    public static final KeyMapping KEY_PLAYER_ARSENAL = new KeyMapping(LangRegistrar.ARSENAL_KEY.key(),
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            LangRegistrar.KEYMAP_CATEGORIES.key());

    @SubscribeEvent
    public static void registerKeymaps(RegisterKeyMappingsEvent event) {
        event.register(KEY_PLAYER_ARSENAL);
    }
}
