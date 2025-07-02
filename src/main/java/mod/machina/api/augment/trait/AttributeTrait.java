package mod.machina.api.augment.trait;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.machina.api.augment.Trait;
import mod.machina.client.util.FormattedAttribute;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public record AttributeTrait(Holder<Attribute> attribute, AttributeModifier modifier) implements Trait {

    public static final MapCodec<AttributeTrait> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Attribute.CODEC.fieldOf("attribute").forGetter(AttributeTrait::attribute),
                    AttributeModifier.MAP_CODEC.forGetter(AttributeTrait::modifier)
            ).apply(instance, AttributeTrait::new)
    );

    @Override
    public void onEquip(Player player) {
        var instance = player.getAttributes().getInstance(attribute);
        if (instance != null) {
            instance.removeModifier(modifier);
            instance.addPermanentModifier(modifier);
        }
    }

    @Override
    public void onRemove(Player player) {
        var instance = player.getAttributes().getInstance(attribute);
        if (instance != null) instance.removeModifier(modifier);
    }

    @Override
    public void getTooltip(List<Component> tooltip) {
        tooltip.add(FormattedAttribute.format(attribute, modifier));
    }

    @Override
    public MapCodec<? extends Trait> type() {
        return CODEC;
    }
}
