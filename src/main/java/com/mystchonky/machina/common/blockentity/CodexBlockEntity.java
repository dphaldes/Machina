package com.mystchonky.machina.common.blockentity;

import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CodexBlockEntity extends BlockEntity {
    public CodexBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistrar.CODEX.get(), pos, blockState);
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, CodexBlockEntity codex) {

    }
}
