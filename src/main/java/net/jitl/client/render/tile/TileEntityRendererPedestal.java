package net.jitl.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.jitl.common.block.tileentity.PedestalTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

public class TileEntityRendererPedestal extends TileEntityRenderer<PedestalTile> {

    private final ItemRenderer renderEntity;

    public TileEntityRendererPedestal(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
        this.renderEntity = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(PedestalTile e, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        ItemStack i = e.getItem(0);
        renderItem(i, new double[] {0.5D, 1.4D, 0.5D}, matrixStackIn, bufferIn, combinedOverlayIn, combinedLightIn, 1.0F);
    }

    private void renderItem(ItemStack stack, double[] translation, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedOverlay, int lightLevel, float scale) {
        matrixStack.pushPose();
        float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 6;
        matrixStack.translate(translation[0], translation[1], translation[2]);
        matrixStack.mulPose(Vector3f.YP.rotation(timeD));
        matrixStack.scale(scale, scale, scale);
        IBakedModel model = renderEntity.getModel(stack, null, null);
        this.renderEntity.render(stack, ItemCameraTransforms.TransformType.GROUND, true, matrixStack, buffer, lightLevel, combinedOverlay, model);
        matrixStack.popPose();
    }
}
