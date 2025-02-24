package mod.machina.client.screen.widget;

import mod.machina.api.gear.Gear;
import mod.machina.client.screen.tooltip.Tooltip;
import mod.machina.client.util.RenderHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import java.util.List;

public class GearButton extends Button implements Tooltip.Provider {

    private static final int SIZE = 16;
    private final Gear gear;
    private final boolean transparent;

    public GearButton(int x, int y, int width, int height, OnPress onPress, Gear gear, boolean transparent) {
        super(x, y, width, height, Component.empty(), onPress, Button.DEFAULT_NARRATION);
        this.gear = gear;
        this.transparent = transparent;
    }

    public GearButton(int x, int y, int width, int height, OnPress onPress, Gear gear) {
        this(x, y, width, height, onPress, gear, false);
    }

    public Gear getGear() {
        return gear;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (!visible || gear == Gear.EMPTY) {
            return;
        }
        RenderHelper.drawGear(gear, guiGraphics, getX(), getY(), SIZE, transparent);
    }

    @Override
    public void getAdditionalTooltip(List<Component> tooltip) {
        if (gear != Gear.EMPTY) {
            tooltip.add(Component.translatable(gear.localizationKey()));
            gear.getAdditionalTooltip(tooltip);
        }
    }
}
