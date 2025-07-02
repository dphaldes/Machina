package mod.machina.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import mod.machina.common.armor.ArsenalManager;
import mod.machina.common.registrar.PerkRegistrar;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ElytraLayer.class)
public abstract class ElytraLayerMixin {

    @ModifyReturnValue(method = "shouldRender", at = @At("RETURN"))
    private boolean renderElytra(boolean original, ItemStack stack, LivingEntity entity) {
        return original || ArsenalManager.hasPerk(entity, PerkRegistrar.GLIDE.get());
    }

}
