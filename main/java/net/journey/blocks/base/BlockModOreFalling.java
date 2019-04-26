package net.journey.blocks.base;

import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.JourneyTabs;
import net.journey.client.render.particles.OreParticleFX;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockModGravity;

public class BlockModOreFalling extends BlockModGravity {

	public boolean hasParticle;
	
	public BlockModOreFalling(String name, String finalName) {
		super(name, finalName);
		this.hasParticle = true;
	}
	
	public BlockModOreFalling(String name, String finalName, Boolean hasParticle) {
		super(name, finalName);
		this.hasParticle = hasParticle;
	}
	
	public BlockModOreFalling(EnumMaterialTypes material, String name, String finalName, Boolean hasParticle) {
		super(name, finalName);
		this.hasParticle = hasParticle;
		this.blockType = material;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (this.hasParticle) {
			renderParticle(worldIn, pos.getX(), pos.getY(), pos.getZ(), pos);
		}
	}
	
	@SideOnly(Side.CLIENT)
	private void renderParticle(World w, int x, int y, int z, BlockPos pos) {
		float r = 0.0F, g = 0.0F, b = 0.0F;
		if(this == JourneyBlocks.heatstoneOre) {
			r = 1.0F;
			g = 0.5F;
			b = 0.0F;
		}
		Random random = w.rand;
		double d0 = 0.0625D;
		for(int l = 0; l < 6; ++l) {
			double d1 = x + random.nextFloat();
			double d2 = y + random.nextFloat();
			double d3 = z + random.nextFloat();
			if(l == 0 && !w.getBlockState(pos.up()).isOpaqueCube()) d2 = y + 1 + d0;
			if(l == 1 && !w.getBlockState(pos.down()).isOpaqueCube()) d2 = y + 0 - d0;
			if(l == 2 && !w.getBlockState(pos.south()).isOpaqueCube()) d3 = z + 1 + d0;    
			if(l == 3 && !w.getBlockState(pos.north()).isOpaqueCube()) d3 = z + 0 - d0;
			if(l == 4 && !w.getBlockState(pos.east()).isOpaqueCube()) d1 = x + 1 + d0;
			if(l == 5 && !w.getBlockState(pos.west()).isOpaqueCube()) d1 = x + 0 - d0;
			if(d1 < x || d1 > x + 1 || d2 < 0.0D || d2 > y + 1 || d3 < z || d3 > z + 1) {
				OreParticleFX var20 = new OreParticleFX(w, d1, d2, d3, r, g, b);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
			}
		}
	}
	
	@Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if(fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
            int j = random.nextInt(fortune + 2) - 1;
            if(j < 0) j = 0; 
            return this.quantityDropped(random) * (j + 1);
        } else {
        	return 1;
        }
	}
	
	@Override
	public Item getItemDropped(IBlockState par1, Random par2, int par3) {
		return Item.getItemFromBlock(this);
	}
}
