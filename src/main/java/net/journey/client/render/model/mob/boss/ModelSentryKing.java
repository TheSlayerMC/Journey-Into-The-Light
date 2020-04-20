package net.journey.client.render.model.mob.boss;

import net.journey.entity.mob.boss.EntitySentryKing;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSentryKing extends ModelBase {

	ModelRenderer Head1;
	ModelRenderer Head2;
	ModelRenderer Head3;
	ModelRenderer Head4;
	ModelRenderer juncture1;
	ModelRenderer juncture2;
	ModelRenderer Head5;
	ModelRenderer Head6;
	ModelRenderer Head7;
	ModelRenderer juncture3;
	ModelRenderer juncture4;
	ModelRenderer CentralHead;
	ModelRenderer juncture5;
	ModelRenderer juncture6;
	ModelRenderer Head8;
	ModelRenderer juncture7;
	ModelRenderer juncture8;

	public ModelSentryKing()  {
		textureWidth = 256;
		textureHeight = 128;

		Head1 = new ModelRenderer(this, 0, 0);
		Head1.addBox(0F, 0F, 0F, 16, 16, 16);
		Head1.setRotationPoint(-8F, -8F, -8F);
		Head1.setTextureSize(256, 128);
		Head1.mirror = true;
		setRotation(Head1, 0F, 0F, 0F);
		Head2 = new ModelRenderer(this, 0, 0);
		Head2.addBox(0F, 0F, 0F, 16, 16, 16);
		Head2.setRotationPoint(8F, -24F, -8F);
		Head2.setTextureSize(256, 128);
		Head2.mirror = true;
		setRotation(Head2, 0F, 0F, 0F);
		Head3 = new ModelRenderer(this, 0, 0);
		Head3.addBox(0F, 0F, 0F, 16, 16, 16);
		Head3.setRotationPoint(-24F, -24F, -8F);
		Head3.setTextureSize(256, 128);
		Head3.mirror = true;
		setRotation(Head3, 0F, 0F, 0F);
		Head4 = new ModelRenderer(this, 0, 0);
		Head4.addBox(0F, 0F, 0F, 16, 16, 16);
		Head4.setRotationPoint(-24F, -56F, -8F);
		Head4.setTextureSize(256, 128);
		Head4.mirror = true;
		setRotation(Head4, 0F, 0F, 0F);
		juncture1 = new ModelRenderer(this, 64, 9);
		juncture1.addBox(0F, 0F, 0F, 16, 4, 4);
		juncture1.setRotationPoint(-15F, -11F, -2F);
		juncture1.setTextureSize(256, 128);
		juncture1.mirror = true;
		setRotation(juncture1, 0F, 0F, 0.8726646F);
		juncture2 = new ModelRenderer(this, 64, 9);
		juncture2.addBox(0F, 0F, 0F, 16, 4, 4);
		juncture2.setRotationPoint(5F, 1F, -2F);
		juncture2.setTextureSize(256, 128);
		juncture2.mirror = true;
		setRotation(juncture2, 0F, 0F, -0.8726646F);
		Head5 = new ModelRenderer(this, 0, 0);
		Head5.addBox(0F, 0F, 0F, 16, 16, 16);
		Head5.setRotationPoint(24F, -40F, -8F);
		Head5.setTextureSize(256, 128);
		Head5.mirror = true;
		setRotation(Head5, 0F, 0F, 0F);
		Head6 = new ModelRenderer(this, 0, 0);
		Head6.addBox(0F, 0F, 0F, 16, 16, 16);
		Head6.setRotationPoint(-8F, -72F, -8F);
		Head6.setTextureSize(256, 128);
		Head6.mirror = true;
		setRotation(Head6, 0F, 0F, 0F);
		Head7 = new ModelRenderer(this, 0, 0);
		Head7.addBox(0F, 0F, 0F, 16, 16, 16);
		Head7.setRotationPoint(8F, -56F, -8F);
		Head7.setTextureSize(256, 128);
		Head7.mirror = true;
		setRotation(Head7, 0F, 0F, 0F);
		juncture3 = new ModelRenderer(this, 64, 9);
		juncture3.addBox(0F, 0F, 0F, 16, 4, 4);
		juncture3.setRotationPoint(8F, -68F, -2F);
		juncture3.setTextureSize(256, 128);
		juncture3.mirror = true;
		setRotation(juncture3, 0F, 0F, 0.8726646F);
		juncture4 = new ModelRenderer(this, 64, 9);
		juncture4.addBox(0F, 0F, 0F, 16, 4, 4);
		juncture4.setRotationPoint(-18F, -56F, -2F);
		juncture4.setTextureSize(256, 128);
		juncture4.mirror = true;
		setRotation(juncture4, 0F, 0F, -0.8726646F);
		CentralHead = new ModelRenderer(this, 0, 32);
		CentralHead.addBox(0F, 0F, 0F, 16, 16, 16);
		CentralHead.setRotationPoint(-8F, -40F, -8F);
		CentralHead.setTextureSize(256, 128);
		CentralHead.mirror = true;
		setRotation(CentralHead, 0F, 0F, 0F);
		juncture5 = new ModelRenderer(this, 64, 9);
		juncture5.addBox(0F, 0F, 0F, 16, 4, 4);
		juncture5.setRotationPoint(21F, -15F, -2F);
		juncture5.setTextureSize(256, 128);
		juncture5.mirror = true;
		setRotation(juncture5, 0F, 0F, -0.8726646F);
		juncture6 = new ModelRenderer(this, 64, 9);
		juncture6.addBox(0F, 0F, 0F, 16, 4, 4);
		juncture6.setRotationPoint(-31F, -27F, -2F);
		juncture6.setTextureSize(256, 128);
		juncture6.mirror = true;
		setRotation(juncture6, 0F, 0F, 0.8726646F);
		Head8 = new ModelRenderer(this, 0, 0);
		Head8.addBox(0F, 0F, 0F, 16, 16, 16);
		Head8.setRotationPoint(-40F, -40F, -8F);
		Head8.setTextureSize(256, 128);
		Head8.mirror = true;
		setRotation(Head8, 0F, 0F, 0F);
		juncture7 = new ModelRenderer(this, 64, 0);
		juncture7.addBox(0F, 0F, 0F, 16, 4, 4);
		juncture7.setRotationPoint(8F, -34F, -2F);
		juncture7.setTextureSize(256, 128);
		juncture7.mirror = true;
		setRotation(juncture7, 0F, 0F, 0F);
		juncture8 = new ModelRenderer(this, 64, 0);
		juncture8.addBox(0F, 0F, 0F, 16, 4, 4);
		juncture8.setRotationPoint(-24F, -34F, -2F);
		juncture8.setTextureSize(256, 128);
		juncture8.mirror = true;
		setRotation(juncture8, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		EntitySentryKing king = (EntitySentryKing)entity;
		int healthDecimal = (int)king.getHealth() / king.getMaxHealth;
		if(healthDecimal >= 0.9) {
			Head1.render(f5);
			Head2.render(f5);
			Head3.render(f5);
			Head4.render(f5);
			juncture1.render(f5);
			juncture2.render(f5);
			Head5.render(f5);
			Head6.render(f5);
			Head7.render(f5);
			juncture3.render(f5);
			juncture4.render(f5);
			CentralHead.render(f5);
			juncture5.render(f5);
			juncture6.render(f5);
			Head8.render(f5);
			juncture7.render(f5);
			juncture8.render(f5);
		}
		else if(healthDecimal >= 0.8) {
			Head2.render(f5);
			Head3.render(f5);
			Head4.render(f5);
			Head5.render(f5);
			Head6.render(f5);
			Head7.render(f5);
			juncture3.render(f5);
			juncture4.render(f5);
			CentralHead.render(f5);
			juncture5.render(f5);
			juncture6.render(f5);
			Head8.render(f5);
			juncture7.render(f5);
			juncture8.render(f5);
		}
		else if(healthDecimal >= 0.7) {
			Head3.render(f5);
			Head4.render(f5);
			Head5.render(f5);
			Head6.render(f5);
			Head7.render(f5);
			juncture3.render(f5);
			juncture4.render(f5);
			CentralHead.render(f5);
			juncture6.render(f5);
			Head8.render(f5);
			juncture7.render(f5);
			juncture8.render(f5);
		}
		else if(healthDecimal >= 0.6) {
			Head4.render(f5);
			Head5.render(f5);
			Head6.render(f5);
			Head7.render(f5);
			juncture3.render(f5);
			juncture4.render(f5);
			CentralHead.render(f5);
			Head8.render(f5);
			juncture7.render(f5);
			juncture8.render(f5);
		}
		else if(healthDecimal >= 0.5) {
			Head5.render(f5);
			Head6.render(f5);
			Head7.render(f5);
			juncture3.render(f5);
			CentralHead.render(f5);
			Head8.render(f5);
			juncture7.render(f5);
			juncture8.render(f5);
		}
		else if(healthDecimal >= 0.4) {
			Head5.render(f5);
			Head7.render(f5);
			CentralHead.render(f5);
			Head8.render(f5);
			juncture7.render(f5);
			juncture8.render(f5);
		}
		else if(healthDecimal >= 0.3) {
			Head5.render(f5);
			CentralHead.render(f5);
			Head8.render(f5);
			juncture7.render(f5);
			juncture8.render(f5);
		}
		else if(healthDecimal >= 0.2) {
			CentralHead.render(f5);
			Head8.render(f5);
			juncture8.render(f5);
		}
		else if(healthDecimal >= 0) {
			CentralHead.render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
