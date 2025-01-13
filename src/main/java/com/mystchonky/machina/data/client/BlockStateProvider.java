package com.mystchonky.machina.data.client;


import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.block.RiftBlock;
import com.mystchonky.machina.common.registrar.BlockRegistrar;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStateProvider extends net.neoforged.neoforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Machina.ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Rift portal
        var rift = models().getExistingFile(modLoc("block/rift"));
        getVariantBuilder(BlockRegistrar.RIFT_PORTAL.get())
                .partialState().with(RiftBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(rift).addModel()
                .partialState().with(RiftBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(rift).rotationY(90).addModel();

    }
}
