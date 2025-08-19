package mod.machina.common.content.armor;

import net.minecraft.tags.DamageTypeTags;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class TraitEventHandler {


    public static void damageEvent(LivingIncomingDamageEvent event) {
        var damageSource = event.getSource();
        var entity = event.getEntity();
        if (damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY))
            return;
        float multiplier = 1;

        event.setAmount(event.getAmount() * multiplier);
    }


}
