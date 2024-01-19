package com.mystchonky.machina.common.nexus

import dev.gigaherz.graph3.Graph
import dev.gigaherz.graph3.Mergeable
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.neoforged.neoforge.common.util.INBTSerializable

class NexusNetwork : Graph<Mergeable.Dummy>(), INBTSerializable<CompoundTag> {
    private var types: List<INexusType<*>> = ArrayList()

    fun updateGraph() {}
    fun tick(level: ServerLevel) {
        types.forEach { type -> tickType(type, level) }
    }

    private fun tickType(type: INexusType<*>, level: ServerLevel) {

    }

    override fun serializeNBT(): CompoundTag? {
        return null
    }

    override fun deserializeNBT(nbt: CompoundTag) {}

}
