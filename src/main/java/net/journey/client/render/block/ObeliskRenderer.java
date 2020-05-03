package net.journey.client.render.block;

import org.lwjgl.opengl.GL11;

import net.journey.blocks.containers.BlockJourneyChest;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.blocks.tileentity.TileEntityObelisk;
import net.journey.blocks.tileentity.TileEntitySenterianAltar;
import net.journey.client.render.Textures;
import net.journey.client.render.model.block.ModelObelisk;
import net.journey.client.render.model.block.ModelSenterianAltar;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ObeliskRenderer extends TileEntitySpecialRenderer {

	private Minecraft mc = Minecraft.getMinecraft();
	private RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
	private ModelObelisk obelisk = new ModelObelisk();

	@Override
	public void render(TileEntity e, double x, double y, double z, float f, int i1, float i) {
        float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) * 3;
        float scale = 1.0F;
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 1.3, z + 0.5);
        bindTexture(Textures.obelisk);
        GlStateManager.rotate(timeD, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(scale, scale, scale);
        obelisk.render(0.0625F, false);
        GL11.glRotatef(180F, 0.0F, 0F, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 1.3, z + 0.5);
        GlStateManager.rotate(-timeD, 0.0F, 1.0F, 0.0F);
        obelisk.render(0.0625F, true);
        GL11.glPopMatrix();
	}
	
	public static class ObeliskTEISR extends TileEntityItemStackRenderer {

		private final TileEntityObelisk obelisk = new TileEntityObelisk();

		@Override
		public void renderByItem(ItemStack itemStackIn, float partialTicks) {
			TileEntityRendererDispatcher.instance.render(obelisk, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
		}
	}
}