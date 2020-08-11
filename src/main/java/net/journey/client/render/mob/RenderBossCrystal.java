package net.journey.client.render.mob;

import net.journey.JITL;
import net.journey.entity.util.EntityBossCrystal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import ru.timeconqueror.timecore.client.render.model.TimeModel;
import ru.timeconqueror.timecore.client.render.model.TimeModelLoader;

public class RenderBossCrystal extends Render<EntityBossCrystal> {
    private static final TimeModel MODEL_BOSS_CRYSTAL = TimeModelLoader.loadJsonModel(JITL.rl("models/entity/boss_crystal.json"));

    public RenderBossCrystal(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityBossCrystal entity, double x, double y, double z, float entityYaw, float partialTicks) {
        long worldTime = Minecraft.getMinecraft().world.getTotalWorldTime();

        float angle = worldTime % 360;
        float yScale = 4.5F;

        float brightness = 4.5F; //why this work as expected? :/ it should be in 0 to 1 range, how...
        float alpha = 0.7F;

        //starts
        GlStateManager.pushMatrix();

        //colors texture
        GL11.glColor4f(brightness, brightness, brightness, alpha);

        //offsets model by camera's position
        GL11.glTranslated(x, y, z);

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
        MODEL_BOSS_CRYSTAL.render(0.0625F);
//        setLightmapDisabled(false);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();

        //ends
        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Sets whether to use the light map when rendering. Disabling this allows rendering ignoring lighting, which can be
     * useful for floating text, e.g.
     */
    protected void setLightmapDisabled(boolean disabled) {
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);

        if (disabled) {
            GlStateManager.disableTexture2D();
        } else {
            GlStateManager.enableTexture2D();
        }

        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityBossCrystal entity) {
        return entity.getTexture();
    }
}
