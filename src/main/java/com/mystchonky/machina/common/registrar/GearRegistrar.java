package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.armament.gear.AbstractGear;
import com.mystchonky.machina.common.armament.gear.standard.BreathGear;
import com.mystchonky.machina.common.armament.gear.standard.GlideGear;
import com.mystchonky.machina.common.armament.gear.standard.HealthGear;
import com.mystchonky.machina.common.armament.gear.standard.SpeedGear;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GearRegistrar {

    public static final DeferredRegister<AbstractGear> GEARS = DeferredRegister.create(MachinaRegistries.GEARS_REGISTRY, Machina.MODID);
    public static final DeferredRegister.Items GEAR_ITEMS = DeferredRegister.createItems(Machina.MODID);

    public static final DeferredHolder<AbstractGear, SpeedGear> SPEED = registerGear(SpeedGear.INSTANCE);
    public static final DeferredHolder<AbstractGear, BreathGear> BREATH = registerGear(BreathGear.INSTANCE);
    public static final DeferredHolder<AbstractGear, GlideGear> GLIDE = registerGear(GlideGear.INSTANCE);
    public static final DeferredHolder<AbstractGear, HealthGear> HEALTH = registerGear(HealthGear.INSTANCE);

    private static <T extends AbstractGear> DeferredHolder<AbstractGear, T> registerGear(T gear) {
        final var holder = GEARS.register(gear.id(), () -> gear);
        GEAR_ITEMS.register(gear.id(), gear::getGearItem);
        return holder;
    }

    public static void register(IEventBus bus) {
        GEARS.register(bus);
        GEAR_ITEMS.register(bus);
    }
}
