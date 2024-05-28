package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntityRegistrar {
    private static final DeferredRegister<BlockEntityType<?>> BlOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Machina.MODID);
//    public static final Supplier<BlockEntityType<NexusBlockEntity>> ENERGY_NEXUS_BLOCK_ENTITY =
//            BlOCK_ENTITIES.register("nexus_block_entity", () ->
//                    BlockEntityType.Builder.of(
//                            NexusBlockEntity::new,
//                            BlockRegistrar.ENERGY_NEXUS.block().get()
//                    ).build(null)
//            );

    public static void register(IEventBus bus) {
        BlOCK_ENTITIES.register(bus);
    }
}
