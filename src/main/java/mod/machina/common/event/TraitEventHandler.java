package mod.machina.common.event;

import mod.machina.common.arsenal.ArsenalManager;
import mod.machina.common.arsenal.PerkLibrary;
import net.minecraft.tags.DamageTypeTags;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class TraitEventHandler {

    public static void damageEvent(LivingIncomingDamageEvent event) {
        var damageSource = event.getSource();
        var entity = event.getEntity();
        if (damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY))
            return;
        float multiplier = 1;

        if (ArsenalManager.hasPerk(entity, PerkLibrary.PROTECTION)) {
            multiplier *= 0.5f; // reduce 50%
        }

        if (damageSource.is(DamageTypeTags.IS_FIRE) && ArsenalManager.hasPerk(entity, PerkLibrary.PROTECTION_FIRE)) {
            multiplier *= 0.8f; // reduce by 20%
        }

        if (damageSource.is(DamageTypeTags.IS_EXPLOSION) && ArsenalManager.hasPerk(entity, PerkLibrary.PROTECTION_BLAST)) {
            multiplier *= 0.8f; // reduce by 20%
        }

        if (damageSource.is(DamageTypeTags.IS_DROWNING) && ArsenalManager.hasPerk(entity, PerkLibrary.PROTECTION_WATER)) {
            multiplier *= 0.8f; // reduce by 20%
        }
        if (damageSource.is(DamageTypeTags.IS_FREEZING) && ArsenalManager.hasPerk(entity, PerkLibrary.PROTECTION_FREEZE)) {
            multiplier *= 0.8f; // reduce by 20%
        }

        event.setAmount(event.getAmount() * multiplier);
    }

    //disabled
//    public static void enchantmentEvent(GetEnchantmentLevelEvent event) {
//        var stack = event.getStack();
//        if (stack.getItem() instanceof VoidArmorItem) {
//            var data = stack.get(DataComponentRegistrar.ARMOR_TRAITS);
//            if (data == null) return;
//
//            var mutable = event.getEnchantments();
//            var lookup = event.getLookup();
//            for (var pair : data.enchantments()) {
//                var holder = lookup.get(pair.enchant());
//                if (holder.isEmpty()) continue;
//                var enchant = holder.get();
//
//                var level = mutable.getLevel(enchant);
//                mutable.upgrade(enchant, level + pair.level());
//            }
//        }
//    }

}
