package com.mystchonky.machina

import com.mojang.logging.LogUtils
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_CONTEXT

//import com.mystchonky.machina.util.Registrate

@Mod(Machina.MODID)
class Machina {
    init {
        val modEventBus = MOD_CONTEXT.getKEventBus()
        modEventBus.addListener { event: FMLCommonSetupEvent -> commonSetup(event) }
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {}

    companion object {
        const val MODID = "machina"

        //        val REGISTRATE by lazy { Registrate.create(MODID) }
        private val LOGGER = LogUtils.getLogger()
    }
}
