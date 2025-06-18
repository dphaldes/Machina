package mod.machina.api.rune;

import mod.machina.Machina;
import net.minecraft.resources.ResourceLocation;

public record Rune(ResourceLocation id) {

    public static final Rune EMPTY = new Rune(Machina.prefix("null"));
}
