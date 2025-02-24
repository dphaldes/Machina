package mod.machina.api.perk;

import com.mojang.serialization.Codec;

public record Perk(String key) {
    public static final Codec<Perk> CODEC = Codec.STRING.xmap(Perk::new, Perk::key);

    @Override
    public String toString() {
        return key();
    }
}
