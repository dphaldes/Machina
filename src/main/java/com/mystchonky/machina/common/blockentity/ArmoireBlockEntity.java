package com.mystchonky.machina.common.blockentity;

import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ArmoireBlockEntity extends BlockEntity {
    public ArmoireBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistrar.ARMOIRE.get(), pos, blockState);
    }
}
