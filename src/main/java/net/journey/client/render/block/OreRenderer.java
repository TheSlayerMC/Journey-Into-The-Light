package net.journey.client.render.block;

public class OreRenderer {

}
	/*implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		Tessellator tess = Tessellator.instance;
		block.setBlockBoundsForItemRender();
		renderer.setRenderBoundsFromBlock(block);
		Block render = Blocks.sandstone;
		
		if(block == EssenceBlocks.celestiumOre) render = EssenceBlocks.eucaStone;
		else if(block == EssenceBlocks.flairiumOre) render = EssenceBlocks.depthsStone;
		else if(block == EssenceBlocks.celestiumBlock) render = EssenceBlocks.celestiumBlockOverlay;
		else if(block == EssenceBlocks.flairiumBlock) render = EssenceBlocks.flairiumBlockOverlay;
		else if(block == EssenceBlocks.hellstoneBlock) render = EssenceBlocks.hellstoneBlockOverlay;
		else if(block == EssenceBlocks.shadiumBlock) render = EssenceBlocks.shadiumBlockOverlay;
		else if(block == EssenceBlocks.luniumBlock) render = EssenceBlocks.luniumBlockOverlay;
		else if(block == EssenceBlocks.hellstoneOre) render = Blocks.netherrack;
		else if(block == EssenceBlocks.sapphireOre) render = Blocks.stone;

		else render = Blocks.stone;
		
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);  
		tess.startDrawingQuads();
		tess.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, render.getBlockTextureFromSide(0));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 0));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, render.getBlockTextureFromSide(1));  
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 1));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, render.getBlockTextureFromSide(2));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 2));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, render.getBlockTextureFromSide(3));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 3));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, render.getBlockTextureFromSide(4));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 4));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, render.getBlockTextureFromSide(5));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSide(block, 5));
		tess.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if(block == EssenceBlocks.celestiumOre) {
			renderer.renderStandardBlock(EssenceBlocks.eucaStone, x, y, z);
			renderer.renderStandardBlock(EssenceBlocks.celestiumOre, x, y, z);
			return true;
		}
		
		if(block == EssenceBlocks.shadiumOre) {
			renderer.renderStandardBlock(Blocks.stone, x, y, z);
			renderer.renderStandardBlock(EssenceBlocks.shadiumOre, x, y, z);
			return true;
		}
		
		if(block == EssenceBlocks.luniumOre) {
			renderer.renderStandardBlock(Blocks.stone, x, y, z);
			renderer.renderStandardBlock(EssenceBlocks.luniumOre, x, y, z);
			return true;
		}
		
		if(block == EssenceBlocks.flairiumOre) {
			renderer.renderStandardBlock(EssenceBlocks.depthsStone, x, y, z);
			renderer.renderStandardBlock(EssenceBlocks.flairiumOre, x, y, z);
			return true;
		}
		
		if(block == EssenceBlocks.celestiumBlock) {
			renderer.renderStandardBlock(EssenceBlocks.celestiumBlockOverlay, x, y, z);
			renderer.renderStandardBlock(EssenceBlocks.celestiumBlock, x, y, z);
			return true;
		}
		
		if(block == EssenceBlocks.flairiumBlock) {
			renderer.renderStandardBlock(EssenceBlocks.flairiumBlockOverlay, x, y, z);
			renderer.renderStandardBlock(EssenceBlocks.flairiumBlock, x, y, z);
			return true;
		}
		
		if(block == EssenceBlocks.luniumBlock) {
			renderer.renderStandardBlock(EssenceBlocks.luniumBlockOverlay, x, y, z);
			renderer.renderStandardBlock(EssenceBlocks.luniumBlock, x, y, z);
			return true;
		}
		
		if(block == EssenceBlocks.hellstoneBlock) {
			renderer.renderStandardBlock(EssenceBlocks.hellstoneBlockOverlay, x, y, z);
			renderer.renderStandardBlock(EssenceBlocks.hellstoneBlock, x, y, z);
			return true;
		}
		
		if(block == EssenceBlocks.shadiumBlock) {
			renderer.renderStandardBlock(EssenceBlocks.shadiumBlockOverlay, x, y, z);
			renderer.renderStandardBlock(EssenceBlocks.shadiumBlock, x, y, z);
			return true;
		}
		
		if(block == EssenceBlocks.hellstoneOre) {
			renderer.renderStandardBlock(Blocks.netherrack, x, y, z);
			renderer.renderStandardBlock(EssenceBlocks.hellstoneOre, x, y, z);
			return true;
		}
		
		if(block == EssenceBlocks.sapphireOre) {
			renderer.renderStandardBlock(Blocks.stone, x, y, z);
			renderer.renderStandardBlock(EssenceBlocks.sapphireOre, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return RenderingRegistry.getNextAvailableRenderId();
	}
}*/