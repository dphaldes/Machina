package com.mystchonky.machina.api.arsenal.gear;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public interface AttributeGear<T extends AbstractGear> {
    Multimap<Holder<Attribute>, AttributeModifier> getModifiers();

    UUID getUUID();

    default void addModifier(T gear, ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder, Holder<Attribute> holder, double amount, AttributeModifier.Operation operation) {
        builder.put(holder, new AttributeModifier(getUUID(), gear.getId(), amount, operation));
    }
}
