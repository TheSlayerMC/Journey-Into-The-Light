package net.journey.client.render.block;

import java.util.Calendar;

import net.journey.blocks.machines.BlockJourneyChest;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.client.render.model.block.ModelJoureyLargeChest;
import net.journey.client.render.model.block.ModelJourneyChest;
import net.journey.util.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.SlayerAPI;

@SideOnly(Side.CLIENT)
public class JourneyChestRenderer extends TileEntitySpecialRenderer<TileEntityJourneyChest> {
	
	private static final ResourceLocation NETHER = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestnether.png");
	private static final ResourceLocation NETHER_DOUBLE = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestnether_double.png");
	
	private static final ResourceLocation EUCA = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chesteuca.png");
	private static final ResourceLocation EUCA_DOUBLE = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chesteuca_double.png");
	
	private static final ResourceLocation BOIL = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestboil.png");
	private static final ResourceLocation BOIL_DOUBLE = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestboil_double.png");
	
	private static final ResourceLocation FROZEN = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestfrozen.png");
	private static final ResourceLocation FROZEN_DOUBLE = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestfrozen_doublee.png");
	
	private static final ResourceLocation DEPTHS = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestdepths.png");
	private static final ResourceLocation DEPTHS_DOUBLE = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestdepths_double.png");
	
	private static final ResourceLocation CORBA = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestcorba.png");
	private static final ResourceLocation CORBA_DOUBLE = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestcorba_double.png");
	
	private static final ResourceLocation TERRA = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestterra.png");
	private static final ResourceLocation TERRA_DOUBLE = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestterra_double.png");
	
	private static final ResourceLocation CLOUDIA = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestcloudia.png");
	private static final ResourceLocation CLOUDIA_DOUBLE = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestcloudia_double.png");

	private static final ResourceLocation JOURNEY = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestjourney.png");
	private static final ResourceLocation JOURNEY_DOUBLE = new ResourceLocation(SlayerAPI.PREFIX + "textures/models/blocks/chestjourney_double.png");
	
	private final ModelJourneyChest simpleChest = new ModelJourneyChest();
	private final ModelJourneyChest largeChest = new ModelJoureyLargeChest();

	public JourneyChestRenderer() {
	}

	@Override
	public void render(TileEntityJourneyChest te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		GlStateManager.enableDepth();
		GlStateManager.depthFunc(515);
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
					GlStateManager.matrixMode(5890);
					GlStateManager.pushMatrix();
					GlStateManager.scale(4.0F, 4.0F, 1.0F);
					GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
					GlStateManager.matrixMode(5888);
				} else if(te.getChestType() == BlockJourneyChest.Type.JOURNEY) {
					this.bindTexture(JOURNEY);
				} else if(te.getChestType() == BlockJourneyChest.Type.NETHER) {
					this.bindTexture(NETHER);
				} else if(te.getChestType() == BlockJourneyChest.Type.EUCA) {
					this.bindTexture(EUCA);
				} else if(te.getChestType() == BlockJourneyChest.Type.BOIL) {
					this.bindTexture(BOIL);
				} else if(te.getChestType() == BlockJourneyChest.Type.FROZEN) {
					this.bindTexture(FROZEN);
				} else if(te.getChestType() == BlockJourneyChest.Type.DEPTHS) {
					this.bindTexture(DEPTHS);
				} else if(te.getChestType() == BlockJourneyChest.Type.CORBA) {
					this.bindTexture(CORBA);
				} else if(te.getChestType() == BlockJourneyChest.Type.TERRA) {
					this.bindTexture(TERRA);
				} else if(te.getChestType() == BlockJourneyChest.Type.CLOUDIA) {
					this.bindTexture(CLOUDIA);
				}
			} else {
				modelchest = this.largeChest;

				if (destroyStage >= 0) {
					this.bindTexture(DESTROY_STAGES[destroyStage]);
					GlStateManager.matrixMode(5890);
					GlStateManager.pushMatrix();
					GlStateManager.scale(8.0F, 4.0F, 1.0F);
					GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
					GlStateManager.matrixMode(5888);
				} else if(te.getChestType() == BlockJourneyChest.Type.JOURNEY){
					this.bindTexture(JOURNEY_DOUBLE);
				} else if(te.getChestType() == BlockJourneyChest.Type.NETHER){
					this.bindTexture(NETHER_DOUBLE);
				} else if(te.getChestType() == BlockJourneyChest.Type.EUCA){
					this.bindTexture(EUCA_DOUBLE);
				} else if(te.getChestType() == BlockJourneyChest.Type.BOIL){
					this.bindTexture(BOIL_DOUBLE);
				} else if(te.getChestType() == BlockJourneyChest.Type.FROZEN){
					this.bindTexture(FROZEN_DOUBLE);
				} else if(te.getChestType() == BlockJourneyChest.Type.DEPTHS){
					this.bindTexture(DEPTHS_DOUBLE);
				} else if(te.getChestType() == BlockJourneyChest.Type.CORBA){
					this.bindTexture(CORBA_DOUBLE);
				} else if(te.getChestType() == BlockJourneyChest.Type.TERRA){
					this.bindTexture(TERRA_DOUBLE);
				} else if(te.getChestType() == BlockJourneyChest.Type.CLOUDIA){
					this.bindTexture(CLOUDIA_DOUBLE);
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
			modelchest.chestLid.rotateAngleX = -(f * ((float) Math.PI / 2F));
			modelchest.renderAll();
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

			if (destroyStage >= 0) {
				GlStateManager.matrixMode(5890);
				GlStateManager.popMatrix();
				GlStateManager.matrixMode(5888);
			}
		}
	}
}