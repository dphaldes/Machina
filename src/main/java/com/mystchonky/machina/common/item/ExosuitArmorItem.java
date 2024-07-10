package com.mystchonky.machina.common.item;

import com.mystchonky.machina.common.armament.perk.PerkLibrary;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ExosuitArmorItem extends ArmorItem {
    public ExosuitArmorItem(Type type) {
        super(ArmorMaterials.IRON, type, new Properties().stacksTo(1));
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
}
