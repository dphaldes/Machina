package com.mystchonky.machina.mixin.perk;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentMixin {

    @ModifyReturnValue(method = "hasAquaAffinity", at = @At("RETURN"))
    private static boolean wrapHasAquaEffinity(boolean original, LivingEntity entity) {
        return original || PerkLibrary.hasPerk(entity, PerkLibrary.AQUA_AFFINITY);
    }

    @ModifyReturnValue(method = "hasFrostWalker", at = @At("RETURN"))
    private static boolean wrapHasFrostWalker(boolean original, LivingEntity entity) {
        return original || PerkLibrary.hasPerk(entity, PerkLibrary.FROST_WALKER);
    }

    @ModifyReturnValue(method = "getDepthStrider", at = @At("RETURN"))
    private static int wrapHasDepthStrider(int original, LivingEntity entity) {
        return original <= 0 ? (PerkLibrary.hasPerk(entity, PerkLibrary.DEPTH_STRIDER) ? 2 : original) : original;
    }

}
