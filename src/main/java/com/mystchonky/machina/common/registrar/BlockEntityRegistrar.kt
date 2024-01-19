package com.mystchonky.machina.common.registrar

import com.mystchonky.machina.Machina
import com.mystchonky.machina.common.blockentity.NexusBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object BlockEntityRegistrar {
    private val BlOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Machina.MODID)
    val ENERGY_NEXUS_BLOCK_ENTITY: Supplier<BlockEntityType<NexusBlockEntity>> =
            BlOCK_ENTITIES.register("nexus_block_entity", Supplier {
                BlockEntityType.Builder.of(
                        { blockPos: BlockPos, blockState: BlockState -> NexusBlockEntity(blockPos, blockState) },
                        BlockRegistrar.ENERGY_NEXUS.block.get()
                ).build(null)
            })

    fun register(bus: IEventBus) {
        BlOCK_ENTITIES.register(bus)
    }
}
