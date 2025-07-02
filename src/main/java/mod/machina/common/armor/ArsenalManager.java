package mod.machina.common.armor;

import mod.machina.api.augment.Augment;
import mod.machina.api.augment.Perk;
import mod.machina.common.registrar.AttachmentRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class ArsenalManager {

    public static boolean active(Player player) {
        var arsenal = getArsenal(player);
        return arsenal.active();
    }

    public static void respec(Player player) {
        var arsenal = getArsenal(player);
        arsenal.respec(player);
    }

    public static Arsenal getArsenal(Player player) {
        return player.getData(AttachmentRegistrar.ARSENAL);
    }

    public static void setEquippedGears(Player player, List<Holder<Augment>> augments) {
        var arsenal = getArsenal(player);
        arsenal.setEquipped(player, augments);
        updateArsenalData(player, arsenal);
        arsenal.respec(player);
    }

    public static void setUnlockedGears(Player player, List<Holder<Augment>> gears) {
        var arsenal = getArsenal(player);
        arsenal.setUnlocked(gears);
        updateArsenalData(player, arsenal);
        arsenal.respec(player);
    }

    public static boolean hasPerk(LivingEntity entity, Perk perk) {
        if (entity instanceof Player player) {
            return getArsenal(player).perks().contains(perk);
        }
        return false;
    }

    private static void updateArsenalData(Player player, Arsenal arsenal) {
        player.setData(AttachmentRegistrar.ARSENAL, arsenal);
    }

}
