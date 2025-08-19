package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.common.block.entity.RuneProjectorEntity;
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

    public static final Supplier<BlockEntityType<RuneProjectorEntity>> RUNE_PROJECTOR = BLOCK_ENTITIES.register("rune_projector",
            () -> BlockEntityType.Builder.of(RuneProjectorEntity::new, BlockRegistrar.RUNE_PROJECTOR.block())
                    .build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
