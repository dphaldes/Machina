package com.mystchonky.machina.common.entity;

import com.mystchonky.machina.common.registrar.BlockRegistrar;
import com.mystchonky.machina.common.registrar.EntityRegistrar;
import com.mystchonky.machina.common.registrar.ItemRegistrar;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.phys.BlockHitResult;

public class ThrownRiftPearl extends ThrowableItemProjectile {
    public ThrownRiftPearl(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownRiftPearl(Level level, LivingEntity shooter) {
        super(EntityRegistrar.RIFT_PEARL.get(), shooter, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistrar.RIFT_PEARL.asItem();
    }

    @Override
    protected boolean canHitEntity(Entity target) {
        return false;
    }

    @Override
    public void tick() {
        Entity entity = this.getOwner();
        if (entity instanceof ServerPlayer && !entity.isAlive() && this.level().getGameRules().getBoolean(GameRules.RULE_ENDER_PEARLS_VANISH_ON_DEATH)) {
            this.discard();
        } else {
            super.tick();
        }
    }

    @Override
    public boolean canChangeDimensions(Level oldLevel, Level newLevel) {
        return false;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        var position = result.getBlockPos().relative(result.getDirection());


        for (int i = 0; i < 32; i++) {
            this.level()
                    .addParticle(
                            ParticleTypes.PORTAL,
                            this.getX(),
                            this.getY() + this.random.nextDouble() * 2.0,
                            this.getZ(),
                            this.random.nextGaussian(),
                            0.0,
                            this.random.nextGaussian()
                    );
        }

        this.level().setBlock(position, BlockRegistrar.RIFT.block().defaultBlockState().setValue(DirectionalBlock.FACING, result.getDirection()), 3);
        this.discard();
    }
}
