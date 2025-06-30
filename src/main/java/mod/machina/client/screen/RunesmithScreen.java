package mod.machina.client.screen;

import it.unimi.dsi.fastutil.Pair;
import mod.machina.Machina;
import mod.machina.api.RegistryKeys;
import mod.machina.api.gear.Gear;
import mod.machina.client.ClientData;
import mod.machina.client.screen.tooltip.RecipeTooltip;
import mod.machina.client.screen.widget.GearButton;
import mod.machina.common.arsenal.ArsenalManager;
import mod.machina.common.network.MessageRegistrar;
import mod.machina.common.network.messages.MessageSetRiftRecipe;
import mod.machina.common.recipe.GearRecipe;
import mod.machina.common.registrar.LangRegistrar;
import mod.machina.common.registrar.RecipeRegistrar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.client.ClientHooks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RunesmithScreen extends BaseScreen {

    private static final ResourceLocation BACKGROUND_LOCATION = Machina.prefix("textures/gui/codex.png");
    private static final ResourceLocation APPLY = Machina.prefix("apply");
    private static final WidgetSprites APPLY_SPRITES = new WidgetSprites(APPLY, APPLY);

    private final Player player;
    private final List<Gear> allGears;
    private final List<Gear> unlockedGears;
    private final List<GearButton> gearButtons = new ArrayList<>();
    private final List<RecipeHolder<GearRecipe>> recipeCache;
    private final BlockPos masterRift;
    @Nullable
    private Pair<Gear, RecipeHolder<GearRecipe>> selectedRecipe = null;

    public RunesmithScreen(Player player, BlockPos masterRift) {
        super(LangRegistrar.CODEX_SCREEN.component(), 216, 148);
        this.player = player;
        this.masterRift = masterRift;

        unlockedGears = new ArrayList<>(ArsenalManager.getArsenal(player).unlocked());
        var level = Minecraft.getInstance().level;
        allGears = level.registryAccess().registryOrThrow(RegistryKeys.GEARS).stream()
                .sorted(Comparator.comparing(Gear::displayName))
                .toList();
        recipeCache = level.getRecipeManager()
                .getAllRecipesFor(RecipeRegistrar.Types.GEAR.get());
    }

    @Override
    protected void init() {
        super.init();
        addGearButtons();

        addRenderableWidget(new ImageButton(leftPos + 162, topPos + 124, 20, 8, APPLY_SPRITES, this::select, Component.literal("Select")));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        if (selectedRecipe != null) {
            var ingredients = selectedRecipe.right().value().ingredients();
            for (int i = 0; i < ingredients.size(); i++) {
                var items = new ArrayList<>(Arrays.asList(ingredients.get(i).getItems()));
                var stack = items.get((ClientData.ticks / 20) % items.size());
                var x = leftPos + 144 + ((i % 3) * 20);
                var y = topPos + 36 + ((i / 3) * 20);
                guiGraphics.renderItem(stack, x, y);
                guiGraphics.renderItemDecorations(font, stack, x, y);
                if (inBounds(mouseX, mouseY, x, y)) {
                    guiGraphics.renderTooltip(font, stack, mouseX, mouseY);
                }
            }
            var stack = selectedRecipe.left().getGearItem().getDefaultInstance();
            var x = leftPos + 164;
            var y = topPos + 96;
            guiGraphics.renderItem(stack, x, y);
            guiGraphics.renderItemDecorations(font, stack, x, y);
            if (inBounds(mouseX, mouseY, x, y)) {
                guiGraphics.renderTooltip(font, stack, mouseX, mouseY);
            }
        }
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.blit(BACKGROUND_LOCATION, leftPos, topPos, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
    }

    private void addGearButtons() {
        clearButtons(gearButtons);

        int xOffset = 0;
        int yOffset = 0;
        for (var gear : allGears) {
            var unlocked = unlockedGears.contains(gear);
            final var button = new GearButton(leftPos + 16 + xOffset, topPos + 16 + yOffset, 16, 16,
                    this::gearButtonClicked, gear, unlocked);
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
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        Optional<RecipeHolder<GearRecipe>> recipe = Optional.empty();
        List<Component> tooltip = new ArrayList<>();
        for (Renderable renderable : renderables) {
            if (renderable instanceof GearButton button) {
                if (button.isMouseOver(mouseX, mouseY)) {
                    button.getAdditionalTooltip(tooltip);

                    if (gearButtons.contains(button)) {
                        recipe = getRecipe(button.getGear());
                    }
                    break;
                }
            }
        }

        if (!tooltip.isEmpty()) {
            List<ClientTooltipComponent> components = new ArrayList<>(ClientHooks.gatherTooltipComponents(ItemStack.EMPTY, tooltip, mouseX, guiGraphics.guiWidth(), guiGraphics.guiHeight(), font));
            recipe.ifPresent(gearRecipe -> components.add(new RecipeTooltip(gearRecipe.value())));
            guiGraphics.renderTooltipInternal(font, components, mouseX, mouseY, DefaultTooltipPositioner.INSTANCE);
        }
    }

    private <T extends Button> void clearButtons(final List<T> buttons) {
        buttons.forEach(this::removeWidget);
        buttons.clear();
    }

    private void gearButtonClicked(Button button) {
        var gear = ((GearButton) button).getGear();
        var recipe = getRecipe(gear);
        recipe.ifPresent(gearRecipe -> selectedRecipe = Pair.of(gear, gearRecipe));
    }

    private Optional<RecipeHolder<GearRecipe>> getRecipe(@Nullable Gear gear) {
        return recipeCache.stream().filter(it -> it.value().result() == gear).findFirst();
    }

    private boolean inBounds(int x, int y, int x1, int y1) {
        return x >= x1 && x < x1 + 16 && y >= y1 && y < y1 + 16;
    }

    private void select(Button btn) {
        if (selectedRecipe == null)
            return;
        var recipe = selectedRecipe.right();
        MessageRegistrar.sendToServer(new MessageSetRiftRecipe(masterRift, recipe.id()));
    }
}
