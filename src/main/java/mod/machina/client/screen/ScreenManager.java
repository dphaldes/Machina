package mod.machina.client.screen;

import mod.machina.common.arsenal.Arsenal;
import mod.machina.common.registrar.LangRegistrar;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

public class ScreenManager {
    private static final Minecraft minecraft = Minecraft.getInstance();

    public static void openArsenalScreen(Player player) {
        if (!Arsenal.shouldActivate(player)) {
            player.displayClientMessage(LangRegistrar.ARMOR_MISSING.component(), true);
            return;
        }
        minecraft.setScreen(new ArsenalScreen(player));
    }

    public static void openCodexScreen(Player player, BlockPos master) {
        minecraft.setScreen(new CodexScreen(player, master));
    }

}
