package mod.machina.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import mod.machina.Machina;
import mod.machina.common.block.entity.RuneProjectorEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import org.joml.Matrix4f;

public class RuneProjectorRenderer implements BlockEntityRenderer<RuneProjectorEntity> {
    private static final Minecraft minecraft = Minecraft.getInstance();

    @Override
    public void render(RuneProjectorEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        var position = minecraft.player.position();
        float f1 = (float) (Mth.atan2(position.z - blockEntity.getBlockPos().getZ() - 0.5D,
                position.x - blockEntity.getBlockPos().getX() - 0.5D) * 180.0f / Math.PI + 90);
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(-f1));

        var sprite = minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(Machina.prefix("block/runes/phi"));
        var u0 = sprite.getU0();
        var u1 = sprite.getU1();
        var v0 = sprite.getV0();
        var v1 = sprite.getV1();

        var consumer = bufferSource.getBuffer(Sheets.translucentCullBlockSheet());
        var pose = poseStack.last().pose();

        vertex(consumer, pose, -0.5f, -0.5f, 0, u0, v0, packedLight, packedOverlay);
        vertex(consumer, pose, -0.5f, 0.5f, 0, u0, v1, packedLight, packedOverlay);
        vertex(consumer, pose, 0.5f, 0.5f, 0, u1, v1, packedLight, packedOverlay);
        vertex(consumer, pose, 0.5f, -0.5f, 0, u1, v0, packedLight, packedOverlay);

        poseStack.popPose();
    }

    private void vertex(VertexConsumer consumer, Matrix4f pose, float x, float y, float z, float u, float v, int packedLight, int packedOverlay) {
        consumer.addVertex(pose, x, y, z).setUv(u, v).setLight(packedLight).setOverlay(packedOverlay)
                .setNormal(1, 1, 1).setColor(255, 255, 255, 255);

    }
}
