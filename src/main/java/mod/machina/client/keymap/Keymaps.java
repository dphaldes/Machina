package mod.machina.client.keymap;

import com.mojang.blaze3d.platform.InputConstants;
import mod.machina.common.registrar.LangRegistrar;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class Keymaps {

    public static final KeyMapping KEY_PLAYER_ARSENAL = new KeyMapping(LangRegistrar.ARSENAL_KEY.key(),
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            LangRegistrar.KEYMAP_CATEGORIES.key());


}
