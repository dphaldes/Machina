package mod.machina.common.gear.standard;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.AttributeTrait;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AttackGear extends Gear {

    public AttackGear() {
        super("attack");

        var attribute = new AttributeTrait(this);
        attribute.addModifier(Attributes.ATTACK_DAMAGE, 5, AttributeModifier.Operation.ADD_VALUE);
        attribute.addModifier(Attributes.ATTACK_SPEED, 0.4, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        addTrait(attribute);
    }

    @Override
    public String displayName() {
        return "Attack";
    }
}
