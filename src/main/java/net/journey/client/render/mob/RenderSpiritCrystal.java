package net.journey.client.render.mob;

import net.journey.client.render.Textures;
import net.journey.client.render.model.mob.corba.ModelSpiritCrystal;
import net.journey.entity.mob.corba.EntitySpiritCrystal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class RenderSpiritCrystal extends Render<EntitySpiritCrystal> {

	private final ModelSpiritCrystal crystal = new ModelSpiritCrystal();

	public RenderSpiritCrystal(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntitySpiritCrystal entity, double x, double y, double z, float entityYaw, float partialTicks) {
		long worldTime = Minecraft.getMinecraft().world.getTotalWorldTime();

		float angle = worldTime % 360;
		float yScale = 1.5F;

		float brightness = 4.5F; //why this work as expected? :/ it should be in 0 to 1 range, how...
		float alpha = 0.7F;

		//starts
		GlStateManager.pushMatrix();

		//colors texture
		GL11.glColor4f(brightness, brightness, brightness, alpha);

		//offsets model by camera's position
		GL11.glTranslated(x, y + 1, z);

		//binds texture
		bindEntityTexture(entity);

		//rotates model
		GlStateManager.rotate(angle + partialTicks, 0.0F, 1.0F, 0.0F);

		//Json Model is adapted for LivingRenderer, where this operation is called to mirror the model by X and Y axis, so we need to reproduce it here
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);

		GlStateManager.scale(1, yScale, 1);

		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.enableAlpha();
//        setLightmapDisabled(true);
		crystal.render(0.0625F);
//        setLightmapDisabled(false);
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();

		//ends
		GlStateManager.popMatrix();

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntitySpiritCrystal entitySpiritCrystal) {
		return Textures.getMobTextureLocation("spirit_crystal");
	}
}
