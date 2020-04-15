package net.journey.dimension.nether.gen;

import java.util.ArrayList;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.entity.mob.boss.EntityTempleGuardian;
import net.journey.util.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
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
		
		WorldGenAPI.addRectangle(17, 12, 1, w, x - 1, y, z - 1, Blocks.NETHERRACK);
		WorldGenAPI.addRectangle(17, 12, 3, w, x - 1, y - 3, z - 1, Blocks.NETHERRACK);

		WorldGenAPI.addCornerlessRectangle(15, 10, height + 10, w, x, y + 1, z, Blocks.NETHER_BRICK);
		WorldGenAPI.addRectangle(13, 8, height + 12, w, x + 1, y + 1, z + 1, Blocks.AIR);
		WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y, z + 1, Blocks.NETHER_BRICK);
		WorldGenAPI.addRectangle(2, 1, 3, w, x + 6, y + 1, z, Blocks.AIR);
		WorldGenAPI.addRectangle(2, 1, 3, w, x + 6, y + 1, z + 9, Blocks.AIR);
		WorldGenAPI.addRectangle(1, 2, 3, w, x, y + 1, z + 4, Blocks.AIR);
		WorldGenAPI.addRectangle(1, 2, 3, w, x + 14, y + 1, z + 4, Blocks.AIR);
		
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
		
		WorldGenAPI.addRectangleWithMetadata(15, 1, 1, w, x, y + height * 2 - 6, z - 1, Blocks.NETHER_BRICK_STAIRS, 6);
		WorldGenAPI.addRectangleWithMetadata(15, 1, 1, w, x, y + height * 2 - 6, z + 10, Blocks.NETHER_BRICK_STAIRS, 7);
		WorldGenAPI.addRectangleWithMetadata(1, 10, 1, w, x + 15, y + height * 2 - 6, z, Blocks.NETHER_BRICK_STAIRS, 5);
		WorldGenAPI.addRectangleWithMetadata(1, 10, 1, w, x - 1, y + height * 2 - 6, z, Blocks.NETHER_BRICK_STAIRS, 12);
		
		WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height + 8, z + 1, JourneyBlocks.mossyEssenceStone);
		WorldGenAPI.addRectangle(15, 10, 1, w, x, y + height + 10, z, Blocks.NETHER_BRICK);
		WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height + 10, z + 1, Blocks.AIR);

		WorldGenAPI.addHollowRectangle(15, 10, 1, w, x, y + height + 11, z, JourneyBlocks.dungeonLampFence);
		WorldGenAPI.addHollowRectangle(13, 8, 1, w, x + 1, y + height + 11, z + 1, Blocks.AIR);
		WorldGenAPI.addRectangle(4, 2, 1, w, x + 2, y + height + 8, z + 1, Blocks.AIR);

		for(int i = 0; i < 4; i++) {
			this.setBlockAndNotifyAdequately(w, new BlockPos(x + 5 - i, y + i + height + 5, z + 1), Blocks.NETHER_BRICK_STAIRS.getStateFromMeta(1));
			this.setBlockAndNotifyAdequately(w, new BlockPos(x + 5 - i, y + i + height + 5, z + 2), Blocks.NETHER_BRICK_STAIRS.getStateFromMeta(1));
		}
		
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 13, y + height + 9, z + 1), JourneyBlocks.dungeonLamp.getDefaultState());
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 1, y + height + 9, z + 8), JourneyBlocks.dungeonLamp.getDefaultState());
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 13, y + height + 9, z + 8), JourneyBlocks.dungeonLamp.getDefaultState());

		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 13, y + height + 10, z + 1), JourneyBlocks.redGems.getDefaultState());
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 1, y + height + 10, z + 8), JourneyBlocks.redGems.getDefaultState());
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 13, y + height + 10, z + 8), JourneyBlocks.redGems.getDefaultState());
		
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 12, y + height + 9, z + 8), JourneyBlocks.dungeonLampStairs.getStateFromMeta(0));
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 12, y + height + 9, z + 7), JourneyBlocks.dungeonLampStairs.getStateFromMeta(0));
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 13, y + height + 9, z + 7), JourneyBlocks.dungeonLampStairs.getStateFromMeta(2));
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 12, y + height + 9, z + 2), JourneyBlocks.dungeonLampStairs.getStateFromMeta(0));
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 12, y + height + 9, z + 1), JourneyBlocks.dungeonLampStairs.getStateFromMeta(0));
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 13, y + height + 9, z + 2), JourneyBlocks.dungeonLampStairs.getStateFromMeta(3));
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 2, y + height + 9, z + 7), JourneyBlocks.dungeonLampStairs.getStateFromMeta(2));
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 1, y + height + 9, z + 7), JourneyBlocks.dungeonLampStairs.getStateFromMeta(2));
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 2, y + height + 9, z + 8), JourneyBlocks.dungeonLampStairs.getStateFromMeta(1));
				
		if(!w.isRemote) {
			EntityTempleGuardian guard = new EntityTempleGuardian(w);
			guard.setLocationAndAngles(x + 5, y + height + 10, z + 5, 0.0F, 0.0F);
			w.spawnEntity(guard);
		}
		return true;
	}

	public void addLevel(World w, int x, int y, int z, int height, int lor, String mobName, String mobName2) {
		Random r = new Random();
		WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height, z + 1, Blocks.NETHER_BRICK);
		WorldGenAPI.addRectangle(13, 8, 1, w, x + 1, y + height + 4, z + 1, Blocks.NETHER_BRICK);
		WorldGenAPI.addRectangle(4, 2, 1, w, x + 2, y + height + 4, z + 1, Blocks.AIR);
		WorldGenAPI.addRectangle(4, 2, 1, w, x + 2, y + height, z + 7, Blocks.AIR);
		WorldGenAPI.addBlock(w, x + 13, y + height - 1, z + 1, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 1, y + height - 1, z + 1, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 1, y + height - 1, z + 8, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 13, y + height - 1, z + 8, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 13, y + height - 5, z + 1, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 1, y + height - 5, z + 1, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 1, y + height - 5, z + 8, JourneyBlocks.dungeonLamp);
		WorldGenAPI.addBlock(w, x + 13, y + height - 5, z + 8, JourneyBlocks.dungeonLamp);
		
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 11, y + height - 3, z + 2), Blocks.MOB_SPAWNER.getDefaultState());
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 11, y + height - 3, z + 7), Blocks.MOB_SPAWNER.getDefaultState());
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 11, y + height + 1, z + 2), Blocks.MOB_SPAWNER.getDefaultState());
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 11, y + height + 1, z + 7), Blocks.MOB_SPAWNER.getDefaultState());
		TileEntityMobSpawner spawner1 = (TileEntityMobSpawner)w.getTileEntity(new BlockPos(x + 11, y + height - 3, z + 2));
		TileEntityMobSpawner spawner2 = (TileEntityMobSpawner)w.getTileEntity(new BlockPos(x + 11, y + height - 3, z + 7));
		TileEntityMobSpawner spawner3 = (TileEntityMobSpawner)w.getTileEntity(new BlockPos(x + 11, y + height + 1, z + 2));
		TileEntityMobSpawner spawner4 = (TileEntityMobSpawner)w.getTileEntity(new BlockPos(x + 11, y + height + 1, z + 7));
		
		if(spawner1 != null && spawner2 != null && spawner3 != null && spawner4 != null) {
			spawner1.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName));
			spawner2.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName2));
			spawner3.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName2));
			spawner4.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName));
		} else {
			LogHelper.error("Error placing mob spawner for Nether tower dungeon");
		}
		
		for(int i = 0; i < 4; i++) {
			if(lor == 0) {
				this.setBlockAndNotifyAdequately(w, new BlockPos(x + 5 - i, y + i + 1 + height, z + 1), Blocks.NETHER_BRICK_STAIRS.getStateFromMeta(1));
				this.setBlockAndNotifyAdequately(w, new BlockPos(x + 5 - i, y + i + 1 + height, z + 2), Blocks.NETHER_BRICK_STAIRS.getStateFromMeta(1));
			} else {
				this.setBlockAndNotifyAdequately(w, new BlockPos(x + 5 - i, y + i + 1 + height - 4, z + 7), Blocks.NETHER_BRICK_STAIRS.getStateFromMeta(1));
				this.setBlockAndNotifyAdequately(w, new BlockPos(x + 5 - i, y + i + 1 + height - 4, z + 8), Blocks.NETHER_BRICK_STAIRS.getStateFromMeta(1));
			}
		}
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 2, y + 1 + height - 1, z + 7), Blocks.NETHER_BRICK_STAIRS.getStateFromMeta(1));
		this.setBlockAndNotifyAdequately(w, new BlockPos(x + 2, y + 1 + height - 1, z + 8), Blocks.NETHER_BRICK_STAIRS.getStateFromMeta(1));
	}
}