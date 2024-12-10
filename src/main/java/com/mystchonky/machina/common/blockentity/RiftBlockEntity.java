package com.mystchonky.machina.common.blockentity;

import com.mystchonky.machina.common.recipe.RiftRecipeInput;
import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import com.mystchonky.machina.common.registrar.RecipeRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RiftBlockEntity extends BlockEntity {


    public RiftBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistrar.RIFT.get(), pos, blockState);
    }

    public void tryCraft(ItemEntity item, Level level) {
        var stack = item.getItem();
        var recipeInput = new RiftRecipeInput(stack);
        var recipe = level.getRecipeManager().getRecipeFor(RecipeRegistrar.Types.RIFT.get(), recipeInput, level);
        if (recipe.isPresent()) {
            var result = recipe.get().value().assemble(recipeInput, level.registryAccess());

            stack.shrink(1);
            var position = getBlockPos().getCenter();
            level.addFreshEntity(new ItemEntity(level, position.x, position.y, position.z, result));
            level.playSound(null, position.x, position.y, position.z, SoundEvents.PLAYER_TELEPORT, SoundSource.PLAYERS);
        }
    }
}
