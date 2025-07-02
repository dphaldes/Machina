package mod.machina.common.event;

import mod.machina.common.network.MessageRegistrar;
import mod.machina.common.registrar.DataComponentRegistrar;
import mod.machina.common.registrar.ItemRegistrar;
import mod.machina.common.registrar.Registries;
import mod.machina.common.registrar.RuneRegistrar;
import mod.machina.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;

public class ModBusEvents {

    @SubscribeEvent
    public static void newRegistry(NewRegistryEvent event) {
        Registries.register(event);
    }

    @SubscribeEvent
    public static void newDatapackRegistry(DataPackRegistryEvent.NewRegistry event) {
        Registries.datapackRegisty(event);
    }

    @SubscribeEvent
    public static void registerPayloadHandler(RegisterPayloadHandlersEvent event) {
        MessageRegistrar.registerMessages(event);
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator.generate(event);
    }

    @SubscribeEvent
    public static void modifyComponents(ModifyDefaultComponentsEvent event) {
        event.modify(ItemRegistrar.GUIDE, builder -> builder.set(DataComponentRegistrar.RUNE.get(), RuneRegistrar.PHI.get()));

    }


}
