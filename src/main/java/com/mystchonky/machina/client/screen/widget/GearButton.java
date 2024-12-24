package com.mystchonky.machina.client.screen.widget;

import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.client.screen.Tooltip;
import com.mystchonky.machina.client.util.RenderHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GearButton extends Button implements Tooltip.Provider {

    private static final int SIZE = 16;
    @Nullable
    private final Gear gear;
    private final boolean transparent;

    public GearButton(int x, int y, int width, int height, OnPress onPress, @Nullable Gear gear, boolean transparent) {
        super(x, y, width, height, Component.empty(), onPress, Button.DEFAULT_NARRATION);
        this.gear = gear;
        this.transparent = transparent;
    }

    public GearButton(int x, int y, int width, int height, OnPress onPress, @Nullable Gear gear) {
        this(x, y, width, height, onPress, gear, false);
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (!visible) {
            return;
        }
        if (gear != null)
            RenderHelper.drawGear(gear, guiGraphics, getX(), getY(), SIZE, transparent);
    }

    @Override
    public void getAdditionalTooltip(List<Component> tooltip) {
        if (gear == null) return;
        tooltip.add(Component.translatable(gear.localizationKey()));
        gear.getAdditionalTooltip(tooltip);
    }
}
