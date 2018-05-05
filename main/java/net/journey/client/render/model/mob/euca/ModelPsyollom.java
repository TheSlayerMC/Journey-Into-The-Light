package net.journey.client.render.model.mob.euca;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelPsyollom extends ModelBase {

	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightarm;
	private ModelRenderer leftarm;
	private ModelRenderer rightleg;
	private ModelRenderer leftleg;
	private ModelRenderer rightarm2;
	private ModelRenderer leftarm2;
	private ModelRenderer left;
	private ModelRenderer right;

	public ModelPsyollom() {
		textureWidth = 64;
		textureHeight = 64;
		left = new ModelRenderer(this, 29, 0);
	      left.addBox(-1F, 0F, -1F, 2, 2, 2);
	      left.setRotationPoint(-10F, 0F, 0F);
	      left.setTextureSize(64, 64);
	      left.mirror = true;
	      setRotation(left, 0F, 0F, 0F);
	      right = new ModelRenderer(this, 29, 0);
	      right.addBox(-1F, 0F, -1F, 2, 2, 2);
	      right.setRotationPoint(10F, 0F, 0F);
	      right.setTextureSize(64, 64);
	      right.mirror = true;
	      setRotation(right, 0F, 0F, 0F);
	      head = new ModelRenderer(this, 0, 0);
	      head.addBox(-3F, -3F, -3F, 6, 6, 6);
	      head.setRotationPoint(0F, -3F, 0F);
	      head.setTextureSize(64, 64);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      body = new ModelRenderer(this, 3, 39);
	      body.addBox(0F, 0F, 0F, 14, 12, 4);
	      body.setRotationPoint(-7F, 0F, -2F);
	      body.setTextureSize(64, 64);
	      body.mirror = true;
	      setRotation(body, 0F, 0F, 0F);
	      rightarm = new ModelRenderer(this, 40, 16);
	      rightarm.addBox(-1F, 0F, -1F, 2, 18, 2);
	      rightarm.setRotationPoint(-8F, 0F, 0F);
	      rightarm.setTextureSize(64, 64);
	      rightarm.mirror = true;
	      setRotation(rightarm, 0F, 0F, 0F);
	      leftarm = new ModelRenderer(this, 40, 16);
	      leftarm.addBox(-1F, 0F, -1F, 2, 18, 2);
	      leftarm.setRotationPoint(8F, 0F, 0F);
	      leftarm.setTextureSize(64, 64);
	      leftarm.mirror = true;
	      setRotation(leftarm, 0F, 0F, 0F);
	      rightleg = new ModelRenderer(this, 4, 16);
	      rightleg.addBox(-1F, 0F, -1F, 2, 12, 2);
	      rightleg.setRotationPoint(-2F, 12F, 0F);
	      rightleg.setTextureSize(64, 64);
	      rightleg.mirror = true;
	      setRotation(rightleg, 0F, 0F, 0F);
	      leftleg = new ModelRenderer(this, 4, 16);
	      leftleg.addBox(-1F, 0F, -1F, 2, 12, 2);
	      leftleg.setRotationPoint(3F, 12F, 0F);
	      leftleg.setTextureSize(64, 64);
	      leftleg.mirror = true;
	      setRotation(leftleg, 0F, 0F, 0F);
	      rightarm2 = new ModelRenderer(this, 40, 16);
	      rightarm2.addBox(-1F, 0F, -1F, 2, 18, 2);
	      rightarm2.setRotationPoint(-12F, 0F, 0F);
	      rightarm2.setTextureSize(64, 64);
	      rightarm2.mirror = true;
	      setRotation(rightarm2, 0F, 0F, 0F);
	      leftarm2 = new ModelRenderer(this, 40, 16);
	      leftarm2.addBox(-1F, 0F, -1F, 2, 18, 2);
	      leftarm2.setRotationPoint(12F, 0F, 0F);
	      leftarm2.setTextureSize(64, 64);
	      leftarm2.mirror = true;
	      setRotation(leftarm2, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		head.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		rightarm2.render(f5);
		leftarm2.render(f5);
		left.render(f5);
		right.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
		this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.rightarm.rotateAngleZ = 0.0F;
		this.leftarm.rotateAngleZ = 0.0F;
		this.rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.rightarm2.rotateAngleZ = 0.0F;
		this.leftarm2.rotateAngleZ = 0.0F;
		this.left.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.right.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.right.rotateAngleZ = 0.0F;
		this.left.rotateAngleZ = 0.0F;
		this.rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.rightleg.rotateAngleY = 0.0F;
		this.leftleg.rotateAngleY = 0.0F;
		this.rightarm.rotateAngleY = 0.0F;
		this.leftarm.rotateAngleY = 0.0F;
	}
}