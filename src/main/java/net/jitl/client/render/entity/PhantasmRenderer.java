package net.jitl.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.jitl.client.render.JModelLayers;
import net.jitl.client.render.JRenderTypes;
import net.jitl.client.render.model.frozen.PhantasmModel;
import net.jitl.common.entity.frozen.PhantasmEntity;
import net.jitl.core.JITL;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PhantasmRenderer extends MobRenderer<PhantasmEntity, PhantasmModel<PhantasmEntity>> {

    public PhantasmRenderer(EntityRendererProvider.Context context) {
        super(context, new PhantasmModel(context.bakeLayer(JModelLayers.PHANTASM_MODEL_LAYER)), 0.5F);
    }

    @Override
    public void render(PhantasmEntity entitylivingbaseIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        super.render(entitylivingbaseIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entitylivingbaseIn)));
        matrixStackIn.translate(0.0D, -0.989F, 0.0D);
        //this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    protected void scale(PhantasmEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
        float f = 0.999F;
        matrixStackIn.scale(f, f, f);
        matrixStackIn.translate(0.0D, 0.001F, 0.0D);
        float f1 = 1;
        float f2 = Mth.lerp(partialTickTime, entitylivingbaseIn.oSquish, entitylivingbaseIn.squish) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        matrixStackIn.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(@NotNull PhantasmEntity livingEntity, boolean boolean_, boolean boolean1_, boolean boolean2_) {
        return JRenderTypes.transparentCutout(getTextureLocation(livingEntity));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull PhantasmEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/frozen/phantasm.png");
    }
}
