package mod.machina.api.augment.trait;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.machina.api.augment.Perk;
import mod.machina.api.augment.Trait;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;

import java.util.List;

public record PerkTrait(Holder<Perk> perk, int level) implements Trait {

    public static final MapCodec<PerkTrait> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Perk.CODEC.fieldOf("perk").forGetter(PerkTrait::perk),
                    Codec.INT.fieldOf("level").forGetter(PerkTrait::level)
            ).apply(instance, PerkTrait::new)
    );

    @Override
    public void getTooltip(List<Component> tooltip) {
        //TODO
    }

    @Override
    public MapCodec<? extends Trait> type() {
        return CODEC;
    }
}
