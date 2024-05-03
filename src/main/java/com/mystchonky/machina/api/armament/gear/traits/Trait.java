package com.mystchonky.machina.api.armament.gear.traits;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public interface Trait {

    void onEquip(Player player);

    void onRemove(Player player);

    default void getTooltip(List<Component> tooltip) {
    }

}
