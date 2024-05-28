package com.mystchonky.machina.common.nexus;

import com.mystchonky.machina.Machina;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber
public class NexusSavedData extends SavedData {

    private final static String KEY_NETWORKS = "Networks";
    private final List<NexusNetwork> networks = new ArrayList<>();
    private final Map<INexusType<?>, Map<ChunkPos, Map<BlockPos, NexusIdentifier<?>>>> deserializedNodes = new HashMap<>();

    public NexusSavedData(ServerLevel level, CompoundTag nbt) {
        ListTag networksTag = nbt.getList(KEY_NETWORKS, Tag.TAG_COMPOUND);
        for (Tag tag : networksTag) {
            // networks.add(deserialize(tag))
        }
    }

    public NexusSavedData() {

    }

    // Deserialization
    public static NexusSavedData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(new Factory<>(NexusSavedData::new, (nbt, provider) -> new NexusSavedData(level, nbt)), "machina_nexus_network");
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel level) {
            get(level).tick(level);
        }
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        // CODEC SAVE
        return tag;
    }

    @Override
    public boolean isDirty() {
        return true;
    }

    public void tick(ServerLevel level) {
        setDirty();
        // Remove empty graphs
        networks.removeIf(network -> network.getObjects().isEmpty() || network.getObjects().iterator().next().getGraph() != network);

        if (level.getGameTime() % 10 == 0L) {
            for (NexusNetwork graph : networks) {
                graph.tick(level);
            }
        }
    }

    @Override
    public void save(File file, HolderLookup.Provider provider) {
        if (isDirty()) {
            //This is an exact copy of Mekanism MekanismSavedData's system which is loosely based on
            // Refined Storage's RSSavedData's system of saving first to a temp file
            // to reduce the odds of corruption if the user's computer crashes while the file is being written

            //Thanks pupnewster
            File tempFile = file.toPath().getParent().resolve(file.getName() + ".tmp").toFile();
            super.save(tempFile, provider);
            if (file.exists() && !file.delete()) {
                Machina.LOGGER.error("Failed to delete " + file.getName());
            }
            if (!tempFile.renameTo(file)) {
                Machina.LOGGER.error("Failed to rename " + tempFile.getName());
            }
        }
    }
}

