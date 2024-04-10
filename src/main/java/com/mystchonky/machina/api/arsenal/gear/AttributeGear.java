package com.mystchonky.machina.api.arsenal.gear;

import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public interface AttributeGear {
    public Multimap<Holder<Attribute>, AttributeModifier> getModifiers();
}
