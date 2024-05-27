package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.common.armament.gear.Gears;
import com.mystchonky.machina.common.armament.gear.standard.AqueousGear;
import com.mystchonky.machina.common.armament.gear.standard.FrostWalkerGear;
import com.mystchonky.machina.common.armament.gear.standard.GildedGear;
import com.mystchonky.machina.common.armament.gear.standard.GlideGear;
import com.mystchonky.machina.common.armament.gear.standard.HealthGear;
import com.mystchonky.machina.common.armament.gear.standard.SpeedGear;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GearRegistrar {

    public static final DeferredRegister<AbstractGear> GEARS = DeferredRegister.create(MachinaRegistries.GEARS_REGISTRY, Machina.MODID);
    public static final DeferredRegister.Items GEAR_ITEMS = DeferredRegister.createItems(Machina.MODID);

    public static final Supplier<AqueousGear> AQUEOUS = registerGear(Gears.AQUEOUS);
    public static final Supplier<FrostWalkerGear> FROST_WALKER = registerGear(Gears.FROST_WALKER);
    public static final Supplier<GildedGear> GILDED = registerGear(Gears.GILDED);
    public static final Supplier<GlideGear> GLIDE = registerGear(Gears.GLIDE);
    public static final Supplier<HealthGear> HEALTH = registerGear(Gears.HEALTH);
    public static final Supplier<SpeedGear> SPEED = registerGear(Gears.SPEED);

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
