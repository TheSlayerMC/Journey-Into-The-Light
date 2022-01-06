package net.jitl.client.render.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.JITL;
import net.jitl.client.render.model.block.ObeliskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import com.mojang.math.Vector3f;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.Level;

public class ObeliskTER extends BlockEntityRenderer {

    private ObeliskModel obelisk = new ObeliskModel();
    public static final ResourceLocation OBELISK_LOCATION = JITL.rl("textures/models/block/obelisk.png");

    public ObeliskTER(BlockEntityRenderDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(BlockEntity tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {

        VertexConsumer ivertexbuilder = bufferIn.getBuffer(obelisk(OBELISK_LOCATION));
        float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 16;
        int lightL = getProperLightLevel(tileEntityIn.getLevel(), tileEntityIn.getBlockPos());

        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5F, 1.75, 0.5F);
        matrixStackIn.mulPose(Vector3f.YP.rotation(timeD));
        obelisk.render(matrixStackIn, ivertexbuilder, lightL, lightL, true);
        matrixStackIn.popPose();

        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5F, -0.5, 0.5F);
        matrixStackIn.mulPose(Vector3f.YP.rotation(-timeD));
        obelisk.render(matrixStackIn, ivertexbuilder, combinedOverlayIn, lightL, false);
        matrixStackIn.popPose();

    }

    public static RenderType obelisk(ResourceLocation resourceLocation) {
        RenderStateShard.TextureStateShard textureState = new RenderStateShard.TextureStateShard(OBELISK_LOCATION, false, false);
        return RenderType.create("obelisk", DefaultVertexFormat.BLOCK, 7, 256, false, true, RenderType.CompositeState.builder().setTextureState(textureState).createCompositeState(true));
    }

    private int getProperLightLevel(Level w, BlockPos pos) {
        int blockLight = w.getBrightness(LightLayer.BLOCK, pos);
        int skyLevel = w.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLevel);
    }
}
