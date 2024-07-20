package com.mystchonky.machina.api.armament.traits;

import com.mystchonky.machina.common.item.ExosuitArmorItem;
import com.mystchonky.machina.common.registrar.LangRegistrar;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.List;

public record EnchantmentTrait(ResourceKey<Enchantment> enchantment, int level, EquipmentSlot slot) implements Trait {

    @Override
    public void onEquip(Player player) {
        var registry = player.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        ItemStack stack = player.getItemBySlot(slot());
        if (stack.getItem() instanceof ExosuitArmorItem) {
            stack.enchant(registry.getHolderOrThrow(enchantment()), level());
        }
    }

    @Override
    public void onRemove(Player player) {
        ItemStack stack = player.getItemBySlot(slot());
        EnchantmentHelper.updateEnchantments(stack, enchants -> enchants.removeIf(holder -> holder.is(enchantment())));
    }

    @Override
    public void getTooltip(List<Component> tooltip) {
        tooltip.add(Component.translatable(LangRegistrar.PERK.key(), enchantment().location().toString()));
    }
}
