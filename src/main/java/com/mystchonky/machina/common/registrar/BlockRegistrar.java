package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.block.RiftBlock;
import com.mystchonky.machina.common.block.RiftPortalBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockRegistrar {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Machina.ID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(Machina.ID);

    //    public static final BlockPair<EnergyNexusBlock, BlockItem> ENERGY_NEXUS = registerBlockPair("energy_nexus", EnergyNexusBlock::new);
    public static final BlockPair<RiftBlock, BlockItem> RIFT = block("rift",
            () -> new RiftBlock(BlockBehaviour.Properties.of()
                    .destroyTime(50.0F)
                    .explosionResistance(6.0F)
                    .lightLevel(state -> 4)
                    .noOcclusion()
                    .noLootTable()
            ));

    public static final DeferredBlock<RiftPortalBlock> RIFT_PORTAL = BLOCKS.register("rift_portal",
            () -> new RiftPortalBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .randomTicks()
                    .strength(-1.0F)
                    .sound(SoundType.AMETHYST)
                    .lightLevel(l -> 11)
                    .pushReaction(PushReaction.BLOCK)
            ));

    private static <X extends Block> BlockPair<X, BlockItem> block(String name, Supplier<X> supplier) {
        var block = BLOCKS.register(name, supplier);
        var blockItem = BLOCK_ITEMS.registerSimpleBlockItem(block);
        return new BlockPair<>(block, blockItem);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        BLOCK_ITEMS.register(bus);
    }

    public record BlockPair<X extends Block, Y extends BlockItem>(DeferredBlock<X> deferredBlock,
                                                                  DeferredItem<Y> deferredBlockItem) {

        public X block() {
            return deferredBlock.get();
        }

        public Y blockItem() {
            return deferredBlockItem.get();
        }
    }
}
