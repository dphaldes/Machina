package com.mystchonky.machina.client.screen.tooltip;

import com.mystchonky.machina.client.ClientData;
import com.mystchonky.machina.common.recipe.GearRecipe;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public record RecipeTooltip(GearRecipe recipe) implements ClientTooltipComponent {

    @Override
    public int getHeight() {
        return 16;
    }

    @Override
    public int getWidth(@NotNull Font font) {
        return 6 * 16;
    }

    @Override
    public void renderImage(@NotNull Font font, int x, int y, @NotNull GuiGraphics guiGraphics) {
        var ingredients = recipe.ingredients();
        for (int i = 0; i < ingredients.size(); i++) {
            var items = new ArrayList<>(Arrays.asList(ingredients.get(i).getItems()));
            var stack = items.get((ClientData.ticks / 20) % items.size());
            guiGraphics.renderItem(stack, x + (i * 16), y);
            guiGraphics.renderItemDecorations(font, stack, x + (i * 16), y);
        }
    }
}
