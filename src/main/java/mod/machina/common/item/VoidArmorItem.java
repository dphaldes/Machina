package mod.machina.common.item;

import mod.machina.common.perk.PerkLibrary;
import mod.machina.common.registrar.MaterialRegistrar;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
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

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return false;
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!(entity instanceof Player player))
            return false;

        return PerkLibrary.hasPerk(player, PerkLibrary.GLIDE);
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        if (!(entity instanceof Player player))
            return false;

        return PerkLibrary.hasPerk(player, PerkLibrary.GLIDE);
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return PerkLibrary.hasPerk(wearer, PerkLibrary.GILDED);
    }

    // if not equipped, remove all enchants
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (level.getGameTime() % 20 == 0 && entity instanceof Player && stack.isEnchanted()) {
            if (slotId < 36 || slotId > 39) {
                EnchantmentHelper.updateEnchantments(stack, enchants -> enchants.removeIf(holder -> true));
            }
        }
    }
}
