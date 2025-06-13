package mod.machina.common.arsenal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.machina.api.gear.Gear;
import mod.machina.api.gear.trait.EnchantmentLevel;
import mod.machina.api.gear.trait.EnchantmentTrait;
import mod.machina.api.gear.trait.PerkTrait;
import mod.machina.api.perk.Perk;
import mod.machina.common.item.VoidArmorItem;
import mod.machina.common.util.SizedList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Arsenal {

    // Gears
    private SizedList<Gear> equipped;
    private List<Gear> unlocked;

    // Stats
    private boolean active = false;
    private List<Perk> perks = List.of();
    private Map<EquipmentSlot, List<EnchantmentLevel>> enchantments = new HashMap<>();

    public Arsenal(SizedList<Gear> equipped, List<Gear> unlocked) {
        this.equipped = equipped;
        this.unlocked = unlocked;
        this.unlocked.remove(Gear.EMPTY);
    }

    public Arsenal() {
        this(Equipped.create(), Unlocked.create());
    }

    public SizedList<Gear> equipped() {
        return equipped;
    }

    public void setEquipped(Player player, SizedList<Gear> equipped) {
        //remove old effects
        this.equipped.forEach(gear -> gear.onRemove(player));

        this.equipped = equipped;
    }

    public List<Gear> unlocked() {
        return unlocked;
    }

    public void setUnlocked(List<Gear> unlocked) {
        this.unlocked = unlocked;
    }

    public boolean active() {
        return active;
    }

    public List<Perk> perks() {
        return perks;
    }

    public Map<EquipmentSlot, List<EnchantmentLevel>> enchantments() {
        return enchantments;
    }

    public void respec(Player player) {

        // check if active condition is met
        var active = true;
        for (var stack : player.getArmorSlots()) {
            active = active && stack.getItem() instanceof VoidArmorItem;
        }

        var perks = new ArrayList<Perk>();
        var enchants = new HashMap<EquipmentSlot, List<EnchantmentLevel>>();

        for (var gear : equipped) {
            if (active) {
                // activate
                gear.onEquip(player);
                for (var trait : gear.getTraits()) {
                    if (trait instanceof PerkTrait(Perk perk)) {
                        perks.add(perk);
                    }
                    if (trait instanceof EnchantmentTrait(EnchantmentLevel enchantment, EquipmentSlot slot)) {
                        var list = enchants.computeIfAbsent(slot, key -> new ArrayList<>());
                        list.add(enchantment);
                    }
                }

//                for (var entry : enchants.entrySet()) {
//                    var stack = player.getItemBySlot(entry.getKey());
//                    if (stack != ItemStack.EMPTY) {
//                        stack.set(DataComponentRegistrar.ARMOR_TRAITS, new ArmorTraits(entry.getValue()));
//                    }
//                }
            } else {
                // remove stats and leave perks empty
                gear.onRemove(player);
            }
        }

        this.active = active;
        this.perks = perks;
        this.enchantments = enchants;
    }

    public static final Codec<Arsenal> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Equipped.CODEC.orElse(Equipped.create()).fieldOf("equipped").forGetter(Arsenal::equipped),
                    Unlocked.CODEC.orElse(Unlocked.create()).fieldOf("unlocked").forGetter(Arsenal::unlocked)
            ).apply(instance, Arsenal::new)
    );

    public static class Equipped {
        private static final int MAX_EQUIP = 8;

        private static final Codec<SizedList<Gear>> CODEC = Gear.CODEC
                .listOf(MAX_EQUIP, MAX_EQUIP)
                .xmap(SizedList::new, Function.identity());

        public static final StreamCodec<RegistryFriendlyByteBuf, SizedList<Gear>> STREAM_CODEC = Gear.STREAM_CODEC
                .apply(ByteBufCodecs.list(MAX_EQUIP))
                .map(SizedList::new, Function.identity());

        private static SizedList<Gear> create() {
            List<Gear> slots = new ArrayList<>(Collections.nCopies(MAX_EQUIP, Gear.EMPTY));
            return new SizedList<>(slots);
        }
    }

    public static class Unlocked {

        private static final Codec<List<Gear>> CODEC =
                Gear.CODEC.listOf().xmap(ArrayList::new, List::copyOf);

        public static final StreamCodec<RegistryFriendlyByteBuf, List<Gear>> STREAM_CODEC =
                Gear.STREAM_CODEC.apply(ByteBufCodecs.list()).map(ArrayList::new, List::copyOf);

        private static List<Gear> create() {
            return new ArrayList<>();
        }

    }

}
