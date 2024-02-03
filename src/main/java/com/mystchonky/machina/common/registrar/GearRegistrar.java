package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.gear.Gear;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GearRegistrar {
    public static final DeferredRegister<Gear> GEARS = DeferredRegister.create(Machina.resource("gears"), Machina.MODID);

    public static void register(IEventBus bus) {
        bus.register(GEARS);
    }
}
