package mod.machina.common.content.armor;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.machina.api.augment.Augment;
import mod.machina.api.augment.Perk;
import mod.machina.api.augment.trait.EnchantmentLevel;
import mod.machina.api.augment.trait.PerkTrait;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Arsenal {

    // Augments
    private List<Holder<Augment>> equipped;
    private List<Holder<Augment>> unlocked;

    // Stats
    private boolean active = false;
    private List<Perk> perks = List.of();
    private Map<EquipmentSlot, List<EnchantmentLevel>> enchantments = new HashMap<>();

    public Arsenal(List<Holder<Augment>> equipped, List<Holder<Augment>> unlocked) {
        this.equipped = equipped;
        this.unlocked = unlocked;
    }

    public Arsenal() {
        this(Equipped.create(), Unlocked.create());
    }

    public List<Holder<Augment>> equipped() {
        return equipped;
    }

    public void setEquipped(Player player, List<Holder<Augment>> equipped) {
        //remove old effects
        this.equipped.forEach(holder -> holder.value().onRemove(player));

        this.equipped = equipped;
    }

    public List<Holder<Augment>> unlocked() {
        return unlocked;
    }

    public void setUnlocked(List<Holder<Augment>> unlocked) {
        this.unlocked = unlocked;
    }

    public boolean active() {
        return active;
    }

    //TODO: use HOLDERS
    public List<Perk> perks() {
        return perks;
    }

    public Map<EquipmentSlot, List<EnchantmentLevel>> enchantments() {
        return enchantments;
    }

    public void respec(Player player) {

        // check if active condition is met
        var active = shouldActivate(player);

        var perks = new ArrayList<Perk>();
        var enchants = new HashMap<EquipmentSlot, List<EnchantmentLevel>>();

        for (var holder : equipped) {
            var augment = holder.value();
            if (active) {
                // activate
                augment.onEquip(player);
                for (var trait : augment.traits()) {
                    if (trait instanceof PerkTrait(Holder<Perk> perk, int level)) {
                        perks.add(perk.value()); // TODO: add level
                    }
//                    if (trait instanceof EnchantmentTrait(EnchantmentLevel enchantment, EquipmentSlot slot)) {
//                        var list = enchants.computeIfAbsent(slot, key -> new ArrayList<>());
//                        list.add(enchantment);
//                    }
                }

//                for (var entry : enchants.entrySet()) {
//                    var stack = player.getItemBySlot(entry.getKey());
//                    if (stack != ItemStack.EMPTY) {
//                        stack.set(DataComponentRegistrar.ARMOR_TRAITS, new ArmorTraits(entry.getValue()));
//                    }
//                }
            } else {
                // remove stats and leave perks empty
                augment.onRemove(player);
            }
        }

        this.active = active;
        this.perks = perks;
        this.enchantments = enchants;
    }

    public static boolean shouldActivate(Player player) {
        var active = true;
        for (var stack : player.getArmorSlots()) {
            active = active && stack.getItem() instanceof VoidArmorItem;
        }
        return active;
    }

    public static final Codec<Arsenal> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Equipped.CODEC.orElse(Equipped.create()).fieldOf("equipped").forGetter(Arsenal::equipped),
                    Unlocked.CODEC.orElse(Unlocked.create()).fieldOf("unlocked").forGetter(Arsenal::unlocked)
            ).apply(instance, Arsenal::new)
    );

    public static class Equipped {
        private static final int MAX_EQUIP = 8;

        private static final Codec<List<Holder<Augment>>> CODEC = Augment.HOLDER_CODEC
                .listOf().xmap(ArrayList::new, Function.identity());

        public static final StreamCodec<RegistryFriendlyByteBuf, List<Holder<Augment>>> STREAM_CODEC = Augment.HOLDER_STREAM_CODEC
                .apply(ByteBufCodecs.list(MAX_EQUIP))
                .map(ArrayList::new, Function.identity());

        private static List<Holder<Augment>> create() {
            return new ArrayList<>();
        }
    }

    public static class Unlocked {

        private static final Codec<List<Holder<Augment>>> CODEC =
                Augment.HOLDER_CODEC.listOf().xmap(ArrayList::new, List::copyOf);

        public static final StreamCodec<RegistryFriendlyByteBuf, List<Holder<Augment>>> STREAM_CODEC =
                Augment.HOLDER_STREAM_CODEC.apply(ByteBufCodecs.list()).map(ArrayList::new, List::copyOf);

        private static List<Holder<Augment>> create() {
            return new ArrayList<>();
        }

    }

}
