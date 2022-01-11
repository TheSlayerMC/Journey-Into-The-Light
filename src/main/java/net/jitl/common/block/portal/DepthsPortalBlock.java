package net.jitl.common.block.portal;

import net.jitl.common.dimension.DepthsTeleporter;
import net.jitl.init.JBlocks;
import net.jitl.init.world.Dimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;
import java.util.Random;

public class DepthsPortalBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

    public DepthsPortalBlock(Properties builder) {
        super(builder);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entity) {
        if (!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions()) {
            if (entity.isOnPortalCooldown()) {
                entity.setPortalCooldown();
            } else {
                if (!entity.level.isClientSide && !pos.equals(entity.portalEntrancePos)) {
                    entity.portalEntrancePos = pos.immutable();
                }
                Level entityWorld = entity.level;
                if (entityWorld != null) {
                    MinecraftServer minecraftserver = entityWorld.getServer();
                    if (minecraftserver != null) {
                        ResourceKey<Level> destination = entity.level.dimension() == Dimensions.DEPTHS ? Level.OVERWORLD : Dimensions.DEPTHS;
                        ServerLevel destinationWorld = minecraftserver.getLevel(destination);
                        PoiType poi = Dimensions.DEPTHS_PORTAL.get();
                        if (destinationWorld != null && minecraftserver.isNetherEnabled() && !entity.isPassenger()) {
                            entity.level.getProfiler().push(Objects.requireNonNull(this.getRegistryName()).toString());
                            entity.setPortalCooldown();
                            entity.changeDimension(destinationWorld, new DepthsTeleporter(destinationWorld, this, JBlocks.DEPTHS_PORTAL_FRAME));
                            entity.level.getProfiler().pop();
                        }
                    }
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        double d0 = (double)pos.getX() + rand.nextDouble();
        double d1 = (double)pos.getY() + 0.8D;
        double d2 = (double)pos.getZ() + rand.nextDouble();
        worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public boolean canBeReplaced(BlockState state, Fluid fluidIn) {
        return false;
    }
}