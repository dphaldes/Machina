package com.mystchonky.machina.data.client;


import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.registrar.BlockRegistrar;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStateProvider extends net.neoforged.neoforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Machina.ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        directionalBlock(BlockRegistrar.RIFT.block(), models().getExistingFile(modLoc("block/rift")));
//        horizontalBlock(BlockRegistrar.CODEX.block(), models().getExistingFile(modLoc("block/codex")));

    }
}
