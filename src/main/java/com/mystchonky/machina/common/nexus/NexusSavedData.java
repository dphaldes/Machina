package com.mystchonky.machina.common.nexus;

import com.mystchonky.machina.Machina;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//@Mod.EventBusSubscriber
class NexusSavedData extends SavedData {

    private final List<NexusNetwork> networks = new ArrayList<>();

    public NexusSavedData() {

    }

    // Deserialization

    private static final String KEY_NETWORKS = "Networks";

    public NexusSavedData(ServerLevel level, CompoundTag nbt) {
        ListTag networksTag = nbt.getList(KEY_NETWORKS, Tag.TAG_COMPOUND);
        for (Tag tag : networksTag) {
            // networks.add(deserialize(tag))
        }
    }

    public static NexusSavedData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(new Factory<>(NexusSavedData::new, (nbt) -> new NexusSavedData(level, nbt)), "machina_nexus_network");
    }

//    @SubscribeEvent
//    public static void onLevelTick(TickEvent.LevelTickEvent event) {
//        if (event.phase == TickEvent.Phase.START) return;
//
//        if (event.level instanceof ServerLevel level) {
//            get(level).tick(level);
//        }
//    }

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

    @Override
    public void save(File file) {
        if (isDirty()) {
            //This is an exact copy of Mekanism MekanismSavedData's system which is loosely based on
            // Refined Storage's RSSavedData's system of saving first to a temp file
            // to reduce the odds of corruption if the user's computer crashes while the file is being written

            //Thanks pupnewster
            File tempFile = file.toPath().getParent().resolve(file.getName() + ".tmp").toFile();
            super.save(tempFile);
            if (file.exists() && !file.delete()) {
                Machina.LOGGER.error("Failed to delete " + file.getName());
            }
            if (!tempFile.renameTo(file)) {
                Machina.LOGGER.error("Failed to rename " + tempFile.getName());
            }
        }
    }
}

