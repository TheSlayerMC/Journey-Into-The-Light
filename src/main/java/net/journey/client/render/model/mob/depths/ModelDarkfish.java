/**
 * An altered version of the Guardian Model
 * Author ~ Ryan Bakody (Dizzlepop12), and Mojang
 */

package net.journey.client.render.model.mob.depths;

import net.journey.entity.mob.nether.EntityLavasnake;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDarkfish extends ModelBase
{
    private ModelRenderer lavasnakeBody;
    private ModelRenderer lavasnakeEye;
    private ModelRenderer[] lavasnakeTail;
    private static final String __OBFID = "CL_00002628";

    public ModelDarkfish()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.lavasnakeBody = new ModelRenderer(this);
        this.lavasnakeBody.setTextureOffset(0, 0).addBox(-6.0F, 10.0F, -8.0F, 12, 12, 16);
        //this.lavasnakeBody.setTextureOffset(0, 28).addBox(-8.0F, 10.0F, -6.0F, 2, 12, 12);
        //this.lavasnakeBody.setTextureOffset(0, 28).addBox(6.0F, 10.0F, -6.0F, 2, 12, 12, true);
        //this.lavasnakeBody.setTextureOffset(16, 40).addBox(-6.0F, 8.0F, -6.0F, 12, 2, 12);
        //this.lavasnakeBody.setTextureOffset(16, 40).addBox(-6.0F, 22.0F, -6.0F, 12, 2, 12);
        {
        }

        this.lavasnakeEye = new ModelRenderer(this, 8, 0);
        this.lavasnakeEye.addBox(-1.0F, 15.0F, 0.0F, 2, 2, 1);
        this.lavasnakeBody.addChild(this.lavasnakeEye);
        this.lavasnakeTail = new ModelRenderer[3];
        this.lavasnakeTail[0] = new ModelRenderer(this, 40, 0);
        this.lavasnakeTail[0].addBox(-2.0F, 14.0F, 7.0F, 4, 4, 8);
        this.lavasnakeTail[1] = new ModelRenderer(this, 0, 54);
        this.lavasnakeTail[1].addBox(0.0F, 14.0F, 0.0F, 3, 3, 7);
        this.lavasnakeTail[2] = new ModelRenderer(this);
        this.lavasnakeTail[2].setTextureOffset(41, 32).addBox(0.0F, 14.0F, 0.0F, 2, 2, 6);
        this.lavasnakeTail[2].setTextureOffset(25, 19).addBox(1.0F, 10.5F, 3.0F, 1, 9, 9);
        this.lavasnakeBody.addChild(this.lavasnakeTail[0]);
        this.lavasnakeTail[0].addChild(this.lavasnakeTail[1]);
        this.lavasnakeTail[1].addChild(this.lavasnakeTail[2]);
    }

    public int func_178706_a()
    {
        return 54;
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
	public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.lavasnakeBody.render(p_78088_7_);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        EntityLavasnake entitylavasnake = (EntityLavasnake)par7Entity;
        float f6 = par3 - entitylavasnake.ticksExisted;
        this.lavasnakeBody.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.lavasnakeBody.rotateAngleX = par5 / (180F / (float)Math.PI);
        float[] afloat = new float[] {1.75F, 0.25F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, 1.25F, 0.75F, 0.0F, 0.0F};
        float[] afloat1 = new float[] {0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 1.75F, 1.25F, 0.75F, 0.0F, 0.0F, 0.0F, 0.0F};
        float[] afloat2 = new float[] {0.0F, 0.0F, 0.25F, 1.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.75F, 1.25F};
        float[] afloat3 = new float[] {0.0F, 0.0F, 8.0F, -8.0F, -8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F, 8.0F, -8.0F};
        float[] afloat4 = new float[] { -8.0F, -8.0F, -8.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F};
        float[] afloat5 = new float[] {8.0F, -8.0F, 0.0F, 0.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F};

        for (int i = 0; i < 12; ++i)
        {
        }

        this.lavasnakeEye.rotationPointZ = -8.25F;
        Object object = Minecraft.getMinecraft().getRenderViewEntity();

        //this.lavasnakeEye.showModel = true;
        //float f8 = entitylavasnake.func_175471_a(f6);
        this.lavasnakeTail[0].rotateAngleY = MathHelper.sin(par3) * (float)Math.PI * 0.01F;
        this.lavasnakeTail[1].rotateAngleY = MathHelper.sin(par3) * (float)Math.PI * 0.1F;
        this.lavasnakeTail[1].rotationPointX = -1.5F;
        this.lavasnakeTail[1].rotationPointY = 0.5F;
        this.lavasnakeTail[1].rotationPointZ = 14.0F;
        this.lavasnakeTail[2].rotateAngleY = MathHelper.sin(par3) * (float)Math.PI * 0.1F;
        this.lavasnakeTail[2].rotationPointX = 0.5F;
        this.lavasnakeTail[2].rotationPointY = 0.5F;
        this.lavasnakeTail[2].rotationPointZ = 6.0F;
    }
}