package net.journey.client.render.model.mob.depths;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDarknessCrawler extends ModelBase {

	private ModelRenderer Body;
	private ModelRenderer LeftFrontThingy;
	private ModelRenderer LeftBackThingy;
	private ModelRenderer RightFrontThingy;
	private ModelRenderer RightBackThingy;
	private ModelRenderer LeftFrontLeg;
	private ModelRenderer RightFrontLeg;
	private ModelRenderer RightBackLeg;
	private ModelRenderer LeftBackLeg;
	private ModelRenderer FrontSmallSpike;
	private ModelRenderer FrontMediumSpike;
	private ModelRenderer MiddleSpike;
	private ModelRenderer BackMediumSpike;
	private ModelRenderer BackSmallSpike;

	public ModelDarknessCrawler() {
		textureWidth = 64;
		textureHeight = 32;

		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(-2.5F, -2.5F, -6F, 5, 5, 12);
		Body.setRotationPoint(0F, 15F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		LeftFrontThingy = new ModelRenderer(this, 0, 17);
		LeftFrontThingy.addBox(-1F, -1F, -1F, 4, 2, 2);
		LeftFrontThingy.setRotationPoint(2F, 15F, -3F);
		LeftFrontThingy.setTextureSize(64, 32);
		LeftFrontThingy.mirror = true;
		setRotation(LeftFrontThingy, 0F, 0F, 0F);
		LeftBackThingy = new ModelRenderer(this, 0, 17);
		LeftBackThingy.addBox(-1F, -1F, -1F, 1, 2, 2);
		LeftBackThingy.setRotationPoint(3F, 15F, 3F);
		LeftBackThingy.setTextureSize(64, 32);
		LeftBackThingy.mirror = true;
		setRotation(LeftBackThingy, 0F, 0F, 0F);
		RightFrontThingy = new ModelRenderer(this, 0, 17);
		RightFrontThingy.addBox(-3F, -1F, -1F, 4, 2, 2);
		RightFrontThingy.setRotationPoint(-2F, 15F, -3F);
		RightFrontThingy.setTextureSize(64, 32);
		RightFrontThingy.mirror = true;
		setRotation(RightFrontThingy, 0F, 0F, 0F);
		RightBackThingy = new ModelRenderer(this, 0, 17);
		RightBackThingy.addBox(-3F, -1F, -1F, 1, 2, 2);
		RightBackThingy.setRotationPoint(0F, 15F, 3F);
		RightBackThingy.setTextureSize(64, 32);
		RightBackThingy.mirror = true;
		setRotation(RightBackThingy, 0F, 0F, 0F);
		LeftFrontLeg = new ModelRenderer(this, 12, 17);
		LeftFrontLeg.addBox(0F, -4F, -1F, 2, 13, 2);
		LeftFrontLeg.setRotationPoint(5F, 15F, -3F);
		LeftFrontLeg.setTextureSize(64, 32);
		LeftFrontLeg.mirror = true;
		setRotation(LeftFrontLeg, 0F, 0F, 0F);
		RightFrontLeg = new ModelRenderer(this, 12, 17);
		RightFrontLeg.addBox(-2F, -4F, -1F, 2, 13, 2);
		RightFrontLeg.setRotationPoint(-5F, 15F, -3F);
		RightFrontLeg.setTextureSize(64, 32);
		RightFrontLeg.mirror = true;
		setRotation(RightFrontLeg, 0F, 0F, 0F);
		RightBackLeg = new ModelRenderer(this, 12, 17);
		RightBackLeg.addBox(-1F, -4F, -1F, 2, 13, 2);
		RightBackLeg.setRotationPoint(-4F, 15F, 3F);
		RightBackLeg.setTextureSize(64, 32);
		RightBackLeg.mirror = true;
		setRotation(RightBackLeg, 0F, 0F, 0F);
		LeftBackLeg = new ModelRenderer(this, 12, 17);
		LeftBackLeg.addBox(-1F, -4F, -1F, 2, 13, 2);
		LeftBackLeg.setRotationPoint(4F, 15F, 3F);
		LeftBackLeg.setTextureSize(64, 32);
		LeftBackLeg.mirror = true;
		setRotation(LeftBackLeg, 0F, 0F, 0F);
		FrontSmallSpike = new ModelRenderer(this, 20, 17);
		FrontSmallSpike.addBox(-0.5F, -4.5F, -0.5F, 1, 5, 1);
		FrontSmallSpike.setRotationPoint(0F, 13F, -4F);
		FrontSmallSpike.setTextureSize(64, 32);
		FrontSmallSpike.mirror = true;
		setRotation(FrontSmallSpike, 0F, 0F, 0F);
		FrontMediumSpike = new ModelRenderer(this, 24, 17);
		FrontMediumSpike.addBox(-0.5F, -5.5F, -0.5F, 1, 6, 1);
		FrontMediumSpike.setRotationPoint(0F, 13F, -2F);
		FrontMediumSpike.setTextureSize(64, 32);
		FrontMediumSpike.mirror = true;
		setRotation(FrontMediumSpike, 0F, 0F, 0F);
		MiddleSpike = new ModelRenderer(this, 28, 17);
		MiddleSpike.addBox(-0.5F, -6.5F, -0.5F, 1, 7, 1);
		MiddleSpike.setRotationPoint(0F, 13F, 0F);
		MiddleSpike.setTextureSize(64, 32);
		MiddleSpike.mirror = true;
		setRotation(MiddleSpike, 0F, 0F, 0F);
		BackMediumSpike = new ModelRenderer(this, 24, 17);
		BackMediumSpike.addBox(-0.5F, -5.5F, -0.5F, 1, 6, 1);
		BackMediumSpike.setRotationPoint(0F, 13F, 2F);
		BackMediumSpike.setTextureSize(64, 32);
		BackMediumSpike.mirror = true;
		setRotation(BackMediumSpike, 0F, 0F, 0F);
		BackSmallSpike = new ModelRenderer(this, 20, 17);
		BackSmallSpike.addBox(-0.5F, -4.5F, -0.5F, 1, 5, 1);
		BackSmallSpike.setRotationPoint(0F, 13F, 4F);
		BackSmallSpike.setTextureSize(64, 32);
		BackSmallSpike.mirror = true;
		setRotation(BackSmallSpike, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Body.render(f5);
		LeftFrontThingy.render(f5);
		LeftBackThingy.render(f5);
		RightFrontThingy.render(f5);
		RightBackThingy.render(f5);
		LeftFrontLeg.render(f5);
		RightFrontLeg.render(f5);
		RightBackLeg.render(f5);
		LeftBackLeg.render(f5);
		FrontSmallSpike.render(f5);
		FrontMediumSpike.render(f5);
		MiddleSpike.render(f5);
		BackMediumSpike.render(f5);
		BackSmallSpike.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.RightBackLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LeftBackLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.RightFrontLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.LeftFrontLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}
}