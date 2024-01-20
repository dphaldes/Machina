package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.block.EnergyNexusBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockRegistrar {

    public static DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Machina.MODID);
    public static DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(Machina.MODID);

    public static BlockItemPair<EnergyNexusBlock, BlockItem> ENERGY_NEXUS = registerBlockPair("energy_nexus", EnergyNexusBlock::new);

    private static <X extends Block> BlockItemPair<X, BlockItem> registerBlockPair(String name, Supplier<X> supplier) {
        var block = BLOCKS.register(name, supplier);
        var blockItem = BLOCK_ITEMS.registerSimpleBlockItem(block);
        return new BlockItemPair<>(block, blockItem);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        BLOCK_ITEMS.register(bus);
    }

    public record BlockItemPair<X extends Block, Y extends BlockItem>(DeferredBlock<X> block,
                                                                      DeferredItem<Y> blockItem) {
    }
}
