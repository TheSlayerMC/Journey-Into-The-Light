package net.jitl.common.block.portal;

import net.jitl.common.dimension.BaseTeleporter;
import net.jitl.common.dimension.Dimensions;
import net.jitl.init.JBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class JBasePortalBlock extends NetherPortalBlock {

    private final RegistryKey<World> dimensionID;
    private final Block frame;

    public JBasePortalBlock(Properties properties, RegistryKey<World> dimID, Block frame) {
        super(properties);
        this.dimensionID = dimID;
        this.frame = frame;
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
        return !flag && facingState.getBlock() != this && !(new PortalSize(worldIn, currentPos, direction$axis1)).isValid() ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void entityInside(@NotNull BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, @NotNull Entity entityIn) {
        if (entityIn instanceof ServerPlayerEntity) {
            if (!entityIn.isPassenger() && !entityIn.isVehicle() && entityIn.canChangeDimensions()) {
                if (entityIn.isOnPortalCooldown()) {
                    entityIn.setPortalCooldown();
                } else {
                    if (!entityIn.level.isClientSide && !pos.equals(entityIn.portalEntrancePos)) {
                        entityIn.portalEntrancePos = pos.immutable();
                    }
                    ServerPlayerEntity playerMP = (ServerPlayerEntity) entityIn;
                    RegistryKey<World> destination;
                    if (((ServerPlayerEntity) entityIn).getLevel().dimension() == dimensionID) {
                        destination = World.OVERWORLD;
                    } else {
                        destination = dimensionID;
                    }
                    ServerWorld serverworld = Objects.requireNonNull(playerMP.level.getServer()).getLevel(destination);
                    PointOfInterestType poi = Dimensions.EUCA_PORTAL.get();
                    if (this == JBlocks.EUCA_PORTAL)
                        poi = Dimensions.EUCA_PORTAL.get();
                    if (this == JBlocks.FROZEN_PORTAL)
                        poi = Dimensions.FROZEN_PORTAL.get();
                    if (this == JBlocks.BOIL_PORTAL)
                        poi = Dimensions.BOILING_PORTAL.get();
                    if (this == JBlocks.DEPTHS_PORTAL)
                        poi = Dimensions.DEPTHS_PORTAL.get();

                    if (serverworld != null) {
                        playerMP.setPortalCooldown();
                        playerMP.changeDimension(serverworld, new BaseTeleporter(((ServerPlayerEntity) entityIn).getLevel(), this, this.frame, poi, dimensionID));
                    }
                }
            }
        }
    }
}