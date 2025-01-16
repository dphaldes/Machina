package com.mystchonky.machina.client.layer;

import com.mystchonky.machina.common.blockentity.RiftBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

public class RiftLayer {
    public static void render(@Nullable RiftBlockEntity rift, GuiGraphics graphics, Font font) {
        if (rift == null)
            return;

        var recipe = rift.getRecipe();
        if (recipe == null)
            return;


        var poseStack = graphics.pose();
        poseStack.pushPose();

        var x = graphics.guiWidth() / 2 + 20;
        var y = graphics.guiHeight() / 2 + 20;
        var result = recipe.value().result().displayName();

        graphics.drawString(font, Component.literal("Crafting:\n" + result), x, y, ChatFormatting.GOLD.getColor());

        poseStack.popPose();
    }
}
