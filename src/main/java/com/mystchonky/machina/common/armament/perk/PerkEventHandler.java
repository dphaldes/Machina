package com.mystchonky.machina.common.armament.perk;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.item.ExosuitArmorItem;
import com.mystchonky.machina.common.registrar.DataComponentRegistrar;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.util.thread.EffectiveSide;
import net.neoforged.neoforge.event.enchanting.GetEnchantmentLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.UUID;

@EventBusSubscriber(modid = Machina.MODID)
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

        if (damageSource.is(DamageTypeTags.IS_FALL) && PerkLibrary.hasPerk(entity, PerkLibrary.PROTECTION_FALL)) {
            event.setAmount(event.getAmount() * 0.8f); // reduce by 20%
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

    @SubscribeEvent
    public static void enchantmentEvent(GetEnchantmentLevelEvent event) {
        if (EffectiveSide.get().isClient()) return;

        ItemStack stack = event.getStack();
        UUID playerID = stack.get(DataComponentRegistrar.PLAYER_UUID);
        if (playerID == null || !(stack.getItem() instanceof ExosuitArmorItem)) return;

        Player player = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayer(playerID);
        if (player == null) return;

        ItemEnchantments.Mutable enchants = event.getEnchantments();
        PerkLibrary.applyPerkEnchants(player, enchants);
    }
}
