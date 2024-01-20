package com.mystchonky.machina.common.blockentity;

import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NexusBlockEntity extends BlockEntity {

    public NexusBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityRegistrar.ENERGY_NEXUS_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);
        if (!level.isClientSide) {
            loadFromSavedData();
        }
    }

    private void loadFromSavedData() {
//        TODO("Not yet implemented")
    }
}
