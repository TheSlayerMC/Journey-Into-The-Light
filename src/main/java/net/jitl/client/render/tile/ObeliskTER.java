package net.jitl.client.render.tile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.jitl.JITL;
import net.jitl.client.render.model.block.ObeliskModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;

public class ObeliskTER extends TileEntityRenderer {

    private ObeliskModel obelisk = new ObeliskModel();
    public static final ResourceLocation OBELISK_LOCATION = JITL.rl("textures/models/block/obelisk.png");

    public ObeliskTER(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(TileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {

        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(obelisk(OBELISK_LOCATION));
        float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 16;

        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5F, 1.75, 0.5F);
        matrixStackIn.mulPose(Vector3f.YP.rotation(timeD));
        obelisk.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, true);
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5F, -0.5, 0.5F);
        matrixStackIn.mulPose(Vector3f.YP.rotation(-timeD));
        obelisk.render(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, false);
        matrixStackIn.popPose();

    }

    public static RenderType obelisk(ResourceLocation resourceLocation) {
        RenderState.TextureState textureState = new RenderState.TextureState(OBELISK_LOCATION, false, false);

        return RenderType.create("obelisk", DefaultVertexFormats.BLOCK, 7, 256, false, true, RenderType.State.builder().setTextureState(textureState).createCompositeState(true));
    }
}
