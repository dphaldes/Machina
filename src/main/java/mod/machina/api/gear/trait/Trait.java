package mod.machina.api.gear.trait;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public interface Trait {

    default void onEquip(Player player) {
    }

    default void onRemove(Player player) {

    }

    default void getTooltip(List<Component> tooltip) {
    }

}
