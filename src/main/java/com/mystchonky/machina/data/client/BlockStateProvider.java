package com.mystchonky.machina.data.client;


import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.block.RiftPortalBlock;
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

        directionalBlock(BlockRegistrar.RIFT.block(), models().getExistingFile(modLoc("block/rift")));

        {
            // Rift portal
            var model = models().getExistingFile(modLoc("block/rift_portal"));
            getVariantBuilder(BlockRegistrar.RIFT_PORTAL.get())
                    .partialState().with(RiftPortalBlock.AXIS, Direction.Axis.X)
                    .modelForState().modelFile(model).addModel()
                    .partialState().with(RiftPortalBlock.AXIS, Direction.Axis.Z)
                    .modelForState().modelFile(model).rotationY(90).addModel();
        }

    }
}
