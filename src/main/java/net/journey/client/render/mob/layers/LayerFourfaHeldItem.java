package net.journey.client.render.mob.layers;

import net.journey.JourneyItems;
import net.journey.JourneyWeapons;
import net.journey.client.render.model.mob.boss.ModelFourfa;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class LayerFourfaHeldItem implements LayerRenderer {
	
	protected final RenderLivingBase<?> entity;

    public LayerFourfaHeldItem(RenderLivingBase<?> livingEntityRendererIn) {
        this.entity = livingEntityRendererIn;
    }
    
    @Override
    public void doRenderLayer(EntityLivingBase e, float f, float f1, float f2, float f3, float f4, float f5, float f6) {
        ItemStack itemstack = e.getHeldItem(EnumHand.MAIN_HAND);
        if(itemstack != null) {
            GlStateManager.pushMatrix();
            if(e instanceof EntityPlayer && ((EntityPlayer)e).fishEntity != null) 
                itemstack = new ItemStack(Items.FISHING_ROD, 0);
            Item item = itemstack.getItem();
            Minecraft minecraft = Minecraft.getMinecraft();
            GL11.glPushMatrix();
            GL11.glTranslatef(1.0F, 0.6F, 0.0F);
            //GL11.glRotatef(90F, 30F, 30F, 30F);
            minecraft.getItemRenderer().renderItem(e, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
            GL11.glPopMatrix();     
            GL11.glPushMatrix();
            GL11.glTranslatef(0.5F, 0.6F, 0.0F);
            minecraft.getItemRenderer().renderItem(e, new ItemStack(JourneyWeapons.flameBow), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
            GL11.glPopMatrix();          
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.75F, 0.6F, 0.0F);
            minecraft.getItemRenderer().renderItem(e, new ItemStack(JourneyWeapons.frozenBow), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef(-1.25F, 0.6F, 0.0F);
            minecraft.getItemRenderer().renderItem(e, new ItemStack(JourneyWeapons.poisonBow), ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND);
            GL11.glPopMatrix();
            GlStateManager.popMatrix();
        }
    }

    private void renderHeldItem(EntityLivingBase e, ItemStack i, ItemCameraTransforms.TransformType t, EnumHandSide handSide) {
        if(!i.isEmpty()) {
            GlStateManager.pushMatrix();
            if (e.isSneaking()) {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }
            this.translateToHand(handSide);
            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            boolean flag = handSide == EnumHandSide.LEFT;
            GlStateManager.translate((flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(e, i, t, flag);
            GlStateManager.popMatrix();
        }
    }
    
    protected void translateToHand(EnumHandSide h) {
        ((ModelFourfa)this.entity.getMainModel()).postRenderArm(0.0625F, h);
    }
    
    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}