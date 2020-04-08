package net.journey.dimension.depths.gen;

import java.util.Random;

import com.google.common.collect.Lists;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.util.JourneyLootTables;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenSorcererShrine extends WorldGenerator {
	
	public boolean locationIsValidSpawn(World w, int x, int y, int z) {
		return WorldGenAPI.checkRadius(w, new BlockPos(x,y,z), 11, JourneyBlocks.depthsGrass);
	}
	
	/*private static WeightedRandomChestContent[] loot = {
			new WeightedRandomChestContent(JourneyItems.depthsFlake, 0, 1, 5, 10), 
			new WeightedRandomChestContent(JourneyItems.scale, 0, 1, 10, 40), 
			new WeightedRandomChestContent(JourneyItems.beastlyStomach, 0, 1, 5, 40)};*/
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int i = pos.getX(), j = pos.getY() - 1, k = pos.getZ();
		if(locationIsValidSpawn(world, i, j, k)) return true;
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 4, k + 3), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 4, k + 5), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 3), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 4), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 5), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 4), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 4), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 4), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 2), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 4), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 6), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 2), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 3), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 4), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 5), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 6), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 1, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 1, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 1), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 7), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 4), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 5, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 6, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 1), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 2), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 3), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 4), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 5), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 6), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 7), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 1, k + 1), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 1, k + 7), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 1), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 7), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 1), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 7), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 0), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 1), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 7), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 8), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 4), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 5, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 6, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 6, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 1), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 2), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 3), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 4), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 5), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 6), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 7), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 1, k + 1), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 1, k + 7), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 2, k + 1), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 2, k + 7), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 1), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 7), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 1), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 7), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 4), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 5, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 1), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 2), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 3), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 4), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 5), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 6), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 7), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 1, k + 1), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 1, k + 4), JourneyBlocks.sorcererSpawner.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 1, k + 7), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 2, k + 1), JourneyBlocks.darklyLock.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 2, k + 4), JourneyBlocks.depthsChest.getStateFromMeta(2));
        TileEntity chest = world.getTileEntity(new BlockPos(i + 5, j + 2, k + 4));
		if (chest instanceof TileEntityJourneyChest) {
			((TileEntityJourneyChest) chest).setLootTable(JourneyLootTables.DEPTHS_SHRINE_CHEST, rand.nextLong());
		}
		
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 2, k + 7), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 3, k + 1), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 3, k + 7), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 4, k + 0), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 4, k + 1), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 4, k + 7), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 4, k + 8), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 5, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 5, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 5, k + 4), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 5, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 5, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 6, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 6, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 1), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 2), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 3), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 4), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 5), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 6), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 7), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 1, k + 1), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 1, k + 7), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 2, k + 1), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 2, k + 7), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 3, k + 1), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 3, k + 7), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 4, k + 1), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 4, k + 7), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 5, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 5, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 5, k + 4), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 5, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 5, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 1), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 2), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 3), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 4), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 5), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 6), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 7), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 1, k + 1), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 1, k + 7), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 2, k + 1), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 2, k + 7), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 3, k + 1), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 3, k + 7), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 0), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 1), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 7), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 8), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 5, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 5, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 5, k + 4), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 5, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 5, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 6, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 6, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 0, k + 2), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 0, k + 3), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 0, k + 4), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 0, k + 5), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 0, k + 6), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 1, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 1, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 2, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 2, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 3, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 3, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 4, k + 1), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 4, k + 2), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 4, k + 6), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 4, k + 7), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 5, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 5, k + 4), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 5, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 6, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 6, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 0, k + 3), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 0, k + 4), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 0, k + 5), JourneyBlocks.darkFloor.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 1, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 1, k + 4), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 1, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 2, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 2, k + 4), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 2, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 3, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 3, k + 4), JourneyBlocks.darklyGate.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 3, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 4, k + 2), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 4, k + 3), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 4, k + 4), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 4, k + 5), JourneyBlocks.darkBrick.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 9, j + 4, k + 6), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 10, j + 4, k + 3), JourneyBlocks.darkShingle.getDefaultState());
		this.setBlockAndNotifyAdequately(world, new BlockPos(i + 10, j + 4, k + 5), JourneyBlocks.darkShingle.getDefaultState());

		return true;
	}
}
