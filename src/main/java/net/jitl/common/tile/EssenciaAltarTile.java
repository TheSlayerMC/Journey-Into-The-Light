package net.jitl.common.tile;

import net.jitl.common.block.base.XZFacedBlock;
import net.jitl.common.entity.EssenciaBoltEntity;
import net.jitl.init.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.timeconqueror.timecore.api.common.tile.SerializationType;
import ru.timeconqueror.timecore.api.common.tile.SyncableTile;
import ru.timeconqueror.timecore.api.util.HorizontalDirection;
import ru.timeconqueror.timecore.api.util.RandHelper;

import java.util.Random;

import static ru.timeconqueror.timecore.api.util.HorizontalDirection.*;

public class EssenciaAltarTile extends SyncableTile implements ITickableTileEntity {
    private static final int PATH_PROGRESS_DELAY = 1;
    private static final float PATH_PROGRESS = 0.1F;
    private final Path northPath = new Path(NORTH, JBlocks.BLOOD_RUNE_DEATH);
    private final Path eastPath = new Path(EAST, JBlocks.BLOOD_RUNE_FLESH);
    private final Path westPath = new Path(WEST, JBlocks.BLOOD_RUNE_LIFE);
    private final Path southPath = new Path(SOUTH, JBlocks.BLOOD_RUNE_SOUL);

    private final Path[] paths = new Path[]{northPath, eastPath, southPath, westPath};

    private boolean activated;

    private int ticks = 0;
    private long randomSeed;

    private final Random random = new Random();

    public EssenciaAltarTile() {
        super(JTiles.ESSENCIA_ALTAR);
    }

    @Override
    public void tick() {
        if (activated) {
            checkNeighbours();

            if (ticks % PATH_PROGRESS_DELAY == 0) {
                for (Path path : getPaths()) {
                    path.updateCurrentLength();
                }
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
            level.playSound(null, this.getBlockPos(), JSounds.ESSENCIA_ALTAR_ACTIVATE.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
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
                } else if (path.readyToActivateRune() && runeState.getBlock() == JBlocks.EMPTY_BLOOD_RUNE) {
                    if (isServerSide()) {
                        transformRune(level, path, mutable);
                        doEffects(level, mutable);
                    } else {
                        spawnParticles(level, mutable);
                    }
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

    private void doEffects(World world, BlockPos pos) {
        EssenciaBoltEntity essenciaBoltEntity = new EssenciaBoltEntity(JEntities.ESSENCIA_BOLT_TYPE, world);
        essenciaBoltEntity.setPos(pos.getX(), pos.above().getY(), pos.getZ());
        essenciaBoltEntity.setARGB(0xff4800);
        essenciaBoltEntity.setVisualOnly(true);
        world.addFreshEntity(essenciaBoltEntity);
        world.playSound(null, pos, JSounds.RUNE_ACTIVATE.get(), SoundCategory.BLOCKS, 1.0F, random.nextFloat() + 0.5F);
    }

    public void spawnParticles(World worldIn, BlockPos pos) {
        for (int i = 0; i < 6; i++) {
            IParticleData particle = JParticleManager.RED_FLAME.get();
            int posThreshold = 16;
            int speedThreshold = 64;
            float posRandom0 = (float) random.nextInt(2 + i) / posThreshold;
            float posRandom1 = (float) random.nextInt(2 + i) / posThreshold;
            float posRandom2 = (float) random.nextInt(2 + i) / posThreshold;
            float posRandom3 = (float) random.nextInt(2 + i) / posThreshold;
            float speedRandom0 = (posRandom0 + 1) / speedThreshold;
            float speedRandom1 = (posRandom1 + 2) / speedThreshold;
            float speedRandom2 = (posRandom2 + 1) / speedThreshold;
            float speedRandom3 = (posRandom3 + 2) / speedThreshold;
            worldIn.addParticle(particle, (pos.getX() + posRandom0) + 0.5F, pos.above().getY(), (pos.getZ() - posRandom0) + 0.5F, speedRandom0, speedRandom2, -speedRandom3);
            worldIn.addParticle(particle, (pos.getX() - posRandom1) + 0.5F, pos.above().getY(), (pos.getZ() + posRandom1) + 0.5F, -speedRandom1, speedRandom1, speedRandom2);
            worldIn.addParticle(particle, (pos.getX() - posRandom2) + 0.5F, pos.above().getY(), (pos.getZ() - posRandom2) + 0.5F, -speedRandom2, speedRandom0, -speedRandom1);
            worldIn.addParticle(particle, (pos.getX() + posRandom3) + 0.5F, pos.above().getY(), (pos.getZ() + posRandom3) + 0.5F, speedRandom3, speedRandom3, speedRandom0);
        }
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
