package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.client.ClientEvents;
import mod.machina.client.ClientModBusEvents;
import mod.machina.common.event.Events;
import mod.machina.common.event.ModBusEvents;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;

public class Registrar {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB, Machina.ID);
    public static final Holder<CreativeModeTab> MACHINA_TAB = CREATIVE_TABS.register("mod/machina",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(Items.RECOVERY_COMPASS))
                    .displayItems((par, output) -> buildTabContents(output))
                    .title(Component.literal("Machina"))
                    .build()
    );

    public static void register(final IEventBus modBus) {
        MaterialRegistrar.register(modBus);
        DataComponentRegistrar.register(modBus);

        ItemRegistrar.register(modBus);
        BlockRegistrar.register(modBus);
        BlockEntityRegistrar.register(modBus);
        RecipeRegistrar.register(modBus);
        CREATIVE_TABS.register(modBus);
        //MenuRegistrar.register(modBus);

        AttachmentRegistrar.register(modBus);
        GearRegistrar.register(modBus);

        LangRegistrar.load();
        TagRegistrar.load();

        modBus.register(ModBusEvents.class);
        modBus.register(ClientModBusEvents.class);

        NeoForge.EVENT_BUS.register(Events.class);
        NeoForge.EVENT_BUS.register(ClientEvents.class);
    }

    private static void buildTabContents(final CreativeModeTab.Output output) {
        Consumer<DeferredRegister<? extends ItemLike>> registryHandler =
                registry -> registry.getEntries().forEach(entry -> output.accept(entry.get()));

        registryHandler.accept(ItemRegistrar.ITEMS);
        registryHandler.accept(BlockRegistrar.BLOCK_ITEMS);
        registryHandler.accept(GearRegistrar.GEAR_ITEMS);

    }


}
