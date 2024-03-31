package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import com.mystchonky.machina.common.arsenal.gear.SpeedGear;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GearRegistrar {

    public static final DeferredRegister<AbstractGear> GEARS = DeferredRegister.create(MachinaRegistries.GEAR_REGISTRY, Machina.MODID);
    public static final DeferredRegister.Items GEAR_ITEMS = DeferredRegister.createItems(Machina.MODID);

    public static final DeferredHolder<AbstractGear, SpeedGear> SPEED = registerGear(SpeedGear.INSTANCE);

    private static <T extends AbstractGear> DeferredHolder<AbstractGear, T> registerGear(T gear) {
        DeferredHolder<AbstractGear, T> holder = GEARS.register(gear.getId(), () -> gear);
        GEAR_ITEMS.register(gear.getId(), gear::getGearItem);
        return holder;
    }

    public static void register(IEventBus bus) {
        GEARS.register(bus);
        GEAR_ITEMS.register(bus);
    }
}
