package net.journey.dimension.nether.gen;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.common.DungeonHooks;

public class WorldGenNetherDungeons extends WorldGenerator {
	
	private static WeightedRandomChestContent[] loot = {
			new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10), 
			new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 4, 10), 
			new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 1, 10), 
			new WeightedRandomChestContent(JourneyItems.blood, 0, 1, 4, 10), 
			new WeightedRandomChestContent(JourneyItems.hellstoneIngot, 0, 1, 4, 10), 
			new WeightedRandomChestContent(JourneyItems.hellcrustIngot, 0, 1, 4, 10),
			new WeightedRandomChestContent(JourneyItems.flamingHide, 0, 1, 1, 10), 
			new WeightedRandomChestContent(JourneyItems.snakeSkin, 0, 1, 1, 1),
			new WeightedRandomChestContent(JourneyItems.flamingSpring, 0, 1, 1, 1)};
	@Override
    public boolean generate(World world, Random r, BlockPos pos) {
		
		int i = pos.getX(),
		j = pos.getY(),
		k = pos.getZ();
		world.setBlockState (new BlockPos (i + 0, j + 0, k + 1), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 0, j + 0, k + 7), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 0, j + 1, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 0, j + 1, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 0, j + 2, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 0, j + 2, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 0, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 0, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 0, j + 4, k + 1), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 0, j + 4, k + 7), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 0, k + 0), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 0, k + 2), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 0, k + 6), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 0, k + 8), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 1, k + 0), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 1, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 1, k + 2), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 1, k + 3), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 1, k + 4), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 1, k + 5), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 1, k + 6), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 1, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 1, k + 8), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 2, k + 0), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 2, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 2, k + 2), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 2, k + 3), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 2, k + 4), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 2, k + 5), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 2, k + 6), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 2, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 2, k + 8), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 3, k + 0), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 3, k + 2), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 3, k + 3), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 3, k + 4), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 3, k + 5), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 3, k + 6), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 3, k + 8), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 4, k + 0), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 4, k + 1), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 4, k + 2), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 4, k + 3), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 4, k + 4), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 4, k + 5), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 4, k + 6), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 4, k + 7), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 1, j + 4, k + 8), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 0, k + 2), JourneyBlocks.nethicLamp.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 0, k + 6), JourneyBlocks.nethicLamp.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 1, k + 1), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 1, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 2, k + 1), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 2, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 4, k + 1), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 2, j + 4, k + 7), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 0, k + 2), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 0, k + 6), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 1, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 1, k + 7), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 2, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 2, k + 7), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 4, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 3, j + 4, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 0, k + 2), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 0, k + 6), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 1, k + 4), JourneyBlocks.hellbotSpawner.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 1, k + 6), JourneyBlocks.netherChest.getStateFromMeta(2));
		TileEntityJourneyChest te = (TileEntityJourneyChest)world.getTileEntity(new BlockPos(i + 4, j + 1, k + 6));
		if(te != null) {
			WeightedRandomChestContent.generateChestContents(r, Lists.newArrayList(loot), te, 4);
		}
		world.setBlockState (new BlockPos (i + 4, j + 1, k + 7), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 2, k + 7), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 4, k + 1), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 4, j + 4, k + 7), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 0, k + 2), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 0, k + 6), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 1, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 1, k + 7), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 2, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 2, k + 7), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 4, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 5, j + 4, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 0, k + 2), JourneyBlocks.nethicLamp.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 0, k + 6), JourneyBlocks.nethicLamp.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 1, k + 1), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 1, k + 4), JourneyBlocks.netherChest.getStateFromMeta(4));
		TileEntityJourneyChest te1 = (TileEntityJourneyChest)world.getTileEntity(new BlockPos(i + 6, j + 1, k + 4));
		if(te1 != null) {
			WeightedRandomChestContent.generateChestContents(r, Lists.newArrayList(loot), te1, 4);
		}
		world.setBlockState (new BlockPos (i + 6, j + 1, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 2, k + 1), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 2, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 4, k + 1), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 6, j + 4, k + 7), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 0, k + 0), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 0, k + 2), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 0, k + 6), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 0, k + 8), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 1, k + 0), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 1, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 1, k + 2), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 1, k + 3), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 1, k + 4), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 1, k + 5), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 1, k + 6), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 1, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 1, k + 8), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 2, k + 0), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 2, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 2, k + 2), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 2, k + 3), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 2, k + 4), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 2, k + 5), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 2, k + 6), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 2, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 2, k + 8), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 3, k + 0), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 3, k + 2), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 3, k + 3), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 3, k + 4), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 3, k + 5), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 3, k + 6), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 3, k + 8), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 4, k + 0), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 4, k + 1), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 4, k + 2), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 4, k + 3), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 4, k + 4), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 4, k + 5), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 4, k + 6), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 4, k + 7), JourneyBlocks.boilingBars.getDefaultState());
		world.setBlockState (new BlockPos (i + 7, j + 4, k + 8), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 8, j + 0, k + 1), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 8, j + 0, k + 7), Blocks.nether_brick.getDefaultState());
		world.setBlockState (new BlockPos (i + 8, j + 1, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 8, j + 1, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 8, j + 2, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 8, j + 2, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 8, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 8, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
		world.setBlockState (new BlockPos (i + 8, j + 4, k + 1), Blocks.nether_brick.getDefaultState());
		return false;
	}
}