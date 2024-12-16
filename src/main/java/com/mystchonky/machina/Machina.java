package com.mystchonky.machina;

import com.mojang.logging.LogUtils;
import com.mystchonky.machina.common.registrar.Registrar;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Machina.ID)
public class Machina {
    public static final String ID = "machina";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Machina(IEventBus modEventBus) {
        // TODO: Handle configs
        Registrar.register(modEventBus);
    }

    public static ResourceLocation prefix(String id) {
        return ResourceLocation.fromNamespaceAndPath(Machina.ID, id);
    }

}
