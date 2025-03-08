package mod.machina.common.arsenal;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mod.machina.api.gear.Gear;
import mod.machina.api.perk.Perk;
import mod.machina.common.util.SizedList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Arsenal {

    private SizedList<Gear> equipped;
    private List<Gear> unlocked;
    private List<Perk> perks;

    public static final Codec<Arsenal> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Equipped.CODEC.orElse(Equipped.create()).fieldOf("equipped").forGetter(Arsenal::equipped),
                    Unlocked.CODEC.orElse(Unlocked.create()).fieldOf("unlocked").forGetter(Arsenal::unlocked),
                    Perks.CODEC.orElse(Perks.create()).fieldOf("perks").forGetter(Arsenal::perks)
            ).apply(instance, Arsenal::new)
    );

    public Arsenal(SizedList<Gear> equipped, List<Gear> unlocked, List<Perk> perks) {
        this.equipped = equipped;
        this.unlocked = unlocked;
        this.perks = perks;
    }

    public Arsenal() {
        this.equipped = Equipped.create();
        this.unlocked = Unlocked.create();
        this.perks = Perks.create();
    }

    public SizedList<Gear> equipped() {
        return equipped;
    }

    public void setEquipped(SizedList<Gear> equipped) {
        this.equipped = equipped;
    }

    public List<Gear> unlocked() {
        return unlocked;
    }

    public void setUnlocked(List<Gear> unlocked) {
        this.unlocked = unlocked;
    }

    public List<Perk> perks() {
        return perks;
    }

    public void setPerks(List<Perk> perks) {
        this.perks = perks;
    }

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

    private static class Perks {

        public static final Codec<List<Perk>> CODEC = Perk.CODEC.listOf();

        public static List<Perk> create() {
            return List.of();
        }
    }


}
