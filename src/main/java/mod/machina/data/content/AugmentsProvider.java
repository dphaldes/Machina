package mod.machina.data.content;

import mod.machina.Machina;
import mod.machina.api.augment.Augment;
import mod.machina.api.augment.Augments;
import mod.machina.api.augment.Trait;
import mod.machina.api.augment.trait.AttributeTrait;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.ArrayList;
import java.util.List;

public class AugmentsProvider {

    public static void bootstrap(BootstrapContext<Augment> context) {
        context.register(Augments.SPEED, new Builder("Augment Speed")
                .attribute(Attributes.MOVEMENT_SPEED, new AttributeModifier(Machina.prefix("speed"), 2, AttributeModifier.Operation.ADD_MULTIPLIED_BASE))
                .build());
    }

    private static class Builder {
        private final Component name;
        private final List<Trait> traits = new ArrayList<>();
        private HolderSet<Augment> exclusiveSet = HolderSet.direct();

        public Builder(String name) {
            this.name = Component.literal(name);
        }

        public Builder exclusiveWith(HolderSet<Augment> exclusiveSet) {
            this.exclusiveSet = exclusiveSet;
            return this;
        }

        public Builder attribute(Holder<Attribute> attribute, AttributeModifier modifier) {
            this.traits.add(new AttributeTrait(attribute, modifier));
            return this;
        }

        public Augment build() {
            return new Augment(name, traits, exclusiveSet);
        }

    }
}
