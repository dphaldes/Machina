package mod.machina.common.perk;

import mod.machina.Machina;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = Machina.ID)
public class PerkEventHandler {

    @SubscribeEvent
    public static void damageEvent(LivingIncomingDamageEvent event) {
        var damageSource = event.getSource();
        var entity = event.getEntity();
        if (damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY))
            return;

        if (PerkLibrary.hasPerk(entity, PerkLibrary.PROTECTION)) {
            event.setAmount(event.getAmount() * 0.5f); // reduce 50%
        }

        if (damageSource.is(DamageTypeTags.IS_FIRE) && PerkLibrary.hasPerk(entity, PerkLibrary.PROTECTION_FIRE)) {
            event.setAmount(event.getAmount() * 0.8f); // reduce by 20%
        }

        if (damageSource.is(DamageTypeTags.IS_EXPLOSION) && PerkLibrary.hasPerk(entity, PerkLibrary.PROTECTION_BLAST)) {
            event.setAmount(event.getAmount() * 0.8f); // reduce by 20%
        }

        if (damageSource.is(DamageTypeTags.IS_DROWNING) && PerkLibrary.hasPerk(entity, PerkLibrary.PROTECTION_WATER)) {
            event.setAmount(event.getAmount() * 0.8f); // reduce by 20%
        }
        if (damageSource.is(DamageTypeTags.IS_FREEZING) && PerkLibrary.hasPerk(entity, PerkLibrary.PROTECTION_FREEZE)) {
            event.setAmount(event.getAmount() * 0.8f); // reduce by 20%
        }
    }

}
