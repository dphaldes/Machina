package com.mystchonky.machina.common.nexus

import dev.gigaherz.graph3.Graph
import dev.gigaherz.graph3.Mergeable
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.neoforged.neoforge.common.util.INBTSerializable

class NexusNetwork : Graph<Mergeable.Dummy>(), INBTSerializable<CompoundTag> {
    var types: List<INexusType> = ArrayList()
    fun tick(level: ServerLevel) {}
    fun updateGraph() {}
    override fun serializeNBT(): CompoundTag? {
        return null
    }

    override fun deserializeNBT(nbt: CompoundTag) {}
}
