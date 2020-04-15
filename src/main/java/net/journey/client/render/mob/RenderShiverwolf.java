package net.journey.client.render.mob;

import net.journey.client.render.mob.layers.LayerShiverwolfCollar;
import net.journey.entity.mob.pet.EntityShiverwolf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

@SideOnly(Side.CLIENT)
public class RenderShiverwolf extends RenderLiving<EntityShiverwolf> {
    private static final ResourceLocation wolfTextures = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/mobs/shiverwolf.png");
    private static final ResourceLocation tamedWolfTextures = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/mobs/shiverwolf_tamed.png");
    private static final ResourceLocation anrgyWolfTextures = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/mobs/shiverwolf_angry.png");

    public RenderShiverwolf(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
        super(renderManagerIn, modelBaseIn, shadowSizeIn);
        this.addLayer(new LayerShiverwolfCollar(this));
    }

    @Override
    protected float handleRotationFloat(EntityShiverwolf livingBase, float partialTicks) {
        return livingBase.getTailRotation();
    }

    @Override
    public void doRender(EntityShiverwolf entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (entity.isWolfWet()) {
            float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
            GlStateManager.color(f, f, f);
        }
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityShiverwolf entity) {
        return entity.isTamed() ? tamedWolfTextures : (entity.isAngry() ? anrgyWolfTextures : wolfTextures);
    }
}