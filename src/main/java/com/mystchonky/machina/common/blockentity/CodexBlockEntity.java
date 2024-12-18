package com.mystchonky.machina.common.blockentity;

import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CodexBlockEntity extends BlockEntity {
    public int time;
    public float flip;
    public float oFlip;
    public float flipT;
    public float flipA;
    public float open;
    public float oOpen;
    private static final RandomSource RANDOM = RandomSource.create();

    public CodexBlockEntity(BlockPos pos, BlockState blockState) {
        super(BlockEntityRegistrar.CODEX.get(), pos, blockState);
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, CodexBlockEntity codex) {
        codex.oOpen = codex.open;
        Player player = level.getNearestPlayer((double) pos.getX() + 0.5, (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5, 3.0, false);
        if (player != null) {
            codex.open += 0.1F;
            if (codex.open < 0.5F || RANDOM.nextInt(40) == 0) {
                float f1 = codex.flipT;

                do {
                    codex.flipT = codex.flipT + (float) (RANDOM.nextInt(4) - RANDOM.nextInt(4));
                } while (f1 == codex.flipT);
            }
        } else {
            codex.open -= 0.1F;
        }

        codex.open = Mth.clamp(codex.open, 0.0F, 1.2F);
        codex.time++;
        codex.oFlip = codex.flip;
        float f = (codex.flipT - codex.flip) * 0.4F;
        f = Mth.clamp(f, -0.2F, 0.2F);
        codex.flipA = codex.flipA + (f - codex.flipA) * 0.9F;
        codex.flip = codex.flip + codex.flipA;
    }
}
