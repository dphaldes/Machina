package mod.machina.api.augment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import mod.machina.common.registrar.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.function.Function;

public interface Trait {

    default void onEquip(Player player) {
    }

    default void onRemove(Player player) {
    }

    default void getTooltip(List<Component> tooltip) {
    }

    MapCodec<? extends Trait> type();

    Codec<Trait> CODEC = Registries.TRAIT_CODECS.byNameCodec().dispatch(Trait::type, Function.identity());
}
