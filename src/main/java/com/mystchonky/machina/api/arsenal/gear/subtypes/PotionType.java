package com.mystchonky.machina.api.arsenal.gear.subtypes;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public final class PotionType implements GearType {
    private final MobEffectInstance effectInstance;

    public PotionType(MobEffectInstance effectInstance) {
        this.effectInstance = effectInstance;
    }

    @Override
    public void onEquip(Player player) {
        player.addEffect(effectInstance);

    }

    @Override
    public void onRemove(Player player) {
        player.removeEffect(effectInstance.getEffect());
    }

    @Override
    public void getTooltip(List<Component> tooltip) {
        tooltip.add(effectInstance.getEffect().value().getDisplayName().copy().withStyle(ChatFormatting.GOLD));
    }
}
