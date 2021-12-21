package net.jitl.common.block.portal;

import net.jitl.common.dimension.BaseTeleporter;
import net.jitl.common.dimension.Dimensions;
import net.jitl.init.JBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

public class JBasePortalBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    private final RegistryKey<World> dimensionID;
    private final Supplier<Block> frame;

    public JBasePortalBlock(Properties properties, RegistryKey<World> dimID, Supplier<Block> frame) {
        super(properties);
        this.dimensionID = dimID;
        this.frame = frame;
        registerDefaultState(stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.getValue(AXIS)) {
            case Z:
                return Z_AABB;
            case X:
            default:
                return X_AABB;
        }
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerWorld worldIn, @NotNull BlockPos pos, @NotNull Random random) {
        //make particals call in constructor
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        Direction.Axis direction$axis = facing.getAxis();
        Direction.Axis direction$axis1 = stateIn.getValue(AXIS);
        boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
        return !flag && facingState.getBlock() != this && !(new JBasePortalBlock.Size(worldIn, currentPos, direction$axis1, getBlock(), frame.get())).validatePortal() ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }


    @Override
    public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        switch (rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.getValue(AXIS)) {
                    case Z:
                        return state.setValue(AXIS, Direction.Axis.X);
                    case X:
                        return state.setValue(AXIS, Direction.Axis.Z);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }


    @Override
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entity) {
        if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            if (entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                if (!entity.level.isClientSide && !pos.equals(entity.portalEntrancePos)) {
                    entity.portalEntrancePos = pos.immutable();
                }
                World entityWorld = entity.level;
                if (entityWorld != null) {
                    MinecraftServer minecraftserver = entityWorld.getServer();
                    if (minecraftserver != null) {
                        RegistryKey<World> destination = entity.level.dimension() == dimensionID ? World.OVERWORLD : dimensionID;
                        ServerWorld destinationWorld = minecraftserver.getLevel(destination);
                        PointOfInterestType poi = Dimensions.EUCA_PORTAL.get();
                        if (this == JBlocks.EUCA_PORTAL)
                            poi = Dimensions.EUCA_PORTAL.get();
                        if (this == JBlocks.FROZEN_PORTAL)
                            poi = Dimensions.FROZEN_PORTAL.get();
                        if (this == JBlocks.BOIL_PORTAL)
                            poi = Dimensions.BOILING_PORTAL.get();
                        if (this == JBlocks.DEPTHS_PORTAL)
                            poi = Dimensions.DEPTHS_PORTAL.get();

                        if (destinationWorld != null && minecraftserver.isNetherEnabled() && !entity.isPassenger()) {
                            entity.level.getProfiler().push(Objects.requireNonNull(this.getRegistryName()).toString());
                            entity.setPortalCooldown();
                            entity.changeDimension(destinationWorld, new BaseTeleporter(destinationWorld, this, frame.get(), poi, destination));
                            entity.level.getProfiler().pop();
                        }
                    }
                }
            }
        }
    }

    public boolean makePortal(IWorld worldIn, BlockPos pos) {
        JBasePortalBlock.Size portal = this.isPortal(worldIn, pos);
        if (portal != null) {
            portal.createPortalBlocks();
            return true;
        } else {
            return false;
        }
    }

    public JBasePortalBlock.Size isPortal(IWorld worldIn, BlockPos pos) {
        JBasePortalBlock.Size portalX = new Size(worldIn, pos, Direction.Axis.X, this, worldIn.getBlockState(pos.below()).getBlock());
        if (portalX.isValid() && portalX.portalBlockCount == 0) {
            return portalX;
        } else {
            JBasePortalBlock.Size portalZ = new Size(worldIn, pos, Direction.Axis.Z, this, worldIn.getBlockState(pos.below()).getBlock());
            return portalZ.isValid() && portalZ.portalBlockCount == 0 ? portalZ : null;
        }
    }

    public static class Size {
        private final IWorld world;
        private final Direction.Axis axis;
        private final Direction rightDir;
        private final Direction leftDir;
        private int portalBlockCount;
        @Nullable
        private BlockPos bottomLeft;
        private int height;
        private int width;
        private final Block portal;
        private final Block frame;

        public Size(IWorld worldIn, BlockPos pos, Direction.Axis axisIn, Block portal, Block frame) {
            this.world = worldIn;
            this.axis = axisIn;
            this.portal = portal;
            this.frame = frame;
            if (axisIn == Direction.Axis.X) {
                this.leftDir = Direction.EAST;
                this.rightDir = Direction.WEST;
            } else {
                this.leftDir = Direction.NORTH;
                this.rightDir = Direction.SOUTH;
            }

            for(BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && this.canConnect(worldIn.getBlockState(pos.below())); pos = pos.below()) {
            }

            int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;
            if (i >= 0) {
                this.bottomLeft = pos.relative(this.leftDir, i);
                this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
                if (this.width < 2 || this.width > 21) {
                    this.bottomLeft = null;
                    this.width = 0;
                }
            }

            if (this.bottomLeft != null) {
                this.height = this.calculatePortalHeight();
            }

        }

        protected int getDistanceUntilEdge(BlockPos pos, Direction directionIn) {
            int i;
            for(i = 0; i < 22; ++i) {
                BlockPos blockpos = pos.relative(directionIn, i);
                if (!this.canConnect(this.world.getBlockState(blockpos)) || !(this.world.getBlockState(blockpos.below()).getBlock().is(this.world.getBlockState(pos.below()).getBlock()))) {
                    break;
                }
            }

            BlockPos framePos = pos.relative(directionIn, i);
            return this.world.getBlockState(framePos).getBlock().is(this.world.getBlockState(pos.below()).getBlock()) ? i : 0;
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }

        protected int calculatePortalHeight() {
            label56:
            for(this.height = 0; this.height < 21; ++this.height) {
                for(int i = 0; i < this.width; ++i) {
                    BlockPos blockpos = this.bottomLeft.relative(this.rightDir, i).above(this.height);
                    BlockState blockstate = this.world.getBlockState(blockpos);
                    if (!this.canConnect(blockstate)) {
                        break label56;
                    }

                    Block block = blockstate.getBlock();
                    if (block == this.portal) {
                        ++this.portalBlockCount;
                    }

                    if (i == 0) {
                        BlockPos framePos = blockpos.relative(this.leftDir);
                        if (!(this.world.getBlockState(framePos).getBlock().is(this.world.getBlockState(framePos).getBlock()))) {
                            break label56;
                        }
                    } else if (i == this.width - 1) {
                        BlockPos framePos = blockpos.relative(this.rightDir);
                        if (!(this.world.getBlockState(framePos).getBlock().is(this.world.getBlockState(framePos).getBlock()))) {
                            break label56;
                        }
                    }
                }
            }

            for(int j = 0; j < this.width; ++j) {
                BlockPos framePos = this.bottomLeft.relative(this.rightDir, j).above(this.height);
                if (!(this.world.getBlockState(framePos).getBlock().is(this.world.getBlockState(framePos).getBlock()))) {
                    this.height = 0;
                    break;
                }
            }

            if (this.height <= 21 && this.height >= 3) {
                return this.height;
            } else {
                this.bottomLeft = null;
                this.width = 0;
                this.height = 0;
                return 0;
            }
        }

        protected boolean canConnect(BlockState pos) {
            Block block = pos.getBlock();
            return pos.isAir() || block == this.portal;
        }

        public boolean isValid() {
            return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
        }

        public void createPortalBlocks() {
            BlockState blockstate = this.portal.defaultBlockState().setValue(JBasePortalBlock.AXIS, this.axis);
            BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDir, this.width - 1)).forEach((pos) -> {
                this.world.setBlock(pos, blockstate, 18);
            });
        }

        private boolean isPortalCountValidForSize() {
            return this.portalBlockCount >= this.width * this.height;
        }

        public boolean validatePortal() {
            return this.isValid() && this.isPortalCountValidForSize();
        }
    }
}
