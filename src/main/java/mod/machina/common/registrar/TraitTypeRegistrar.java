package mod.machina.common.registrar;

import com.mojang.serialization.MapCodec;
import mod.machina.Machina;
import mod.machina.api.augment.Trait;
import mod.machina.api.augment.trait.AttributeTrait;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class TraitTypeRegistrar {

    public static final DeferredRegister<MapCodec<? extends Trait>> TRAIT_TYPES = DeferredRegister.create(Registries.TRAIT_TYPES, Machina.ID);

    public static final Supplier<MapCodec<AttributeTrait>> ATTRIBUTE = TRAIT_TYPES.register("attribute", () -> AttributeTrait.CODEC);

    public static void register(IEventBus bus) {
        TRAIT_TYPES.register(bus);
    }
}
