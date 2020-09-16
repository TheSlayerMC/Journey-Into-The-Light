package net.journey.dimension.overworld.gen;

import net.journey.blocks.containers.BlockJourneyChest;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.mob.boss.EntityTempleGuardian;
import net.journey.init.JourneyLootTables;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.RandHelper;
import net.journey.util.handler.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenTowerDungeonCyl extends WorldGenerator {

    @Override
    public boolean generate(World w, Random r, BlockPos pos) {
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        int levels = 4;
        int height = levels * 4;
        
        WorldGenAPI.addHollowCylinder(w, 4, 6, r, x, y, z, JourneyBlocks.dungeonBrick);

        addDifferentBlocks(w, x, y, z);
        return true;
    }
    
    public void addLevel(World w, int x, int y, int z, int height, int lor, String mobName, String mobName2) {
    	
    }

    public void addDifferentBlocks(World w, int x, int y, int z) {
        Random r = new Random();
        for (int x1 = 0; x1 < 15; x1++) {
            for (int y1 = 0; y1 < 50; y1++) {
                for (int z1 = 0; z1 < 15; z1++) {
                    if (w.getBlockState(new BlockPos(x + x1, y + y1, z + z1)) == JourneyBlocks.dungeonBrick.getDefaultState() && r.nextInt(15) == 0) {
                        this.setBlockAndNotifyAdequately(w, new BlockPos(x + x1, y + y1, z + z1), JourneyBlocks.dungeonChisledBrick.getDefaultState());
                        break;
                    }
                }
            }
        }

        for (int x1 = 0; x1 < 15; x1++) {
            for (int y1 = 0; y1 < 50; y1++) {
                for (int z1 = 0; z1 < 15; z1++) {
                    if (w.getBlockState(new BlockPos(x + x1, y + y1, z + z1)) == JourneyBlocks.dungeonBrick.getDefaultState() && r.nextInt(10) == 0) {
                        this.setBlockAndNotifyAdequately(w, new BlockPos(x + x1, y + y1, z + z1), JourneyBlocks.dungeonBrickCarved.getDefaultState());
                        break;
                    }
                }
            }
        }

        for (int x1 = 0; x1 < 15; x1++) {
            for (int y1 = 0; y1 < 50; y1++) {
                for (int z1 = 0; z1 < 15; z1++) {
                    if (w.getBlockState(new BlockPos(x + x1, y + y1, z + z1)) == JourneyBlocks.dungeonBrick.getDefaultState() && r.nextInt(10) == 0) {
                        this.setBlockAndNotifyAdequately(w, new BlockPos(x + x1, y + y1, z + z1), JourneyBlocks.dungeonCrackedBrick.getDefaultState());
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                for (int k = 0; k < 50; k++) {
                    if (w.getBlockState(new BlockPos(i, j, k)) == JourneyBlocks.dungeonBrick.getDefaultState()) {
                        if (r.nextInt(10) == 0)
                            this.setBlockAndNotifyAdequately(w, new BlockPos(i, j, k), JourneyBlocks.dungeonCrackedBrick.getDefaultState());
                    }
                }
            }
        }
    }

   
}