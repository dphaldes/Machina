package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
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

//    public static final BlockPair<EnergyNexusBlock, BlockItem> ENERGY_NEXUS = registerBlockPair("energy_nexus", EnergyNexusBlock::new);

    private static <X extends Block> BlockPair<X, BlockItem> registerBlockPair(String name, Supplier<X> supplier) {
        var block = BLOCKS.register(name, supplier);
        var blockItem = BLOCK_ITEMS.registerSimpleBlockItem(block);
        return new BlockPair<>(block, blockItem);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        BLOCK_ITEMS.register(bus);
    }

    public record BlockPair<X extends Block, Y extends BlockItem>(DeferredBlock<X> block,
                                                                  DeferredItem<Y> blockItem) {
    }
}
