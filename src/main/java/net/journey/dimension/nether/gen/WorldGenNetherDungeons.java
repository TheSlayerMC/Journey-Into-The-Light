package net.journey.dimension.nether.gen;

import net.journey.JourneyBlocks;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.util.JourneyLootTables;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenNetherDungeons extends WorldGenerator {

    /*private static WeightedRandomChestContent[] loot = {
            new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10),
            new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 4, 10),
            new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 1, 10),
            new WeightedRandomChestContent(JourneyItems.blood, 0, 1, 4, 10),
            new WeightedRandomChestContent(JourneyItems.hellstoneIngot, 0, 1, 4, 10),
            new WeightedRandomChestContent(JourneyItems.hellcrustIngot, 0, 1, 4, 10),
            new WeightedRandomChestContent(JourneyItems.flamingHide, 0, 1, 1, 10),
            new WeightedRandomChestContent(JourneyItems.snakeSkin, 0, 1, 1, 1),
            new WeightedRandomChestContent(JourneyItems.flamingSpring, 0, 1, 1, 1)};*/
    @Override
    public boolean generate(World world, Random r, BlockPos pos) {

        int i = pos.getX(),
                j = pos.getY(),
                k = pos.getZ();
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 0, k + 1), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 0, k + 7), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 1, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 1, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 2, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 2, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 4, k + 1), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 0, j + 4, k + 7), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 0), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 2), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 6), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 0, k + 8), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 0), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 2), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 3), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 4), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 5), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 6), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 1, k + 8), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 0), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 2), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 3), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 4), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 5), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 6), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 2, k + 8), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 0), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 2), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 3), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 4), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 5), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 6), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 3, k + 8), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 0), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 1), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 2), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 3), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 4), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 5), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 6), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 7), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 1, j + 4, k + 8), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 2), JourneyBlocks.nethicLamp.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 6), JourneyBlocks.nethicLamp.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 1, k + 1), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 1, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 1), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 2, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 1), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 2, j + 4, k + 7), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 2), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 6), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 1, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 1, k + 7), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 2, k + 7), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 3, j + 4, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 2), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 6), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 1, k + 4), JourneyBlocks.hellbotSpawner.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 1, k + 6), JourneyBlocks.netherChest.getStateFromMeta(2));
        TileEntity chest = world.getTileEntity(new BlockPos(i + 4, j + 1, k + 6));
        if (chest instanceof TileEntityJourneyChest) {
            ((TileEntityJourneyChest) chest).setLootTable(JourneyLootTables.NETHER_DUNGEON_CHEST, r.nextLong());
        }
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 1, k + 7), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 2, k + 7), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 1), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 4, j + 4, k + 7), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 2), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 6), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 1, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 1, k + 7), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 2, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 2, k + 7), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 4, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 5, j + 4, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 2), JourneyBlocks.nethicLamp.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 6), JourneyBlocks.nethicLamp.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 1, k + 1), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 1, k + 4), JourneyBlocks.netherChest.getStateFromMeta(4));
        TileEntity chest1 = world.getTileEntity(new BlockPos(i + 6, j + 1, k + 4));
        if (chest1 instanceof TileEntityJourneyChest) {
            ((TileEntityJourneyChest) chest1).setLootTable(JourneyLootTables.NETHER_DUNGEON_CHEST, r.nextLong());
        }
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 1, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 2, k + 1), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 2, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 4, k + 1), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 6, j + 4, k + 7), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 0), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 1), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 2), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 3), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 4), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 5), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 6), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 7), JourneyBlocks.largeNetherBrick.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 0, k + 8), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 1, k + 0), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 1, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 1, k + 2), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 1, k + 3), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 1, k + 4), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 1, k + 5), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 1, k + 6), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 1, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 1, k + 8), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 2, k + 0), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 2, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 2, k + 2), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 2, k + 3), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 2, k + 4), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 2, k + 5), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 2, k + 6), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 2, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 2, k + 8), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 3, k + 0), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 3, k + 2), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 3, k + 3), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 3, k + 4), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 3, k + 5), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 3, k + 6), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 3, k + 8), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 0), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 1), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 2), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 3), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 4), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 5), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 6), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 7), JourneyBlocks.boilingBars.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 7, j + 4, k + 8), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 0, k + 1), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 0, k + 7), Blocks.NETHER_BRICK.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 1, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 1, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 2, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 2, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 3, k + 1), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 3, k + 7), JourneyBlocks.nethicDungeonBricks.getDefaultState());
        this.setBlockAndNotifyAdequately(world, new BlockPos(i + 8, j + 4, k + 1), Blocks.NETHER_BRICK.getDefaultState());
        return false;
    }
}