package net.jitl.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.jitl.JITL;
import net.jitl.client.render.model.frozen.PhantasmModel;
import net.jitl.common.entity.frozen.PhantasmEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class PhantasmRenderer extends MobRenderer<PhantasmEntity, PhantasmModel<PhantasmEntity>> {
    public PhantasmRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PhantasmModel(), 0.5F);
    }

    @Override
    public void render(PhantasmEntity entitylivingbaseIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entitylivingbaseIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entitylivingbaseIn)));
        matrixStackIn.translate(0.0D, -0.989F, 0.0D);
      //  this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getOverlayCoords(entitylivingbaseIn, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    protected void scale(PhantasmEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = 0.999F;
        matrixStackIn.scale(f, f, f);
        matrixStackIn.translate(0.0D, 0.001F, 0.0D);
        float f1 = 1;
        float f2 = MathHelper.lerp(partialTickTime, entitylivingbaseIn.oSquish, entitylivingbaseIn.squish) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        matrixStackIn.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull PhantasmEntity entity) {
        return new ResourceLocation(JITL.MODID, "textures/entity/frozen/phantasm.png");
    }
}
