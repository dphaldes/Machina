package com.mystchonky.machina.common.registrar;

import com.enderio.regilite.data.RegiliteDataProvider;
import com.mystchonky.machina.Machina;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

public class LangRegistrar {

    // region Commands
    public static MutableComponent GET_UNLOCKED_GEAR = addTranslation("command", Machina.resource("unlocked_gears"), "%s");

    // endregion
    private static MutableComponent addTranslation(String prefix, ResourceLocation id, String translation) {
        return RegiliteDataProvider.addTranslation(prefix, id, translation);
    }

    private static MutableComponent addTranslation(String prefix, ResourceLocation path, String name, String translation) {
        return RegiliteDataProvider.addTranslation(prefix, new ResourceLocation(path.getNamespace(), path.getPath() + "/" + name), translation);
    }


    public static void register() {
    }
}
