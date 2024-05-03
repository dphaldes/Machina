package com.mystchonky.machina.api.armament.gear.traits;

import com.mystchonky.machina.common.armament.perk.Perk;
import net.minecraft.world.entity.player.Player;

public record PerkTrait(Perk perk) implements Trait {
    @Override
    public void onEquip(Player player) {
        //do nothing
    }

    @Override
    public void onRemove(Player player) {
        //do nothing
    }
}
