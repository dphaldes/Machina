package com.mystchonky.machina.common.blockentity

import com.mystchonky.machina.common.registrar.BlockEntityRegistrar
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class NexusBlockEntity(blockPos: BlockPos, blockState: BlockState) :
        BlockEntity(BlockEntityRegistrar.NEXUS_BLOCK_ENTITY.get(), blockPos, blockState) {

    init {

    }
}
