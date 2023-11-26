package com.mystchonky.machina.common.nexus

import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.saveddata.SavedData
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.event.TickEvent

@Mod.EventBusSubscriber
class NexusSavedData() : SavedData() {

    constructor(level: ServerLevel, nbt: CompoundTag) : this() {
        // CODEC LOAD
        TODO("Not yet implemented")

    }

    override fun save(p_77763_: CompoundTag): CompoundTag {
        // CODEC SAVE
        TODO("Not yet implemented")
    }

    override fun isDirty(): Boolean {
        return true
    }

    fun tick(level: ServerLevel) {
        setDirty()

        TODO("Clear empty graphs")

        TODO("Tick each graph")
    }

    companion object {
        fun get(level: ServerLevel): NexusSavedData {
            return level.dataStorage.computeIfAbsent(Factory(::NexusSavedData, { nbt -> NexusSavedData(level, nbt) }), "machina_nexus_network")
        }

        @SubscribeEvent
        fun onLevelTick(event: TickEvent.LevelTickEvent) {
            if (event.phase == TickEvent.Phase.START) return

            val level = event.level
            if (level is ServerLevel) {
                get(level).tick(level)
            }


        }
    }

}