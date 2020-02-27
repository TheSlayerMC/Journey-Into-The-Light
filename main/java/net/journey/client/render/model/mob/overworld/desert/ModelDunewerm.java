package net.journey.client.render.model.mob.overworld.desert;

import net.journey.entity.mob.nether.EntityLavasnake;
import net.journey.entity.mob.overworld.EntityDunewerm;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelDune - Dizzlepop12
 * Created using Tabula 5.1.0
 */
public class ModelDunewerm extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer[] tail;

    public ModelDunewerm() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.tail = new ModelRenderer[3];
        this.tail[1] = new ModelRenderer(this, 0, 44);
        this.tail[1].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[1].addBox(-28.0F, 3.0F, -4.0F, 10, 6, 6, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-6.0F, 0.0F, -6.0F, 12, 12, 12, 0.0F);
        this.tail[0] = new ModelRenderer(this, 0, 24);
        this.tail[0].setRotationPoint(0.0F, 0.0F, 1.0F);
        this.tail[0].addBox(-18.0F, 1.0F, -6.0F, 12, 10, 10, 0.0F);
        this.tail[2] = new ModelRenderer(this, 36, 0);
        this.tail[2].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[2].addBox(-38.0F, 4.5F, -2.5F, 10, 3, 3, 0.0F);
        this.tail[0].addChild(this.tail[1]);
        this.head.addChild(this.tail[0]);
        this.tail[1].addChild(this.tail[2]);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.head.render(f5);
    }
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
    	EntityDunewerm entitylavasnake = (EntityDunewerm)par7Entity;
        float f6 = par3 - entitylavasnake.ticksExisted;
        this.head.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.head.rotateAngleX = par5 / (180F / (float)Math.PI);
        float[] afloat = new float[] {1.75F, 0.25F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, 1.25F, 0.75F, 0.0F, 0.0F};
        float[] afloat1 = new float[] {0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 1.75F, 1.25F, 0.75F, 0.0F, 0.0F, 0.0F, 0.0F};
        float[] afloat2 = new float[] {0.0F, 0.0F, 0.25F, 1.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.75F, 1.25F};
        float[] afloat3 = new float[] {0.0F, 0.0F, 8.0F, -8.0F, -8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F, 8.0F, -8.0F};
        float[] afloat4 = new float[] { -8.0F, -8.0F, -8.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F};
        float[] afloat5 = new float[] {8.0F, -8.0F, 0.0F, 0.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F};

        for (int i = 0; i < 12; ++i){}
        Object object = Minecraft.getMinecraft().getRenderViewEntity();

        this.tail[0].rotateAngleY = MathHelper.sin(par3) * (float)Math.PI * 0.01F;
        this.tail[0].rotateAngleY = MathHelper.sin(par3) * (float)Math.PI * 0.1F;
        this.tail[0].rotationPointX = -1.5F;
        this.tail[0].rotationPointY = 0.5F;
        this.tail[0].rotationPointZ = 14.0F;
        this.tail[1].rotateAngleY = MathHelper.sin(par3) * (float)Math.PI * 0.1F;
        this.tail[1].rotationPointX = 0.5F;
        this.tail[1].rotationPointY = 0.5F;
        this.tail[1].rotationPointZ = 6.0F;
    }
}
