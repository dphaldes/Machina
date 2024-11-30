package com.mystchonky.machina.common.blockentity;

import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class RiftBlockEntity extends BlockEntity {

    @Nullable
    private ItemEntity currentItem = null;

    public RiftBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistrar.RIFT.get(), pos, blockState);
    }

    public void tryCraft(ItemEntity item) {
        if (currentItem != null)
            return;

        currentItem = item;
    }

    public void craft() {

    }
}
