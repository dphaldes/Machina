package com.mystchonky.machina

import com.mojang.logging.LogUtils
import com.mystchonky.machina.util.Registrate
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_CONTEXT

@Mod(Machina.MODID)
class Machina {
    init {
        val modEventBus = MOD_CONTEXT.getKEventBus()
        modEventBus.addListener { event: FMLCommonSetupEvent -> commonSetup(event) }
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {}

    companion object {
        const val MODID = "machina"
        val REGISTRATE by lazy { Registrate.create(MODID) }
        private val LOGGER = LogUtils.getLogger()
    }
}
