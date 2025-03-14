package mod.machina.common.item;

import mod.machina.api.gear.trait.EnchantmentLevel;
import mod.machina.common.arsenal.ArsenalManager;
import mod.machina.common.item.components.PerkEnchantments;
import mod.machina.common.perk.PerkLibrary;
import mod.machina.common.registrar.DataComponentRegistrar;
import mod.machina.common.registrar.MaterialRegistrar;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
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
    public boolean isFoil(ItemStack stack) {
        return stack.isEnchanted() || stack.has(DataComponentRegistrar.PERK_ENCHANTMENTS);
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!(entity instanceof Player player))
            return false;

        return ArsenalManager.hasPerk(player, PerkLibrary.GLIDE);
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        if (!(entity instanceof Player player))
            return false;

        return ArsenalManager.hasPerk(player, PerkLibrary.GLIDE);
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return ArsenalManager.hasPerk(wearer, PerkLibrary.GILDED);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player) {
            if (!ArsenalManager.active(player)) {
                removeEnchantmentTraits(stack);
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    public void applyEnchantmentTraits(ItemStack stack, List<EnchantmentLevel> enchants) {
        stack.set(DataComponentRegistrar.PERK_ENCHANTMENTS, new PerkEnchantments(enchants));
    }

    public void removeEnchantmentTraits(ItemStack stack) {
        stack.remove(DataComponentRegistrar.PERK_ENCHANTMENTS);
    }

}
