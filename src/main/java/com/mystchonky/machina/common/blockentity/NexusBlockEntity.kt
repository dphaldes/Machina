package com.mystchonky.machina.common.blockentity

import com.mystchonky.machina.common.registrar.BlockEntityRegistrar
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class NexusBlockEntity(blockPos: BlockPos, blockState: BlockState) :
        BlockEntity(BlockEntityRegistrar.ENERGY_NEXUS_BLOCK_ENTITY.get(), blockPos, blockState) {

    override fun setLevel(level: Level) {
        super.setLevel(level)
        if (!level.isClientSide) {
            loadFromSavedData()
        }
    }

    private fun loadFromSavedData() {
        TODO("Not yet implemented")
    }
}
