package mod.machina.common.block.blockentity;

import com.mojang.serialization.Codec;
import mod.machina.api.rune.Rune;
import mod.machina.api.rune.RuneProvider;
import mod.machina.common.registrar.BlockEntityRegistrar;
import mod.machina.common.registrar.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RuneProjectorBlockEntity extends BlockEntity implements RuneProvider {

    private Rune rune = Rune.EMPTY;

    public RuneProjectorBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistrar.RUNE_PROJECTOR.get(), pos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (rune != Rune.EMPTY) {
            Data.CODEC.encodeStart(NbtOps.INSTANCE, new Data(rune))
                    .ifSuccess(data -> tag.put("data", data));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        Data.CODEC.decode(NbtOps.INSTANCE, tag.getCompound("data"))
                .ifSuccess(pair -> this.rune = pair.getFirst().rune());
    }

    @Override
    public Rune getRune() {
        return rune;
    }

    private record Data(Rune rune) {
        public static Codec<Data> CODEC = Registries.RUNES.byNameCodec().orElse(Rune.EMPTY)
                .xmap(Data::new, Data::rune);
    }
}
