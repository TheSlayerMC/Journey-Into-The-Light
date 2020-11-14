package net.jitl.common.world.gen.structures;

import net.jitl.common.world.gen.util.GenHelper;
import net.jitl.init.JBlocks;
import net.jitl.init.JStructurePieces;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class GuardianTowerPieces {
    public static final int WIDTH = 21;
    private static final int H_CENTER_MINUS_ONE = WIDTH / 2;

    public static class Floor extends ScatteredStructurePiece {
        public Floor(Random rand, int x, int y, int z) {
            super(JStructurePieces.GUARDIAN_TOWER_PIECE, rand, x, y, z, WIDTH, 5, WIDTH);
        }

        public Floor(TemplateManager templateManager, CompoundNBT nbt) {
            super(JStructurePieces.GUARDIAN_TOWER_PIECE, nbt);
        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT tagCompound) {

        }

        @Override
        public boolean postProcess(ISeedReader world, StructureManager structureManager_, ChunkGenerator chunkGenerator_, Random random_, MutableBoundingBox chunkBox, ChunkPos chunkPos_, BlockPos pos) {
            GenHelper.genHollowCircle(10, Direction.Axis.Y, relPos -> {
                placeBlock(world, JBlocks.DUNGEON_BRICKS.defaultBlockState(), H_CENTER_MINUS_ONE + relPos.getX(), relPos.getY(), H_CENTER_MINUS_ONE + relPos.getZ(), chunkBox);
            });

            return true;
        }
    }
}
