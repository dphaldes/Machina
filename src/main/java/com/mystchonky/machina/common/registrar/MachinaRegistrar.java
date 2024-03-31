package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.client.registrar.ScreenRegistrar;
import com.mystchonky.machina.common.command.CommandManager;
import com.mystchonky.machina.common.network.MessageRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;

public class MachinaRegistrar {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Machina.MODID);
    public static final Holder<CreativeModeTab> MACHINA_TAB = CREATIVE_TABS.register("machina", () ->
            CreativeModeTab.builder()
                    .icon(() -> new ItemStack(Items.RECOVERY_COMPASS))
                    .displayItems((par, output) -> buildTabContents(output))
                    .title(Component.literal("Machina"))
                    .build()
    );

    public static void register(IEventBus bus) {
        ItemRegistrar.register(bus);
        BlockRegistrar.register(bus);
        BlockEntityRegistrar.register(bus);
        CREATIVE_TABS.register(bus);

        bus.addListener(MachinaRegistries::register);
        AttachmentRegistrar.register(bus);
        GearRegistrar.register(bus);

        LangRegistrar.load();
        bus.addListener(MessageRegistrar::registerMessages);
        bus.addListener(ScreenRegistrar::registerScreens);

        NeoForge.EVENT_BUS.addListener(MachinaRegistrar::registerCommands);
    }

    private static void buildTabContents(CreativeModeTab.Output output) {
        Consumer<DeferredRegister<? extends ItemLike>> registryHandler = registry -> registry.getEntries().forEach(entry -> output.accept(entry.get()));

        registryHandler.accept(ItemRegistrar.ITEMS);
        registryHandler.accept(BlockRegistrar.BLOCKS);
        registryHandler.accept(GearRegistrar.GEAR_ITEMS);

    }

    private static void registerCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(CommandManager.register());
    }

}
