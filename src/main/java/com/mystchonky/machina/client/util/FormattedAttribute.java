package com.mystchonky.machina.client.util;

import com.mystchonky.machina.common.registrar.LangRegistrar;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class FormattedAttribute {

    public static Component format(Holder<Attribute> holder, AttributeModifier modifier) {
        var name = Component.translatable(holder.value().getDescriptionId());
        var side = modifier.amount() > 0;
        var component = switch (modifier.operation()) {
            case ADD_VALUE -> {
                var key = side ? LangRegistrar.ATTRIBUTE_ADD.key() : LangRegistrar.ATTRIBUTE_ADD_N.key();
                yield Component.translatable(key, modifier.amount(), name);
            }
            case ADD_MULTIPLIED_BASE -> {
                var key = side ? LangRegistrar.ATTRIBUTE_MULTIPLY_BASE.key() : LangRegistrar.ATTRIBUTE_MULTIPLY_BASE_N.key();
                yield Component.translatable(key, formatted(modifier), name);
            }
            case ADD_MULTIPLIED_TOTAL -> {
                var key = side ? LangRegistrar.ATTRIBUTE_MULTIPLY_TOTAL.key() : LangRegistrar.ATTRIBUTE_MULTIPLY_TOTAL_N.key();
                yield Component.translatable(key, formatted(modifier), name);
            }
        };

        return side ? component.withStyle(ChatFormatting.AQUA) : component.withStyle(ChatFormatting.RED);
    }

    public static String formatted(AttributeModifier modifier) {
        return ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(Math.abs(modifier.amount()) * 100);
    }
}
