package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.common.item.components.ArmorTraits;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class DataComponentRegistrar {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Machina.ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ArmorTraits>> ARMOR_TRAITS = COMPONENTS.registerComponentType(
            "armor_traits",
            builder -> builder
                    .persistent(ArmorTraits.CODEC)
                    .networkSynchronized(ArmorTraits.STREAM_CODEC)
    );

    public static final Supplier<DataComponentType<Integer>> COMPENDIUM_LAST_USED = COMPONENTS.registerComponentType(
            "compendium_last_used",
            builder -> builder.networkSynchronized(ByteBufCodecs.INT)
    );

    public static void register(IEventBus bus) {
        COMPONENTS.register(bus);
    }
}
