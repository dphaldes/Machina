package com.mystchonky.machina.common.armament.gear.standard;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.api.armament.traits.EnchantmentTrait;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantments;

public class AqueousGear extends AbstractGear {

    public AqueousGear() {
        super("aqueous");

        addTrait(new EnchantmentTrait(Enchantments.AQUA_AFFINITY, 1, EquipmentSlot.HEAD));
        addTrait(new EnchantmentTrait(Enchantments.DEPTH_STRIDER, 2, EquipmentSlot.FEET));
        addTrait(new EnchantmentTrait(Enchantments.RESPIRATION, 2, EquipmentSlot.HEAD));
    }

    @Override
    public String displayName() {
        return "Aqueous Gear";
    }
}
