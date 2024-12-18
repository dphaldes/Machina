package com.mystchonky.machina.mixin.perk;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mystchonky.machina.common.perk.PerkLibrary;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ElytraLayer.class)
public abstract class ElytraLayerMixin {

    @ModifyReturnValue(method = "shouldRender", at = @At("RETURN"))
    private boolean renderElytra(boolean original, ItemStack stack, LivingEntity entity) {
        return original || PerkLibrary.hasPerk(entity, PerkLibrary.GLIDE);
    }

}
