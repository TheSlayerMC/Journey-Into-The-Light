package net.journey.client.render.model.mob.depths;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDarknessCrawler extends ModelBase {

    public ModelRenderer head1;
    public ModelRenderer head2;
    public ModelRenderer head3;
    public ModelRenderer body;
    public ModelRenderer legleft;
    public ModelRenderer legright;
    public ModelRenderer legbackright;
    public ModelRenderer legbackleft;
    public ModelRenderer spike1;
    public ModelRenderer spike2;

	public ModelDarknessCrawler() {
		textureWidth = 64;
		textureHeight = 64;

		this.head3 = new ModelRenderer(this, 0, 16);
        this.head3.setRotationPoint(-6.0F, 12.0F, -6.0F);
        this.head3.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.legleft = new ModelRenderer(this, 32, 32);
        this.legleft.mirror = true;
        this.legleft.setRotationPoint(3.0F, 14.0F, 4.0F);
        this.legleft.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.body = new ModelRenderer(this, 0, 32);
        this.body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.body.addBox(-4.0F, -4.0F, -4.0F, 8, 10, 8, 0.0F);
        this.head1 = new ModelRenderer(this, 0, 16);
        this.head1.setRotationPoint(6.0F, 12.0F, -6.0F);
        this.head1.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.legbackleft = new ModelRenderer(this, 32, 32);
        this.legbackleft.setRotationPoint(-3.0F, 14.0F, 4.0F);
        this.legbackleft.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.spike1 = new ModelRenderer(this, 32, 50);
        this.spike1.setRotationPoint(0.0F, 8.0F, 2.0F);
        this.spike1.addBox(-1.0F, -4.0F, -2.0F, 2, 10, 4, 0.0F);
        this.setRotation(spike1, -0.40980330836826856F, 0.0F, 0.0F);
        this.spike2 = new ModelRenderer(this, 32, 50);
        this.spike2.setRotationPoint(0.0F, 12.0F, 6.0F);
        this.spike2.addBox(-1.0F, -4.0F, -2.0F, 2, 10, 4, 0.0F);
        this.setRotation(spike2, -0.9560913642424937F, 0.0F, 0.0F);
        this.head2 = new ModelRenderer(this, 0, 0);
        this.head2.setRotationPoint(0.0F, 8.0F, -4.0F);
        this.head2.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.legbackright = new ModelRenderer(this, 32, 0);
        this.legbackright.mirror = true;
        this.legbackright.setRotationPoint(-4.0F, 14.0F, -4.0F);
        this.legbackright.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
        this.legright = new ModelRenderer(this, 32, 0);
        this.legright.setRotationPoint(4.0F, 14.0F, -4.0F);
        this.legright.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head3.render(f5);
        this.legleft.render(f5);
        this.body.render(f5);
        this.head1.render(f5);
        this.legbackleft.render(f5);
        this.spike1.render(f5);
        this.spike2.render(f5);
        this.head2.render(f5);
        this.legbackright.render(f5);
        this.legright.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.head1.rotateAngleY = par1 / (180F / (float)Math.PI);
		this.head1.rotateAngleX = par1 / (180F / (float)Math.PI);
		this.head2.rotateAngleY = par1 / (180F / (float)Math.PI);
		this.head2.rotateAngleX = par1 / (180F / (float)Math.PI);
		this.head3.rotateAngleY = par1 / (180F / (float)Math.PI);
		this.head3.rotateAngleX = par1 / (180F / (float)Math.PI);
		this.legbackright.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.legbackleft.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.legright.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		this.legleft.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}
}