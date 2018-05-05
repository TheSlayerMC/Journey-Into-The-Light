package net.journey.client.render.model.mob.nether;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMiniGhast extends ModelBase {

	public ModelRenderer body;
	public ModelRenderer[] tentacles = new ModelRenderer[9];

	public ModelMiniGhast() {
        this.textureWidth = 64;
        this.textureHeight = 32;
		int i = -16;
		this.body = new ModelRenderer(this, 0, 0);
		this.body.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
		this.body.rotationPointY += 24 + i;
		Random random = new Random(1660L);

		for (int j = 0; j < this.tentacles.length; ++j) {
			this.tentacles[j] = new ModelRenderer(this, 0, 0);
			float f = ((j % 3 - j / 3 % 2 * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
			float f1 = (j / 3 / 2.0F * 2.0F - 1.0F) * 5.0F;
			int k = random.nextInt(7) + 8;
			this.tentacles[j].addBox(-1.0F, 0.0F, -1.0F, 2, k, 2);
			this.tentacles[j].rotationPointX = f;
			this.tentacles[j].rotationPointZ = f1;
			this.tentacles[j].rotationPointY = 31 + i;
		}
	}

	@Override
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entityIn) {
		for (int i = 0; i < this.tentacles.length; ++i) {
			this.tentacles[i].rotateAngleX = 0.2F * MathHelper.sin(p_78087_3_ * 0.3F + i) + 0.4F;
		}
	}

	@Override
	public void render(Entity e, float par2, float par3, float par4, float par5, float par6, float scale) {
		this.setRotationAngles(par2, par3, par4, par5, par6, scale, e);
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, 0.6F, 0.0F);
		this.body.render(scale);

		for (ModelRenderer modelrenderer : this.tentacles) {
			modelrenderer.render(scale);
		}
		GlStateManager.popMatrix();
	}
}