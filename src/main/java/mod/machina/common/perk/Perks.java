package mod.machina.common.perk;

import com.mojang.serialization.Codec;
import mod.machina.api.perk.Perk;
import mod.machina.common.registrar.AttachmentRegistrar;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public final class Perks {

    public static final Codec<List<Perk>> CODEC = Perk.CODEC.listOf();

    public static List<Perk> create() {
        return List.of();
    }

    public static List<Perk> get(Player player) {
        return player.getData(AttachmentRegistrar.PERKS);
    }

    public static void set(Player player, List<Perk> perks) {
        player.setData(AttachmentRegistrar.PERKS, perks);
    }
}
