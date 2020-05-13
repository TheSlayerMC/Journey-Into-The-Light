package net.journey.client.render.model.mob.boil;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHellwing extends ModelBase {

    public ModelRenderer head;
    public ModelRenderer childrightwing;
    public ModelRenderer childleftwing;
    public ModelRenderer rightwingchild;
    public ModelRenderer leftwingchild;

    public ModelHellwing() {
    	this.textureWidth = 64;
        this.textureHeight = 64;
        this.childleftwing = new ModelRenderer(this, 24, 46);
        this.childleftwing.mirror = true;
        this.childleftwing.setRotationPoint(3.0F, 0.0F, 3.0F);
        this.childleftwing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1, 0.0F);
        this.setRotateAngle(childleftwing, 0.0F, 0.767944870877505F, 0.0F);
        this.rightwingchild = new ModelRenderer(this, 0, 30);
        this.rightwingchild.setRotationPoint(-12.0F, 1.0F, 1.5F);
        this.rightwingchild.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1, 0.0F);
        this.setRotateAngle(rightwingchild, 0.0F, -0.4553564018453205F, 0.0F);
        this.leftwingchild = new ModelRenderer(this, 24, 30);
        this.leftwingchild.mirror = true;
        this.leftwingchild.setRotationPoint(12.0F, 1.0F, 1.5F);
        this.leftwingchild.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1, 0.0F);
        this.setRotateAngle(leftwingchild, 0.0F, 0.45378560551852565F, 0.0F);
        this.childrightwing = new ModelRenderer(this, 0, 46);
        this.childrightwing.setRotationPoint(-3.0F, 0.0F, 3.0F);
        this.childrightwing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1, 0.0F);
        this.setRotateAngle(childrightwing, 0.0F, -0.7740535232594852F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-6.0F, 0.0F, -3.0F, 12, 12, 12, 0.0F);
        this.head.addChild(this.childleftwing);
        this.childrightwing.addChild(this.rightwingchild);
        this.childleftwing.addChild(this.leftwingchild);
        this.head.addChild(this.childrightwing);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        head.render(f5);
    }

    private void setRotateAngle(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleZ = 0.0F;
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.childrightwing.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.childleftwing.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.childrightwing.rotateAngleY = MathHelper.cos(ageInTicks * 0.65F) * (float)Math.PI * 0.25F;
        this.childleftwing.rotateAngleY = -this.childrightwing.rotateAngleY;
        this.rightwingchild.rotateAngleY = this.childrightwing.rotateAngleY * 0.5F;
        this.leftwingchild.rotateAngleY = -this.childrightwing.rotateAngleY * 0.5F;

    }
}