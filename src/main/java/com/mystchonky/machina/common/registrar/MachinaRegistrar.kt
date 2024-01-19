package com.mystchonky.machina.common.registrar

import com.mystchonky.machina.Machina
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.ItemLike
import net.neoforged.bus.api.IEventBus
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object MachinaRegistrar {
    private val CREATIVE_TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Machina.MODID)
    val MACHINA_TAB = CREATIVE_TABS.register("machina", Supplier {
        CreativeModeTab.builder()
                .icon { ItemStack(Items.RECOVERY_COMPASS) }
                .displayItems { _: ItemDisplayParameters, output: CreativeModeTab.Output -> buildTabContents(output) }
                .title(Component.literal("Machina"))
                .build()
    })

    fun register(bus: IEventBus) {
        BlockRegistrar.register(bus)
        BlockEntityRegistrar.register(bus)

        CREATIVE_TABS.register(bus)
    }

    private fun buildTabContents(output: CreativeModeTab.Output) {
        val registryHandler = { registry: DeferredRegister<out ItemLike> -> registry.entries.forEach { output.accept(it.get()) } }

        registryHandler(BlockRegistrar.BLOCKS)
    }

}
