package com.mystchonky.machina.common.blockentity;

import com.mystchonky.machina.common.recipe.RiftRecipe;
import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import com.mystchonky.machina.common.registrar.RecipeRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RiftBlockEntity extends BlockEntity {

    private static final String TIMER = "timer";
    private int timer = 0;

    public RiftBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistrar.RIFT.get(), pos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RiftBlockEntity rift) {
        rift.timer += 1;

        // 5 minutes
        if (rift.timer >= 20 * 60 * 5) {
            level.removeBlock(rift.getBlockPos(), false);
            level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS,
                    0.5F, level.random.nextFloat() * 0.4F + 0.8F);
        }
    }

    public void tryCraft(ItemEntity item, Level level) {
        var stack = item.getItem();
        var recipeInput = new RiftRecipe.Input(stack);
        var recipe = level.getRecipeManager().getRecipeFor(RecipeRegistrar.Types.RIFT.get(), recipeInput, level);
        if (recipe.isPresent()) {
            var result = recipe.get().value().assemble(recipeInput, level.registryAccess());

            stack.shrink(1);
            var position = getBlockPos().getCenter();
            level.addFreshEntity(new ItemEntity(level, position.x, position.y, position.z, result));
            level.playSound(null, position.x, position.y, position.z, SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt(TIMER, timer);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        timer = tag.getInt(TIMER);
    }
}
