package net.journey.client.render.block;

import net.journey.JourneyItems;
import net.journey.blocks.tileentity.TileEntityKnowledgeTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.google.common.primitives.SignedBytes;

public class KnowledgeTableRenderer extends TileEntitySpecialRenderer {

	private Minecraft mc = Minecraft.getMinecraft();
	private RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	private RenderEntityItem renderEntity;
	
	public KnowledgeTableRenderer() {
		renderEntity = new RenderEntityItem(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem()){
            @Override
            public int func_177078_a(ItemStack stack) {
                return SignedBytes.saturatedCast(Math.min(stack.getCount() / 32, 15) + 1);
            }
            
            @Override
            public boolean shouldBob() {
                return false;
            }
            @Override
            public boolean shouldSpreadItems() {
                return true;
            }
        };
	}
	
	public Item getItemFromDimension(int dimID) {
		switch(dimID) {
		case -1: return JourneyItems.netherKnowledge;
		case 0: return JourneyItems.overworldKnowledge;
		case 1: return JourneyItems.endKnowledge;
		}
		return JourneyItems.blankKnowledge;
	}
	
	@Override
	public void render(TileEntity t, double x, double y, double z, float f, int j, float f) {
		TileEntityKnowledgeTable tile = (TileEntityKnowledgeTable)t;
		float scale = 1.0F;
		float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) * 8;
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5D, y + 0.8D, z + 0.5D);
		ItemStack i = new ItemStack(getItemFromDimension(t.getWorld().provider.getDimension()));
		EntityItem item = new EntityItem(t.getWorld(), x, y, z, i);
		GlStateManager.rotate(timeD, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(scale, scale, scale);
		item.setEntityItemStack(i);
		item.hoverStart = 0.0F;
		renderEntity.doRender(item, 0, 0, 0, 0, 0);
		//renderItem.renderItemModel(item.getEntityItem());
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		renderText(StatCollector.translateToLocal(i.getItem().getUnlocalizedName() + ".name"), x + 0.5F, y + 1.5F, z + 0.5F);
		GL11.glPopMatrix();
	}

	public void renderText(String s, double x, double y, double z) {
		FontRenderer fontrenderer = mc.fontRenderer;
		float f = 1.6F;
		float f1 = 0.016666668F * f;
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-this.mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(this.mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
		GlStateManager.scale(-f1, -f1, f1);
		GlStateManager.disableLighting();
		GlStateManager.depthMask(false);
		GlStateManager.disableDepth();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		byte b0 = 0;
		GlStateManager.disableTexture2D();
		worldrenderer.begin(GL11.GL_QUADS, worldrenderer.getVertexFormat());
		int j = fontrenderer.getStringWidth(s) / 2;
		worldrenderer.color(0.0F, 0.0F, 0.0F, (float) 0.25);
		worldrenderer.pos((double)(-j - 1), (double)(-1 + b0), 0.0D);
		worldrenderer.pos((double)(-j - 1), (double)(8 + b0), 0.0D);
		worldrenderer.pos((double)(j + 1), (double)(8 + b0), 0.0D);
		worldrenderer.pos((double)(j + 1), (double)(-1 + b0), 0.0D);
		tessellator.draw();
		GlStateManager.enableTexture2D();
		fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, b0, 553648127);
		GlStateManager.enableDepth();
		GlStateManager.depthMask(true);
		fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, b0, -1);
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.popMatrix();
	}
}