package com.mystchonky.machina.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.mystchonky.machina.common.block.CodexBlock;
import com.mystchonky.machina.common.blockentity.CodexBlockEntity;
import net.minecraft.client.model.BookModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class CodexRenderer implements BlockEntityRenderer<CodexBlockEntity> {

    @SuppressWarnings("deprecation")
    public static final Material BOOK_LOCATION = new Material(
            TextureAtlas.LOCATION_BLOCKS, ResourceLocation.withDefaultNamespace("entity/enchanting_table_book")
    );
    private final BookModel bookModel;

    public CodexRenderer(BlockEntityRendererProvider.Context context) {
        this.bookModel = new BookModel(context.bakeLayer(ModelLayers.BOOK));
    }

    @Override
    public void render(CodexBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        BlockState blockstate = blockEntity.getBlockState();
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.0625F, 0.5F);

        float f = (float) blockEntity.time + partialTick;
        poseStack.translate(0.0F, Mth.sin(f * 0.1F) * 0.01F, 0.0F);

        float rot = blockstate.getValue(CodexBlock.FACING).getClockWise().toYRot();
        poseStack.mulPose(Axis.YP.rotationDegrees(-rot));
        poseStack.mulPose(Axis.ZP.rotationDegrees(67.5F));

        poseStack.translate(0.0F, -0.125F, 0.0F);
        float f3 = Mth.lerp(partialTick, blockEntity.oFlip, blockEntity.flip);
        float f4 = Mth.frac(f3 + 0.25F) * 1.6F - 0.3F;
        float f5 = Mth.frac(f3 + 0.75F) * 1.6F - 0.3F;
        float f6 = Mth.lerp(partialTick, blockEntity.oOpen, blockEntity.open);
        this.bookModel.setupAnim(f, Mth.clamp(f4, 0.0F, 1.0F), Mth.clamp(f5, 0.0F, 1.0F), f6);
//        this.bookModel.setupAnim(0.0F, 0.1F, 0.9F, 1.2F);
        VertexConsumer vertexconsumer = BOOK_LOCATION.buffer(bufferSource, RenderType::entitySolid);
        this.bookModel.render(poseStack, vertexconsumer, packedLight, packedOverlay, -1);
        poseStack.popPose();
    }

    @Override
    public AABB getRenderBoundingBox(CodexBlockEntity blockEntity) {
        net.minecraft.core.BlockPos pos = blockEntity.getBlockPos();
        return new net.minecraft.world.phys.AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.5, pos.getZ() + 1.0);
    }
}
