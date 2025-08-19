package mod.machina.common.registrar;

import mod.machina.Machina;
import mod.machina.common.content.armor.Arsenal;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class AttachmentRegistrar {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Machina.ID);

    public static final Supplier<AttachmentType<Arsenal>> ARSENAL = ATTACHMENTS.register("arsenal",
            () -> AttachmentType.builder(Arsenal::new)
                    .serialize(Arsenal.CODEC)
                    .copyOnDeath()
                    .build());


    public static void register(IEventBus bus) {
        ATTACHMENTS.register(bus);
    }
}
