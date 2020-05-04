package net.journey.client.render.block;

import net.journey.blocks.containers.BlockJourneyChest;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.client.render.model.block.ModelJourneyChest;
import net.journey.client.render.model.block.ModelJourneyLargeChest;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class JourneyChestTESR extends TileEntitySpecialRenderer<TileEntityJourneyChest> {

	private final ModelJourneyChest simpleChest = new ModelJourneyChest();
	private final ModelJourneyChest largeChest = new ModelJourneyLargeChest();

	@Override
	public void render(TileEntityJourneyChest te, double x, double y, double z, float partialTicks, int destroyStage,
	                   float alpha) {
		GlStateManager.enableDepth();
		GlStateManager.depthFunc(GL11.GL_LEQUAL);
		GlStateManager.depthMask(true);
		int i;

		if (te.hasWorld()) {
			Block block = te.getBlockType();
			i = te.getBlockMetadata();

			if (block instanceof BlockJourneyChest && i == 0) {
				((BlockJourneyChest) block).checkForSurroundingChests(te.getWorld(), te.getPos(),
						te.getWorld().getBlockState(te.getPos()));
				i = te.getBlockMetadata();
			}

			te.checkForAdjacentChests();
		} else {
			i = 0;
		}

		if (te.adjacentChestZNeg == null && te.adjacentChestXNeg == null) {
			ModelJourneyChest modelchest;

			if (te.adjacentChestXPos == null && te.adjacentChestZPos == null) {
				modelchest = this.simpleChest;

				if (destroyStage >= 0) {
					this.bindTexture(DESTROY_STAGES[destroyStage]);
					GlStateManager.matrixMode(GL11.GL_TEXTURE);
					GlStateManager.pushMatrix();
					GlStateManager.scale(4.0F, 4.0F, 1.0F);
					GlStateManager.matrixMode(GL11.GL_MODELVIEW);

				} else {
					bindTexture(te.getChestType().getSingleChestTexture());
				}
			} else {
				modelchest = this.largeChest;

				if (destroyStage >= 0) {
					this.bindTexture(DESTROY_STAGES[destroyStage]);
					GlStateManager.matrixMode(GL11.GL_TEXTURE);
					GlStateManager.pushMatrix();
					GlStateManager.scale(8.0F, 4.0F, 1.0F);
					GlStateManager.matrixMode(GL11.GL_MODELVIEW);

				} else {
					bindTexture(te.getChestType().getDoubleChestTexture());
				}
			}

			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();

			if (destroyStage < 0) {
				GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
			}

			GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.translate(0.5F, 0.5F, 0.5F);
			int j = 0;

			if (i == 2) {
				j = 180;
			}

			if (i == 3) {
				j = 0;
			}

			if (i == 4) {
				j = 90;
			}

			if (i == 5) {
				j = -90;
			}

			if (i == 2 && te.adjacentChestXPos != null) {
				GlStateManager.translate(1.0F, 0.0F, 0.0F);
			}

			if (i == 5 && te.adjacentChestZPos != null) {
				GlStateManager.translate(0.0F, 0.0F, -1.0F);
			}

			GlStateManager.rotate((float) j, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;

			if (te.adjacentChestZNeg != null) {
				float f1 = te.adjacentChestZNeg.prevLidAngle
						+ (te.adjacentChestZNeg.lidAngle - te.adjacentChestZNeg.prevLidAngle) * partialTicks;

				if (f1 > f) {
					f = f1;
				}
			}

			if (te.adjacentChestXNeg != null) {
				float f2 = te.adjacentChestXNeg.prevLidAngle
						+ (te.adjacentChestXNeg.lidAngle - te.adjacentChestXNeg.prevLidAngle) * partialTicks;

				if (f2 > f) {
					f = f2;
				}
			}

			f = 1.0F - f;
			f = 1.0F - f * f * f;
			modelchest.chestlid.rotateAngleX = -(f * ((float) Math.PI / 2F));
			modelchest.renderAll();
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

			if (destroyStage >= 0) {
				GlStateManager.matrixMode(GL11.GL_TEXTURE);
				GlStateManager.popMatrix();
				GlStateManager.matrixMode(GL11.GL_MODELVIEW);
			}
		}
	}

	public static class ChestTEISR extends TileEntityItemStackRenderer {

		private final TileEntityJourneyChest chest = new TileEntityJourneyChest();

		@Override
		public void renderByItem(ItemStack itemStackIn, float partialTicks) {
			BlockJourneyChest.Type chestType = ((BlockJourneyChest) Block.getBlockFromItem(itemStackIn.getItem())).chestType;
			chest.setChestType(chestType);
			TileEntityRendererDispatcher.instance.render(chest, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
		}
	}
}