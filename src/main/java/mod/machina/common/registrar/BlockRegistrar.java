package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.common.block.RiftBlock;
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

    public static final BlockHolder<RiftBlock> RIFT_PORTAL = blockWithItem("rift",
            () -> new RiftBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .strength(-1.0F)
                    .sound(SoundType.AMETHYST)
                    .lightLevel(l -> 11)
                    .pushReaction(PushReaction.BLOCK)
            ));

    private static <X extends Block> BlockHolder<X> blockWithItem(String name, Supplier<X> supplier) {
        var block = BLOCKS.register(name, supplier);
        var blockItem = BLOCK_ITEMS.registerSimpleBlockItem(block);
        return new BlockHolder<>(block, blockItem);
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        BLOCK_ITEMS.register(bus);
    }

    public record BlockHolder<X extends Block>(DeferredBlock<X> deferredBlock,
                                               DeferredItem<BlockItem> deferredBlockItem) {

        public X block() {
            return deferredBlock.get();
        }

        public BlockItem blockItem() {
            return deferredBlockItem.get();
        }
    }
}
