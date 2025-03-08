package mod.machina.common.arsenal;

import com.mojang.serialization.Codec;
import mod.machina.api.gear.Gear;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.ArrayList;
import java.util.List;

public final class UnlockedGears {
    public static final Codec<ArrayList<Gear>> CODEC =
            Gear.CODEC.listOf().xmap(ArrayList::new, List::copyOf);

    public static final StreamCodec<RegistryFriendlyByteBuf, ArrayList<Gear>> STREAM_CODEC =
            Gear.STREAM_CODEC.apply(ByteBufCodecs.list()).map(ArrayList::new, List::copyOf);

    public static ArrayList<Gear> create() {
        return new ArrayList<>();
    }

}
