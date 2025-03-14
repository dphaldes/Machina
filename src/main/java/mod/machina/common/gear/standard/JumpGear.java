package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.AttributeTrait;
import mod.machina.api.gear.trait.EnchantmentTrait;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.Enchantments;

public class JumpGear extends Gear {

    public JumpGear() {
        super("jump");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.JUMP_STRENGTH, 0.2, AttributeModifier.Operation.ADD_VALUE);
        addTrait(attribute);

        addTrait(EnchantmentTrait.of(Enchantments.FEATHER_FALLING, 3, EquipmentSlot.FEET));
    }

    @Override
    public String displayName() {
        return "Jump Boost";
    }
}
