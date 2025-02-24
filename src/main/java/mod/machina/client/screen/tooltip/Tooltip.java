package mod.machina.client.screen.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class Tooltip {
    public interface Provider {
        void getAdditionalTooltip(List<Component> tooltip);
    }

    public interface Renderer {
        default void renderAdditionalTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, Font font, List<Renderable> renderables) {
            List<Component> tooltip = new ArrayList<>();
            for (Renderable renderable : renderables) {
                if (renderable instanceof AbstractWidget widget && widget instanceof Provider provider) {
                    if (widget.isMouseOver(mouseX, mouseY)) {
                        provider.getAdditionalTooltip(tooltip);
                        break;
                    }
                }
            }

            if (!tooltip.isEmpty()) {
                guiGraphics.renderComponentTooltip(font, tooltip, mouseX, mouseY);
            }
        }


    }
}
