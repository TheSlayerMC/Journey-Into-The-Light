/**
 * An altered version of the Guardian Model
 * Author ~ Ryan Bakody (Dizzlepop12), and Mojang
 */

package net.journey.client.render.model.mob.boss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSoulWatcher extends ModelBase {
    public ModelRenderer body_tails_parent;
    public ModelRenderer body_seperate;
    public ModelRenderer head;
    public ModelRenderer tail1;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer tailFin;

    public ModelSoulWatcher() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.tailFin = new ModelRenderer(this, 0, 0);
        this.tailFin.setRotationPoint(0.5F, 0.5F, 6.0F);
        this.tailFin.addBox(1.0F, 10.0F, -3.0F, 1, 10, 10, 0.0F);
        this.setRotateAngle(tailFin, 0.0F, 0.01064650843716541F, 0.0F);
        this.head = new ModelRenderer(this, 39, 56);
        this.head.setRotationPoint(-5.0F, 10.0F, -36.0F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 10, 10, 10, 0.0F);
        this.tail2 = new ModelRenderer(this, 0, 54);
        this.tail2.setRotationPoint(-1.5F, 0.5F, 14.0F);
        this.tail2.addBox(0.0F, 14.0F, -6.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(tail2, 0.0F, 0.00715584993317675F, 0.0F);
        this.tail1 = new ModelRenderer(this, 40, 0);
        this.tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail1.addBox(-1.0F, 13.0F, -7.0F, 6, 6, 8, 0.0F);
        this.setRotateAngle(tail1, 0.0F, 0.003490658503988659F, 0.0F);
        this.tail3 = new ModelRenderer(this, 0, 70);
        this.tail3.setRotationPoint(0.5F, 0.5F, 6.0F);
        this.tail3.addBox(0.0F, 14.0F, -6.0F, 2, 2, 6, 0.0F);
        this.setRotateAngle(tail3, 0.0F, 0.01064650843716541F, 0.0F);
        this.body_tails_parent = new ModelRenderer(this, 14, 94);
        this.body_tails_parent.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body_tails_parent.addBox(-6.0F, 10.0F, -8.0F, 12, 12, 16, 0.0F);
        this.body_seperate = new ModelRenderer(this, 14, 88);
        this.body_seperate.setRotationPoint(0.0F, 8.0F, -26.0F);
        this.body_seperate.addBox(-8.0F, 0.0F, 0.0F, 16, 16, 18, 0.0F);
        this.tail2.addChild(this.tailFin);
        this.tail1.addChild(this.tail2);
        this.body_tails_parent.addChild(this.tail1);
        this.tail2.addChild(this.tail3);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public int func_178706_a() {
        return 54;
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.head.render(f5);
        this.body_tails_parent.render(f5);
        this.body_seperate.render(f5);
    }


    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.tail1.rotateAngleY = MathHelper.sin(par3) * (float) Math.PI * 0.01F;
        this.tail1.rotateAngleY = MathHelper.sin(par3) * (float) Math.PI * 0.1F;
        this.tail1.rotationPointX = -1.5F;
        this.tail1.rotationPointY = 0.5F;
        this.tail1.rotationPointZ = 14.0F;
        this.tail2.rotateAngleY = MathHelper.sin(par3) * (float) Math.PI * 0.1F;
        this.tail2.rotationPointX = 0.5F;
        this.tail2.rotationPointY = 0.5F;
        this.tail2.rotationPointZ = 6.0F;
    }
}
