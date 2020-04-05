package net.journey.client.render.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * CloudAltar - Dizzlepop12
 * Created using Tabula 7.1.0
 */
public class ModelCloudAltar extends ModelBase {
    public ModelRenderer mainsidebottom1;
    public ModelRenderer mainsidebottom3;
    public ModelRenderer mainsidebottom4;
    public ModelRenderer mainsidebottom2;
    public ModelRenderer mainsidetop2;
    public ModelRenderer mainsidetop3;
    public ModelRenderer mainsidetop4;
    public ModelRenderer mainsidetop1;
    public ModelRenderer mainside1;
    public ModelRenderer mainside2;
    public ModelRenderer mainside3;
    public ModelRenderer mainside4;
    public ModelRenderer rotator;
    public ModelRenderer top1;
    public ModelRenderer top2;
    public ModelRenderer top3;
    public ModelRenderer rotator2;
    public ModelRenderer edge1;
    public ModelRenderer edge2;
    public ModelRenderer edge3;
    public ModelRenderer edge4;
    public ModelRenderer edge5;
    public ModelRenderer edge6;
    public ModelRenderer edge7;
    public ModelRenderer edge8;

    public ModelCloudAltar() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.edge8 = new ModelRenderer(this, 0, 115);
        this.edge8.setRotationPoint(-8.0F, 22.0F, -8.0F);
        this.edge8.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.rotator = new ModelRenderer(this, 78, 0);
        this.rotator.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.rotator.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.edge4 = new ModelRenderer(this, 0, 115);
        this.edge4.setRotationPoint(6.0F, 22.0F, 6.0F);
        this.edge4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.mainside4 = new ModelRenderer(this, 16, 16);
        this.mainside4.setRotationPoint(-8.0F, 10.0F, -8.0F);
        this.mainside4.addBox(0.0F, 0.0F, 0.0F, 2, 12, 2, 0.0F);
        this.edge6 = new ModelRenderer(this, 0, 115);
        this.edge6.setRotationPoint(6.0F, 8.0F, -8.0F);
        this.edge6.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.mainsidebottom2 = new ModelRenderer(this, 48, 32);
        this.mainsidebottom2.setRotationPoint(6.0F, 22.0F, -6.0F);
        this.mainsidebottom2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 12, 0.0F);
        this.mainsidetop4 = new ModelRenderer(this, 48, 72);
        this.mainsidetop4.setRotationPoint(-6.0F, 8.0F, -22.0F);
        this.mainsidetop4.addBox(0.0F, 0.0F, 14.0F, 12, 2, 2, 0.0F);
        this.mainsidebottom4 = new ModelRenderer(this, 0, 64);
        this.mainsidebottom4.setRotationPoint(-6.0F, 22.0F, -8.0F);
        this.mainsidebottom4.addBox(0.0F, 0.0F, 14.0F, 12, 2, 2, 0.0F);
        this.edge2 = new ModelRenderer(this, 0, 115);
        this.edge2.setRotationPoint(-8.0F, 8.0F, -8.0F);
        this.edge2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.rotator2 = new ModelRenderer(this, 78, 90);
        this.rotator2.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.rotator2.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.edge1 = new ModelRenderer(this, 0, 115);
        this.edge1.setRotationPoint(-8.0F, 8.0F, 6.0F);
        this.edge1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.mainsidebottom3 = new ModelRenderer(this, 48, 64);
        this.mainsidebottom3.setRotationPoint(-6.0F, 22.0F, -8.0F);
        this.mainsidebottom3.addBox(0.0F, 0.0F, 0.0F, 12, 2, 2, 0.0F);
        this.mainsidetop3 = new ModelRenderer(this, 0, 72);
        this.mainsidetop3.setRotationPoint(-6.0F, 8.0F, -8.0F);
        this.mainsidetop3.addBox(0.0F, 0.0F, 14.0F, 12, 2, 2, 0.0F);
        this.edge7 = new ModelRenderer(this, 0, 115);
        this.edge7.setRotationPoint(-8.0F, 22.0F, 6.0F);
        this.edge7.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.edge5 = new ModelRenderer(this, 0, 115);
        this.edge5.setRotationPoint(6.0F, 22.0F, -8.0F);
        this.edge5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.mainside2 = new ModelRenderer(this, 40, 16);
        this.mainside2.setRotationPoint(6.0F, 10.0F, 6.0F);
        this.mainside2.addBox(0.0F, 0.0F, 0.0F, 2, 12, 2, 0.0F);
        this.top1 = new ModelRenderer(this, 78, 50);
        this.top1.setRotationPoint(-2.0F, 8.0F, -2.0F);
        this.top1.addBox(-4.0F, -4.0F, -4.0F, 12, 4, 12, 0.0F);
        this.mainsidetop1 = new ModelRenderer(this, 0, 84);
        this.mainsidetop1.setRotationPoint(6.0F, 8.0F, -6.0F);
        this.mainsidetop1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 12, 0.0F);
        this.mainsidetop2 = new ModelRenderer(this, 48, 84);
        this.mainsidetop2.setRotationPoint(-8.0F, 8.0F, -6.0F);
        this.mainsidetop2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 12, 0.0F);
        this.mainsidebottom1 = new ModelRenderer(this, 0, 32);
        this.mainsidebottom1.setRotationPoint(-8.0F, 22.0F, -6.0F);
        this.mainsidebottom1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 12, 0.0F);
        this.top3 = new ModelRenderer(this, 78, 115);
        this.top3.setRotationPoint(2.0F, 0.0F, 2.0F);
        this.top3.addBox(-4.0F, -4.0F, -4.0F, 4, 4, 4, 0.0F);
        this.mainside1 = new ModelRenderer(this, 64, 16);
        this.mainside1.setRotationPoint(6.0F, 10.0F, -8.0F);
        this.mainside1.addBox(0.0F, 0.0F, 0.0F, 2, 12, 2, 0.0F);
        this.mainside3 = new ModelRenderer(this, 0, 16);
        this.mainside3.setRotationPoint(-8.0F, 10.0F, 6.0F);
        this.mainside3.addBox(0.0F, 0.0F, 0.0F, 2, 12, 2, 0.0F);
        this.top2 = new ModelRenderer(this, 78, 70);
        this.top2.setRotationPoint(0.0F, 4.0F, 0.0F);
        this.top2.addBox(-4.0F, -4.0F, -4.0F, 8, 4, 8, 0.0F);
        this.edge3 = new ModelRenderer(this, 0, 115);
        this.edge3.setRotationPoint(6.0F, 8.0F, 6.0F);
        this.edge3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.edge8.render(f5);
        this.rotator.render(f5);
        this.edge4.render(f5);
        this.mainside4.render(f5);
        this.edge6.render(f5);
        this.mainsidebottom2.render(f5);
        this.mainsidetop4.render(f5);
        this.mainsidebottom4.render(f5);
        this.edge2.render(f5);
        this.rotator2.render(f5);
        this.edge1.render(f5);
        this.mainsidebottom3.render(f5);
        this.mainsidetop3.render(f5);
        this.edge7.render(f5);
        this.edge5.render(f5);
        this.mainside2.render(f5);
        this.top1.render(f5);
        this.mainsidetop1.render(f5);
        this.mainsidetop2.render(f5);
        this.mainsidebottom1.render(f5);
        this.top3.render(f5);
        this.mainside1.render(f5);
        this.mainside3.render(f5);
        this.top2.render(f5);
        this.edge3.render(f5);
    }
    
    public void render(float f5) {
    	this.edge8.render(f5);
        this.rotator.render(f5);
        this.edge4.render(f5);
        this.mainside4.render(f5);
        this.edge6.render(f5);
        this.mainsidebottom2.render(f5);
        this.mainsidetop4.render(f5);
        this.mainsidebottom4.render(f5);
        this.edge2.render(f5);
        this.rotator2.render(f5);
        this.edge1.render(f5);
        this.mainsidebottom3.render(f5);
        this.mainsidetop3.render(f5);
        this.edge7.render(f5);
        this.edge5.render(f5);
        this.mainside2.render(f5);
        this.top1.render(f5);
        this.mainsidetop1.render(f5);
        this.mainsidetop2.render(f5);
        this.mainsidebottom1.render(f5);
        this.top3.render(f5);
        this.mainside1.render(f5);
        this.mainside3.render(f5);
        this.top2.render(f5);
        this.edge3.render(f5);
	}

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
