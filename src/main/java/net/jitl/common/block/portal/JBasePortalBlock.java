package net.jitl.common.block.portal;

import net.jitl.common.dimension.BaseTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
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
    public void entityInside(@NotNull BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, @NotNull Entity entityIn) {
        if (entityIn instanceof ServerPlayerEntity) {
            if (!entityIn.isPassenger() && !entityIn.isVehicle() && entityIn.canChangeDimensions()) {
                ServerPlayerEntity playerMP = (ServerPlayerEntity) entityIn;
                RegistryKey<World> destination;
                if (((ServerPlayerEntity) entityIn).getLevel().dimension() == dimensionID) {
                    destination = World.OVERWORLD;
                } else {
                    destination = dimensionID;
                }
                ServerWorld serverworld = Objects.requireNonNull(playerMP.level.getServer()).getLevel(destination);
                playerMP.changeDimension(serverworld, new BaseTeleporter(((ServerPlayerEntity) entityIn).getLevel(), this, this.frame));
            }
        }
    }
}