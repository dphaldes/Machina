package mod.machina.common.item;

import mod.machina.common.arsenal.ArsenalManager;
import mod.machina.common.registrar.MaterialRegistrar;
import mod.machina.common.registrar.PerkRegistrar;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class VoidArmorItem extends ArmorItem {
    public VoidArmorItem(Type type) {
        super(MaterialRegistrar.VOID_MATERIAL, type, new Properties().stacksTo(1));
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> onBroken) {
        return 0;
    }

//    @Override
//    public boolean isFoil(ItemStack stack) {
//        return stack.isEnchanted() || stack.has(DataComponentRegistrar.ARMOR_TRAITS);
//    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!(entity instanceof Player player))
            return false;

        return ArsenalManager.hasPerk(player, PerkRegistrar.GLIDE);
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        if (!(entity instanceof Player player))
            return false;

        return ArsenalManager.hasPerk(player, PerkRegistrar.GLIDE);
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return ArsenalManager.hasPerk(wearer, PerkRegistrar.GILDED);
    }

}
