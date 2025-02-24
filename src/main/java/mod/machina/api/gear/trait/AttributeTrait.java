package mod.machina.api.gear.trait;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import mod.machina.Machina;
import mod.machina.api.gear.Gear;
import mod.machina.client.util.FormattedAttribute;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.List;

public final class AttributeTrait implements Trait {
    private final Gear gear;
    private final ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = new ImmutableMultimap.Builder<>();
    private @Nullable Multimap<Holder<Attribute>, AttributeModifier> modifiers = null;

    public AttributeTrait(Gear gear) {
        this.gear = gear;
    }

    private Multimap<Holder<Attribute>, AttributeModifier> getModifiers() {
        if (modifiers == null) modifiers = builder.build();

        return modifiers;
    }

    public void addModifier(Holder<Attribute> holder, double amount, AttributeModifier.Operation operation) {
        builder.put(holder, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Machina.ID, gear.id()), amount, operation));
    }

    @Override
    public void onEquip(Player player) {
        var gearMap = this.getModifiers();
        var playerMap = player.getAttributes();
        gearMap.forEach((holder, modifier) -> {
            var instance = playerMap.getInstance(holder);
            if (instance != null) {
                instance.removeModifier(modifier);  //Remove just in case it already exists
                instance.addPermanentModifier(modifier);
            }
        });
    }

    @Override
    public void onRemove(Player player) {
        var gearMap = this.getModifiers();
        var playerMap = player.getAttributes();
        gearMap.forEach((holder, modifier) -> {
            var instance = playerMap.getInstance(holder);
            if (instance != null) instance.removeModifier(modifier);
        });
    }

    @Override
    public void getTooltip(List<Component> tooltip) {
        var modifiers = this.getModifiers();
        modifiers.forEach((holder, modifier) -> tooltip.add(FormattedAttribute.format(holder, modifier)));
    }
}
