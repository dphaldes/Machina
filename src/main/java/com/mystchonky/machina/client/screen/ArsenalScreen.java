package com.mystchonky.machina.client.screen;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.client.screen.widget.GearButton;
import com.mystchonky.machina.common.arsenal.Arsenal;
import com.mystchonky.machina.common.gear.UnlockedGears;
import com.mystchonky.machina.common.network.NetworkedAttachments;
import com.mystchonky.machina.common.registrar.LangRegistrar;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArsenalScreen extends Screen implements Tooltip.Renderer {

    public static ResourceLocation background = Machina.prefix("textures/gui/arsenal.png");
    public static ResourceLocation applyButton = Machina.prefix("apply");
    public static WidgetSprites applySprites = new WidgetSprites(applyButton, applyButton);

    protected final int imageWidth = 216;
    protected final int imageHeight = 148;
    private final Player player;
    private final Arsenal playerArsenal;
    private final List<Gear> unlockedGears;
    private final List<Gear> equippedGears;
    private final List<GearButton> gearButtons = new ArrayList<>();
    private final List<GearButton> arsenalButtons = new ArrayList<>();
    private final int currentPage = 0;
    protected int leftPos;
    protected int topPos;
    private int maxPages = 1;

    public ArsenalScreen(Player player) {
        super(LangRegistrar.ARSENAL_SCREEN.component());
        this.player = player;
        playerArsenal = Arsenal.get(player);
        unlockedGears = new ArrayList<>(UnlockedGears.get(player));
        equippedGears = new ArrayList<>(Arsenal.get(player).gears());

        updateNumPages();
    }

    @Override
    protected void init() {
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        displayUnlockedGears(currentPage);
        displayArsenalGears();

        addRenderableWidget(new ImageButton(leftPos + imageWidth - 48, topPos + imageHeight - 16, 48, 16, applySprites, this::onApply, Component.literal("Apply")));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderAdditionalTooltip(guiGraphics, mouseX, mouseY, font, renderables);
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
        this.maxPages = (int) Math.ceil((double) unlockedGears.size() / 36);
    }

    private void displayUnlockedGears(final int page) {
        clearButtons(gearButtons);

        //TODO:PAGES
        int xOffset = 0;
        int yOffset = 0;
        for (var gear : unlockedGears) {
            final var button = new GearButton(leftPos + 16 + xOffset, topPos + 16 + yOffset, 16, 16, (btn) -> tryEquipGear(gear), gear);
            addRenderableWidget(button);
            gearButtons.add(button);
            xOffset += 20;
            if (xOffset > 116) {
                xOffset = 0;
                yOffset += 20;
            }
        }
    }

    private void displayArsenalGears() {
        clearButtons(arsenalButtons);

        int xOffset = 0;
        int yOffset = 0;
        for (var gear : equippedGears) {
            final var button = new GearButton(leftPos + 164 + xOffset, topPos + 36 + yOffset, 16, 16, (btn) -> removeGear(gear), gear);
            addRenderableWidget(button);
            arsenalButtons.add(button);
            xOffset += 20;
            if (xOffset > 36) {
                xOffset = 0;
                yOffset += 20;
            }
        }
    }

    private <T extends Button> void clearButtons(final List<T> buttons) {
        buttons.forEach(this::removeWidget);
        buttons.clear();
    }

    private void tryEquipGear(Gear gear) {
        var compatible = equippedGears.stream().filter(Objects::nonNull).allMatch(it -> it.isCompatibleWith(gear));
        if (compatible) {
            for (int i = 0; i < equippedGears.size(); i++) {
                if (equippedGears.get(i) != null) continue;
                equippedGears.set(i, gear);
                break;
            }
            displayArsenalGears();
        }
    }

    private void removeGear(@Nullable Gear gear) {
        if (gear == null) return;
        for (int i = 0; i < equippedGears.size(); i++) {
            if (equippedGears.get(i) == gear) equippedGears.set(i, null);
        }
        displayArsenalGears();
    }

    private void onApply(Button button) {
        for (int i = 0; i < playerArsenal.gears().size(); i++) {
            playerArsenal.gears().set(i, equippedGears.get(i));
        }
        NetworkedAttachments.updateArsenal(playerArsenal);
    }

}
