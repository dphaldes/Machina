package com.mystchonky.machina.common.block;

import com.mystchonky.machina.client.screen.ScreenManager;
import com.mystchonky.machina.common.blockentity.RiftBlockEntity;
import com.mystchonky.machina.common.level.RiftShape;
import com.mystchonky.machina.common.registrar.BlockEntityRegistrar;
import com.mystchonky.machina.common.registrar.ItemRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class RiftBlock extends Block implements EntityBlock {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AXIS_AABB = Block.box(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(6.0, 0.0, 0.0, 10.0, 16.0, 16.0);

    public RiftBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(AXIS) == Direction.Axis.Z)
            return Z_AXIS_AABB;
        return X_AXIS_AABB;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        Direction.Axis facingAxis = facing.getAxis();
        Direction.Axis stateAxis = state.getValue(AXIS);
        boolean flag = stateAxis != facingAxis && facingAxis.isHorizontal();
        return !flag && !facingState.is(this) && !new RiftShape(level, currentPos, stateAxis).isComplete()
                ? destroyBlock(level, currentPos)
                : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        double x = (double) pos.getX() + random.nextDouble();
        double y = (double) pos.getY() + random.nextDouble();
        double z = (double) pos.getZ() + random.nextDouble();
        double xSpeed = ((double) random.nextFloat() - 0.5) * 0.5;
        double ySpeed = ((double) random.nextFloat() - 0.5) * 0.5;
        double zSpeed = ((double) random.nextFloat() - 0.5) * 0.5;
        int j = random.nextInt(2) * 2 - 1;
        if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
            x = (double) pos.getX() + 0.5 + 0.25 * (double) j;
            xSpeed = random.nextFloat() * 2.0F * (float) j;
        } else {
            z = (double) pos.getZ() + 0.5 + 0.25 * (double) j;
            zSpeed = random.nextFloat() * 2.0F * (float) j;
        }

        level.addParticle(ParticleTypes.ENCHANT, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rot) {
        return switch (rot) {
            case COUNTERCLOCKWISE_90, CLOCKWISE_90 -> switch (state.getValue(AXIS)) {
                case Z -> state.setValue(AXIS, Direction.Axis.X);
                case X -> state.setValue(AXIS, Direction.Axis.Z);
                default -> state;
            };
            default -> state;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntityRegistrar.RIFT_PORTAL.get().create(pos, state);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide)
            return ItemInteractionResult.CONSUME;

        if (stack.is(ItemRegistrar.CODEX) && level.getBlockEntity(pos) instanceof RiftBlockEntity rift) {
            var master = rift.getMasterPos();
            if (master != null) {
                ScreenManager.openCodexScreen(player, rift.getMasterPos());
            }
        }

        return ItemInteractionResult.SUCCESS;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (level.isClientSide)
            return;

        if (!(level.getBlockEntity(pos) instanceof RiftBlockEntity rift))
            return;

        if (entity instanceof ItemEntity item) {
            rift.tryConsumeStack(item);
        }

        if (entity instanceof Player player) {
            rift.tryUnlock(player);
        }
    }

    public BlockState destroyBlock(LevelAccessor world, BlockPos pos) {
        if (!world.isClientSide()) {
            BlockEntity entity = world.getBlockEntity(pos);
            if (entity instanceof RiftBlockEntity rift) {
                rift.refundConsumed();
            }
        }
        return Blocks.AIR.defaultBlockState();
    }

}
