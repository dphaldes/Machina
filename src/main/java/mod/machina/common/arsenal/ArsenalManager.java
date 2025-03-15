package mod.machina.common.arsenal;

import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.EnchantmentLevel;
import mod.machina.api.perk.Perk;
import mod.machina.common.item.VoidArmorItem;
import mod.machina.common.registrar.AttachmentRegistrar;
import mod.machina.common.util.SizedList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.StreamSupport;

public class ArsenalManager {

    public static boolean active(Player player) {
        return StreamSupport.stream(player.getArmorSlots().spliterator(), false)
                .allMatch(itemStack -> itemStack.getItem() instanceof VoidArmorItem);
    }

    public static void activate(Player player) {
        //perks
        var perks = new ArrayList<Perk>();
        var enchants = new HashMap<EquipmentSlot, List<EnchantmentLevel>>();

        // activate equipped
        getArsenal(player).equipped().forEach(gear -> gear.onEquip(player, perks, enchants));

        enchants.forEach((slot, slotEnchants) -> {
            var stack = player.getItemBySlot(slot);
            if (stack.getItem() instanceof VoidArmorItem armor) {
                armor.applyTraitEffects(stack, slotEnchants);
            }
        });

        setPerks(player, perks);
    }

    public static void deactivate(Player player) {
        // deactivate equipped
        getArsenal(player).equipped().forEach(gear -> gear.onRemove(player));

        player.getArmorSlots().forEach(stack -> {
            if (stack.getItem() instanceof VoidArmorItem armor) {
                armor.removeTraitEffects(stack);
            }
        });

        // remove perks
        setPerks(player, List.of());
    }

    public static Arsenal getArsenal(Player player) {
        return player.getData(AttachmentRegistrar.ARSENAL);
    }

    public static void setEquippedGears(Player player, SizedList<Gear> gears) {
        deactivate(player);
        var arsenal = getArsenal(player);
        arsenal.setEquipped(gears);
        player.setData(AttachmentRegistrar.ARSENAL, arsenal);
        activate(player);
    }

    public static void setUnlockedGears(Player player, List<Gear> gears) {
        gears.remove(Gear.EMPTY);
        var arsenal = getArsenal(player);
        arsenal.setUnlocked(gears);
        player.setData(AttachmentRegistrar.ARSENAL, arsenal);
    }

    public static void setPerks(Player player, List<Perk> perks) {
        var arsenal = getArsenal(player);
        arsenal.setPerks(perks);
        player.setData(AttachmentRegistrar.ARSENAL, arsenal);
    }

    public static boolean hasPerk(LivingEntity entity, Perk perk) {
        if (entity instanceof Player player) {
            return getArsenal(player).perks().contains(perk);
        }
        return false;
    }
}
