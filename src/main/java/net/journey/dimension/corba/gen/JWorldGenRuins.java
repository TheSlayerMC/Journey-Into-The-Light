package net.journey.dimension.corba.gen;

import net.journey.blocks.base.JBlockRandomLoot;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.RandHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.Random;

public class JWorldGenRuins extends WorldGenerator {

	/**
	 * maximum amount of space between blocks
	 */
	private static final int SPREADING = 5; // must be less or equal 9 otherwise may cause cascading lags
	public LootType type;
	public Block acceptableSurface;
	public IBlockState[] ruinBlocks;
	public Block specialBlock;
	public ResourceLocation lootTable;

	public JWorldGenRuins(Block acceptableSurface, LootType type, IBlockState... ruinBlocks) {
		this.acceptableSurface = acceptableSurface;
		this.type = type;
		this.ruinBlocks = ruinBlocks;
	}

	/**
	 * returns a list of random blocks for the structure foundation, specified by the list in the constructor
	 */
	public IBlockState getRandomBlocks(Random rand) {
		return RandHelper.chooseEqual(rand, ruinBlocks);
	}

	/**
	 * called if "LOOT_BOX" type is used.
	 * returns different loot box types with varying rarities
	 * AIR is most common
	 */
	public IBlockState getRandomLootBox(Random rand) {
		if (rand.nextInt(5) == 0) {
			return JourneyBlocks.ironLootBox.getDefaultState().withProperty(JBlockRandomLoot.FACING, getRandomFacing(rand));
		}
		if (rand.nextInt(10) == 0) {
			return JourneyBlocks.goldLootBox.getDefaultState().withProperty(JBlockRandomLoot.FACING, getRandomFacing(rand));
		}
		if (rand.nextInt(15) == 0) {
			return JourneyBlocks.diamondLootBox.getDefaultState().withProperty(JBlockRandomLoot.FACING, getRandomFacing(rand));
		}
		return Blocks.AIR.getDefaultState();
	}

	/**
	 * when called, a special block can be set inside of the structure if type isn't LOOT_BOX
	 *
	 * @param block ~ the block to be set in the structure
	 * @return
	 */
	public JWorldGenRuins setSpecialBlock(Block block) {
		this.specialBlock = block;
		return this;
	}

	/**
	 * when called, a loot table can be applied to the block inside of the structure if type is CONTAINER
	 *
	 * @param lootTable ~ the loot table being applied to the structure's 'special block'
	 * @return
	 */
	public JWorldGenRuins setLootTable(ResourceLocation lootTable) {
		this.lootTable = lootTable;
		return this;
	}

	/**
	 * called to generate the special block. generates a block depending on the type called in the constructor
	 */
	public IBlockState getSpecialBlock(Random rand) {
		switch (type) {
			case SPECIAL_BLOCK:
			case CONTAINER:
				return specialBlock.getDefaultState();
			case LOOT_BOX:
				return getRandomLootBox(rand);
			case RUINS:
				return Blocks.AIR.getDefaultState();
		}
		return Blocks.AIR.getDefaultState();
	}

	/**
	 * helper for Loot Boxes to have a random facing if they're being generated
	 */
	public EnumFacing getRandomFacing(Random rand) {
		return RandHelper.chooseEqual(rand, EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos) {
		pos = WorldGenAPI.optimizeAndRandomize(pos, rand);
		boolean generated = false;

		for (int j1 = 0; j1 < rand.nextInt(3) + 5; j1++) {
			BlockPos placePos = pos.add(rand.nextInt(SPREADING), 0, rand.nextInt(SPREADING));
			placePos = WorldGenAPI.findPosAboveSurface(worldIn, placePos);
			if (worldIn.getBlockState(placePos.down()).getBlock() == acceptableSurface) {
				int height = 1 + rand.nextInt(5);
				for (int j = 0; j < height; j++) {
					setBlockAndNotifyAdequately(worldIn, placePos, getRandomBlocks(rand));
					placePos = placePos.up();
				}
				generated = true;
			}
		}
		BlockPos specialBlockPos = WorldGenAPI.findPosAboveSurface(worldIn, pos);
		if (worldIn.getBlockState(specialBlockPos.down()).getBlock() == acceptableSurface) {
			setBlockAndNotifyAdequately(worldIn, specialBlockPos, getSpecialBlock(rand));
			if (type == LootType.CONTAINER) {
				TileEntity container = worldIn.getTileEntity(specialBlockPos);
				if (container instanceof TileEntityLockableLoot) {
					((TileEntityLockableLoot) container).setLootTable(this.lootTable, rand.nextLong());
				}
			}
			generated = true;
		}
		return generated;
	}

	/**
	 * enum to help with various blocks the structure can generate
	 */
	public enum LootType {
		/**
		 * generates a random loot box with probability
		 */
		LOOT_BOX,
		/**
		 * generates any other block
		 * use setSpecialBlock() method
		 */
		SPECIAL_BLOCK,
		/**
		 * generates any other block with a container
		 * use setSpecialBlock() method
		 * use setLootTable() method
		 */
		CONTAINER,
		/**
		 * generates no special block, just structure blocks
		 */
		RUINS
	}
}
