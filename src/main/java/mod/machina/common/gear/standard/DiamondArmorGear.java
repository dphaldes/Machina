package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.AttributeTrait;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class DiamondArmorGear extends Gear {

    public DiamondArmorGear() {
        super("diamond_armor");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.ARMOR, 5, AttributeModifier.Operation.ADD_VALUE);
        attribute.addModifier(Attributes.ARMOR_TOUGHNESS, 8, AttributeModifier.Operation.ADD_VALUE);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Brilliance";
    }
}
