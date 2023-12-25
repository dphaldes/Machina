package com.mystchonky.machina.common.blockentity;

import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NexusBlockEntity extends BlockEntity {
    public NexusBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityRegistrar.NEXUS_BLOCK_ENTITY.get(), blockPos, blockState);
    }
}
