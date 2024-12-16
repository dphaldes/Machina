package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.entity.ThrownRiftPearl;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EntityRegistrar {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Machina.ID);

    public static final Supplier<EntityType<ThrownRiftPearl>> RIFT_PEARL = ENTITY_TYPES.register("rift_pearl",
            () -> EntityType.Builder.<ThrownRiftPearl>of(ThrownRiftPearl::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("rift_pearl")
    );


    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
