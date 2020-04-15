package net.journey.client.render;

import net.journey.client.render.model.item.ModelObsidianBoat;
import net.journey.entity.item.EntityObsidianBoat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.IMultipassModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

@SideOnly(Side.CLIENT)
public class RenderModBoat<T> extends Render<EntityObsidianBoat> {

    private static final ResourceLocation BOAT_TEXTURE = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/entity/boat_obsidian.png");

    protected ModelBase model = new ModelObsidianBoat();

    public RenderModBoat(ModelBase model, float shadow) {
        super(Minecraft.getMinecraft().getRenderManager());
    }

    public RenderModBoat(ModelBase model, ResourceLocation tex) {
        this(model, 0.5F);
    }

    @Override
    public void doRender(EntityObsidianBoat entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        this.setupTranslation(x, y, z);
        this.setupRotation(entity, entityYaw, partialTicks);
        this.bindEntityTexture(entity);

        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.model.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void setupRotation(EntityObsidianBoat boat, float par2, float par3) {
        GlStateManager.rotate(180.0F - par2, 0.0F, 1.0F, 0.0F);
        float f = (float) boat.getTimeSinceHit() - par3;
        float f1 = boat.getDamageTaken() - par3;

        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            GlStateManager.rotate(MathHelper.sin(f) * f * f1 / 10.0F * (float) boat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }

        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
    }

    public void setupTranslation(double p_188309_1_, double p_188309_3_, double p_188309_5_) {
        GlStateManager.translate((float) p_188309_1_, (float) p_188309_3_ + 0.375F, (float) p_188309_5_);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityObsidianBoat entity) {
        return BOAT_TEXTURE;
    }

    @Override
    public boolean isMultipass() {
        return true;
    }

    @Override
    public void renderMultipass(EntityObsidianBoat p_188300_1_, double p_188300_2_, double p_188300_4_, double p_188300_6_, float p_188300_8_, float p_188300_9_) {
        GlStateManager.pushMatrix();
        this.setupTranslation(p_188300_2_, p_188300_4_, p_188300_6_);
        this.setupRotation(p_188300_1_, p_188300_8_, p_188300_9_);
        this.bindEntityTexture(p_188300_1_);
        ((IMultipassModel) this.model).renderMultipass(p_188300_1_, p_188300_9_, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
    }
}