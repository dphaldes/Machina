package mod.machina.client;

import mod.machina.Machina;
import mod.machina.client.keymap.Keymaps;
import mod.machina.client.layer.Layers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = Machina.ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class RegisterEvents {
//    @SubscribeEvent
//    public static void entityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
//        // entities
//
//        // block_entities
//        // event.registerBlockEntityRenderer(BlockEntityRegistrar.CODEX.get(), CodexRenderer::new);
//    }

    @SubscribeEvent
    public static void overlays(final RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.CROSSHAIR, Machina.prefix("layer"), Layers.LAYER);
    }

    @SubscribeEvent
    public static void registerKeymaps(RegisterKeyMappingsEvent event) {
        event.register(Keymaps.KEY_PLAYER_ARSENAL);
    }
}
