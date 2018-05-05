package net.journey.dimension.nether.gen;

import java.util.ArrayList;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.entity.mob.boss.EntityTempleGuardian;
import net.journey.util.Helper;
import net.journey.util.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

public class WorldGenNetherTower extends WorldGenerator {

	@Override
	public boolean generate(World w, Random r, BlockPos pos) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		int levels = 4;
		int height = levels * 4;

		/*WorldGenAPI.placeChestWithContents(w, x + 13, y + 14, z + 1, 1, false, items);
		WorldGenAPI.placeChestWithContents(w, x + 1, y + 14, z + 8, 2, false, items);
		WorldGenAPI.placeChestWithContents(w, x + 13, y + 14, z + 8, 2, false, items);*/
		
		WorldGenAPI.addRectangle(17, 12, 1, w, x - 1, y, z - 1, Blocks.netherrack);
		WorldGenAPI.addRectangle(17, 12, 3, w, x - 1, y - 3, z - 1, Blocks.netherrack);

		WorldGenAPI.addCornerlessRectangle(15, 10, height + 10, w, x, y + 1, z, Blocks.nether_brick);
		WorldGenAPI.addRectangle(13, 8, height + 12, w, x + 1, y + 1, z + 1, Blocks.air);
		WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y, z + 1, Blocks.nether_brick);
		WorldGenAPI.addRectangle(2, 1, 3, w, x + 6, y + 1, z, Blocks.air);
		WorldGenAPI.addRectangle(2, 1, 3, w, x + 6, y + 1, z + 9, Blocks.air);
		WorldGenAPI.addRectangle(1, 2, 3, w, x, y + 1, z + 4, Blocks.air);
		WorldGenAPI.addRectangle(1, 2, 3, w, x + 14, y + 1, z + 4, Blocks.air);
		
		ArrayList<String> mobNames = new ArrayList<String>();
		
		mobNames.add("Blaze");
		mobNames.add("LavaSlime");
		mobNames.add("PigZombie");
		mobNames.add("Skeleton");
		mobNames.add("lavasnake");
		mobNames.add("reaper");
		
		addLevel(w, x, y, z, 4, 1, mobNames.get(r.nextInt(mobNames.size())), mobNames.get(r.nextInt(mobNames.size())));
		addLevel(w, x, y, z, 4, 0, mobNames.get(r.nextInt(mobNames.size())), mobNames.get(r.nextInt(mobNames.size())));
		addLevel(w, x, y, z, 12, 1, mobNames.get(r.nextInt(mobNames.size())), mobNames.get(r.nextInt(mobNames.size())));
		addLevel(w, x, y, z, 12, 0, mobNames.get(r.nextInt(mobNames.size())), mobNames.get(r.nextInt(mobNames.size())));
		addLevel(w, x, y, z, 20, 1, mobNames.get(r.nextInt(mobNames.size())), mobNames.get(r.nextInt(mobNames.size())));
		
		WorldGenAPI.addRectangleWithMetadata(15, 1, 1, w, x, y + height * 2 - 6, z - 1, Blocks.nether_brick_stairs, 6);
		WorldGenAPI.addRectangleWithMetadata(15, 1, 1, w, x, y + height * 2 - 6, z + 10, Blocks.nether_brick_stairs, 7);
		WorldGenAPI.addRectangleWithMetadata(1, 10, 1, w, x + 15, y + height * 2 - 6, z, Blocks.nether_brick_stairs, 5);
		WorldGenAPI.addRectangleWithMetadata(1, 10, 1, w, x - 1, y + height * 2 - 6, z, Blocks.nether_brick_stairs, 12);
		
		WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height + 8, z + 1, JourneyBlocks.mossyEssenceStone);
		WorldGenAPI.addRectangle(15, 10, 1, w, x, y + height + 10, z, Blocks.nether_brick);
		WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height + 10, z + 1, Blocks.air);

		WorldGenAPI.addHollowRectangle(15, 10, 1, w, x, y + height + 11, z, JourneyBlocks.dungeonLampFence);
		WorldGenAPI.addHollowRectangle(13, 8, 1, w, x + 1, y + height + 11, z + 1, Blocks.air);
		WorldGenAPI.addRectangle(4, 2, 1, w, x + 2, y + height + 8, z + 1, Blocks.air);

		for(int i = 0; i < 4; i++) {
			w.setBlockState(new BlockPos(x + 5 - i, y + i + height + 5, z + 1), Blocks.nether_brick_stairs.getStateFromMeta(1), 2);
			w.setBlockState(new BlockPos(x + 5 - i, y + i + height + 5, z + 2), Blocks.nether_brick_stairs.getStateFromMeta(1), 2);
		}
		
		w.setBlockState(new BlockPos(x + 13, y + height + 9, z + 1), JourneyBlocks.dungeonLamp.getDefaultState());
		w.setBlockState(new BlockPos(x + 1, y + height + 9, z + 8), JourneyBlocks.dungeonLamp.getDefaultState());
		w.setBlockState(new BlockPos(x + 13, y + height + 9, z + 8), JourneyBlocks.dungeonLamp.getDefaultState());

		w.setBlockState(new BlockPos(x + 13, y + height + 10, z + 1), JourneyBlocks.redGems.getDefaultState());
		w.setBlockState(new BlockPos(x + 1, y + height + 10, z + 8), JourneyBlocks.redGems.getDefaultState());
		w.setBlockState(new BlockPos(x + 13, y + height + 10, z + 8), JourneyBlocks.redGems.getDefaultState());
		
		w.setBlockState(new BlockPos(x + 12, y + height + 9, z + 8), JourneyBlocks.dungeonLampStairs.getStateFromMeta(0), 2);
		w.setBlockState(new BlockPos(x + 12, y + height + 9, z + 7), JourneyBlocks.dungeonLampStairs.getStateFromMeta(0), 2);
		w.setBlockState(new BlockPos(x + 13, y + height + 9, z + 7), JourneyBlocks.dungeonLampStairs.getStateFromMeta(2), 2);
		w.setBlockState(new BlockPos(x + 12, y + height + 9, z + 2), JourneyBlocks.dungeonLampStairs.getStateFromMeta(0), 2);
		w.setBlockState(new BlockPos(x + 12, y + height + 9, z + 1), JourneyBlocks.dungeonLampStairs.getStateFromMeta(0), 2);
		w.setBlockState(new BlockPos(x + 13, y + height + 9, z + 2), JourneyBlocks.dungeonLampStairs.getStateFromMeta(3), 2);
		w.setBlockState(new BlockPos(x + 2, y + height + 9, z + 7), JourneyBlocks.dungeonLampStairs.getStateFromMeta(2), 2);
		w.setBlockState(new BlockPos(x + 1, y + height + 9, z + 7), JourneyBlocks.dungeonLampStairs.getStateFromMeta(2), 2);
		w.setBlockState(new BlockPos(x + 2, y + height + 9, z + 8), JourneyBlocks.dungeonLampStairs.getStateFromMeta(1), 2);
				
		if(!w.isRemote) {
			EntityTempleGuardian guard = new EntityTempleGuardian(w);
			guard.setLocationAndAngles(x + 5, y + height + 10, z + 5, 0.0F, 0.0F);
			w.spawnEntityInWorld(guard);
		}
		return true;
	}

	public void addLevel(World w, int x, int y, int z, int height, int lor, String mobName, String mobName2) {
		Random r = new Random();
		WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height, z + 1, Blocks.nether_brick);
		WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height + 4, z + 1, Blocks.nether_brick);
		WorldGenAPI.addRectangle(4, 2, 1, w, x + 2, y + height + 4, z + 1, Blocks.air);
		WorldGenAPI.addRectangle(4, 2, 1, w, x + 2, y + height, z + 7, Blocks.air);
		WorldGenAPI.addBlock(w, x + 13, y + height - 1, z + 1, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 1, y + height - 1, z + 1, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 1, y + height - 1, z + 8, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 13, y + height - 1, z + 8, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 13, y + height - 5, z + 1, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 1, y + height - 5, z + 1, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 1, y + height - 5, z + 8, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 13, y + height - 5, z + 8, JourneyBlocks.dungeonLamp);
		
		w.setBlockState(new BlockPos(x + 11, y + height - 3, z + 2), Blocks.mob_spawner.getDefaultState());
		w.setBlockState(new BlockPos(x + 11, y + height - 3, z + 7), Blocks.mob_spawner.getDefaultState());
		w.setBlockState(new BlockPos(x + 11, y + height + 1, z + 2), Blocks.mob_spawner.getDefaultState());
		w.setBlockState(new BlockPos(x + 11, y + height + 1, z + 7), Blocks.mob_spawner.getDefaultState());
		TileEntityMobSpawner spawner1 = (TileEntityMobSpawner)w.getTileEntity(new BlockPos(x + 11, y + height - 3, z + 2));
		TileEntityMobSpawner spawner2 = (TileEntityMobSpawner)w.getTileEntity(new BlockPos(x + 11, y + height - 3, z + 7));
		TileEntityMobSpawner spawner3 = (TileEntityMobSpawner)w.getTileEntity(new BlockPos(x + 11, y + height + 1, z + 2));
		TileEntityMobSpawner spawner4 = (TileEntityMobSpawner)w.getTileEntity(new BlockPos(x + 11, y + height + 1, z + 7));
		
		if(spawner1 != null && spawner2 != null && spawner3 != null && spawner4 != null) {
			spawner1.getSpawnerBaseLogic().setEntityName(mobName);
			spawner2.getSpawnerBaseLogic().setEntityName(mobName2);
			spawner3.getSpawnerBaseLogic().setEntityName(mobName2);
			spawner4.getSpawnerBaseLogic().setEntityName(mobName);
		} else {
			LogHelper.error("Error placing mob spawner for Nether tower dungeon");
		}
		
		for(int i = 0; i < 4; i++) {
			if(lor == 0) {
				w.setBlockState(new BlockPos(x + 5 - i, y + i + 1 + height, z + 1), Blocks.nether_brick_stairs.getStateFromMeta(1), 2);
				w.setBlockState(new BlockPos(x + 5 - i, y + i + 1 + height, z + 2), Blocks.nether_brick_stairs.getStateFromMeta(1), 2);
			} else {
				w.setBlockState(new BlockPos(x + 5 - i, y + i + 1 + height - 4, z + 7), Blocks.nether_brick_stairs.getStateFromMeta(1), 2);
				w.setBlockState(new BlockPos(x + 5 - i, y + i + 1 + height - 4, z + 8), Blocks.nether_brick_stairs.getStateFromMeta(1), 2);
			}
		}
		w.setBlockState(new BlockPos(x + 2, y + 1 + height - 1, z + 7), Blocks.nether_brick_stairs.getStateFromMeta(1), 2);
		w.setBlockState(new BlockPos(x + 2, y + 1 + height - 1, z + 8), Blocks.nether_brick_stairs.getStateFromMeta(1), 2);
	}
}