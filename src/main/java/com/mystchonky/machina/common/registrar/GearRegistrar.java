package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.gear.Gear;
import com.mystchonky.machina.common.gear.ProtectionGear;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GearRegistrar {
    public static final DeferredRegister<Gear> GEARS = DeferredRegister.create(Machina.prefix("gears"), Machina.MODID);

    public static final DeferredHolder<Gear, ProtectionGear> PROTECTION = GEARS.register("protection", ProtectionGear::new);

    public static void register(IEventBus bus) {
        GEARS.register(bus);
    }
}
