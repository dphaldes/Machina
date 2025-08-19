package mod.machina.common.content.nexus;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

interface INexusType<T extends IExtendedNexusData<T>> {
    T createExtendedNexusData(Level level, BlockPos pos);

//    default <K> K proxyCapability(BlockCapability<K, Void> cap, T extendedConduitData, Level level, BlockPos pos, Direction direction,
//                                  Optional<NexusIdentifier.IOState> state) {
//        return null;
//    }
}
