package com.mystchonky.machina.common.gear;

import com.mystchonky.machina.common.registrar.AttachmentRegistrar;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.Map;

public class GearManager {
    public static Map<ResourceLocation, Integer> getAllUnlockedGears(Player player) {
        return player.getData(AttachmentRegistrar.UNLOCKED_GEARS).gears();
    }
}
