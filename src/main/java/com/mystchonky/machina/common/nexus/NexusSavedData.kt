package com.mystchonky.machina.common.nexus

import com.mystchonky.machina.Machina
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.ChunkPos
import net.minecraft.world.level.saveddata.SavedData
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.Mod.EventBusSubscriber
import net.neoforged.neoforge.event.TickEvent
import net.neoforged.neoforge.event.TickEvent.LevelTickEvent
import java.io.File

@EventBusSubscriber
internal class NexusSavedData() : SavedData() {
    private val networks: MutableList<NexusNetwork> = ArrayList()
    private val deserializedNodes: Map<INexusType<*>, Map<ChunkPos, Map<BlockPos, NexusIdentifier<*>>>> = HashMap()

    constructor(level: ServerLevel, nbt: CompoundTag) : this() {
        val networksTag = nbt.getList(KEY_NETWORKS, Tag.TAG_COMPOUND.toInt())
        for (tag in networksTag) {
            // networks.add(deserialize(tag))
        }
    }

    override fun save(tag: CompoundTag): CompoundTag {
        // CODEC SAVE
        return tag
    }

    override fun isDirty(): Boolean {
        return true
    }

    fun tick(level: ServerLevel) {
        setDirty()
        // Remove empty graphs
        networks.removeIf { network: NexusNetwork ->
            network.objects.isEmpty() || network.objects.iterator().next().graph !== network
        }
        if (level.gameTime % 10 == 0L) {
            for (graph in networks) {
                graph.tick(level)
            }
        }
    }

    override fun save(file: File) {
        if (isDirty) {
            //This is an exact copy of Mekanism MekanismSavedData's system which is loosely based on
            // Refined Storage's RSSavedData's system of saving first to a temp file
            // to reduce the odds of corruption if the user's computer crashes while the file is being written

            //Thanks pupnewster
            val tempFile = file.toPath().parent.resolve(file.name + ".tmp").toFile()
            super.save(tempFile)
            if (file.exists() && !file.delete()) {
                Machina.LOGGER.error("Failed to delete " + file.name)
            }
            if (!tempFile.renameTo(file)) {
                Machina.LOGGER.error("Failed to rename " + tempFile.name)
            }
        }
    }

    companion object {
        // Deserialization
        private const val KEY_NETWORKS = "Networks"
        operator fun get(level: ServerLevel): NexusSavedData {
            return level.dataStorage.computeIfAbsent(Factory({ NexusSavedData() }) { nbt: CompoundTag ->
                NexusSavedData(level, nbt)
            }, "machina_nexus_network")
        }

        @SubscribeEvent
        fun onLevelTick(event: LevelTickEvent) {
            if (event.phase == TickEvent.Phase.START) return

            val level = event.level
            if (level is ServerLevel) {
                get(level).tick(level)
            }
        }
    }
}
