package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.EnchantmentTrait;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantments;

public class AqueousGear extends Gear {

    public AqueousGear() {
        super("aqueous");

        addTrait(EnchantmentTrait.of(Enchantments.AQUA_AFFINITY, 1, EquipmentSlot.HEAD));
        addTrait(EnchantmentTrait.of(Enchantments.DEPTH_STRIDER, 2, EquipmentSlot.FEET));
        addTrait(EnchantmentTrait.of(Enchantments.RESPIRATION, 2, EquipmentSlot.HEAD));
    }

    @Override
    public String displayName() {
        return "Aqueous";
    }
}
