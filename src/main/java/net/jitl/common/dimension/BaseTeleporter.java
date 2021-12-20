package net.jitl.common.dimension;

import com.google.common.collect.ImmutableSet;
import net.jitl.common.block.portal.JBasePortalBlock;
import net.jitl.init.JBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.TeleportationRepositioner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterest;
import net.minecraft.village.PointOfInterestManager;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.Teleporter;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;
import net.minecraftforge.common.util.ITeleporter;

import java.util.Comparator;
import java.util.Optional;

public class BaseTeleporter extends Teleporter {

    protected final ServerWorld level;
    private final JBasePortalBlock portal_block;
    private final Block portal_frame;
    private final PointOfInterestType poi;

    public BaseTeleporter(ServerWorld worldIn, JBasePortalBlock portal, Block frame, PointOfInterestType poi) {
        super(worldIn);
        this.level = worldIn;
        this.portal_block = portal;
        this.portal_frame = frame;
        this.poi = poi;
    }

    @Override
    public Optional<TeleportationRepositioner.Result> findPortalAround(BlockPos pos, boolean isNether) {
        PointOfInterestManager poiManager = this.level.getPoiManager();
        poiManager.ensureLoadedAndValid(this.level, pos, 128);
        Optional<PointOfInterest> optional = poiManager.getInSquare((poiType) ->
                poiType == poi, pos, 128, PointOfInterestManager.Status.ANY).sorted(Comparator.<PointOfInterest>comparingDouble((poi) ->
                poi.getPos().distSqr(pos)).thenComparingInt((poi) ->
                poi.getPos().getY())).filter((poi) ->
                this.level.getBlockState(poi.getPos()).hasProperty(BlockStateProperties.HORIZONTAL_AXIS)).findFirst();
        return optional.map((poi) -> {
            BlockPos blockpos = poi.getPos();
            this.level.getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockpos), 3, blockpos);
            BlockState blockstate = this.level.getBlockState(blockpos);
            return TeleportationRepositioner.getLargestRectangleAround(blockpos, blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS), 21, Direction.Axis.Y, 21, (posIn) ->
                    this.level.getBlockState(posIn) == blockstate);
        });
    }

    @Override
    public Optional<TeleportationRepositioner.Result> createPortal(BlockPos pos, Direction.Axis axis) {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0D;
        BlockPos blockpos = null;
        double d1 = -1.0D;
        BlockPos blockpos1 = null;
        WorldBorder worldborder = this.level.getWorldBorder();
        int i = this.level.getHeight() - 1;
        BlockPos.Mutable blockpos$mutable = pos.mutable();

        for(BlockPos.Mutable blockpos$mutable1 : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
            int j = Math.min(i, this.level.getHeight(Heightmap.Type.MOTION_BLOCKING, blockpos$mutable1.getX(), blockpos$mutable1.getZ()));
            int k = 1;
            if (worldborder.isWithinBounds(blockpos$mutable1) && worldborder.isWithinBounds(blockpos$mutable1.move(direction, 1))) {
                blockpos$mutable1.move(direction.getOpposite(), 1);

                for(int l = j; l >= 0; --l) {
                    blockpos$mutable1.setY(l);
                    if (this.level.isEmptyBlock(blockpos$mutable1)) {
                        int i1;
                        for(i1 = l; l > 0 && this.level.isEmptyBlock(blockpos$mutable1.move(Direction.DOWN)); --l) {
                        }

                        if (l + 4 <= i) {
                            int j1 = i1 - l;
                            if (j1 <= 0 || j1 >= 3) {
                                blockpos$mutable1.setY(l);
                                if (this.canHostFrame(blockpos$mutable1, blockpos$mutable, direction, 0)) {
                                    double d2 = pos.distSqr(blockpos$mutable1);
                                    if (this.canHostFrame(blockpos$mutable1, blockpos$mutable, direction, -1) && this.canHostFrame(blockpos$mutable1, blockpos$mutable, direction, 1) && (d0 == -1.0D || d0 > d2)) {
                                        d0 = d2;
                                        blockpos = blockpos$mutable1.immutable();
                                    }

                                    if (d0 == -1.0D && (d1 == -1.0D || d1 > d2)) {
                                        d1 = d2;
                                        blockpos1 = blockpos$mutable1.immutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (d0 == -1.0D && d1 != -1.0D) {
            blockpos = blockpos1;
            d0 = d1;
        }

        if (d0 == -1.0D) {
            blockpos = (new BlockPos(pos.getX(), MathHelper.clamp(pos.getY(), 70, this.level.getHeight() - 10), pos.getZ())).immutable();
            Direction direction1 = direction.getClockWise();
            if (!worldborder.isWithinBounds(blockpos)) {
                return Optional.empty();
            }

            for(int l1 = -1; l1 < 2; ++l1) {
                for(int k2 = 0; k2 < 2; ++k2) {
                    for(int i3 = -1; i3 < 3; ++i3) {
                        BlockState blockstate1 = i3 < 0 ? this.portal_frame.defaultBlockState() : Blocks.AIR.defaultBlockState();
                        blockpos$mutable.setWithOffset(blockpos, k2 * direction.getStepX() + l1 * direction1.getStepX(), i3, k2 * direction.getStepZ() + l1 * direction1.getStepZ());
                        this.level.setBlockAndUpdate(blockpos$mutable, blockstate1);
                    }
                }
            }
        }

        for(int k1 = -1; k1 < 3; ++k1) {
            for(int i2 = -1; i2 < 4; ++i2) {
                if (k1 == -1 || k1 == 2 || i2 == -1 || i2 == 3) {
                    blockpos$mutable.setWithOffset(blockpos, k1 * direction.getStepX(), i2, k1 * direction.getStepZ());
                    this.level.setBlock(blockpos$mutable, this.portal_frame.defaultBlockState(), 3);
                }
            }
        }

        BlockState blockstate = this.portal_block.defaultBlockState().setValue(JBasePortalBlock.AXIS, axis);

        for(int j2 = 0; j2 < 2; ++j2) {
            for(int l2 = 0; l2 < 3; ++l2) {
                blockpos$mutable.setWithOffset(blockpos, j2 * direction.getStepX(), l2, j2 * direction.getStepZ());
                this.level.setBlock(blockpos$mutable, blockstate, 18);
            }
        }

        return Optional.of(new TeleportationRepositioner.Result(blockpos.immutable(), 2, 3));
    }

    private boolean canHostFrame(BlockPos originalPos, BlockPos.Mutable offsetPos, Direction directionIn, int offsetScale) {
        Direction direction = directionIn.getClockWise();

        for(int i = -1; i < 3; ++i) {
            for(int j = -1; j < 4; ++j) {
                offsetPos.setWithOffset(originalPos, directionIn.getStepX() * i + direction.getStepX() * offsetScale, j, directionIn.getStepZ() * i + direction.getStepZ() * offsetScale);
                if (j < 0 && !this.level.getBlockState(offsetPos).getMaterial().isSolid()) {
                    return false;
                }

                if (j >= 0 && !this.level.isEmptyBlock(offsetPos)) {
                    return false;
                }
            }
        }

        return true;
    }
}
