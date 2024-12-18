package com.mystchonky.machina.api.gear.trait;

import com.mystchonky.machina.common.registrar.LangRegistrar;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public record PotionTrait(MobEffectInstance effect) implements Trait {
    @Override
    public void onEquip(Player player) {
        player.addEffect(effect);
    }

    @Override
    public void onRemove(Player player) {
        player.removeEffect(effect.getEffect());
    }

    @Override
    public void getTooltip(List<Component> tooltip) {
        tooltip.add(Component.translatable(LangRegistrar.POTION.key(), effect.getEffect().value().getDisplayName())
                .withStyle(ChatFormatting.GREEN));
    }
}
