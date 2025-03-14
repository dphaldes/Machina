package mod.machina.client.screen;

import mod.machina.Machina;
import mod.machina.api.gear.Gear;
import mod.machina.client.screen.tooltip.Tooltip;
import mod.machina.client.screen.widget.GearButton;
import mod.machina.common.arsenal.Arsenal;
import mod.machina.common.arsenal.ArsenalManager;
import mod.machina.common.network.NetworkedAttachments;
import mod.machina.common.registrar.LangRegistrar;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArsenalScreen extends BaseScreen implements Tooltip.Renderer {

    private static final ResourceLocation BACKGROUND = Machina.prefix("textures/gui/arsenal.png");
    private static final ResourceLocation APPLY = Machina.prefix("apply");
    private static final WidgetSprites APPLY_SPRITES = new WidgetSprites(APPLY, APPLY);

    private final Player player;
    private final Arsenal arsenal;
    private final List<Gear> unlocked;
    private final List<Gear> equipped;
    private final List<GearButton> gearButtons = new ArrayList<>();
    private final List<GearButton> arsenalButtons = new ArrayList<>();
    private final int currentPage = 0;
    private int maxPages = 1;

    public ArsenalScreen(Player player) {
        super(LangRegistrar.ARSENAL_SCREEN.component(), 216, 148);
        this.player = player;
        arsenal = ArsenalManager.getArsenal(player);
        unlocked = new ArrayList<>(arsenal.unlocked());
        equipped = new ArrayList<>(arsenal.equipped()); //TODO: better names

        updateNumPages();
    }

    @Override
    protected void init() {
        super.init();
        displayUnlockedGears(currentPage);
        displayArsenalGears();

        addRenderableWidget(new ImageButton(leftPos + 172, topPos + 120, 20, 8, APPLY_SPRITES, this::onApply, Component.literal("Apply")));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderAdditionalTooltip(guiGraphics, mouseX, mouseY, font, renderables);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.blit(BACKGROUND, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
    }

    private void updateNumPages() {
        this.maxPages = (int) Math.ceil((double) unlocked.size() / 36);
    }

    private void displayUnlockedGears(final int page) {
        clearButtons(gearButtons);

        //TODO:PAGES
        int xOffset = 0;
        int yOffset = 0;
        for (var gear : unlocked) {
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
        for (var gear : equipped) {
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
        var compatible = equipped.stream().filter(Objects::nonNull).allMatch(it -> it.isCompatibleWith(gear));
        if (compatible) {
            for (int i = 0; i < equipped.size(); i++) {
                if (equipped.get(i) != Gear.EMPTY) continue;
                equipped.set(i, gear);
                break;
            }
            displayArsenalGears();
        }
    }

    private void removeGear(Gear gear) {
        for (int i = 0; i < equipped.size(); i++) {
            if (equipped.get(i) == gear) equipped.set(i, Gear.EMPTY);
        }
        displayArsenalGears();
    }

    private void onApply(Button button) {
        var arsenalEquipped = arsenal.equipped();
        for (int i = 0; i < arsenalEquipped.size(); i++) {
            arsenalEquipped.set(i, equipped.get(i));
        }
        NetworkedAttachments.updateArsenal(arsenalEquipped);
    }

}
