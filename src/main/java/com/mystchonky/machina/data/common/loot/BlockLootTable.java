package com.mystchonky.machina.data.common.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class BlockLootTable extends BlockLootSubProvider {
    private final List<Block> blocks = new ArrayList<>();

    public BlockLootTable(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return blocks;
    }

    @Override
    protected void generate() {
//        loot(BlockRegistrar.CODEX.block(), this::dropSelf);
    }

    protected void loot(Block block, Consumer<Block> lootFunction) {
        blocks.add(block);
        lootFunction.accept(block);
    }
}

