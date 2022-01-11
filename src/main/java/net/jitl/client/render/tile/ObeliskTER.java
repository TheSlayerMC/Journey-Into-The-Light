package net.jitl.client.render.tile;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Vector3f;
import net.jitl.JITL;
import net.jitl.client.render.JModelLayers;
import net.jitl.common.tile.ObeliskTile;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

import java.util.Objects;

public class ObeliskTER implements BlockEntityRenderer<ObeliskTile> {

    //FIXME
    public static final ResourceLocation OBELISK_LOCATION = JITL.rl("textures/models/block/obelisk.png");

    private final ModelPart top;
    private final ModelPart bottom;

    public ObeliskTER(BlockEntityRendererProvider.Context context) {
        this.top = context.bakeLayer(JModelLayers.OBELISK_TOP_MODEL_LAYER);
        this.bottom = context.bakeLayer(JModelLayers.OBELISK_TOP_MODEL_LAYER);
    }

    @Override
    public void render(ObeliskTile tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 16;
        int lightL = getProperLightLevel(Objects.requireNonNull(tileEntityIn.getLevel()), tileEntityIn.getBlockPos());
        float yOffset = 1.0F;
        renderPart(matrixStackIn, top, bufferIn, timeD, lightL, yOffset);
        renderPart(matrixStackIn, bottom, bufferIn, -timeD, lightL, 0);
    }

    public static RenderType obelisk(ResourceLocation resourceLocation) {
        RenderStateShard.TextureStateShard textureState = new RenderStateShard.TextureStateShard(OBELISK_LOCATION, false, false);
        return RenderType.create("obelisk", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 256, false, true, RenderType.CompositeState.builder().setTextureState(textureState).createCompositeState(true));
    }

    public void renderPart(PoseStack matrixStackIn, ModelPart part, MultiBufferSource bufferIn, float timeD, int lightL, float yOffset) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.5F, 0.5F + yOffset, 0.5F);
        matrixStackIn.mulPose(Vector3f.YP.rotation(timeD));
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entitySolid(OBELISK_LOCATION));
        part.render(matrixStackIn, ivertexbuilder, lightL, lightL);
        matrixStackIn.popPose();
    }

    private int getProperLightLevel(Level w, BlockPos pos) {
        int blockLight = w.getBrightness(LightLayer.BLOCK, pos);
        int skyLevel = w.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLevel);
    }
}
