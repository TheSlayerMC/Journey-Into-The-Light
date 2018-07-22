package net.journey.client.render.model.mob.nether;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelReaper extends ModelBase {

    ModelRenderer head;
    ModelRenderer shoulderthing;
    ModelRenderer rib1;
    ModelRenderer rib2;
    ModelRenderer spine;
    ModelRenderer spine2;
    ModelRenderer wingthing;
    ModelRenderer wingthing1;
    ModelRenderer wingthing2;
    ModelRenderer wingthing3;
    ModelRenderer wingthing4;
    ModelRenderer wingthing5;
    ModelRenderer wingthing6;
    ModelRenderer wingend;
    ModelRenderer wingend1;
    ModelRenderer wingend2;
    ModelRenderer wingend3;
    ModelRenderer wingend4;
    ModelRenderer wingend5;
    ModelRenderer wingend6;

	public ModelReaper() {
	      textureWidth = 64;
	      textureHeight = 64;
	    
	      head = new ModelRenderer(this, 0, 0);
	      head.addBox(-4F, -8F, -4F, 8, 8, 8);
	      head.setRotationPoint(0F, 2F, 0F);
	      head.setTextureSize(64, 64);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      shoulderthing = new ModelRenderer(this, 16, 20);
	      shoulderthing.addBox(-4F, 0F, -2F, 16, 3, 4);
	      shoulderthing.setRotationPoint(-4F, 0F, 0F);
	      shoulderthing.setTextureSize(64, 64);
	      shoulderthing.mirror = true;
	      setRotation(shoulderthing, 0F, 0F, 0F);
	      rib1 = new ModelRenderer(this, 0, 46);
	      rib1.addBox(-2F, 0F, -2F, 12, 2, 2);
	      rib1.setRotationPoint(-4F, 4F, 1F);
	      rib1.setTextureSize(64, 64);
	      rib1.mirror = true;
	      setRotation(rib1, 0F, 0F, 0F);
	      rib2 = new ModelRenderer(this, 0, 46);
	      rib2.addBox(0F, 0F, 0F, 12, 2, 2);
	      rib2.setRotationPoint(-6F, 7F, -1F);
	      rib2.setTextureSize(64, 64);
	      rib2.mirror = true;
	      setRotation(rib2, 0F, 0F, 0F);
	      spine = new ModelRenderer(this, 0, 20);
	      spine.addBox(-2F, 0F, -2F, 3, 6, 3);
	      spine.setRotationPoint(0.5F, 3F, 0.5F);
	      spine.setTextureSize(64, 64);
	      spine.mirror = true;
	      setRotation(spine, 0F, 0F, 0F);
	      spine2 = new ModelRenderer(this, 0, 36);
	      spine2.addBox(0F, 0F, 0F, 3, 6, 3);
	      spine2.setRotationPoint(-1.5F, 9.2F, -1.5F);
	      spine2.setTextureSize(64, 64);
	      spine2.mirror = true;
	      setRotation(spine2, 0.6283185F, 0F, 0F);
	      wingthing = new ModelRenderer(this, 40, 32);
	      wingthing.addBox(-3F, -2F, -2F, 3, 12, 0);
	      wingthing.setRotationPoint(-5F, 1F, 2F);
	      wingthing.setTextureSize(64, 64);
	      wingthing.mirror = true;
	      setRotation(wingthing, 0F, 0F, 0.6981317F);
	      wingthing1 = new ModelRenderer(this, 40, 32);
	      wingthing1.addBox(0F, -2F, -2F, 3, 12, 0);
	      wingthing1.setRotationPoint(5F, 1F, 2F);
	      wingthing1.setTextureSize(64, 64);
	      wingthing1.mirror = true;
	      setRotation(wingthing1, 0F, 0F, -0.6981317F);
	      wingthing2 = new ModelRenderer(this, 40, 32);
	      wingthing2.addBox(0F, 0F, 0F, 3, 12, 0);
	      wingthing2.setRotationPoint(-2F, -2F, 0F);
	      wingthing2.setTextureSize(64, 64);
	      wingthing2.mirror = true;
	      setRotation(wingthing2, 0F, 0F, 1.570796F);
	      wingthing3 = new ModelRenderer(this, 40, 32);
	      wingthing3.addBox(0F, 0F, 0F, 3, 12, 0);
	      wingthing3.setRotationPoint(14F, -2F, 0F);
	      wingthing3.setTextureSize(64, 64);
	      wingthing3.mirror = true;
	      setRotation(wingthing3, 0F, 0F, 1.570796F);
	      wingthing4 = new ModelRenderer(this, 40, 32);
	      wingthing4.addBox(0F, 0F, 0F, 3, 12, 0);
	      wingthing4.setRotationPoint(-4F, 0F, 0F);
	      wingthing4.setTextureSize(64, 64);
	      wingthing4.mirror = true;
	      setRotation(wingthing4, 0F, 0F, 2.443461F);
	      wingthing5 = new ModelRenderer(this, 40, 32);
	      wingthing5.addBox(0F, 0F, 0F, 3, 12, 0);
	      wingthing5.setRotationPoint(6F, 2F, 0F);
	      wingthing5.setTextureSize(64, 64);
	      wingthing5.mirror = true;
	      setRotation(wingthing5, 0F, 0F, -2.443461F);
	      wingthing6 = new ModelRenderer(this, 40, 32);
	      wingthing6.addBox(0F, 0F, 0F, 3, 12, 0);
	      wingthing6.setRotationPoint(-1.5F, -12F, 0F);
	      wingthing6.setTextureSize(64, 64);
	      wingthing6.mirror = true;
	      setRotation(wingthing6, 0F, 0F, 0F);
	      wingend = new ModelRenderer(this, 32, 0);
	      wingend.addBox(0F, 0F, 0F, 4, 4, 4);
	      wingend.setRotationPoint(9F, -2.5F, -2F);
	      wingend.setTextureSize(64, 64);
	      wingend.mirror = true;
	      setRotation(wingend, 0F, 0F, 0F);
	      wingend1 = new ModelRenderer(this, 32, 0);
	      wingend1.addBox(0F, 0F, 0F, 4, 4, 4);
	      wingend1.setRotationPoint(-13F, -2.5F, -2F);
	      wingend1.setTextureSize(64, 64);
	      wingend1.mirror = true;
	      setRotation(wingend1, 0F, 0F, 0F);
	      wingend2 = new ModelRenderer(this, 32, 0);
	      wingend2.addBox(0F, 0F, 0F, 4, 4, 4);
	      wingend2.setRotationPoint(-2F, -11F, -2F);
	      wingend2.setTextureSize(64, 64);
	      wingend2.mirror = true;
	      setRotation(wingend2, 0F, 0F, 0F);
	      wingend3 = new ModelRenderer(this, 32, 0);
	      wingend3.addBox(0F, 0F, 0F, 4, 4, 4);
	      wingend3.setRotationPoint(11F, 3F, -2F);
	      wingend3.setTextureSize(64, 64);
	      wingend3.mirror = true;
	      setRotation(wingend3, 0F, 0F, 0.8726646F);
	      wingend4 = new ModelRenderer(this, 32, 0);
	      wingend4.addBox(0F, 0F, 0F, 4, 4, 4);
	      wingend4.setRotationPoint(-14F, 6F, -2F);
	      wingend4.setTextureSize(64, 64);
	      wingend4.mirror = true;
	      setRotation(wingend4, 0F, 0F, -0.8726646F);
	      wingend5 = new ModelRenderer(this, 32, 0);
	      wingend5.addBox(0F, 0F, 0F, 4, 4, 4);
	      wingend5.setRotationPoint(-11F, -9F, -2F);
	      wingend5.setTextureSize(64, 64);
	      wingend5.mirror = true;
	      setRotation(wingend5, 0F, 0F, 0.8726646F);
	      wingend6 = new ModelRenderer(this, 32, 0);
	      wingend6.addBox(0F, 0F, 0F, 4, 4, 4);
	      wingend6.setRotationPoint(11F, -9F, -2F);
	      wingend6.setTextureSize(64, 64);
	      wingend6.mirror = true;
	      setRotation(wingend6, 0F, 0F, 0.6981317F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    head.render(f5);
	    shoulderthing.render(f5);
	    rib1.render(f5);
	    rib2.render(f5);
	    spine.render(f5);
	    spine2.render(f5);
	    wingthing.render(f5);
	    wingthing1.render(f5);
	    wingthing2.render(f5);
	    wingthing3.render(f5);
	    wingthing4.render(f5);
	    wingthing5.render(f5);
	    wingthing6.render(f5);
	    wingend.render(f5);
	    wingend1.render(f5);
	    wingend2.render(f5);
	    wingend3.render(f5);
	    wingend4.render(f5);
	    wingend5.render(f5);
	    wingend6.render(f5);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
		this.head.rotateAngleX = f5 / (180F / (float)Math.PI);

	}
}