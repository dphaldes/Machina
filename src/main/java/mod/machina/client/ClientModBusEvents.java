package mod.machina.client;

import mod.machina.client.keymap.Keymaps;
import mod.machina.client.renderer.block.RuneProjectorRenderer;
import mod.machina.common.item.GuideItem;
import mod.machina.common.registrar.BlockEntityRegistrar;
import mod.machina.common.registrar.ItemRegistrar;
import net.minecraft.client.renderer.item.ItemProperties;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

public class ClientModBusEvents {

    @SubscribeEvent
    public static void fmlClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(ItemRegistrar.GUIDE.asItem(), ItemOverridePredicates.GUIDE_SWORD_ACTIVE, GuideItem::getVisualState);
        });
    }

    @SubscribeEvent
    public static void entityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        // entities

        // block_entities
        event.registerBlockEntityRenderer(BlockEntityRegistrar.RUNE_PROJECTOR.get(), context -> new RuneProjectorRenderer());
    }


    @SubscribeEvent
    public static void registerKeymaps(RegisterKeyMappingsEvent event) {
        event.register(Keymaps.KEY_PLAYER_ARSENAL);
    }
}
