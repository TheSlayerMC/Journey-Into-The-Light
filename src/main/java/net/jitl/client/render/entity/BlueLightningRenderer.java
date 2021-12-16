package net.jitl.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import java.util.Random;

import net.jitl.common.entity.BlueLightningEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class BlueLightningRenderer extends EntityRenderer<BlueLightningEntity> {

    public BlueLightningRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void render(BlueLightningEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        float[] afloat = new float[8];
        float[] afloat1 = new float[8];
        float f = 0.0F;
        float f1 = 0.0F;
        Random random = new Random(entityIn.seed);

        for (int i = 7; i >= 0; --i) {
            afloat[i] = f;
            afloat1[i] = f1;
            f += (float) (random.nextInt(11) - 5);
            f1 += (float) (random.nextInt(11) - 5);
        }

        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.lightning());
        Matrix4f matrix4f = matrixStackIn.last().pose();

        for (int layer = 0; layer < 4; ++layer) {
            Random random1 = new Random(entityIn.seed);

            for (int rayIndex = 0; rayIndex < 3; ++rayIndex) {
                int l = 7;
                int i1 = 0;
                if (rayIndex > 0) {
                    l = 7 - rayIndex;
                }

                if (rayIndex > 0) {
                    i1 = l - 2;
                }

                float f2 = afloat[l] - f;
                float f3 = afloat1[l] - f1;

                for (int j1 = l; j1 >= i1; --j1) {
                    boolean mainRay = rayIndex == 0;

                    float f4 = f2;
                    float f5 = f3;
                    if (mainRay) {
                        f2 += (float) (random1.nextInt(11) - 5);
                        f3 += (float) (random1.nextInt(11) - 5);
                    } else {
                        f2 += (float) (random1.nextInt(31) - 15);
                        f3 += (float) (random1.nextInt(31) - 15);
                    }

                    float oneDirectionExpansion = 0.1F + (float) layer * 0.2F;
                    if (mainRay) {
                        oneDirectionExpansion = (float) ((double) oneDirectionExpansion * ((double) j1 * 0.1D + 1.0D));
                    }

                    float anotherDirectionExpansion = 0.1F + (float) layer * 0.2F;
                    if (mainRay) {
                        anotherDirectionExpansion *= (float) (j1 - 1) * 0.1F + 1.0F;
                    }

                    // shrinking vanilla expansions
                    oneDirectionExpansion *= 1F / 3;
                    anotherDirectionExpansion *= 1F / 3;

                    float red = 0.0F;
                    float green = 0.15F;
                    float blue = 1.0F;
                    float alpha = 1.0F;

                    quad(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, red, green, blue, alpha, oneDirectionExpansion, anotherDirectionExpansion, false, false, true, false);
                    quad(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, red, green, blue, alpha, oneDirectionExpansion, anotherDirectionExpansion, true, false, true, true);
                    quad(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, red, green, blue, alpha, oneDirectionExpansion, anotherDirectionExpansion, true, true, false, true);
                    quad(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, red, green, blue, alpha, oneDirectionExpansion, anotherDirectionExpansion, false, true, false, false);
                }
            }
        }
    }

    private static void quad(Matrix4f matrix, IVertexBuilder builder, float startCenterX, float startCenterZ, int startY, float endCenterX, float endCenterZ, float r, float g, float b, float alpha, float oneDirectionExpansion, float anotherDirectionExpansion, boolean boolean_, boolean boolean1_, boolean boolean2_, boolean boolean3_) {
        builder.vertex(matrix, startCenterX + (boolean_ ? anotherDirectionExpansion : -anotherDirectionExpansion) + 0.5F, (float) (startY * 16), startCenterZ + (boolean1_ ? anotherDirectionExpansion : -anotherDirectionExpansion) + 0.5F).color(r, g, b, alpha).endVertex();
        builder.vertex(matrix, endCenterX + (boolean_ ? oneDirectionExpansion : -oneDirectionExpansion) + 0.5F, (float) ((startY + 1) * 16), endCenterZ + (boolean1_ ? oneDirectionExpansion : -oneDirectionExpansion) + 0.5F).color(r, g, b, alpha).endVertex();
        builder.vertex(matrix, endCenterX + (boolean2_ ? oneDirectionExpansion : -oneDirectionExpansion) + 0.5F, (float) ((startY + 1) * 16), endCenterZ + (boolean3_ ? oneDirectionExpansion : -oneDirectionExpansion) + 0.5F).color(r, g, b, alpha).endVertex();
        builder.vertex(matrix, startCenterX + (boolean2_ ? anotherDirectionExpansion : -anotherDirectionExpansion) + 0.5F, (float) (startY * 16), startCenterZ + (boolean3_ ? anotherDirectionExpansion : -anotherDirectionExpansion) + 0.5F).color(r, g, b, alpha).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(BlueLightningEntity entityIn) {
        return AtlasTexture.LOCATION_BLOCKS;
    }

}