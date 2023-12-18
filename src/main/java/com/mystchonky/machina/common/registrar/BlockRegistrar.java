package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.block.NexusBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistrar {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Machina.MODID);

    public static final DeferredBlock<NexusBlock> NEXUS_BLOCK = BLOCKS.register("nexus_block", NexusBlock::new);

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
