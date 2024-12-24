package com.mystchonky.machina.client.screen;

import com.mystchonky.machina.Machina;
import com.mystchonky.machina.api.gear.Gear;
import com.mystchonky.machina.client.screen.widget.GearButton;
import com.mystchonky.machina.common.gear.UnlockedGears;
import com.mystchonky.machina.common.menu.CodexMenu;
import com.mystchonky.machina.common.registrar.Registries;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CodexScreen extends AbstractContainerScreen<CodexMenu> implements Tooltip.Renderer {

    private final Player player;
    private final List<Gear> allGears;
    private final List<Gear> unlockedGears;
    private final List<GearButton> gearButtons = new ArrayList<>();

    private static final ResourceLocation BACKGROUND_LOCATION = Machina.prefix("textures/gui/codex.png");

    public CodexScreen(CodexMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);

        imageWidth = 216;
        imageHeight = 218;
        player = playerInventory.player;
        unlockedGears = new ArrayList<>(UnlockedGears.get(player));
        allGears = Minecraft.getInstance().level.registryAccess().registryOrThrow(Registries.GEARS).stream()
                .sorted(Comparator.comparing(Gear::displayName))
                .toList();
    }

    @Override
    protected void init() {
        super.init();
        addGearButtons();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(BACKGROUND_LOCATION, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // don't draw any labels
    }

    private void addGearButtons() {
        clearButtons(gearButtons);

        int xOffset = 0;
        int yOffset = 0;
        for (var gear : allGears) {
            final var button = new GearButton(leftPos + 16 + xOffset, topPos + 16 + yOffset, 16, 16,
                    (btn) -> {
                    }, gear);
            addRenderableWidget(button);
            gearButtons.add(button);
            xOffset += 20;
            if (xOffset > 116) {
                xOffset = 0;
                yOffset += 20;
            }
        }
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int x, int y) {
        super.renderTooltip(guiGraphics, x, y);
        renderAdditionalTooltip(guiGraphics, x, y, font, renderables);
    }

    private <T extends Button> void clearButtons(final List<T> buttons) {
        buttons.forEach(button -> {
            renderables.remove(button);
            children().remove(button);
        });
        buttons.clear();
    }


}
