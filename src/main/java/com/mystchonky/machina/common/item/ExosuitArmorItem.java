package com.mystchonky.machina.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class ExosuitArmorItem extends ArmorItem {
    public ExosuitArmorItem(Type type) {
        super(ArmorMaterials.IRON, type, new Properties().stacksTo(1));
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return 0;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

}
