package mod.machina.client.layer;

import mod.machina.common.blockentity.RiftBlockEntity;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.world.phys.BlockHitResult;

public class Layers {
    public static final LayeredDraw.Layer LAYER = (GuiGraphics guiGraphics, DeltaTracker deltaTracker) -> {
        var minecraft = Minecraft.getInstance();
        if (minecraft.hitResult instanceof BlockHitResult blockHitResult) {
            if (minecraft.level.getBlockEntity(blockHitResult.getBlockPos()) instanceof RiftBlockEntity rift) {
                //render rift
                RiftLayer.render(rift.getMaster(), guiGraphics, minecraft.font);
            }
        }
    };
}
