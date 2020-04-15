package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelTerranianProtector - Dizzlepop12
 * Created using Tabula 5.1.0
 */
public class ModelTerranianProtector extends ModelBase {
    public ModelRenderer head1;
    public ModelRenderer spike1;
    public ModelRenderer spike2;
    public ModelRenderer spike3;
    public ModelRenderer spike4;
    public ModelRenderer spike4_2;
    public ModelRenderer spike3_2;
    public ModelRenderer spike4_3;
    public ModelRenderer spike3_3;
    public ModelRenderer spike5;
    public ModelRenderer spike6;
    public ModelRenderer spike7;
    public ModelRenderer head3;
    public ModelRenderer spike8;
    public ModelRenderer head2;
    public ModelRenderer head4;
    public ModelRenderer head5;

    public ModelTerranianProtector() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.spike8 = new ModelRenderer(this, 0, 68);
        this.spike8.setRotationPoint(-1.0F, 3.0F, -1.0F);
        this.spike8.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.spike5 = new ModelRenderer(this, 60, 80);
        this.spike5.setRotationPoint(-2.0F, -7.0F, -2.0F);
        this.spike5.addBox(0.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
        this.spike7 = new ModelRenderer(this, 0, 68);
        this.spike7.setRotationPoint(-1.0F, -59.0F, -1.0F);
        this.spike7.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.head3 = new ModelRenderer(this, 0, 32);
        this.head3.setRotationPoint(-0.0F, -64.0F, -0.0F);
        this.head3.addBox(-6.0F, -6.0F, -6.0F, 12, 12, 12, 0.0F);
        this.head2 = new ModelRenderer(this, 0, 32);
        this.head2.setRotationPoint(-0.0F, 18.0F, -0.0F);
        this.head2.addBox(-6.0F, -6.0F, -6.0F, 12, 12, 12, 0.0F);
        this.spike6 = new ModelRenderer(this, 60, 80);
        this.spike6.setRotationPoint(-2.0F, -49.0F, -2.0F);
        this.spike6.addBox(0.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
        this.spike2 = new ModelRenderer(this, 64, 0);
        this.spike2.setRotationPoint(-3.0F, -39.0F, -3.0F);
        this.spike2.addBox(0.0F, 0.0F, 0.0F, 6, 10, 6, 0.0F);
        this.spike4 = new ModelRenderer(this, 32, 100);
        this.spike4.setRotationPoint(6.0F, -26.0F, -3.0F);
        this.spike4.addBox(0.0F, 0.0F, 0.0F, 10, 6, 6, 0.0F);
        this.head4 = new ModelRenderer(this, 0, 32);
        this.head4.setRotationPoint(41.0F, -23.0F, 0.0F);
        this.head4.addBox(-6.0F, -6.0F, -6.0F, 12, 12, 12, 0.0F);
        this.spike3_2 = new ModelRenderer(this, 60, 60);
        this.spike3_2.setRotationPoint(-26.0F, -25.0F, -2.0F);
        this.spike3_2.addBox(0.0F, 0.0F, 0.0F, 10, 4, 4, 0.0F);
        this.spike1 = new ModelRenderer(this, 64, 0);
        this.spike1.setRotationPoint(-3.0F, -17.0F, -3.0F);
        this.spike1.addBox(0.0F, 0.0F, 0.0F, 6, 10, 6, 0.0F);
        this.head5 = new ModelRenderer(this, 0, 32);
        this.head5.setRotationPoint(-41.0F, -23.0F, -0.0F);
        this.head5.addBox(-6.0F, -6.0F, -6.0F, 12, 12, 12, 0.0F);
        this.spike4_2 = new ModelRenderer(this, 60, 60);
        this.spike4_2.setRotationPoint(16.0F, -25.0F, -2.0F);
        this.spike4_2.addBox(0.0F, 0.0F, 0.0F, 10, 4, 4, 0.0F);
        this.spike4_3 = new ModelRenderer(this, 0, 60);
        this.spike4_3.setRotationPoint(26.0F, -24.0F, -1.0F);
        this.spike4_3.addBox(0.0F, 0.0F, 0.0F, 10, 2, 2, 0.0F);
        this.spike3 = new ModelRenderer(this, 32, 100);
        this.spike3.setRotationPoint(-16.0F, -26.0F, -3.0F);
        this.spike3.addBox(0.0F, 0.0F, 0.0F, 10, 6, 6, 0.0F);
        this.head1 = new ModelRenderer(this, 0, 0);
        this.head1.setRotationPoint(-2.0F, -25.0F, -0.0F);
        this.head1.addBox(-6.0F, -6.0F, -6.0F, 16, 16, 12, 0.0F);
        this.spike3_3 = new ModelRenderer(this, 0, 60);
        this.spike3_3.setRotationPoint(-36.0F, -24.0F, -1.0F);
        this.spike3_3.addBox(0.0F, 0.0F, 0.0F, 10, 2, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.spike8.render(f5);
        this.spike5.render(f5);
        this.spike7.render(f5);
        this.head3.render(f5);
        this.head2.render(f5);
        this.spike6.render(f5);
        this.spike2.render(f5);
        this.spike4.render(f5);
        this.head4.render(f5);
        this.spike3_2.render(f5);
        this.spike1.render(f5);
        this.head5.render(f5);
        this.spike4_2.render(f5);
        this.spike4_3.render(f5);
        this.spike3.render(f5);
        this.head1.render(f5);
        this.spike3_3.render(f5);

    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head2.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.head2.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.head3.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.head3.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.head4.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.head4.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.head5.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.head5.rotateAngleX = par5 / (180F / (float) Math.PI);
        float f6;
        float f7;
    }
}
