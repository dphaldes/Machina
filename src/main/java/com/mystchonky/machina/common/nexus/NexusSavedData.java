package com.mystchonky.machina.common.nexus;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.TickEvent;

@Mod.EventBusSubscriber
class NexusSavedData extends SavedData {

    public NexusSavedData(ServerLevel level, CompoundTag nbt) {
        // CODEC LOAD
    }

    public NexusSavedData() {

    }

    public static NexusSavedData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(new Factory<>(NexusSavedData::new, (nbt) -> new NexusSavedData(level, nbt)), "machina_nexus_network");
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.START) return;

        if (event.level instanceof ServerLevel level) {
            get(level).tick(level);
        }
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        // CODEC SAVE
        return tag;
    }

    @Override
    public boolean isDirty() {
        return true;
    }

    public void tick(ServerLevel level) {
        setDirty();

//        TODO("Clear empty graphs")

//        TODO("Tick each graph")

    }
}

