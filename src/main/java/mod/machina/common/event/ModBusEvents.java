package mod.machina.common.event;

import mod.machina.common.network.MessageRegistrar;
import mod.machina.common.registrar.Registries;
import mod.machina.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;

public class ModBusEvents {

    @SubscribeEvent
    public static void newRegistry(NewRegistryEvent event) {
        Registries.register(event);
    }

    @SubscribeEvent
    public static void registerPayloadHandler(RegisterPayloadHandlersEvent event) {
        MessageRegistrar.registerMessages(event);
    }


    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator.generate(event);
    }


}
