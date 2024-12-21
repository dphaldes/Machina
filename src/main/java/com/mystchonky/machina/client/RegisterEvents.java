package com.mystchonky.machina.client;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.client.renderer.block.CodexRenderer;
import com.mystchonky.machina.client.screen.CodexScreen;
import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import com.mystchonky.machina.common.registrar.EntityRegistrar;
import com.mystchonky.machina.common.registrar.MenuRegistrar;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = Machina.ID, bus = EventBusSubscriber.Bus.MOD)
public class RegisterEvents {
    @SubscribeEvent
    public static void entityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        // entities
        event.registerEntityRenderer(EntityRegistrar.RIFT_PEARL.get(), ThrownItemRenderer::new);

        // block_entities
        event.registerBlockEntityRenderer(BlockEntityRegistrar.CODEX.get(), CodexRenderer::new);
    }

    @SubscribeEvent
    public static void menuScreens(final RegisterMenuScreensEvent event) {
        event.register(MenuRegistrar.CODEX.get(), CodexScreen::new);
    }
}
