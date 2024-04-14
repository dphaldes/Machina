package com.mystchonky.machina.api.arsenal.gear.subtypes;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public interface GearType {

    void onEquip(Player player);

    void onRemove(Player player);

    void getTooltip(List<Component> tooltip);
}
