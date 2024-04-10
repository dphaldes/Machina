package com.mystchonky.machina.client.screen;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.arsenal.gear.AbstractGear;
import com.mystchonky.machina.client.screen.widget.ArsenalGearButton;
import com.mystchonky.machina.client.screen.widget.GearButton;
import com.mystchonky.machina.common.attachment.Arsenal;
import com.mystchonky.machina.common.attachment.ArsenalGearSlot;
import com.mystchonky.machina.common.attachment.AttachmentManager;
import com.mystchonky.machina.common.attachment.UnlockedGears;
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

public class ArsenalScreen extends Screen {

    public static ResourceLocation background = Machina.prefix("textures/gui/arsenal.png");
    public static ResourceLocation applyButton = Machina.prefix("apply");
    public static WidgetSprites applySprites = new WidgetSprites(applyButton, applyButton);

    protected final int imageWidth = 216;
    protected final int imageHeight = 148;
    private final Player player;
    private final Arsenal playerArsenal;
    private final List<AbstractGear> unlockedGearsList;
    private final List<AbstractGear> arsenalGearsList;
    private final List<GearButton> gearButtons = new ArrayList<>();
    private final List<ArsenalGearButton> arsenalButtons = new ArrayList<>();
    private final int currentPage = 0;
    protected int leftPos;
    protected int topPos;
    private int maxPages = 1;

    public ArsenalScreen(Player player) {
        super(LangRegistrar.ARSENAL_SCREEN);
        this.player = player;
        playerArsenal = Arsenal.get(player);
        unlockedGearsList = new ArrayList<>(UnlockedGears.get(player).gears());
        arsenalGearsList = new ArrayList<>(Arsenal.get(player).gears());

        updateNumPages();
    }

    @Override
    protected void init() {
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        displayUnlockedGears(currentPage);
        displayArsenalGears();

        addRenderableWidget(new ImageButton(leftPos + imageWidth - 48, topPos + imageHeight - 16, 48, 16, applySprites, this::applyButtonClicked, Component.literal("Apply")));
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
        this.maxPages = (int) Math.ceil((double) unlockedGearsList.size() / 36);
    }

    private void displayUnlockedGears(final int page) {
        clearButtons(gearButtons);

        //TODO:PAGES
        int xOffset = 0;
        int yOffset = 0;
        for (var gear : unlockedGearsList) {
            final var button = new GearButton(leftPos + 16 + xOffset, topPos + 16 + yOffset, 16, 16, (btn) -> onGearClicked(btn, gear), gear);
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
        for (var gear : arsenalGearsList) {
            final var button = new ArsenalGearButton(leftPos + 164 + xOffset, topPos + 36 + yOffset, 16, 16, (btn) -> arsenalGearClicked(btn, gear), gear);
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
        buttons.forEach(button -> {
            renderables.remove(button);
            children().remove(button);
        });
        buttons.clear();
    }

    private void onGearClicked(Button button, AbstractGear gear) {
        var contain = arsenalGearsList.stream().anyMatch(it -> it == gear);
        var compatible = arsenalGearsList.stream().filter(Objects::nonNull).allMatch(it -> it.isCompatibleWith(gear));
        if (!contain && compatible) {
            for (int i = 0; i < arsenalGearsList.size(); i++) {
                if (arsenalGearsList.get(i) != null) continue;
                arsenalGearsList.set(i, gear);
                break;
            }
            displayArsenalGears();
        }
    }

    private void arsenalGearClicked(Button button, @Nullable AbstractGear gear) {
        if (gear == null) return;
        for (int i = 0; i < arsenalGearsList.size(); i++) {
            if (arsenalGearsList.get(i) == gear) arsenalGearsList.set(i, null);
        }
        displayArsenalGears();
    }

    private void applyButtonClicked(Button button) {
        for (int i = 0; i < playerArsenal.slots().size(); i++) {
            playerArsenal.slots().set(i, new ArsenalGearSlot(arsenalGearsList.get(i)));
        }
        AttachmentManager.updateArsenal(playerArsenal);
    }
}
