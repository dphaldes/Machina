package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.api.gear.Gear;
import mod.machina.api.perk.Perk;
import mod.machina.common.arsenal.Arsenal;
import mod.machina.common.gear.UnlockedGears;
import mod.machina.common.perk.Perks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class AttachmentRegistrar {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Machina.ID);

    public static final Supplier<AttachmentType<Arsenal>> ARSENAL = ATTACHMENTS.register("arsenal",
            () -> AttachmentType.builder(Arsenal::create)
                    .serialize(Arsenal.CODEC)
                    .copyOnDeath()
                    .build());

    public static final Supplier<AttachmentType<ArrayList<Gear>>> UNLOCKED_GEARS = ATTACHMENTS.register("unlocked_gears",
            () -> AttachmentType.builder(UnlockedGears::create)
                    .serialize(UnlockedGears.CODEC)
                    .copyOnDeath()
                    .build());

    public static final Supplier<AttachmentType<List<Perk>>> PERKS = ATTACHMENTS.register("perks",
            () -> AttachmentType.builder(Perks::create)
                    .serialize(Perks.CODEC)
                    .copyOnDeath()
                    .build());

    public static void register(IEventBus bus) {
        ATTACHMENTS.register(bus);
    }
}
