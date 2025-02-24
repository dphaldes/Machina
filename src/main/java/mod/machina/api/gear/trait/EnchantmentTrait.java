package mod.machina.api.gear.trait;

import mod.machina.common.item.VoidArmorItem;
import mod.machina.common.registrar.LangRegistrar;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.CommonComponents;
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
        if (stack.getItem() instanceof VoidArmorItem) {
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
        var registry = Minecraft.getInstance().level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        var enchant = registry.getHolderOrThrow(enchantment()).value();
        var component = enchant.description().copy();

        if (level != 1 || enchant.getMaxLevel() != 1) {
            component.append(CommonComponents.SPACE).append(Component.translatable("enchantment.level." + level));
        }

        tooltip.add(Component.translatable(LangRegistrar.ENCHANT.key(), component)
                .withStyle(ChatFormatting.LIGHT_PURPLE));
    }
}
