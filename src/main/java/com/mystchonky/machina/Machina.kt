package com.mystchonky.machina;

import com.mojang.logging.LogUtils;
import com.mystchonky.machina.common.registrar.MachinaRegistrar;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Machina.MODID)
public class Machina {
    public static final String MODID = "machina";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Machina(IEventBus modEventBus) {
        // TODO: Handle configs
        MachinaRegistrar.register(modEventBus);
    }
}