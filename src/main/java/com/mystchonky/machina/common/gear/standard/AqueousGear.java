package com.mystchonky.machina.common.gear.standard;

import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.api.gear.trait.EnchantmentTrait;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantments;

public class AqueousGear extends Gear {

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
