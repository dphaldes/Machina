package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.command.CommandManager;
import com.mystchonky.machina.common.network.MessageRegistrar;
import net.minecraft.core.Holder;
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

public class Registrar {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB, Machina.ID);
    public static final Holder<CreativeModeTab> MACHINA_TAB = CREATIVE_TABS.register("machina",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(Items.RECOVERY_COMPASS))
                    .displayItems((par, output) -> buildTabContents(output))
                    .title(Component.literal("Machina"))
                    .build()
    );

    public static void register(final IEventBus modBus) {
        MaterialRegistrar.register(modBus);
        ItemRegistrar.register(modBus);
        EntityRegistrar.register(modBus);
        BlockRegistrar.register(modBus);
        BlockEntityRegistrar.register(modBus);
        RecipeRegistrar.register(modBus);
        CREATIVE_TABS.register(modBus);
        MenuRegistrar.register(modBus);

        modBus.addListener(Registries::register);
        AttachmentRegistrar.register(modBus);
        GearRegistrar.register(modBus);

        LangRegistrar.load();
        modBus.addListener(MessageRegistrar::registerMessages);

        NeoForge.EVENT_BUS.addListener(Registrar::registerCommands);
    }

    private static void buildTabContents(final CreativeModeTab.Output output) {
        Consumer<DeferredRegister<? extends ItemLike>> registryHandler =
                registry -> registry.getEntries().forEach(entry -> output.accept(entry.get()));

        registryHandler.accept(ItemRegistrar.ITEMS);
        registryHandler.accept(BlockRegistrar.BLOCKS);
        registryHandler.accept(GearRegistrar.GEAR_ITEMS);

    }

    private static void registerCommands(final RegisterCommandsEvent event) {
        event.getDispatcher().register(CommandManager.register());
    }

}
