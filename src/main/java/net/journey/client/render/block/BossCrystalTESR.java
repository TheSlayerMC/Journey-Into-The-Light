package net.journey.client.render.block;

import net.journey.blocks.tileentity.TileEntityBossCrystal;
import net.journey.client.render.model.block.ModelBossCrystal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class BossCrystalTESR extends TileEntitySpecialRenderer<TileEntityBossCrystal> {

    private final Minecraft mc = Minecraft.getMinecraft();
    private final RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    private final ModelBossCrystal crystal = new ModelBossCrystal();
    private static final Random RANDOM = new Random(432L);

    @Override
    public void render(TileEntityBossCrystal te, double x, double y, double z, float f, int i1, float i) {

        float timeD = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) * 3;
        float XZScale = 1.0F;
        float YScale = 4.5F;
        float brightness = 4.5F;
        float transparency = 0.7F;

        //start
        GL11.glPushMatrix();

        //add transparency, color offset(brightness)
        GL11.glColor4f(brightness, brightness, brightness, transparency);

        //offset model
        GL11.glTranslated(x + 0.5, y - 0.5, z + 0.5);

        //bind texture
        bindTexture(te.getChestType().getCrystalTexture());

        //rotate model
        GlStateManager.rotate(timeD, 0.0F, 1.0F, 0.0F);

        //YScale is higher than XZ scale, as to create a stretched/distorted effect with the model
        GL11.glScalef(XZScale, YScale, XZScale);

        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableAlpha();
        crystal.render(0.0625F, false);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();

        //rotate model (again?)
        GL11.glRotatef(180F, 0.0F, 0F, 1.0F);

        //end
        GL11.glPopMatrix();
    }
}
