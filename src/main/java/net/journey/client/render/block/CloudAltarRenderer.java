package net.journey.client.render.block;

import net.journey.client.render.Textures;
import net.journey.client.render.model.block.ModelCloudAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

import org.lwjgl.opengl.GL11;

public class CloudAltarRenderer extends TileEntitySpecialRenderer {

    private Minecraft mc = Minecraft.getMinecraft();
    private RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    private ModelCloudAltar altar = new ModelCloudAltar();

    @Override
    public void render(TileEntity e, double x, double y, double z, float f, int i1, float i) {
        float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) * 3;
        float scale = 1.0F;
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 1.3, z + 0.5);
        bindTexture(Textures.cloudaltar);
        GlStateManager.rotate(timeD, 0.0F, 1.0F, 0.0F);
        GL11.glScalef(scale, scale, scale);
        altar.render(0.0625F, false);
        GL11.glRotatef(180F, 0.0F, 0F, 1.0F);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 1.3, z + 0.5);
        GlStateManager.rotate(-timeD, 0.0F, 1.0F, 0.0F);
        altar.render(0.0625F, true);
        GL11.glPopMatrix();
    }
}
