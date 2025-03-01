package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.common.item.components.ItemStackHolder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentRegistrar {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Machina.ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemStackHolder>> STACK_HOLDER = COMPONENTS.registerComponentType(
            "stack_holder",
            builder -> builder
                    .persistent(ItemStackHolder.CODEC)
                    .networkSynchronized(ItemStackHolder.STREAM_CODEC)
    );

    public static void register(IEventBus bus) {
        COMPONENTS.register(bus);
    }
}
