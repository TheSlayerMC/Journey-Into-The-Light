package net.journey.client.render.block;

import net.journey.blocks.tileentity.TileEntitySenterianAltar;
import net.journey.client.render.Textures;
import net.journey.client.render.model.block.ModelCloudAltar;
import net.journey.client.render.model.block.ModelSenterianAltar;
import net.journey.util.handler.Helper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
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
}
