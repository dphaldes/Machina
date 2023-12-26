package com.mystchonky.machina

import com.mojang.logging.LogUtils
import com.mystchonky.machina.common.registrar.MachinaRegistrar
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import org.slf4j.Logger

@Mod(Machina.MODID)
class Machina(modEventBus: IEventBus) {
    init {
        // TODO: Handle configs
        MachinaRegistrar.register(modEventBus)
    }

    companion object {
        const val MODID = "machina"
        val LOGGER: Logger = LogUtils.getLogger()
    }
}