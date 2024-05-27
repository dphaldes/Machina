package com.mystchonky.machina.mixin.perk;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mystchonky.machina.common.armament.perk.PerkLibrary;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @ModifyExpressionValue(method = "onChangedBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getEnchantmentLevel(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/entity/LivingEntity;)I"))
    public int wrapEnchantmentLevel(int original) {
        return original <= 0 ? (PerkLibrary.hasPerk(((LivingEntity) (Object) this), PerkLibrary.FROST_WALKER) ? 2 : original) : original;
    }

}
