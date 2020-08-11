package net.journey.client.render.mob;

import net.journey.JITL;
import net.journey.entity.util.EntityBossCrystal;
import net.journey.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;
import ru.timeconqueror.timecore.client.render.model.TimeModel;
import ru.timeconqueror.timecore.client.render.model.TimeModelLoader;

public class RenderBossCrystal extends Render<EntityBossCrystal> {
    private static final TimeModel MODEL_BOSS_CRYSTAL = TimeModelLoader.loadJsonModel(JITL.rl("models/entity/boss_crystal.json"));
    private static final ResourceLocation CRYSTAL_TEXTURE = JITL.rl("textures/models/entity/boss_crystal.png");

    public RenderBossCrystal(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityBossCrystal entity, double x, double y, double z, float entityYaw, float partialTicks) {
        long worldTime = Minecraft.getMinecraft().world.getTotalWorldTime();

        float angle = worldTime % 360;
        float yScale = 4.5F;

        int color = entity.getColor();
        float red = RenderUtils.getRed(color) / 255F;
        float blue = RenderUtils.getBlue(color) / 255F;
        float green = RenderUtils.getGreen(color) / 255F;
        float alpha = RenderUtils.getAlpha(color) / 255F;

        //starts
        GlStateManager.pushMatrix();

        //colors texture
        GL11.glColor4f(red, green, blue, alpha);

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
        MODEL_BOSS_CRYSTAL.render(0.0625F);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();

        //ends
        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityBossCrystal entity) {
        return CRYSTAL_TEXTURE;
    }
}
