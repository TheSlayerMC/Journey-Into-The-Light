package net.jitl.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.jitl.client.render.JRenderTypes;
import net.jitl.common.entity.EssenciaBoltEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

//Copy of default LightningBoltRenderer, but with with red color shift and shrunk size
@OnlyIn(Dist.CLIENT)
public class EssenciaBoltRenderer extends EntityRenderer<EssenciaBoltEntity> {
	public EssenciaBoltRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	public void render(EssenciaBoltEntity entityIn, float entityYaw, float partialTicks, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int packedLightIn) {
		float[] afloat = new float[8];
		float[] afloat1 = new float[8];
		float f = 0.0F;
		float f1 = 0.0F;
		Random random = new Random(entityIn.seed);

		for (int i = 7; i >= 0; --i) {
			afloat[i] = f;
			afloat1[i] = f1;
			f += (float) (random.nextInt(1));
			f += (float) (random.nextInt(1));
			f1 += (float) (random.nextInt(1));
		}

		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(JRenderTypes.essenciaBolt());
		Matrix4f matrix4f = matrixStackIn.last().pose();

		for (int j = 0; j < 4; ++j) {
			Random random1 = new Random(entityIn.seed);

			for (int k = 0; k < 3; ++k) {
				int l = 7;
				int i1 = 0;
				if (k > 0) {
					l = 7 - k;
				}

				if (k > 0) {
					i1 = l - 2;
				}

				float f2 = afloat[l] - f;
				float f3 = afloat1[l] - f1;

				for (int j1 = l; j1 >= i1; --j1) {
					float f4 = f2;
					float f5 = f3;
					f2 += (float) (random1.nextInt(1));
					f3 += (float) (random1.nextInt(1));

					float f10 = 0.1F + (float) j * 0.2F;
					if (k == 0) {
						f10 = (float) ((double) f10 * ((double) j1 * 0.1D + 1.0D));
					}

					float f11 = 0.1F + (float) j * 0.2F;
					if (k == 0) {
						f11 *= (float) (j1 - 1) * 0.1F + 1.0F;
					}

					quad(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, f10, f11, false, false, true, false);
					quad(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, f10, f11, true, false, true, true);
					quad(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, f10, f11, true, true, false, true);
					quad(matrix4f, ivertexbuilder, f2, f3, j1, f4, f5, f10, f11, false, true, false, false);
				}
			}
		}

	}

	private static void quad(Matrix4f matrix4f_, IVertexBuilder vertexBuilder_, float float2_, float float3_, int int_, float float4_, float float5_, float float_, float float1_, boolean boolean_, boolean boolean1_, boolean boolean2_, boolean boolean3_) {
		vertexBuilder_.vertex(matrix4f_, float2_ + (boolean_ ? float1_ : -float1_) / 8 + 0.5F, (float) (int_ * 16), float3_ + (boolean1_ ? float1_ : -float1_) / 8 + 0.5F).color((float) 5.0, (float) 0.25, (float) 0.1, 1.0F).endVertex();
		vertexBuilder_.vertex(matrix4f_, float4_ + (boolean_ ? float_ : -float_) / 8 + 0.5F, (float) ((int_ + 1) * 16), float5_ + (boolean1_ ? float_ : -float_) / 8 + 0.5F).color((float) 5.0, (float) 0.25, (float) 0.1, 1.0F).endVertex();
		vertexBuilder_.vertex(matrix4f_, float4_ + (boolean2_ ? float_ : -float_) / 8 + 0.5F, (float) ((int_ + 1) * 16), float5_ + (boolean3_ ? float_ : -float_) / 8 + 0.5F).color((float) 5.0, (float) 0.25, (float) 0.1, 1.0F).endVertex();
		vertexBuilder_.vertex(matrix4f_, float2_ + (boolean2_ ? float1_ : -float1_) / 8 + 0.5F, (float) (int_ * 16), float3_ + (boolean3_ ? float1_ : -float1_) / 8 + 0.5F).color((float) 5.0, (float) 0.25, (float) 0.1, 1.0F).endVertex();
	}

	/**
	 * Returns the location of an entity's texture.
	 */
	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull EssenciaBoltEntity entityIn) {
		return new ResourceLocation("textures/atlas/blocks.png");
	}
}
