package net.journey.client.render.mob.layers;

import net.journey.entity.mob.pet.EntityShiverwolf;
import net.minecraft.client.renderer.GlStateManager;
import net.journey.client.render.mob.RenderShiverwolf;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

@SideOnly(Side.CLIENT)
public class LayerShiverwolfCollar implements LayerRenderer<EntityShiverwolf> {
    private static final ResourceLocation WOLF_COLLAR = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/mobs/shiverwolf_collar.png");
    private final RenderShiverwolf wolfRenderer;

    public LayerShiverwolfCollar(RenderShiverwolf wolfRendererIn) {
        this.wolfRenderer = wolfRendererIn;
    }

	@Override
	public void doRenderLayer(EntityShiverwolf entitylivingbaseIn, float f, float f1, float t, float x, float y, float z, float scale) {
        if (entitylivingbaseIn.isTamed() && !entitylivingbaseIn.isInvisible()) {
            this.wolfRenderer.bindTexture(WOLF_COLLAR);
            EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(entitylivingbaseIn.getCollarColor().getMetadata());
            float[] afloat = EntitySheep.getDyeRgb(enumdyecolor);
            GlStateManager.color(afloat[0], afloat[1], afloat[2]);
            this.wolfRenderer.getMainModel().render(entitylivingbaseIn, f, f1, x, y, z, scale);
        }
    }

	@Override
    public boolean shouldCombineTextures() {
        return true;
	}
}