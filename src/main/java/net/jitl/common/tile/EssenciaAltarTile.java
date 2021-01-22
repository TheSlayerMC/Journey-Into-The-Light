package net.jitl.common.tile;

import net.jitl.common.block.base.XZFacedBlock;
import net.jitl.init.JBlocks;
import net.jitl.init.JItems;
import net.jitl.init.JTiles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.api.common.tile.SerializationType;
import ru.timeconqueror.timecore.api.common.tile.SyncableTile;
import ru.timeconqueror.timecore.api.util.HorizontalDirection;
import ru.timeconqueror.timecore.api.util.RandHelper;

import java.util.Arrays;
import java.util.stream.Collectors;

import static ru.timeconqueror.timecore.api.util.HorizontalDirection.*;

public class EssenciaAltarTile extends SyncableTile implements ITickableTileEntity {
    private static final int PATH_PROGRESS_DELAY = 5;
    private static final float PATH_PROGRESS = 0.1F;
    private final Path northPath = new Path(NORTH, JBlocks.BLOOD_RUNE_DEATH);
    private final Path eastPath = new Path(EAST, JBlocks.BLOOD_RUNE_FLESH);
    private final Path westPath = new Path(WEST, JBlocks.BLOOD_RUNE_LIFE);
    private final Path southPath = new Path(SOUTH, JBlocks.BLOOD_RUNE_SOUL);

    private final Path[] paths = new Path[]{northPath, eastPath, southPath, westPath};

    private boolean activated;

    private int ticks = 0;
    private long randomSeed;

    public EssenciaAltarTile() {
        super(JTiles.ESSENCIA_ALTAR);
    }

    @Override
    public void tick() {
        for (Path path : getPaths()) {
            path.tick();
        }

        if (activated) {
            checkNeighbours();
            System.out.println(Arrays.stream(getPaths()).map(path -> path.direction.get() + ": " + path.getValidBlockCount() + ", ").collect(Collectors.joining()));
        } else {
            for (Path path : getPaths()) {
                path.setValidBlockCount(0);
            }
        }

        if (ticks % PATH_PROGRESS_DELAY == 0) {
            for (Path path : getPaths()) {
                path.updateCurrentLength();
            }
        }

        if (ticks % 4 == 0) {
            randomSeed = RandHelper.RAND.nextLong();
        }

        ticks++;
    }

    public void onRightClick(ServerPlayerEntity entity, ItemStack itemStack) {
        if (itemStack.getItem() == JItems.POWDER_OF_ESSENCIA) {
            itemStack.shrink(1);
            activated = true;
            saveAndSync();
        }
    }

    public long getRandomSeed() {
        return randomSeed;
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
                mutable.move((i + 1) * path.stepX(), 0, (i + 1) * path.stepZ());

                BlockState blockState = level.getBlockState(mutable);
                if (blockState.getBlock() != JBlocks.RUNIC_CONNECTOR || blockState.getValue(XZFacedBlock.HORIZONTAL_AXIS) != path.connectorAxis()) {
                    changed = true;
                    break;
                }
            }

            if (!changed) {
                mutable.set(pos);
                mutable.move(4 * path.stepX(), 0, 4 * path.stepZ());

                BlockState runeState = level.getBlockState(mutable);
                if (runeState.getBlock() == path.validRune) {
                    i++;
                } else if (isServerSide() && path.readyToActivateRune() && runeState.getBlock() == JBlocks.EMPTY_BLOOD_RUNE) {
                    transformRune(level, path, mutable);
                }
            }

            if (path.getValidBlockCount() != i || path.isFullLength()) {
                changed = true;
            }

            path.setValidBlockCount(i);

            if (changed) save();
        }
    }

    private void transformRune(World world, Path path, BlockPos pos) {
        world.setBlockAndUpdate(pos, path.validRune.defaultBlockState());
    }

    @Override
    protected void writeNBT(CompoundNBT nbt, SerializationType type) {
        super.writeNBT(nbt, type);

        nbt.putBoolean("activated", activated);
    }

    @Override
    protected void readNBT(BlockState state, CompoundNBT nbt, SerializationType type) {
        super.readNBT(state, nbt, type);

        activated = nbt.getBoolean("activated");
    }

    private Path[] getPaths() {
        return paths;
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

    public boolean isActivated() {
        return activated;
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

        private float currentLength = validBlockCount;
        private boolean isFullLength;

        public Path(HorizontalDirection direction, Block validRune) {
            this.direction = direction;
            this.validRune = validRune;
        }

        private void tick() {

        }

        public int stepX() {
            return direction.get().getStepX();
        }

        public int stepZ() {
            return direction.get().getStepZ();
        }

        public int getValidBlockCount() {
            return validBlockCount;
        }

        public void setValidBlockCount(int validBlockCount) {
            int lastCount = this.validBlockCount;
            this.validBlockCount = validBlockCount;

            if (validBlockCount < lastCount) {
                updateCurrentLength();
            }
        }

        private void updateCurrentLength() {
            currentLength = Math.min(validBlockCount, currentLength + PATH_PROGRESS);
            isFullLength = currentLength == validBlockCount;
        }

        public boolean readyToActivateRune() {
            return currentLength == 3;
        }

        public boolean isFullLength() {
            return isFullLength;
        }

        public Direction.Axis connectorAxis() {
            return direction.get().getAxis();
        }

        public float getCurrentLength() {
            return currentLength;
        }
    }
}
