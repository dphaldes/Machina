package com.mystchonky.machina.common.block

import com.mystchonky.machina.common.registrar.BlockEntityRegistrar
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.EntityBlock
import net.minecraft.world.level.block.SimpleWaterloggedBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.FluidState
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.material.PushReaction

abstract class NexusBlock : Block(Properties.of()), EntityBlock, SimpleWaterloggedBlock {
    init {
        registerDefaultState(getStateDefinition().any().setValue(WATERLOGGED, false))
    }

    override fun getPistonPushReaction(state: BlockState): PushReaction? {
        return PushReaction.BLOCK
    }

    override fun canBeReplaced(blockState: BlockState, fluid: Fluid): Boolean {
        return false
    }

    // region Water-logging
    override fun getStateForPlacement(context: BlockPlaceContext): BlockState {
        return defaultBlockState().setValue(WATERLOGGED, context.level.getFluidState(context.clickedPos).type === Fluids.WATER)
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(WATERLOGGED)
    }

    @Suppress("DEPRECATION")
    override fun updateShape(
            state: BlockState, direction: Direction, neighborState: BlockState, level: LevelAccessor, currentPos: BlockPos,
            neighborPos: BlockPos
    ): BlockState {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level))
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.getValue(WATERLOGGED)) Fluids.WATER.getSource(false) else super.getFluidState(state)
    }

    // endregion
    override fun newBlockEntity(pos: BlockPos, blockState: BlockState): BlockEntity? {
        return BlockEntityRegistrar.ENERGY_NEXUS_BLOCK_ENTITY.get().create(pos, blockState)
    }

    companion object {
        val WATERLOGGED: BooleanProperty = BlockStateProperties.WATERLOGGED
    }
}
