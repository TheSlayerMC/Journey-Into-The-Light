package net.journey.client.render.model.mob.boil;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFlameLotus extends ModelBiped {

    public ModelRenderer pedalbottom4;
    public ModelRenderer pedalbottom2;
    public ModelRenderer pedalbottom1;
    public ModelRenderer pedalbottom3;
    public ModelRenderer pedaltop1;
    public ModelRenderer pedaltop2;
    public ModelRenderer pedaltop3;
    public ModelRenderer pedalmid4;
    public ModelRenderer pedalmid8;
    public ModelRenderer pedalmid3;
    public ModelRenderer pedalmid2;
    public ModelRenderer pedalmid1;
    public ModelRenderer pedalmid5;
    public ModelRenderer pedalmid6;
    public ModelRenderer pedalmid7;
    public ModelRenderer flowerbudtop;
    public ModelRenderer flowerbud;
    public ModelRenderer pedaltop4;

	public ModelFlameLotus() {

        this.textureWidth = 128;
        this.textureHeight = 32;
        this.flowerbud = new ModelRenderer(this, 0, 18);
        this.flowerbud.setRotationPoint(-0.0F, 22.0F, -0.0F);
        this.flowerbud.addBox(-6.0F, 0.0F, -6.0F, 12, 2, 12, 0.0F);
        this.pedalbottom2 = new ModelRenderer(this, 28, 10);
        this.pedalbottom2.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedalbottom2.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 20, 0.0F);
        this.setRotation(pedalbottom2, -0.13962634015954636F, 0.767944870877505F, 0.0F);
        this.pedaltop1 = new ModelRenderer(this, 16, 0);
        this.pedaltop1.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedaltop1.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 16, 0.0F);
        this.setRotation(pedaltop1, -0.24434609527920614F, 0.0F, 0.0F);
        this.pedalbottom3 = new ModelRenderer(this, 28, 10);
        this.pedalbottom3.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedalbottom3.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 20, 0.0F);
        this.setRotation(pedalbottom3, -0.13962634015954636F, -0.767944870877505F, 0.0F);
        this.pedaltop4 = new ModelRenderer(this, 16, 0);
        this.pedaltop4.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedaltop4.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 16, 0.0F);
        this.setRotation(pedaltop4, -0.24434609527920614F, 3.141592653589793F, 0.0F);
        this.pedalmid3 = new ModelRenderer(this, 44, 10);
        this.pedalmid3.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedalmid3.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 20, 0.0F);
        this.setRotation(pedalmid3, -0.19198621771937624F, 1.186823891356144F, 0.0F);
        this.pedalmid2 = new ModelRenderer(this, 44, 10);
        this.pedalmid2.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedalmid2.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 20, 0.0F);
        this.setRotation(pedalmid2, -0.19198621771937624F, 1.9554668939344468F, 0.0F);
        this.pedalmid8 = new ModelRenderer(this, 44, 10);
        this.pedalmid8.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedalmid8.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 20, 0.0F);
        this.setRotation(pedalmid8, -0.19198621771937624F, -2.739643326855499F, 0.0F);
        this.pedalmid6 = new ModelRenderer(this, 44, 10);
        this.pedalmid6.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedalmid6.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 20, 0.0F);
        this.setRotation(pedalmid6, -0.19198621771937624F, -1.1838568316277536F, 0.0F);
        this.flowerbudtop = new ModelRenderer(this, 0, 0);
        this.flowerbudtop.setRotationPoint(2.0F, 21.0F, 2.0F);
        this.flowerbudtop.addBox(-6.0F, 0.0F, -6.0F, 8, 2, 8, 0.0F);
        this.pedalmid4 = new ModelRenderer(this, 44, 10);
        this.pedalmid4.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedalmid4.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 20, 0.0F);
        this.setRotation(pedalmid4, -0.19198621771937624F, 0.3839724354387525F, 0.0F);
        this.pedaltop3 = new ModelRenderer(this, 16, 0);
        this.pedaltop3.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedaltop3.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 16, 0.0F);
        this.setRotation(pedaltop3, -0.24434609527920614F, -1.5707963267948966F, 0.0F);
        this.pedalmid5 = new ModelRenderer(this, 44, 10);
        this.pedalmid5.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedalmid5.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 20, 0.0F);
        this.setRotation(pedalmid5, -0.19198621771937624F, -0.36425021489121656F, 0.0F);
        this.pedalbottom4 = new ModelRenderer(this, 28, 10);
        this.pedalbottom4.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedalbottom4.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 20, 0.0F);
        this.setRotation(pedalbottom4, -0.13962634015954636F, -2.3743459144130856F, 0.0F);
        this.pedalbottom1 = new ModelRenderer(this, 28, 10);
        this.pedalbottom1.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedalbottom1.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 20, 0.0F);
        this.setRotation(pedalbottom1, -0.13962634015954636F, 2.373647782712288F, 0.0F);
        this.pedaltop2 = new ModelRenderer(this, 16, 0);
        this.pedaltop2.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedaltop2.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 16, 0.0F);
        this.setRotation(pedaltop2, -0.24434609527920614F, 1.5707963267948966F, 0.0F);
        this.pedalmid7 = new ModelRenderer(this, 44, 10);
        this.pedalmid7.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedalmid7.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 20, 0.0F);
        this.setRotation(pedalmid7, -0.19198621771937624F, -1.9577358219620393F, 0.0F);
        this.pedalmid1 = new ModelRenderer(this, 44, 10);
        this.pedalmid1.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.pedalmid1.addBox(-4.0F, 0.0F, -20.0F, 8, 0, 20, 0.0F);
        this.setRotation(pedalmid1, -0.19198621771937624F, 2.7317893452215247F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.flowerbud.render(f5);
        this.pedalbottom2.render(f5);
        this.pedaltop1.render(f5);
        this.pedalbottom3.render(f5);
        this.pedaltop4.render(f5);
        this.pedalmid3.render(f5);
        this.pedalmid2.render(f5);
        this.pedalmid8.render(f5);
        this.pedalmid6.render(f5);
        this.flowerbudtop.render(f5);
        this.pedalmid4.render(f5);
        this.pedaltop3.render(f5);
        this.pedalmid5.render(f5);
        this.pedalbottom4.render(f5);
        this.pedalbottom1.render(f5);
        this.pedaltop2.render(f5);
        this.pedalmid7.render(f5);
        this.pedalmid1.render(f5);
		   
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		
        this.pedaltop1.rotateAngleZ = MathHelper.cos(par3 * 0.1F) * (float)Math.PI * 0.01F;
        this.pedaltop2.rotateAngleZ = MathHelper.cos(par3 * 0.12F) * (float)Math.PI * 0.01F;
        this.pedaltop3.rotateAngleZ = MathHelper.cos(par3 * 0.11F) * (float)Math.PI * 0.01F;
        this.pedaltop4.rotateAngleZ = MathHelper.cos(par3 * 0.125F) * (float)Math.PI * 0.01F;
        
        this.pedalmid1.rotateAngleX = MathHelper.cos(par3 * 0.0551F) * (float)Math.PI * 0.01F;
        this.pedalmid2.rotateAngleZ = MathHelper.cos(par3 * 0.055F) * (float)Math.PI * 0.01F;
        this.pedalmid3.rotateAngleX = MathHelper.cos(par3 * 0.054F) * (float)Math.PI * 0.01F;
        this.pedalmid4.rotateAngleZ = MathHelper.cos(par3 * 0.055F) * (float)Math.PI * 0.01F;
        this.pedalmid5.rotateAngleX = MathHelper.cos(par3 * 0.056F) * (float)Math.PI * 0.01F;
        this.pedalmid6.rotateAngleZ = MathHelper.cos(par3 * 0.055F) * (float)Math.PI * 0.01F;
        this.pedalmid7.rotateAngleX = MathHelper.cos(par3 * 0.0552F) * (float)Math.PI * 0.01F;
        this.pedalmid8.rotateAngleZ = MathHelper.cos(par3 * 0.055F) * (float)Math.PI * 0.01F;
        
        this.pedalbottom1.rotateAngleZ = MathHelper.cos(par3 * 0.05F) * (float)Math.PI * 0.01F;
        this.pedalbottom2.rotateAngleZ = MathHelper.cos(par3 * 0.05F) * (float)Math.PI * 0.01F;
        this.pedalbottom3.rotateAngleZ = MathHelper.cos(par3 * 0.0511F) * (float)Math.PI * 0.01F;
        this.pedalbottom4.rotateAngleZ = MathHelper.cos(par3 * 0.0525F) * (float)Math.PI * 0.01F;
		
	}
}