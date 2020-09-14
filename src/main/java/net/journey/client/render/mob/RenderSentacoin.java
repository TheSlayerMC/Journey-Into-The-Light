package net.journey.client.render.mob;

import net.journey.entity.util.EntitySentacoin;
import net.journey.init.items.JourneyItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSentacoin extends Render<EntitySentacoin>{

	public RenderSentacoin() {
		super(Minecraft.getMinecraft().getRenderManager());
        this.shadowSize = 0.07F;
        this.shadowOpaque = 0.75F;
	}

	@Override
	public void doRender(EntitySentacoin entity, double x, double y, double z, float entityYaw, float partialTicks) {
        float angle = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) * 10;
        float scale = 0.55F;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y + 0.05F, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(scale, scale, scale);
        GlStateManager.rotate(angle + partialTicks, 0.0F, 1.0F, 0.0F);
        Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(JourneyItems.sentacoin, 1, 0), ItemCameraTransforms.TransformType.GROUND);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySentacoin entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}
}