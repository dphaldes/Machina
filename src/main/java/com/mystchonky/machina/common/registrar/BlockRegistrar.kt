package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.block.NexusBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockRegistrar {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Machina.MODID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(Machina.MODID);

    public static final BlockItemPair<NexusBlock, BlockItem> NEXUS_BLOCK = registerBlockPair("nexus_block", NexusBlock::new);

    public record BlockItemPair<X extends Block, Y extends BlockItem>(DeferredBlock<X> block,
                                                                      DeferredItem<Y> blockItem) {
    }

    private static <X extends Block> BlockItemPair<X, BlockItem> registerBlockPair(String name, Supplier<X> supplier) {
        DeferredBlock<X> block = BLOCKS.register(name, supplier);
        DeferredItem<BlockItem> blockItem = BLOCK_ITEMS.registerSimpleBlockItem(block);
        return new BlockItemPair<>(block, blockItem);
    }

    static void register(IEventBus bus) {
        BLOCKS.register(bus);
        BLOCK_ITEMS.register(bus);
    }
}
