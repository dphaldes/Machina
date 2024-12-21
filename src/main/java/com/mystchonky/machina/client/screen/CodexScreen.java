package com.mystchonky.machina.client.screen;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.common.menu.CodexMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CodexScreen extends AbstractContainerScreen<CodexMenu> {

    private static final ResourceLocation BACKGROUND_LOCATION = Machina.prefix("textures/gui/codex.png");

    public CodexScreen(CodexMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);

        imageWidth = 216;
        imageHeight = 218;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(BACKGROUND_LOCATION, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // don't draw any labels
    }
}
