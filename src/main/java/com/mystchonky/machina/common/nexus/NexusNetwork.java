package com.mystchonky.machina.common.nexus;

import dev.gigaherz.graph3.Graph;
import dev.gigaherz.graph3.Mergeable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

public class NexusNetwork extends Graph<Mergeable.Dummy> implements INBTSerializable<CompoundTag> {

    List<INexusType> types = new ArrayList<>();

    public void tick(ServerLevel level) {

    }

    public void updateGraph() {

    }

    @Override
    public CompoundTag serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }

}
