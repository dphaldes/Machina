package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.EnchantmentTrait;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantments;

public class FrostWalkerGear extends AbstractGear {

    public FrostWalkerGear() {
        super("frost_walker");

        addTrait(new EnchantmentTrait(Enchantments.FROST_WALKER, 2, EquipmentSlot.FEET));
    }

    @Override
    public String displayName() {
        return "Frost Walker";
    }
}
