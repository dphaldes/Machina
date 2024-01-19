package com.mystchonky.machina.common.nexus

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.Level
import net.neoforged.neoforge.capabilities.BlockCapability
import java.util.*

interface INexusType<T : IExtendedNexusData<T>> {
    fun createExtendedNexusData(level: Level, pos: BlockPos): T

    fun <K> proxyCapability(cap: BlockCapability<K, Void>, extendedConduitData: T, level: Level, pos: BlockPos, direction: Direction,
                            state: Optional<NexusIdentifier.IOState>): K? {
        return null
    }
}
