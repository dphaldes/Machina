package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.UUID;

public class DataComponentRegistrar {
    private static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Machina.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<UUID>> PLAYER_UUID = COMPONENTS.registerComponentType(
            "player_uuid", builder -> builder.persistent(UUIDUtil.CODEC).networkSynchronized(UUIDUtil.STREAM_CODEC)
    );

    public static void register(IEventBus bus) {
        COMPONENTS.register(bus);
    }
}
