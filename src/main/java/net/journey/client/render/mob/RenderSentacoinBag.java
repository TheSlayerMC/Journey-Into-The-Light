package net.journey.client.render.mob;

import net.journey.entity.util.EntitySentacoinBag;
import net.journey.init.items.JourneyItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSentacoinBag extends Render<EntitySentacoinBag>{

	public RenderSentacoinBag() {
		super(Minecraft.getMinecraft().getRenderManager());
		this.shadowSize = 0.20F;
		this.shadowOpaque = 0.75F;
	}

	@Override
	public void doRender(EntitySentacoinBag entity, double x, double y, double z, float entityYaw, float partialTicks) {
		float angle = (float) (360.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) * 5;
		float scale = 1.0F;
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)x, (float)y + 0.15F, (float)z);
		GlStateManager.scale(scale, scale, scale);
		//renderCoin((int)x, (int)y + 1, (int)z, 1, 1, 1, 90F);
		GlStateManager.enableRescaleNormal();
		 GlStateManager.rotate(angle + partialTicks, 0.0F, 1.0F, 0.0F);
			Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(JourneyItems.sentacoinBag, 1, 0), ItemCameraTransforms.TransformType.GROUND);

		GlStateManager.popMatrix();
		
	}

	private void renderCoin(int x, int y, int z, int xr, int yr, int zr, float angle) {
		GlStateManager.pushMatrix();
		//GlStateManager.translate((float)x, (float)y + 0.15F, (float)z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.rotate(angle, xr, yr, zr);
		Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(JourneyItems.sentacoin, 1, 0), ItemCameraTransforms.TransformType.GROUND);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySentacoinBag entity) {
		return TextureMap.LOCATION_BLOCKS_TEXTURE;
	}
}