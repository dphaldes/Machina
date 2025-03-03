package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.common.blockentity.RiftBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockEntityRegistrar {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Machina.ID);
    //    public static final Supplier<BlockEntityType<NexusBlockEntity>> ENERGY_NEXUS_BLOCK_ENTITY =
//            BlOCK_ENTITIES.register("nexus_block_entity", () ->
//                    BlockEntityType.Builder.of(
//                            NexusBlockEntity::new,
//                            BlockRegistrar.ENERGY_NEXUS.deferredBlock().get()
//                    ).build(null)
//            );

    public static final Supplier<BlockEntityType<RiftBlockEntity>> RIFT_PORTAL = BLOCK_ENTITIES.register("rift",
            () -> BlockEntityType.Builder.of(RiftBlockEntity::new, BlockRegistrar.RIFT_PORTAL.block())
                    .build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
