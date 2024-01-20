package com.mystchonky.machina.common.nexus;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.BlockCapability;

import java.util.Optional;

interface INexusType<T extends IExtendedNexusData<T>> {
    T createExtendedNexusData(Level level, BlockPos pos);

    default <K> K proxyCapability(BlockCapability<K, Void> cap, T extendedConduitData, Level level, BlockPos pos, Direction direction,
                                  Optional<NexusIdentifier.IOState> state) {
        return null;
    }
}
