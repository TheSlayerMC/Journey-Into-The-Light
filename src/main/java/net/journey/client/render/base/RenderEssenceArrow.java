package net.journey.client.render.base;

import net.journey.client.render.Textures;
import net.journey.entity.projectile.arrow.EntityEssenceArrow;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEssenceArrow extends RenderArrow<EntityEssenceArrow> {
    public static final ResourceLocation RES_ARROW = Textures.getProjectileTextureLocation("essence_arrow");

    public RenderEssenceArrow(RenderManager manager) {
        super(manager);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityEssenceArrow entity) {
        return RES_ARROW;
    }
}