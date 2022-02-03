package net.jitl.client.render.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.jitl.common.tile.BloodRuneTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;

public class BloodRuneTER implements BlockEntityRenderer<BloodRuneTile> {

    private final ItemRenderer renderEntity;

    public BloodRuneTER(BlockEntityRendererProvider.Context context) {
        this.renderEntity = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(BloodRuneTile e, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        ItemStack i = e.getItem(0);
        renderItem(i, new double[]{0.5D, 1.25D, 0.5D}, matrixStackIn, bufferIn, combinedOverlayIn, combinedLightIn, 1.0F);
    }

    private void renderItem(ItemStack stack, double[] translation, PoseStack matrixStack, MultiBufferSource buffer, int combinedOverlay, int lightLevel, float scale) {
        matrixStack.pushPose();
        float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 16;
        matrixStack.translate(translation[0], translation[1], translation[2]);
        matrixStack.mulPose(Vector3f.YP.rotation(timeD));
        matrixStack.scale(scale, scale, scale);
        BakedModel model = renderEntity.getModel(stack, null, null, 0);

        this.renderEntity.render(stack, ItemTransforms.TransformType.GROUND, true, matrixStack, buffer, lightLevel, combinedOverlay, model);
        matrixStack.popPose();
    }
}
