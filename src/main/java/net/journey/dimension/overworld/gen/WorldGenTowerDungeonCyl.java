package net.journey.dimension.overworld.gen;

import net.journey.blocks.containers.BlockJourneyChest;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.mob.boss.EntityTempleGuardian;
import net.journey.init.JourneyLootTables;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.RandHelper;
import net.journey.util.WorldGenHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.block.BlockMod;
import net.slayer.api.block.BlockModStairs;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenTowerDungeonCyl extends WorldGenerator {

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		int x = position.getX(), y = position.getY(), z = position.getZ();
		int levels = 4;
		int height = levels * 4;

		ArrayList<String> mobNames = new ArrayList<String>();

        
		Block stair = JourneyBlocks.dungeonBrickStairs;

		BlockMod stone = JourneyBlocks.dungeonBricks;
		
		mobNames.add("minecraft:spider");
        mobNames.add("minecraft:creeper");
        mobNames.add("journey:boomBoom");
        mobNames.add("journey:robot");
        mobNames.add("journey:spyclops");
        mobNames.add("journey:bighongo");
        mobNames.add("journey:mediumhongo");
        mobNames.add("minecraft:zombie");
        mobNames.add("minecraft:skeleton");
        mobNames.add("journey:sandcrawler");
        mobNames.add("journey:firemage");
        mobNames.add("journey:icemage");
        
		addBaseLayer(worldIn, x, y, z, stone, mobNames.get(rand.nextInt(mobNames.size())), mobNames.get(rand.nextInt(mobNames.size())));
		
		addLevel(worldIn, x, y + 5, z, 5, stone, mobNames.get(rand.nextInt(mobNames.size())), mobNames.get(rand.nextInt(mobNames.size())));
		addLevel(worldIn, x, y + 10, z, 5, stone, mobNames.get(rand.nextInt(mobNames.size())), mobNames.get(rand.nextInt(mobNames.size())));
		addLevel(worldIn, x, y + 15, z, 5, stone, mobNames.get(rand.nextInt(mobNames.size())), mobNames.get(rand.nextInt(mobNames.size())));
		addLevel(worldIn, x, y + 20, z, 5, stone, mobNames.get(rand.nextInt(mobNames.size())), mobNames.get(rand.nextInt(mobNames.size())));
		addLevel(worldIn, x, y + 25, z, 5, stone, mobNames.get(rand.nextInt(mobNames.size())), mobNames.get(rand.nextInt(mobNames.size())));
		addLevel(worldIn, x, y + 30, z, 5, stone, mobNames.get(rand.nextInt(mobNames.size())), mobNames.get(rand.nextInt(mobNames.size())));
		addLevel(worldIn, x, y + 35, z, 5, stone, mobNames.get(rand.nextInt(mobNames.size())), mobNames.get(rand.nextInt(mobNames.size())));
		
		
		Block mossyStone = JourneyBlocks.mossyEssenceStone;
		WorldGenAPI.addCylinder(worldIn, 1, 6, null, x, y + 39, z, mossyStone);
		WorldGenHelper.genHollowCylinder(new BlockPos(x, y + 39, z), 6, 1, EnumFacing.UP, mutablePos -> {
			IBlockState state = stone.getDefaultState();
			WorldGenHelper.setStateFast(worldIn, mutablePos, state);
		});
		
		WorldGenHelper.genHollowCylinder(new BlockPos(x, y + 40, z), 6, 4, EnumFacing.UP, mutablePos -> {
			IBlockState state = JourneyBlocks.dungeonLampFence.getDefaultState();
			WorldGenHelper.setStateFast(worldIn, mutablePos, state);
		});

		addStaircase(worldIn, x, y, z, stone, (BlockModStairs)stair);
		addStaircase(worldIn, x, y + 10, z, stone, (BlockModStairs)stair);
		addStaircase(worldIn, x, y + 20, z, stone, (BlockModStairs)stair);
		addStaircase(worldIn, x, y + 30, z, stone, (BlockModStairs)stair);
		
		WorldGenAPI.addRectangle(3, 3, 1, worldIn, x - 1, y + 39, z - 1, mossyStone);
		worldIn.setBlockState(new BlockPos(x - 1, y + 39, z - 2), mossyStone.getDefaultState());

		y = y + 40;
		worldIn.setBlockState(new BlockPos(x + 5, y, z + 1), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(3));
		worldIn.setBlockState(new BlockPos(x + 5, y, z), JourneyBlocks.dungeonBricks.getDefaultState());
		worldIn.setBlockState(new BlockPos(x + 5, y + 1, z), JourneyBlocks.redGems.getDefaultState());
		worldIn.setBlockState(new BlockPos(x + 5, y, z - 1), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(2));

		worldIn.setBlockState(new BlockPos(x - 5, y, z + 1), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(3));
		worldIn.setBlockState(new BlockPos(x - 5, y, z), JourneyBlocks.dungeonBricks.getDefaultState());
		worldIn.setBlockState(new BlockPos(x - 5, y + 1, z), JourneyBlocks.redGems.getDefaultState());
		worldIn.setBlockState(new BlockPos(x - 5, y, z - 1), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(2));
		
		worldIn.setBlockState(new BlockPos(x + 1, y, z - 5), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(1));
		worldIn.setBlockState(new BlockPos(x, y, z - 5), JourneyBlocks.dungeonBricks.getDefaultState());
		worldIn.setBlockState(new BlockPos(x, y + 1, z - 5), JourneyBlocks.blueGems.getDefaultState());
		worldIn.setBlockState(new BlockPos(x - 1, y, z - 5), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(0));
		
		worldIn.setBlockState(new BlockPos(x + 1, y, z + 5), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(1));
		worldIn.setBlockState(new BlockPos(x, y, z + 5), JourneyBlocks.dungeonBricks.getDefaultState());
		worldIn.setBlockState(new BlockPos(x, y + 1, z + 5), JourneyBlocks.blueGems.getDefaultState());
		worldIn.setBlockState(new BlockPos(x - 1, y, z + 5), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(0));
		
		y = y - 40;
		randomizeBlocks(worldIn, x, y, z);
		
		 if (!worldIn.isRemote) {
	            EntityTempleGuardian guard = new EntityTempleGuardian(worldIn);
	            guard.setLocationAndAngles(x, y + 40, z, 0.0F, 0.0F);
	            worldIn.spawnEntity(guard);
	        }
		return true;
	}

	public void addLevel(World w, int x, int y, int z, int height, Block stone, String mobName, String mobName2) {
		WorldGenHelper.genHollowCylinder(new BlockPos(x, y, z), 6, height, EnumFacing.UP, mutablePos -> {
			IBlockState state = JourneyBlocks.dungeonBricks.getDefaultState();
			WorldGenHelper.setStateFast(w, mutablePos, state);
		});
		addFlatBase(w, x, y + height - 1, z, stone);
		
		w.setBlockState(new BlockPos(x - 3, y + 4, z - 3), JourneyBlocks.dungeonLamp.getDefaultState());
		w.setBlockState(new BlockPos(x + 3, y + 4, z + 3), JourneyBlocks.dungeonLamp.getDefaultState());
		w.setBlockState(new BlockPos(x - 3, y + 4, z + 3), JourneyBlocks.dungeonLamp.getDefaultState());
		w.setBlockState(new BlockPos(x + 3, y + 4, z - 3), JourneyBlocks.dungeonLamp.getDefaultState());
		
		w.setBlockState(new BlockPos(x - 3, y + 3, z - 3), Blocks.MOB_SPAWNER.getDefaultState());
		w.setBlockState(new BlockPos(x + 3, y + 3, z + 3), Blocks.MOB_SPAWNER.getDefaultState());
		w.setBlockState(new BlockPos(x - 3, y + 3, z + 3), Blocks.MOB_SPAWNER.getDefaultState());
		w.setBlockState(new BlockPos(x + 3, y + 3, z - 3), Blocks.MOB_SPAWNER.getDefaultState());
		
		w.setBlockState(new BlockPos(x + 5, y, z + 1), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(3));
		w.setBlockState(new BlockPos(x + 5, y, z), JourneyBlocks.dungeonBricks.getDefaultState());
		w.setBlockState(new BlockPos(x + 5, y + 1, z), JourneyBlocks.journeyChest.getDefaultState().withProperty(BlockJourneyChest.FACING, EnumFacing.WEST));
		w.setBlockState(new BlockPos(x + 5, y, z - 1), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(2));
		w.setBlockState(new BlockPos(x + 5, y + 3, z + 2), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(6));
		w.setBlockState(new BlockPos(x + 5, y + 3, z - 2), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(7));

		w.setBlockState(new BlockPos(x - 5, y, z + 1), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(3));
		w.setBlockState(new BlockPos(x - 5, y, z), JourneyBlocks.dungeonBricks.getDefaultState());
		w.setBlockState(new BlockPos(x - 5, y + 1, z), JourneyBlocks.journeyChest.getDefaultState().withProperty(BlockJourneyChest.FACING, EnumFacing.EAST));
		w.setBlockState(new BlockPos(x - 5, y, z - 1), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(2));
		w.setBlockState(new BlockPos(x - 5, y + 3, z + 2), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(6));
		w.setBlockState(new BlockPos(x - 5, y + 3, z - 2), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(7));
		
		w.setBlockState(new BlockPos(x - 2, y + 3, z - 5), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(5));
		w.setBlockState(new BlockPos(x + 2, y + 3, z - 5), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(4));
		w.setBlockState(new BlockPos(x - 2, y, z - 5), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(1));
		w.setBlockState(new BlockPos(x + 2, y, z - 5), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(0));
		
		w.setBlockState(new BlockPos(x - 2, y + 3, z + 5), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(5));
		w.setBlockState(new BlockPos(x + 2, y + 3, z + 5), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(4));
		w.setBlockState(new BlockPos(x - 2, y, z + 5), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(1));
		w.setBlockState(new BlockPos(x + 2, y, z + 5), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(0));
		
		TileEntityJourneyChest chest1 = (TileEntityJourneyChest) w.getTileEntity(new BlockPos(x + 5, y + 1, z));
		TileEntityJourneyChest chest2 = (TileEntityJourneyChest) w.getTileEntity(new BlockPos(x - 5, y + 1, z));

		TileEntityMobSpawner spawner1 = (TileEntityMobSpawner) w.getTileEntity(new BlockPos(x - 3, y + 3, z - 3));
		TileEntityMobSpawner spawner2 = (TileEntityMobSpawner) w.getTileEntity(new BlockPos(x + 3, y + 3, z + 3));
		TileEntityMobSpawner spawner3 = (TileEntityMobSpawner) w.getTileEntity(new BlockPos(x - 3, y + 3, z + 3));
		TileEntityMobSpawner spawner4 = (TileEntityMobSpawner) w.getTileEntity(new BlockPos(x + 3, y + 3, z - 3));
		
		if(chest1 != null && chest2 != null) {
			 ResourceLocation lootTable = RandHelper.chooseEqual(w.rand, JourneyLootTables.VANILLA_SIMPLE_DUNGEON, JourneyLootTables.LOOT_BASIC, JourneyLootTables.LOOT_GOLD);
		     chest1.setLootTable(lootTable, w.rand.nextLong());
		     chest2.setLootTable(lootTable, w.rand.nextLong());
		}

		if(spawner1 != null && spawner2 != null && spawner3 != null && spawner4 != null) {
			spawner1.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName));
			spawner2.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName));
			spawner3.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName2));
			spawner4.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName2));
		}
	}
	
	public void addBaseLayer(World w, int x, int y, int z, Block stone, String mobName, String mobName2) {
		WorldGenHelper.genHollowCylinder(new BlockPos(x, y, z), 6, 5, EnumFacing.UP, mutablePos -> {
			IBlockState state = JourneyBlocks.dungeonBricks.getDefaultState();
			WorldGenHelper.setStateFast(w, mutablePos, state);
		});
		addFlatBase(w, x, y - 1, z, stone);

		WorldGenAPI.addRectangle(1, 3, 3, w, x + 6, y, z - 1, Blocks.AIR);
		WorldGenAPI.addRectangle(1, 3, 3, w, x - 6, y, z - 1, Blocks.AIR);
		WorldGenAPI.addRectangle(3, 1, 3, w, x - 1, y, z + 6, Blocks.AIR);
		WorldGenAPI.addRectangle(3, 1, 3, w, x - 1, y, z - 6, Blocks.AIR);
		WorldGenAPI.addRectangle(1, 3, 1, w, x + 6, y - 1, z - 1, stone);
		WorldGenAPI.addRectangle(1, 3, 1, w, x - 6, y - 1, z - 1, stone);
		WorldGenAPI.addRectangle(3, 1, 1, w, x - 1, y - 1, z + 6, stone);
		WorldGenAPI.addRectangle(3, 1, 1, w, x - 1, y - 1, z - 6, stone);
		
		WorldGenAPI.addCylinder(w, 1, 6, null, x, y - 1, z, stone);
		WorldGenAPI.addBlock(w, x + 4, y - 2, z + 4, stone);
		WorldGenAPI.addBlock(w, x + 2, y - 2, z + 5, stone);
		
		w.setBlockState(new BlockPos(x + 1, y + 2, z + 6), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(4));
		w.setBlockState(new BlockPos(x - 1, y + 2, z + 6), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(5));
		w.setBlockState(new BlockPos(x + 1, y + 2, z - 6), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(4));
		w.setBlockState(new BlockPos(x - 1, y + 2, z - 6), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(5));
		w.setBlockState(new BlockPos(x + 6, y + 2, z + 1), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(6));
		w.setBlockState(new BlockPos(x + 6, y + 2, z - 1), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(7));
		w.setBlockState(new BlockPos(x - 6, y + 2, z + 1), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(6));
		w.setBlockState(new BlockPos(x - 6, y + 2, z - 1), JourneyBlocks.dungeonBrickStairs.getStateFromMeta(7));
		addFlatBase(w, x, y + 4, z, stone);
		
		
		
		w.setBlockState(new BlockPos(x - 3, y - 1, z - 3), JourneyBlocks.dungeonLamp.getDefaultState());
		w.setBlockState(new BlockPos(x + 3, y - 1, z + 3), JourneyBlocks.dungeonLamp.getDefaultState());
		w.setBlockState(new BlockPos(x - 3, y - 1, z + 3), JourneyBlocks.dungeonLamp.getDefaultState());
		w.setBlockState(new BlockPos(x + 3, y - 1, z - 3), JourneyBlocks.dungeonLamp.getDefaultState());
		
		w.setBlockState(new BlockPos(x - 3, y + 3, z - 3), Blocks.MOB_SPAWNER.getDefaultState());
		w.setBlockState(new BlockPos(x + 3, y + 3, z + 3), Blocks.MOB_SPAWNER.getDefaultState());
		w.setBlockState(new BlockPos(x - 3, y + 3, z + 3), Blocks.MOB_SPAWNER.getDefaultState());
		w.setBlockState(new BlockPos(x + 3, y + 3, z - 3), Blocks.MOB_SPAWNER.getDefaultState());
		
		TileEntityMobSpawner spawner1 = (TileEntityMobSpawner) w.getTileEntity(new BlockPos(x - 3, y + 3, z - 3));
		TileEntityMobSpawner spawner2 = (TileEntityMobSpawner) w.getTileEntity(new BlockPos(x + 3, y + 3, z + 3));
		TileEntityMobSpawner spawner3 = (TileEntityMobSpawner) w.getTileEntity(new BlockPos(x - 3, y + 3, z + 3));
		TileEntityMobSpawner spawner4 = (TileEntityMobSpawner) w.getTileEntity(new BlockPos(x + 3, y + 3, z - 3));

		if(spawner1 != null && spawner2 != null && spawner3 != null && spawner4 != null) {
			spawner1.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName));
			spawner2.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName));
			spawner3.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName2));
			spawner4.getSpawnerBaseLogic().setEntityId(new ResourceLocation(mobName2));
		}
		
		w.setBlockState(new BlockPos(x - 3, y + 4, z - 3), JourneyBlocks.dungeonLamp.getDefaultState());
		w.setBlockState(new BlockPos(x + 3, y + 4, z + 3), JourneyBlocks.dungeonLamp.getDefaultState());
		w.setBlockState(new BlockPos(x - 3, y + 4, z + 3), JourneyBlocks.dungeonLamp.getDefaultState());
		w.setBlockState(new BlockPos(x + 3, y + 4, z - 3), JourneyBlocks.dungeonLamp.getDefaultState());
	}
	
	public void addFlatBase(World w, int x, int y, int z, Block stone) {
		WorldGenAPI.addCylinder(w, 1, 6, null, x, y, z, stone);
		WorldGenAPI.addBlock(w, x + 4, y - 1, z + 4, stone);
		WorldGenAPI.addBlock(w, x + 2, y - 1, z + 5, stone);
	}
	
	public void addStaircase(World w, int x, int y, int z, Block middle, BlockModStairs stairs) {
		WorldGenAPI.addRectangle(4, 4, 1, w, x - 2, y + 4, z - 1, Blocks.AIR);
		WorldGenAPI.addRectangle(4, 4, 1, w, x - 1, y + 9, z - 2, Blocks.AIR);
		WorldGenAPI.addRectangle(3, 3, 10, w, x - 1, y, z - 1, middle);
		w.setBlockState(new BlockPos(x - 2, y, z - 1), stairs.getStateFromMeta(2));
		w.setBlockState(new BlockPos(x - 2, y + 1, z), stairs.getStateFromMeta(2));
		w.setBlockState(new BlockPos(x - 2, y + 2, z + 1), stairs.getStateFromMeta(2));
		w.setBlockState(new BlockPos(x - 2, y + 2, z + 2), middle.getDefaultState());
		
		w.setBlockState(new BlockPos(x - 1, y + 3, z + 2), stairs.getStateFromMeta(0));
		w.setBlockState(new BlockPos(x, y + 4, z + 2), stairs.getStateFromMeta(0));
		w.setBlockState(new BlockPos(x + 1, y + 4, z + 2), middle.getDefaultState());

		w.setBlockState(new BlockPos(x + 2, y + 5, z + 1), stairs.getStateFromMeta(3));
		w.setBlockState(new BlockPos(x + 2, y + 6, z), stairs.getStateFromMeta(3));
		w.setBlockState(new BlockPos(x + 2, y + 7, z - 1), stairs.getStateFromMeta(3));
		w.setBlockState(new BlockPos(x + 2, y + 7, z - 2), middle.getDefaultState());

		w.setBlockState(new BlockPos(x + 1, y + 8, z - 2), stairs.getStateFromMeta(1));
		w.setBlockState(new BlockPos(x, y + 9, z - 2), stairs.getStateFromMeta(1));
		w.setBlockState(new BlockPos(x - 1, y + 9, z - 2), middle.getDefaultState());

	}
	
	public void randomizeBlocks(World w, int x, int y, int z) {
		Random r = new Random();
		int height = 40;
		int radius = 15;
		int chance = 6;
        for (int x1 = 0; x1 < radius; x1++) {
            for (int y1 = 0; y1 < height; y1++) {
                for (int z1 = 0; z1 < radius; z1++) {
                    if (w.getBlockState(new BlockPos(x + x1 - radius / 2, y + y1, z + z1- radius / 2)) == JourneyBlocks.dungeonBricks.getDefaultState() && r.nextInt(chance) == 0) {
                        this.setBlockAndNotifyAdequately(w, new BlockPos(x + x1- radius / 2, y + y1, z + z1- radius / 2), JourneyBlocks.dungeonChiseledBricks.getDefaultState());
                        break;
                    }
                    if (w.getBlockState(new BlockPos(x + x1- radius / 2, y + y1, z + z1- radius / 2)) == JourneyBlocks.dungeonBricks.getDefaultState() && r.nextInt(chance) == 0) {
                        this.setBlockAndNotifyAdequately(w, new BlockPos(x + x1- radius / 2, y + y1, z + z1- radius / 2), JourneyBlocks.dungeonCarvedBricks.getDefaultState());
                        break;
                    }
                    if (w.getBlockState(new BlockPos(x + x1- radius / 2, y + y1, z + z1- radius / 2)) == JourneyBlocks.dungeonBricks.getDefaultState() && r.nextInt(chance) == 0) {
                        this.setBlockAndNotifyAdequately(w, new BlockPos(x + x1- radius / 2, y + y1, z + z1- radius / 2), JourneyBlocks.dungeonCrackedBricks.getDefaultState());
                        break;
                    }
                }
            }
        }
	}
}