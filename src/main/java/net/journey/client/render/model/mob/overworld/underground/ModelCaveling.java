package net.journey.client.render.model.mob.overworld.underground;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCaveling extends ModelBase {

    ModelRenderer headPiece;
    ModelRenderer head;
    ModelRenderer head1;
    ModelRenderer head2;
    ModelRenderer head3;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;

    public ModelCaveling() {
        textureWidth = 64;
        textureHeight = 64;

        headPiece = new ModelRenderer(this, 0, 0);
        headPiece.addBox(-4F, -8F, -4F, 8, 8, 8);
        headPiece.setRotationPoint(0F, 12F, 0F);
        headPiece.setTextureSize(64, 64);
        headPiece.mirror = true;
        setRotation(headPiece, 0F, 0F, 0F);
        head = new ModelRenderer(this, 0, 16);
        head.addBox(-3F, -3F, -6F, 6, 6, 6);
        head.setRotationPoint(0F, 8F, 0F);
        head.setTextureSize(64, 64);
        head.mirror = true;
        setRotation(head, 0F, -1.570796F, 0F);
        head1 = new ModelRenderer(this, 0, 16);
        head1.addBox(-3F, -3F, -6F, 6, 6, 6);
        head1.setRotationPoint(0F, 8F, 0F);
        head1.setTextureSize(64, 64);
        head1.mirror = true;
        setRotation(head1, 0F, 3.141593F, 0F);
        head2 = new ModelRenderer(this, 0, 16);
        head2.addBox(-3F, -3F, -6F, 6, 6, 6);
        head2.setRotationPoint(0F, 8F, 0F);
        head2.setTextureSize(64, 64);
        head2.mirror = true;
        setRotation(head2, 0F, 1.570796F, 0F);
        head3 = new ModelRenderer(this, 0, 16);
        head3.addBox(-3F, -3F, -6F, 6, 6, 6);
        head3.setRotationPoint(0F, 8F, 0F);
        head3.setTextureSize(64, 64);
        head3.mirror = true;
        setRotation(head3, 0F, 0F, 0F);
        body = new ModelRenderer(this, 16, 28);
        body.addBox(-6F, 0F, -6F, 12, 4, 12);
        body.setRotationPoint(0F, 12F, 0F);
        body.setTextureSize(64, 64);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        rightarm = new ModelRenderer(this, 0, 32);
        rightarm.addBox(-2F, 0F, -2F, 4, 8, 4);
        rightarm.setRotationPoint(-5F, 16F, 5F);
        rightarm.setTextureSize(64, 64);
        rightarm.mirror = true;
        setRotation(rightarm, 0F, 0F, 0F);
        leftarm = new ModelRenderer(this, 0, 32);
        leftarm.addBox(-2F, 0F, -2F, 4, 8, 4);
        leftarm.setRotationPoint(5F, 16F, 5F);
        leftarm.setTextureSize(64, 64);
        leftarm.mirror = true;
        setRotation(leftarm, 0F, 0F, 0F);
        rightleg = new ModelRenderer(this, 0, 32);
        rightleg.addBox(-2F, 0F, -2F, 4, 8, 4);
        rightleg.setRotationPoint(-5F, 16F, -5F);
        rightleg.setTextureSize(64, 64);
        rightleg.mirror = true;
        setRotation(rightleg, 0F, 0F, 0F);
        leftleg = new ModelRenderer(this, 0, 32);
        leftleg.addBox(-2F, 0F, -2F, 4, 8, 4);
        leftleg.setRotationPoint(5F, 16F, -5F);
        leftleg.setTextureSize(64, 64);
        leftleg.mirror = true;
        setRotation(leftleg, 0F, 0F, 0F);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        headPiece.render(f5);
        head.render(f5);
        head1.render(f5);
        head2.render(f5);
        head3.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;

    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        //this.headPiece.rotateAngleY = f4 / (180F / (float)Math.PI);
        //this.headPiece.rotateAngleX = f5 / (180F / (float)Math.PI);
        //this.head.rotateAngleY = f4 / (180F / (float)Math.PI);
        //this.head.rotateAngleX = f5 / (180F / (float)Math.PI);
        //this.head1.rotateAngleY = f4 / (180F / (float)Math.PI);
        //this.head1.rotateAngleX = f5 / (180F / (float)Math.PI);
        //this.head2.rotateAngleY = f4 / (180F / (float)Math.PI);
        //this.head2.rotateAngleX = f5 / (180F / (float)Math.PI);
        //this.head3.rotateAngleY = f4 / (180F / (float)Math.PI);
        //this.head3.rotateAngleX = f5 / (180F / (float)Math.PI);
        this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
        this.rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
        this.leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;

    }
}