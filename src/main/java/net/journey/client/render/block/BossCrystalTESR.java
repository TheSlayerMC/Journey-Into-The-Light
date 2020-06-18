package net.journey.client.render.block;

import net.journey.blocks.containers.BlockJourneyChest;
import net.journey.blocks.containers.JBlockBossCrystal;
import net.journey.blocks.tileentity.TileEntityBossCrystal;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.client.render.Textures;
import net.journey.client.render.model.block.ModelBossCrystal;
import net.journey.client.render.model.block.ModelCloudAltar;
import net.journey.client.render.model.block.ModelJourneyChest;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

import org.lwjgl.opengl.GL11;

import java.util.Random;

public class BossCrystalTESR extends TileEntitySpecialRenderer<TileEntityBossCrystal> {

    private Minecraft mc = Minecraft.getMinecraft();
    private RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    private ModelBossCrystal crystal = new ModelBossCrystal();
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
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_LESS, 1.0f);

        //offset model
        GL11.glTranslated(x + 0.5, y - 0.5, z + 0.5);

        //bind texture
        bindTexture(te.getChestType().getCrystalTexture());

        //rotate model
        GlStateManager.rotate(timeD, 0.0F, 1.0F, 0.0F);

        //YScale is higher than XZ scale, as to create a stretched/distorted effect with the model
        GL11.glScalef(XZScale, YScale, XZScale);
        crystal.render(0.0625F, false);

        //rotate model (again?)
        GL11.glRotatef(180F, 0.0F, 0F, 1.0F);

        //end
        GL11.glPopMatrix();
    }
}
