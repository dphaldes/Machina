package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.AttributeTrait;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SmallGear extends Gear {

    public SmallGear() {
        super("small");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.SCALE, -0.6, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        attribute.addModifier(Attributes.MOVEMENT_SPEED, 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Small";
    }
}
