package mod.machina.client.screen.widget;

import mod.machina.client.screen.tooltip.Tooltip;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import java.util.List;

public class GearButton extends Button implements Tooltip.Provider {

    private static final int SIZE = 16;
    private final boolean transparent;

    public GearButton(int x, int y, int width, int height, OnPress onPress, boolean transparent) {
        super(x, y, width, height, Component.empty(), onPress, Button.DEFAULT_NARRATION);
        this.transparent = transparent;
    }

    public GearButton(int x, int y, int width, int height, OnPress onPress) {
        this(x, y, width, height, onPress, false);
    }


    //    @Override
//    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
//        if (!visible || gear == Gear.EMPTY) {
//            return;
//        }
//        RenderHelper.drawGear(gear, guiGraphics, getX(), getY(), SIZE, transparent);
//    }
//
    @Override
    public void getAdditionalTooltip(List<Component> tooltip) {
    }
}
