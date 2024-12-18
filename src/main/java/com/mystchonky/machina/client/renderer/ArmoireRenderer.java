package com.mystchonky.machina.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.mystchonky.machina.common.block.ArmoireBlock;
import com.mystchonky.machina.common.blockentity.ArmoireBlockEntity;
import net.minecraft.client.model.BookModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.EnchantTableRenderer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class ArmoireRenderer implements BlockEntityRenderer<ArmoireBlockEntity> {
    private final BookModel bookModel;

    public ArmoireRenderer(BlockEntityRendererProvider.Context context) {
        this.bookModel = new BookModel(context.bakeLayer(ModelLayers.BOOK));
    }

    @Override
    public void render(ArmoireBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState blockstate = blockEntity.getBlockState();
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.0625F, 0.5F);
        float f = blockstate.getValue(ArmoireBlock.FACING).getClockWise().toYRot();
        poseStack.mulPose(Axis.YP.rotationDegrees(-f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(67.5F));
        poseStack.translate(0.0F, -0.125F, 0.0F);
        this.bookModel.setupAnim(0.0F, 0.1F, 0.9F, 1.2F);
        VertexConsumer vertexconsumer = EnchantTableRenderer.BOOK_LOCATION.buffer(bufferSource, RenderType::entitySolid);
        this.bookModel.render(poseStack, vertexconsumer, packedLight, packedOverlay, -1);
        poseStack.popPose();
    }

    @Override
    public AABB getRenderBoundingBox(ArmoireBlockEntity blockEntity) {
        net.minecraft.core.BlockPos pos = blockEntity.getBlockPos();
        return new net.minecraft.world.phys.AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.5, pos.getZ() + 1.0);
    }
}
