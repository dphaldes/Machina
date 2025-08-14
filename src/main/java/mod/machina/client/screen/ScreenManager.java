package mod.machina.client.screen;

import mod.machina.common.armor.Arsenal;
import mod.machina.common.registrar.LangRegistrar;
import net.minecraft.client.Minecraft;
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

    public static void openCodexScreen(Player player) {
        minecraft.setScreen(new RunesmithScreen(player));
    }

}
