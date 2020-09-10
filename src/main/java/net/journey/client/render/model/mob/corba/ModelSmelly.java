package net.journey.client.render.model.mob.corba;

import net.journey.entity.mob.corba.EntitySmelly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelSmelly extends ModelBase {

	private final ModelRenderer arms;
	private final ModelRenderer arms2;
	private final ModelRenderer arms1;
	private final ModelRenderer legs;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer ear1;
	private final ModelRenderer ear2;

	public ModelSmelly() {
		textureHeight = 256;
		textureWidth = 256;
		arms = new ModelRenderer(this);
		arms.setRotationPoint(0.0F, 24.0F, 0.0F);


		arms2 = new ModelRenderer(this);
		arms2.setRotationPoint(14.0F, -42.0F, -1.0F);
		arms.addChild(arms2);
		arms2.cubeList.add(new ModelBox(arms2, 0, 38, 2.0F, 6.0F, -4.0F, 8, 24, 8, 0.0F, false));
		arms2.cubeList.add(new ModelBox(arms2, 88, 0, 0.0F, -6.0F, -6.0F, 12, 12, 12, 0.0F, false));

		arms1 = new ModelRenderer(this);
		arms1.setRotationPoint(-14.0F, -42.0F, 0.0F);
		arms.addChild(arms1);
		arms1.cubeList.add(new ModelBox(arms1, 88, 24, -12.0F, -6.0F, -7.0F, 12, 12, 12, 0.0F, false));
		arms1.cubeList.add(new ModelBox(arms1, 48, 94, -10.0F, 6.0F, -5.0F, 8, 24, 8, 0.0F, false));
		arms1.cubeList.add(new ModelBox(arms1, 0, 38, -8.0F, 24.0F, -27.0F, 4, 4, 32, 0.0F, false));
		arms1.cubeList.add(new ModelBox(arms1, 0, 74, -12.0F, 14.0F, -39.0F, 12, 24, 12, 0.0F, false));

		legs = new ModelRenderer(this);
		legs.setRotationPoint(0.0F, 24.0F, 0.0F);


		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-6.0F, -16.0F, 0.0F);
		legs.addChild(leg1);
		leg1.cubeList.add(new ModelBox(leg1, 0, 110, -4.0F, 0.0F, -4.0F, 8, 16, 8, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(6.0F, -16.0F, 0.0F);
		legs.addChild(leg2);
		leg2.cubeList.add(new ModelBox(leg2, 80, 94, -4.0F, 0.0F, -4.0F, 8, 16, 8, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -14.0F, -50.0F, -9.0F, 28, 22, 16, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 60, 70, -11.0F, -28.0F, -6.0F, 22, 12, 12, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -15.0F, -8.0F);
		head.cubeList.add(new ModelBox(head, 40, 38, -9.0F, -14.0F, -10.0F, 16, 16, 16, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 104, 48, -4.0F, -7.0F, -16.0F, 6, 12, 6, 0.0F, false));

		ear1 = new ModelRenderer(this);
		ear1.setRotationPoint(-9.8939F, -10.0404F, -1.0F);
		head.addChild(ear1);
		setRotationAngle(ear1, 0.0F, 0.0F, 0.3491F);
		ear1.cubeList.add(new ModelBox(ear1, 0, 0, -0.5F, -4.0F, -3.0F, 1, 8, 6, 0.0F, false));

		ear2 = new ModelRenderer(this);
		ear2.setRotationPoint(7.5F, -13.0F, -1.0F);
		head.addChild(ear2);
		setRotationAngle(ear2, 0.0F, 0.0F, -0.3491F);
		ear2.cubeList.add(new ModelBox(ear2, 40, 40, -1.0383F, -0.9831F, -3.0F, 1, 8, 6, 0.0F, false));

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		arms.render(f5);
		legs.render(f5);
		body.render(f5);
		head.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
		this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
		this.leg2.rotateAngleX = -1.5F * this.triangleWave(par1, 13.0F) * par2;
		this.leg1.rotateAngleX = 1.5F * this.triangleWave(par1, 13.0F) * par2;
		this.leg2.rotateAngleY = 0.0F;
		this.leg1.rotateAngleY = 0.0F;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
		EntitySmelly smelly = (EntitySmelly) entitylivingbaseIn;
		int i = smelly.getAttackTimer();

		if (i > 0) {
			this.arms1.rotateAngleX = -2.0F + 1.5F * this.triangleWave((float) i - partialTickTime, 10.0F);
		} else {
			this.arms1.rotateAngleX = (-0.2F + 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
		}
		this.arms2.rotateAngleX = (-0.2F - 1.5F * this.triangleWave(limbSwing, 13.0F)) * limbSwingAmount;
	}

	private float triangleWave(float par1, float par2) {
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}
}
