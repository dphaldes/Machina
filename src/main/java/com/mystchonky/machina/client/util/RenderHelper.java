package com.mystchonky.machina.client.util;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mystchonky.machina.api.armament.AbstractGear;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;

public class RenderHelper {
    private static final RenderType TRANSLUCENT = RenderType.entityTranslucent(TextureAtlas.LOCATION_BLOCKS);
    private static final Matrix4f SCALE_INVERT_Y = new Matrix4f().scaling(1F, -1F, 1F);

    public static void drawGear(AbstractGear gear, GuiGraphics graphics, int positionX, int positionY, int size, boolean renderTransparent, int zIndex) {
        renderFakeItemTransparent(graphics.pose(), gear.getGearItem().getDefaultInstance(), positionX, positionY, size, 0, renderTransparent, zIndex);
    }

    public static void drawGear(AbstractGear gear, GuiGraphics graphics, int positionX, int positionY, int size, boolean renderTransparent) {
        renderFakeItemTransparent(graphics.pose(), gear.getGearItem().getDefaultInstance(), positionX, positionY, size, 0, renderTransparent, 150);
    }

    public static void drawItemAsIcon(ItemStack itemStack, GuiGraphics graphics, int positionX, int positionY, int size, boolean renderTransparent) {
        renderFakeItemTransparent(graphics.pose(), itemStack, positionX, positionY, size, 0, renderTransparent, 150);
    }

    public static void renderFakeItemTransparent(PoseStack poseStack, ItemStack stack, int x, int y, int scale, int alpha, boolean transparent, int zIndex) {
        if (stack.isEmpty()) return;

        var renderer = Minecraft.getInstance().getItemRenderer();
        var model = renderer.getModel(stack, null, Minecraft.getInstance().player, 0);
        renderItemModel(poseStack, stack, x, y, scale, alpha, model, renderer, transparent, zIndex);
    }

    public static void renderItemModel(PoseStack poseStack, ItemStack stack, int x, int y, int scale, int alpha, BakedModel model, ItemRenderer renderer, boolean transparent, int zIndex) {
        poseStack.pushPose();
        poseStack.translate(x + 8F, y + 8F, zIndex);
        poseStack.mulPose(SCALE_INVERT_Y);
        poseStack.scale(scale, scale, scale);

        boolean flatLight = !model.usesBlockLight();
        if (flatLight) {
            Lighting.setupForFlatItems();
        }

        var buffer = Minecraft.getInstance().renderBuffers().bufferSource();
        renderer.render(stack, ItemDisplayContext.GUI, false, poseStack, transparent ? transparentBuffer(buffer) : buffer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, model);
        buffer.endBatch();

        RenderSystem.enableDepthTest();

        if (flatLight) {
            Lighting.setupFor3DItems();
        }

        poseStack.popPose();
    }


    private static MultiBufferSource transparentBuffer(MultiBufferSource buffer) {
        return renderType -> new TintedVertexConsumer(buffer.getBuffer(TRANSLUCENT), 1.0f, 1.0f, 1.0f, 0.25f);
    }
}
