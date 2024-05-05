package com.mystchonky.machina.api.armament.traits;

import com.mystchonky.machina.api.armament.Perk;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;
import com.mystchonky.machina.common.registrar.LangRegistrar;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public record PerkTrait(Perk perk) implements Trait {
    @Override
    public void onEquip(Player player) {
        //do nothing
    }

    @Override
    public void onRemove(Player player) {
        //do nothing
    }

    @Override
    public void getTooltip(List<Component> tooltip) {
        tooltip.add(Component.translatable(LangRegistrar.PERK.key(), Component.translatable(PerkLibrary.localizationKey(perk().key())))
                .withStyle(ChatFormatting.GOLD));
    }
}
