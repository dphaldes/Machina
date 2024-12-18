package com.mystchonky.machina.client;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.registrar.EntityRegistrar;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Machina.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegistrarEvents {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegistrar.RIFT_PEARL.get(), ThrownItemRenderer::new);
    }
}
