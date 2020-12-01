package net.jitl.common.world.gen.structures;

import net.jitl.common.world.gen.util.BoundMutablePos;
import net.jitl.common.world.gen.util.GenHelper;
import net.jitl.init.JBlocks;
import net.jitl.init.JStructurePieces;
import net.minecraft.block.Blocks;
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
    public static final int BB_WIDTH = WIDTH + 2;
    private static final int H_CENTER_MINUS_ONE = BB_WIDTH / 2;

    public static class Floor extends ScatteredStructurePiece {
        public Floor(Random rand, int x, int y, int z) {
            super(JStructurePieces.GUARDIAN_TOWER_PIECE, rand, x, y, z, BB_WIDTH, 5, BB_WIDTH);
        }

        public Floor(TemplateManager templateManager, CompoundNBT nbt) {
            super(JStructurePieces.GUARDIAN_TOWER_PIECE, nbt);
        }

        @Override
        protected void addAdditionalSaveData(CompoundNBT tagCompound) {

        }

        @Override
        public boolean postProcess(ISeedReader world, StructureManager structureManager_, ChunkGenerator chunkGenerator_, Random random_, MutableBoundingBox chunkBox, ChunkPos chunkPos_, BlockPos pos) {
            BoundMutablePos p = new BoundMutablePos();
            p.bindAndSet(1, 0, 1);

            GenHelper.IBorderGenerator borderGenerator = new GenHelper.IBorderGenerator() {
                @Override
                public void gen(BlockPos relPos) {
                    p.moveFromBound(relPos);
                    p.move(1, 0, 1);
                    placeBlock(world, JBlocks.DUNGEON_BRICKS.defaultBlockState(), H_CENTER_MINUS_ONE + p.getX(), p.getY(), H_CENTER_MINUS_ONE + p.getZ(), chunkBox);
                }

                @Override
                public boolean isBorder(BlockPos relPos) {
                    p.moveFromBound(getWorldX(pos.getX(), pos.getZ()), getWorldY(pos.getY()), getWorldZ(pos.getX(), pos.getZ()))
                            .move(relPos)
                            .move(H_CENTER_MINUS_ONE, 0, H_CENTER_MINUS_ONE);
                    if (!chunkBox.isInside(p)) return true;
                    return world.getBlockState(p) == JBlocks.DUNGEON_BRICKS.defaultBlockState();
                }
            };

            GenHelper.genFilledBorderedCircle(10, Direction.Axis.Y, borderGenerator, relPos -> {
                p.moveFromBound(relPos);
                placeBlock(world, Blocks.AIR.defaultBlockState(), H_CENTER_MINUS_ONE + p.getX(), p.getY(), H_CENTER_MINUS_ONE + p.getZ(), chunkBox);
            });

            return true;
        }
    }
}
