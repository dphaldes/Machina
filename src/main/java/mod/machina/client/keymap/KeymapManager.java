package mod.machina.client.keymap;

import mod.machina.client.screen.ScreenManager;
import net.minecraft.client.Minecraft;

public class KeymapManager {
    private static final Minecraft minecraft = Minecraft.getInstance();

    public static void handleKeymaps() {
        if (minecraft.player == null)
            return;

        while (Keymaps.KEY_PLAYER_ARSENAL.consumeClick()) {
            ScreenManager.openArsenalScreen(minecraft.player);
        }
    }
}
