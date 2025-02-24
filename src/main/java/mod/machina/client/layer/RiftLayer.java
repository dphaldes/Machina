package mod.machina.client.layer;

import mod.machina.client.ClientData;
import mod.machina.common.blockentity.RiftBlockEntity;
import mod.machina.common.registrar.LangRegistrar;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class RiftLayer {
    private static final int radius = 30;

    public static void render(@Nullable RiftBlockEntity rift, GuiGraphics graphics, Font font) {
        if (rift == null)
            return;

        var recipe = rift.getRecipe();
        if (recipe == null)
            return;

        var poseStack = graphics.pose();
        poseStack.pushPose();
        var centerX = graphics.guiWidth() / 2;
        var centerY = graphics.guiHeight() / 2;

        var toolY = centerY - 100;
        var components = new ArrayList<Component>();

        var gear = recipe.value().result().displayName();
        components.add(Component.translatable(LangRegistrar.CRAFTING.key(), Component.literal(gear)).withStyle(ChatFormatting.GOLD));

        var remaining = rift.getRemainingRequired();
        if (remaining.isEmpty()) {
            components.add(LangRegistrar.RIFT_READY.component());
        } else {
            components.add(LangRegistrar.RIFT_WAITING.component());

            var delta = Mth.TWO_PI / remaining.size();
            var spinOffset = ((float) (ClientData.ticks % 100) / 100) * Mth.TWO_PI;
            for (int index = 0; index < remaining.size(); index++) {
                var items = new ArrayList<>(Arrays.asList(remaining.get(index).getItems()));
                var stack = items.get((ClientData.ticks / 40) % items.size());
                // subtract 8 to account for item width
                var x = (int) (centerX + Mth.cos((delta * index) + spinOffset) * radius - 8);
                var y = (int) (centerY + Mth.sin((delta * index) + spinOffset) * radius - 8);
                graphics.renderFakeItem(stack, x, y);
            }

        }
        var width = 0;
        for (var c : components) {
            width = Math.max(width, font.width(c));
        }
        graphics.renderTooltip(font, components, Optional.empty(), centerX - (width / 2), toolY);


        poseStack.popPose();
    }
}
