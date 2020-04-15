package net.journey.client.render.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;

import static org.lwjgl.opengl.GL11.*;

public class EntityKnowledgeTableFX extends Particle {

    private static final ResourceLocation texture = new ResourceLocation(SlayerAPI.MOD_ID, "textures/particle/knowledge.png");

    public EntityKnowledgeTableFX(World par1World, double x, double y, double z) {
        super(par1World, x, y, z);
    }

    public void renderParticle(Tessellator tess, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        glDepthMask(false);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glAlphaFunc(GL_GREATER, 0.003921569F);
        //startDrawingQuads();
        getBrightnessForRender(getBrightnessForRender(partialTicks));
        float scale = 0.1F * particleScale;
        float x = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
        float y = (float) (prevPosX + (posY - prevPosY) * partialTicks - interpPosY);
        float z = (float) (prevPosX + (posZ - prevPosZ) * partialTicks - interpPosZ);
        //addVertexWithUV(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, 0, 0);
    }
}