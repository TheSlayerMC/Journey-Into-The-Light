package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelWitheringBeast extends ModelBase {

    public ModelRenderer spine1;
    public ModelRenderer foot1;
    public ModelRenderer foot2;
    public ModelRenderer spine2;
    public ModelRenderer rib1;
    public ModelRenderer spine3;
    public ModelRenderer spine4;
    public ModelRenderer head;
    public ModelRenderer rib2;
    public ModelRenderer rib3;
    public ModelRenderer rib4;
    public ModelRenderer rib5;
    public ModelRenderer head_1;
    public ModelRenderer head_2;
    public ModelRenderer rib4_1;

	public ModelWitheringBeast() {
		this.textureWidth = 128;
        this.textureHeight = 128;
        this.head_2 = new ModelRenderer(this, 0, 0);
        this.head_2.setRotationPoint(-16.0F, -9.0F, 1.5F);
        this.head_2.addBox(-5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F);
        this.rib2 = new ModelRenderer(this, 80, 20);
        this.rib2.setRotationPoint(-9.0F, 1.0F, 0.5F);
        this.rib2.addBox(0.0F, 0.0F, 0.0F, 18, 2, 2, 0.0F);
        this.foot1 = new ModelRenderer(this, 80, 100);
        this.foot1.setRotationPoint(2.0F, 10.0F, 1.5F);
        this.foot1.addBox(0.0F, 0.0F, -3.0F, 6, 14, 6, 0.0F);
        this.foot2 = new ModelRenderer(this, 80, 100);
        this.foot2.setRotationPoint(1.8F, 10.0F, 1.5F);
        this.foot2.addBox(-10.0F, 0.0F, -3.0F, 6, 14, 6, 0.0F);
        this.head = new ModelRenderer(this, 0, 48);
        this.head.setRotationPoint(0.0F, -22.0F, -3.0F);
        this.head.addBox(-5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F);
        this.rib4_1 = new ModelRenderer(this, 80, 20);
        this.rib4_1.setRotationPoint(-9.0F, -5.0F, 0.5F);
        this.rib4_1.addBox(0.0F, 0.0F, 0.0F, 18, 2, 2, 0.0F);
        this.spine3 = new ModelRenderer(this, 0, 100);
        this.spine3.setRotationPoint(-1.5F, -19.3F, -2.55F);
        this.spine3.addBox(0.0F, 0.0F, 0.0F, 3, 4, 3, 0.0F);
        this.setRotation(spine3, 0.45378560551852565F, 0.0F, 0.0F);
        this.rib4 = new ModelRenderer(this, 80, 20);
        this.rib4.setRotationPoint(-9.0F, -2.0F, 0.5F);
        this.rib4.addBox(0.0F, 0.0F, 0.0F, 18, 2, 2, 0.0F);
        this.spine4 = new ModelRenderer(this, 0, 22);
        this.spine4.setRotationPoint(-1.5F, -22.1F, -5.05F);
        this.spine4.addBox(0.0F, 0.0F, 0.0F, 3, 4, 3, 0.0F);
        this.setRotation(spine4, 0.7330382858376184F, 0.0F, 0.0F);
        this.rib5 = new ModelRenderer(this, 0, 80);
        this.rib5.setRotationPoint(-18.0F, -10.0F, 0.0F);
        this.rib5.addBox(0.0F, 0.0F, 0.0F, 36, 3, 3, 0.0F);
        this.head_1 = new ModelRenderer(this, 0, 0);
        this.head_1.setRotationPoint(16.0F, -9.0F, 1.5F);
        this.head_1.addBox(-5.0F, -5.0F, -10.0F, 10, 10, 10, 0.0F);
        this.spine1 = new ModelRenderer(this, 0, 22);
        this.spine1.setRotationPoint(-1.5F, -12.0F, 0.0F);
        this.spine1.addBox(0.0F, 0.0F, 0.0F, 3, 22, 3, 0.0F);
        this.spine2 = new ModelRenderer(this, 0, 100);
        this.spine2.setRotationPoint(-1.5F, -15.8F, -0.85F);
        this.spine2.addBox(0.0F, 0.0F, 0.0F, 3, 4, 3, 0.0F);
        this.setRotation(spine2, 0.22689280275926282F, 0.0F, 0.0F);
        this.rib1 = new ModelRenderer(this, 60, 40);
        this.rib1.setRotationPoint(-9.0F, 8.0F, -2.5F);
        this.rib1.addBox(0.0F, 0.0F, 0.0F, 18, 5, 8, 0.0F);
        this.rib3 = new ModelRenderer(this, 80, 20);
        this.rib3.setRotationPoint(-9.0F, 4.0F, 0.5F);
        this.rib3.addBox(0.0F, 0.0F, 0.0F, 18, 2, 2, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head_2.render(f5);
        this.rib2.render(f5);
        this.foot1.render(f5);
        this.foot2.render(f5);
        this.head.render(f5);
        this.rib4_1.render(f5);
        this.spine3.render(f5);
        this.rib4.render(f5);
        this.spine4.render(f5);
        this.rib5.render(f5);
        this.head_1.render(f5);
        this.spine1.render(f5);
        this.spine2.render(f5);
        this.rib1.render(f5);
        this.rib3.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / 57.29578f;
		this.head.rotateAngleX= f4 / 57.29578f;
		this.head_1.rotateAngleY = f3 / 57.29578f;
		this.head_1.rotateAngleX= f4 / 57.29578f;
		this.head_2.rotateAngleY = f3 / 57.29578f;
		this.head_2.rotateAngleX= f4 / 57.29578f;
		this.foot1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
		this.foot2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}