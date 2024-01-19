package com.mystchonky.machina.common.nexus

import dev.gigaherz.graph3.Graph
import dev.gigaherz.graph3.GraphObject
import dev.gigaherz.graph3.Mergeable
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import java.util.*

class NexusIdentifier<T : IExtendedNexusData<*>>(val blockPos: BlockPos, val extendedNexusData: T) :
        GraphObject<Mergeable.Dummy> {
    private val ioStates: MutableMap<Direction, IOState> = EnumMap(
            Direction::class.java
    )
    private var graph: Graph<Mergeable.Dummy>? = null
    override fun getGraph(): Graph<Mergeable.Dummy>? {
        return graph
    }

    override fun setGraph(graph: Graph<Mergeable.Dummy>) {
        this.graph = graph
    }

    fun pushState(direction: Direction, insert: Boolean, extract: Boolean) {
        ioStates[direction] = IOState(insert, extract)
    }

    fun getIOState(direction: Direction): Optional<IOState> {
        return Optional.ofNullable(ioStates[direction])
    }

    fun clearState(direction: Direction) {
        ioStates.remove(direction)
    }

    data class IOState(val insert: Boolean, val extract: Boolean) {
        //        private static IOState of() {
        //            return new IOState(Optional.ofNullable(in), Optional.ofNullable(extract), control, redstoneChannel);
        //        }
        //        public boolean isInsert() {
        //            return insert()
        //        }
        //
        //        public boolean isExtract() {
        //            return extract().isPresent();
        //        }
    }
}