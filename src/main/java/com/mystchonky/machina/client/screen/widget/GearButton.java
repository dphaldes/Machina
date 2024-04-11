package com.mystchonky.machina.client.screen.widget;

import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import com.mystchonky.machina.client.screen.TooltipProvider;
import com.mystchonky.machina.client.util.RenderHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import java.util.List;

public class GearButton extends Button implements TooltipProvider {

    private static final int SIZE = 16;
    private final AbstractGear gear;

    public GearButton(int x, int y, int width, int height, OnPress onPress, AbstractGear gear) {
        super(x, y, width, height, Component.empty(), onPress, Button.DEFAULT_NARRATION);
        this.gear = gear;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (!visible) {
            return;
        }
        RenderHelper.drawGear(gear, guiGraphics, getX(), getY(), SIZE, false);
    }

    @Override
    public void getTooltip(List<Component> tooltip) {
        tooltip.add(Component.translatable(gear.getLocalizationKey()));
        gear.getTooltip(tooltip);
    }
}
