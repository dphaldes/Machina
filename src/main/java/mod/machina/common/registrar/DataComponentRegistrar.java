package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.common.item.components.ItemStackReference;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentRegistrar {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Machina.ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemStackReference>> ITEM_STACK_REF = COMPONENTS.registerComponentType(
            "item_stack_ref",
            builder -> builder
                    .persistent(ItemStackReference.CODEC)
                    .networkSynchronized(ItemStackReference.STREAM_CODEC)
    );

    public static void register(IEventBus bus) {
        COMPONENTS.register(bus);
    }
}
