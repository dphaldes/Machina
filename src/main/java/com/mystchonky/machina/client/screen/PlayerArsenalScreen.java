package com.mystchonky.machina.client.screen;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import com.mystchonky.machina.client.screen.widget.GearButton;
import com.mystchonky.machina.common.attachment.Arsenal;
import com.mystchonky.machina.common.attachment.UnlockedGears;
import com.mystchonky.machina.common.registrar.LangRegistrar;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerArsenalScreen extends Screen {

    public static ResourceLocation background = Machina.prefix("textures/gui/arsenal.png");

    protected final int imageWidth = 216;
    protected final int imageHeight = 148;
    protected int leftPos;
    protected int topPos;

    private final Player player;
    private final UnlockedGears unlockedGears;
    private final Arsenal playerArsenal;
    private int maxPages = 1;
    private int currentPage = 0;

    private List<AbstractGear> displayGears;
    private List<GearButton> gearButtons = new ArrayList<>();

    public PlayerArsenalScreen(Player player) {
        super(LangRegistrar.ARSENAL_SCREEN);
        this.player = player;
        unlockedGears = UnlockedGears.get(player);
        playerArsenal = Arsenal.get(player);

        displayGears = new ArrayList<>(unlockedGears.gears());
        updateNumPages();
    }

    @Override
    protected void init() {
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        layoutGearButtons(currentPage);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderTransparentBackground(guiGraphics);
        guiGraphics.blit(background, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void updateNumPages() {
        this.maxPages = (int) Math.ceil((double) displayGears.size() / 36);
    }

    private void layoutGearButtons(final int page) {
        clearButtons(gearButtons);

        //TODO:PAGES

        int xOffset = 0;
        int yOffset = 0;
        for (var gear : displayGears) {
            final var button = new GearButton(leftPos + 16 + xOffset, topPos + 16 + yOffset, 16, 16, this::onGearClicked, gear);
            addRenderableWidget(button);
            gearButtons.add(button);
            xOffset += 20;
            if (xOffset > width) {
                xOffset = 0;
                yOffset += 20;
            }
        }
    }

    private <T extends Button> void clearButtons(final List<T> buttons) {
        buttons.forEach(button -> {
            renderables.remove(button);
            children().remove(button);
        });
        buttons.clear();
    }

    public void onGearClicked(Button button) {
        Machina.LOGGER.info("Button pressed!");
    }
}
