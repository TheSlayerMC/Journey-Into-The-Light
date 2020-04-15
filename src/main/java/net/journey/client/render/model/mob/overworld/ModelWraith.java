package net.journey.client.render.model.mob.overworld;

import net.journey.JourneyItems;
import net.journey.JourneyWeapons;
import net.journey.entity.mob.overworld.EntityWraith;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelWraith extends ModelBiped
{
    public ModelWraith() {
        this(0.0F, false);
    }
    
    ModelRenderer rightarmsleeve;
    ModelRenderer leftarmsleeve;

    public ModelWraith(float modelSize, boolean p_i46303_2_) {
        super(modelSize, 0.0F, 64, 64);

        if (!p_i46303_2_) {
        	
        }
	      textureWidth = 64;
	      textureHeight = 64;
	    
          this.bipedRightArm = new ModelRenderer(this, 40, 16);
          this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
          this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
          this.bipedLeftArm = new ModelRenderer(this, 40, 16);
          this.bipedLeftArm.mirror = true;
          this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
          this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
          this.bipedRightLeg = new ModelRenderer(this, 0, 16);
          this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, modelSize);
          this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
          this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
          this.bipedLeftLeg.mirror = true;
          this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, modelSize);
          this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
          
	      rightarmsleeve = new ModelRenderer(this, 16, 40);
	      rightarmsleeve.addBox(0F, 0F, 0F, 4, 10, 8);
	      rightarmsleeve.setRotationPoint(-8F, 0F, -6F);
	      rightarmsleeve.setTextureSize(64, 64);
	      rightarmsleeve.mirror = true;
	      setRotation(rightarmsleeve, 0F, 0F, 0F);
	      leftarmsleeve = new ModelRenderer(this, 40, 30);
	      leftarmsleeve.addBox(0F, 0F, 0F, 4, 10, 8);
	      leftarmsleeve.setRotationPoint(4F, 0F, -6F);
	      leftarmsleeve.setTextureSize(64, 64);
	      leftarmsleeve.mirror = true;
	      setRotation(leftarmsleeve, 0F, 0F, 0F);

	}
   
    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
        this.rightArmPose = ModelBiped.ArmPose.EMPTY;
        this.leftArmPose = ModelBiped.ArmPose.EMPTY;
        ItemStack itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);
        
        if (itemstack.getItem() == JourneyWeapons.demonicSword) {
            if (entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT) {
                this.rightArmPose = ModelBiped.ArmPose.ITEM;
            }
            else {
                this.leftArmPose = ModelBiped.ArmPose.ITEM;
            }
        }
        
        super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
    }

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.bipedBody.render(f5);
        this.bipedRightArm.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedHeadwear.render(f5);
	    rightarmsleeve.render(f5);
	    leftarmsleeve.render(f5);

	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        ItemStack itemstack = ((EntityLivingBase)entityIn).getHeldItemMainhand();
        EntityWraith w = (EntityWraith)entityIn;

        if (itemstack.getItem() != JourneyWeapons.demonicSword) {
            float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
            float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
            this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
            this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F);
            this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F);
            this.bipedRightArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
            this.bipedLeftArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        }
    }

	@Override
    public void postRenderArm(float scale, EnumHandSide side) {
        float f = side == EnumHandSide.RIGHT ? 1.0F : -1.0F;
        ModelRenderer modelrenderer = this.getArmForSide(side);
        modelrenderer.rotationPointX += f;
        modelrenderer.postRender(scale);
        modelrenderer.rotationPointX -= f;
    }
}