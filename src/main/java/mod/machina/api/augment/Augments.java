package mod.machina.api.augment;

import mod.machina.Machina;
import mod.machina.api.RegistryKeys;
import net.minecraft.resources.ResourceKey;

public class Augments {
    public static final ResourceKey<Augment> SPEED = augment("speed");

    private static ResourceKey<Augment> augment(String path) {
        return ResourceKey.create(RegistryKeys.AUGMENT, Machina.prefix(path));
    }
}
