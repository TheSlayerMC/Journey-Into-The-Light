package net.jitl.common.tile;

import net.jitl.init.JBlocks;
import net.jitl.init.JTiles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.api.util.HorizontalDirection;

import java.util.Arrays;
import java.util.stream.Collectors;

import static ru.timeconqueror.timecore.api.util.HorizontalDirection.*;

public class EssenciaAltarTile extends TileEntity implements ITickableTileEntity {
    private static final int ACTIVATION_DELAY = 3 * 20;
    private final Path northPath = new Path(NORTH, JBlocks.BLOOD_RUNE_DEATH);
    private final Path eastPath = new Path(EAST, JBlocks.BLOOD_RUNE_FLESH);
    private final Path westPath = new Path(WEST, JBlocks.BLOOD_RUNE_LIFE);
    private final Path southPath = new Path(SOUTH, JBlocks.BLOOD_RUNE_SOUL);

    private int ticks = 0;
    private final int activationDelay = -1;

    public EssenciaAltarTile() {
        super(JTiles.ESSENCIA_ALTAR);
    }

    @Override
    public void tick() {
        if (ticks % 2 == 0) {
            checkNeighbours();
            System.out.println(Arrays.stream(getPaths()).map(path -> path.direction.get() + ": " + path.validBlockCount + ", ").collect(Collectors.joining()));
        }

        ticks++;
    }

    private void checkNeighbours() {
        World level = getLevel();
        BlockPos pos = getBlockPos();

        for (Path path : getPaths()) {
            boolean changed = false;

            BlockPos.Mutable mutable = pos.mutable();

            int i = 0;
            for (; i < 3; i++) {
                mutable.set(pos);
                mutable.move(Direction.DOWN, 1);
                mutable.move((i + 1) * path.getStepX(), 0, (i + 1) * path.getStepZ());

                BlockState blockState = level.getBlockState(mutable);
                if (blockState.getBlock() != JBlocks.RUNIC_CONNECTOR) {
                    changed = true;
                    break;
                }
            }

            if (!changed) {
                mutable.set(pos);
                mutable.move(4 * path.getStepX(), 0, 4 * path.getStepZ());

                BlockState runeState = level.getBlockState(mutable);
                if (runeState.getBlock() == path.validRune) {
                    i++;
                }

//                if (runeState.getBlock() != JBlocks.ACTIVATED_BLOOD_RUNE) {
//                    if (runeState.getBlock() == JBlocks.EMPTY_BLOOD_RUNE && activationDelay == -1) {
//                        activationDelay = ACTIVATION_DELAY;
//                    } else {
//                        activationDelay = -1;
//                    }
//                } else {
//                    activationDelay = -1;
//                }
            }

            if (path.validBlockCount != i) {
                changed = true;
            }

            path.validBlockCount = i;
        }
    }

    private Path[] getPaths() {
        return new Path[]{northPath, eastPath, southPath, westPath};
    }

    public Path getPath(HorizontalDirection direction) {
        switch (direction) {
            case EAST:
                return eastPath;
            case WEST:
                return westPath;
            case SOUTH:
                return southPath;
            default:
                return northPath;
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        BlockPos pos = getBlockPos();
        return new AxisAlignedBB(pos.offset(-5, 0, -5), pos.offset(4, 1, 4));
    }

    public static class Path {
        private final HorizontalDirection direction;
        private final Block validRune;
        private int validBlockCount = 0;

        public Path(HorizontalDirection direction, Block validRune) {
            this.direction = direction;
            this.validRune = validRune;
        }

        public int getStepX() {
            return direction.get().getStepX();
        }

        public int getStepZ() {
            return direction.get().getStepZ();
        }

        public int getValidBlockCount() {
            return validBlockCount;
        }
    }
}
