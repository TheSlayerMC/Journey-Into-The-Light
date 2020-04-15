package net.journey.dimension.frozen.gen;

import net.journey.JourneyBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenNewLamp extends WorldGenerator {

    @Override
    public boolean generate(World w, Random rand, BlockPos pos) {
        int x = pos.getX(), y = pos.getY() - 1, z = pos.getZ();
        this.setBlockAndNotifyAdequately(w, pos, JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(2), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(3), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(4), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(5), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(6), JourneyBlocks.frozenLamp.getDefaultState());

        this.setBlockAndNotifyAdequately(w, pos.up(4).east(), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(4).west(), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(4).north(), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(4).south(), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(4).east(2), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(4).west(2), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(4).north(2), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(4).south(2), JourneyBlocks.workshopStone.getDefaultState());

        this.setBlockAndNotifyAdequately(w, pos.up(3).east(2), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(3).west(2), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(3).north(2), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(3).south(2), JourneyBlocks.workshopStone.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(2).east(2), JourneyBlocks.frozenLamp.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(2).west(2), JourneyBlocks.frozenLamp.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(2).north(2), JourneyBlocks.frozenLamp.getDefaultState());
        this.setBlockAndNotifyAdequately(w, pos.up(2).south(2), JourneyBlocks.frozenLamp.getDefaultState());
        return true;
    }
}