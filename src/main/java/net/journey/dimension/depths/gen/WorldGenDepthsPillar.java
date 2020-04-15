package net.journey.dimension.depths.gen;

import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.slayer.api.worldgen.WorldGenAPI;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenDepthsPillar extends WorldGenerator {

    public boolean locationIsValidSpawn(World w, int x, int y, int z) {
        return WorldGenAPI.checkRadius(w, new BlockPos(x, y, z), 11, JourneyBlocks.eucaGrass);
    }

    @Override
    public boolean generate(World w, Random r, BlockPos pos) {
        int x = pos.getX() - 3, y = pos.getY(), z = pos.getZ() - 1;
        if (locationIsValidSpawn(w, x, y, z)) return true;

        ArrayList<ItemStack> chestGen = new ArrayList<ItemStack>();

        chestGen.add(new ItemStack(JourneyItems.caveCrystal, 16));
        chestGen.add(new ItemStack(JourneyItems.depthsPortalGem, 12));
        chestGen.add(new ItemStack(JourneyItems.blazierOrb, 12));
        chestGen.add(new ItemStack(JourneyItems.blueGlowShroom, 12));
        chestGen.add(new ItemStack(JourneyItems.diamondDust, 12));
        chestGen.add(new ItemStack(JourneyItems.frozenIceball, 12));

        int height = r.nextInt(5) + 10;
        WorldGenAPI.addRectangle(1, 1, 1, w, x + 3, y + height + 2, z + 1, Blocks.MOB_SPAWNER);
        WorldGenAPI.addRectangle(1, 1, 1, w, x + 3, y + height + 3, z + 1, JourneyBlocks.journeyChest);
        TileEntityMobSpawner spawner = (TileEntityMobSpawner) w.getTileEntity(new BlockPos(x + 3, y + height + 2, z + 1));
        if (spawner != null)
            spawner.getSpawnerBaseLogic().setEntityId(new ResourceLocation("journey:cavemage"));

        TileEntityJourneyChest chest = (TileEntityJourneyChest) w.getTileEntity(new BlockPos(x + 3, y + height + 3, z + 1));

        for (ItemStack i : chestGen)
            chest.setInventorySlotContents(r.nextInt(27), i);

        WorldGenAPI.addRectangle(1, 1, height + 1, w, x + 3, y + 1, z + 1, JourneyBlocks.depthsBrick);
        WorldGenAPI.addRectangle(3, 1, height, w, x + 2, y + 1, z + 1, JourneyBlocks.depthsBrick);
        WorldGenAPI.addRectangle(1, 3, height, w, x + 3, y + 1, z, JourneyBlocks.depthsBrick);
        WorldGenAPI.addRectangle(7, 3, 1, w, x, y + height, z, JourneyBlocks.darkFloor);
        WorldGenAPI.addRectangle(3, 7, 1, w, x + 2, y + height, z - 2, JourneyBlocks.darkFloor);
        WorldGenAPI.addRectangle(5, 5, 1, w, x + 1, y + height, z - 1, JourneyBlocks.darkFloor);
        return false;
    }
}