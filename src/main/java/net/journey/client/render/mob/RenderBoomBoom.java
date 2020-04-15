package net.journey.client.render.mob;

import net.journey.client.render.RenderModMob;
import net.journey.client.render.mob.layers.LayerBoomCharge;
import net.journey.client.render.model.mob.overworld.ModelBoomBoom;
import net.journey.entity.mob.overworld.EntityBoom;
import net.journey.util.Textures;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBoomBoom extends RenderModMob<EntityBoom> {

    private final ResourceLocation armoredBoomTextures = Textures.boomArmor;
    private final ResourceLocation boomTextures = Textures.boom;
    private final ModelBoomBoom boomModel = new ModelBoomBoom();
    public RenderBoomBoom(ModelBase model, ResourceLocation tex) {
        super(model, tex);
        this.addLayer(new LayerBoomCharge(this));
    }

    protected void render(EntityBoom e, float f) {
        float f1 = e.getFlashIntensity(f);
        float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;
        f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
        f1 *= f1;
        f1 *= f1;
        float f3 = (1.0F + f1 * 0.4F) * f2;
        float f4 = (1.0F + f1 * 0.1F) / f2;
        GlStateManager.scale(f3, f4, f3);
    }

    protected ResourceLocation getEntityTexture(EntityBoom e) {
        return boomTextures;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase e, float f) {
        render((EntityBoom) e, f);
    }

    protected int flash(EntityBoom e, float x, float y) {
        float f2 = e.getFlashIntensity(y);
        if ((int) (f2 * 10.0F) % 2 == 0) {
            return 0;
        } else {
            int i = (int) (f2 * 0.2F * 255.0F);
            i = MathHelper.clamp(i, 0, 255);
            return i << 24 | 16777215;
        }
    }

    @Override
    protected int getColorMultiplier(EntityLivingBase e, float x, float y) {
        return this.flash((EntityBoom) e, x, y);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity e) {
        return this.getEntityTexture((EntityBoom) e);
    }
}