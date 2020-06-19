package net.journey.client.render.block;

import net.journey.blocks.containers.BlockSenterianAltar;
import net.journey.blocks.containers.JBlockBossCrystal;
import net.journey.blocks.tileentity.TileEntityBossCrystal;
import net.journey.blocks.tileentity.TileEntitySenterianAltar;
import net.journey.client.render.Textures;
import net.journey.client.render.model.block.ModelSenterianAltar;
import net.journey.init.blocks.JourneyBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.slayer.api.SlayerAPI;
import org.lwjgl.opengl.GL11;

public class SenterianAltarRenderer extends TileEntitySpecialRenderer {

    private Minecraft mc = Minecraft.getMinecraft();
    private RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    private ModelSenterianAltar altar = new ModelSenterianAltar();

    @Override
    public void render(TileEntity e, double x, double y, double z, float f, int i1, float i) {
    	TileEntitySenterianAltar te = (TileEntitySenterianAltar)e;
        float scale = 1.0F;
	    GL11.glPushMatrix();
	    GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
	    GL11.glRotatef(180F, 0.0F, 0F, 2.0F);
	    bindTexture(Textures.senterianAltar);
	    GL11.glScalef(scale, scale, scale);
	    altar.render(0.0625F, te.getHasOrb());
	    //Helper.print(te.getHasOrb());
	    GL11.glPopMatrix();
    }

	public static class SenterianAltarTEISR extends TileEntityItemStackRenderer {

		private final TileEntitySenterianAltar altar = new TileEntitySenterianAltar();

		@Override
		public void renderByItem(ItemStack itemStackIn, float partialTicks) {
			GL11.glPushMatrix();
			GL11.glScalef(0.52F, 0.52F, 0.52F);
			GL11.glTranslated(0.5F,  - 1.5F, 0.5F);
			TileEntityRendererDispatcher.instance.render(altar, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
			GlStateManager.disableAlpha();
			GlStateManager.disableBlend();
			GL11.glPopMatrix();
		}
	}
}
