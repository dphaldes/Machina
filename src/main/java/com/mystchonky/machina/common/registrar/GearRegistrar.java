package com.mystchonky.machina.common.registrar;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.common.armament.gear.standard.AqueousGear;
import com.mystchonky.machina.common.armament.gear.standard.ArmorGear;
import com.mystchonky.machina.common.armament.gear.standard.ElementalProtectionGear;
import com.mystchonky.machina.common.armament.gear.standard.FrostWalkerGear;
import com.mystchonky.machina.common.armament.gear.standard.GildedGear;
import com.mystchonky.machina.common.armament.gear.standard.GlideGear;
import com.mystchonky.machina.common.armament.gear.standard.HealthGear;
import com.mystchonky.machina.common.armament.gear.standard.JumpGear;
import com.mystchonky.machina.common.armament.gear.standard.ProtectionGear;
import com.mystchonky.machina.common.armament.gear.standard.SpeedGear;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GearRegistrar {

    public static final DeferredRegister<AbstractGear> GEARS = DeferredRegister.create(Registries.GEARS_REGISTRY, Machina.ID);
    public static final DeferredRegister.Items GEAR_ITEMS = DeferredRegister.createItems(Machina.ID);

    public static final Supplier<ArmorGear> ARMOR = register(new ArmorGear());
    public static final Supplier<AqueousGear> AQUEOUS = register(new AqueousGear());
    public static final Supplier<FrostWalkerGear> FROST_WALKER = register(new FrostWalkerGear());
    public static final Supplier<GildedGear> GILDED = register(new GildedGear());
    public static final Supplier<GlideGear> GLIDE = register(new GlideGear());
    public static final Supplier<HealthGear> HEALTH = register(new HealthGear());
    public static final Supplier<ProtectionGear> PROTECTION = register(new ProtectionGear());
    public static final Supplier<ElementalProtectionGear> ELMENTAL_PROTECTION = register(new ElementalProtectionGear());
    public static final Supplier<SpeedGear> SPEED = register(new SpeedGear());
    public static final Supplier<JumpGear> JUMP = register(new JumpGear());

    private static <T extends AbstractGear> DeferredHolder<AbstractGear, T> register(T gear) {
        final var holder = GEARS.register(gear.id(), () -> gear);
        GEAR_ITEMS.register(gear.id(), gear::getGearItem);
        return holder;
    }

    public static void register(IEventBus bus) {
        GEARS.register(bus);
        GEAR_ITEMS.register(bus);
    }
}
