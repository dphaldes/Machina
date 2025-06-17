package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.api.gear.Gear;
import mod.machina.common.gear.standard.AqueousGear;
import mod.machina.common.gear.standard.AttackGear;
import mod.machina.common.gear.standard.BigGear;
import mod.machina.common.gear.standard.DiamondArmorGear;
import mod.machina.common.gear.standard.ElementalProtectionGear;
import mod.machina.common.gear.standard.ElytraGear;
import mod.machina.common.gear.standard.FrostWalkerGear;
import mod.machina.common.gear.standard.GildedGear;
import mod.machina.common.gear.standard.HealthGear;
import mod.machina.common.gear.standard.JumpGear;
import mod.machina.common.gear.standard.MiningGear;
import mod.machina.common.gear.standard.ProtectionGear;
import mod.machina.common.gear.standard.SmallGear;
import mod.machina.common.gear.standard.SpeedGear;
import mod.machina.common.gear.standard.ToughGear;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GearRegistrar {

    public static final DeferredRegister<Gear> GEARS = DeferredRegister.create(Registries.GEARS, Machina.ID);
    public static final DeferredRegister.Items GEAR_ITEMS = DeferredRegister.createItems(Machina.ID);

    private static final Supplier<Gear> EMPTY = GEARS.register(Gear.EMPTY.id(), () -> Gear.EMPTY);

    public static final Supplier<DiamondArmorGear> ARMOR = register(new DiamondArmorGear());
    public static final Supplier<AqueousGear> AQUEOUS = register(new AqueousGear());
    public static final Supplier<FrostWalkerGear> FROST_WALKER = register(new FrostWalkerGear());
    public static final Supplier<GildedGear> GILDED = register(new GildedGear());
    public static final Supplier<ElytraGear> ELYTRA = register(new ElytraGear());
    public static final Supplier<HealthGear> HEALTH = register(new HealthGear());
    public static final Supplier<ProtectionGear> PROTECTION = register(new ProtectionGear());
    public static final Supplier<ElementalProtectionGear> ELMENTAL_PROTECTION = register(new ElementalProtectionGear());
    public static final Supplier<SpeedGear> SPEED = register(new SpeedGear());
    public static final Supplier<JumpGear> JUMP = register(new JumpGear());
    public static final Supplier<ToughGear> TOUGH = register(new ToughGear());
    public static final Supplier<MiningGear> MINING = register(new MiningGear());
    public static final Supplier<AttackGear> ATTACK = register(new AttackGear());
    public static final Supplier<SmallGear> SMALL = register(new SmallGear());
    public static final Supplier<BigGear> BIG = register(new BigGear());

    private static <T extends Gear> DeferredHolder<Gear, T> register(T gear) {
        final var holder = GEARS.register(gear.id(), () -> gear);
        GEAR_ITEMS.register(gear.id() + "_gear", gear::getGearItem);
        return holder;
    }

    public static void register(IEventBus bus) {
        GEARS.register(bus);
        GEAR_ITEMS.register(bus);
    }
}
