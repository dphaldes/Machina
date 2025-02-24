package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.EnchantmentTrait;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantments;

public class FrostWalkerGear extends Gear {

    public FrostWalkerGear() {
        super("frost_walker");

        addTrait(new EnchantmentTrait(Enchantments.FROST_WALKER, 2, EquipmentSlot.FEET));
    }

    @Override
    public String displayName() {
        return "Frost Walker";
    }
}
