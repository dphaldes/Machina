package com.mystchonky.machina.common.registrar

import com.mystchonky.machina.Machina
import com.mystchonky.machina.common.block.NexusBlock
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.Block
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredItem
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object BlockRegistrar {
    val BLOCKS: DeferredRegister.Blocks = DeferredRegister.createBlocks(Machina.MODID)
    val BLOCK_ITEMS: DeferredRegister.Items = DeferredRegister.createItems(Machina.MODID)

    val NEXUS_BLOCK = registerBlockPair("nexus_block") { NexusBlock() }
    private fun <X : Block> registerBlockPair(name: String, supplier: Supplier<X>): BlockItemPair<X, BlockItem> {
        val block = BLOCKS.register(name, supplier)
        val blockItem = BLOCK_ITEMS.registerSimpleBlockItem(block)
        return BlockItemPair(block, blockItem)
    }

    fun register(bus: IEventBus) {
        BLOCKS.register(bus)
        BLOCK_ITEMS.register(bus)
    }

    data class BlockItemPair<X : Block, Y : BlockItem>(val block: DeferredBlock<X>, val blockItem: DeferredItem<Y>)
}
