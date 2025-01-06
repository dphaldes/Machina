package com.mystchonky.machina.common.blockentity;

import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class RiftPortalBlockEntity extends BlockEntity {

    @Nullable
    private BlockPos masterPos = null;

    public RiftPortalBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistrar.RIFT_PORTAL.get(), pos, blockState);
    }

    public void setMasterPos(BlockPos pos) {
        this.masterPos = pos;
    }

    @Nullable
    public BlockPos getMasterPos() {
        return masterPos;
    }

    @Nullable
    private RiftPortalBlockEntity getMaster() {
        if (masterPos == null)
            return null;

        if (level.getBlockEntity(masterPos) instanceof RiftPortalBlockEntity rift)
            return rift;

        return null;
    }


}
