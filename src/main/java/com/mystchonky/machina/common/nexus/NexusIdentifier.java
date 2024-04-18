package com.mystchonky.machina.common.nexus;

import dev.gigaherz.graph3.Graph;
import dev.gigaherz.graph3.GraphObject;
import dev.gigaherz.graph3.Mergeable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class NexusIdentifier<T extends IExtendedNexusData<?>> implements GraphObject<Mergeable.Dummy> {

    private final Map<Direction, IOState> ioStates = new EnumMap<>(Direction.class);
    BlockPos blockPos;
    T extendedNexusData;
    @Nullable
    private Graph<Mergeable.Dummy> graph = null;

    public NexusIdentifier(BlockPos blockPos, T extendedNexusData) {
        this.blockPos = blockPos;
        this.extendedNexusData = extendedNexusData;
    }

    @Override
    public @Nullable Graph<Mergeable.Dummy> getGraph() {
        return graph;
    }

    @Override
    public void setGraph(Graph<Mergeable.Dummy> graph) {
        this.graph = graph;
    }

    public void pushState(Direction direction, boolean insert, boolean extract) {
        ioStates.put(direction, new IOState(insert, extract));
    }

    public Optional<IOState> getIOState(Direction direction) {
        return Optional.ofNullable(ioStates.get(direction));
    }

    public void clearState(Direction direction) {
        ioStates.remove(direction);
    }

    public record IOState(Boolean insert, Boolean extract) {
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