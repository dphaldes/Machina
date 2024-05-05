package com.mystchonky.machina.client.screen.widget;

import com.mystchonky.machina.api.armament.AbstractGear;
import com.mystchonky.machina.client.screen.TooltipProvider;
import com.mystchonky.machina.client.util.RenderHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;
import java.util.List;

public class ArsenalGearButton extends Button implements TooltipProvider {

    private static final int SIZE = 16;
    @Nullable
    private final AbstractGear gear;

    public ArsenalGearButton(int x, int y, int width, int height, OnPress onPress, @Nullable AbstractGear gear) {
        super(x, y, width, height, Component.empty(), onPress, Button.DEFAULT_NARRATION);
        this.gear = gear;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (!visible) {
            return;
        }
        if (gear != null)
            RenderHelper.drawGear(gear, guiGraphics, getX(), getY(), SIZE, false);
    }

    @Override
    public void getTooltip(List<Component> tooltip) {
        if (gear == null) return;
        tooltip.add(Component.translatable(gear.localizationKey()));
        gear.getTooltip(tooltip);
    }
}
