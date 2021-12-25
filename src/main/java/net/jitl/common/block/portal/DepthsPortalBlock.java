package net.jitl.common.block.portal;

import java.util.Objects;
import java.util.Random;

import net.jitl.common.dimension.BaseTeleporter;
import net.jitl.common.dimension.DepthsTeleporter;
import net.jitl.common.dimension.Dimensions;
import net.jitl.init.JBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.EndPortalTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DepthsPortalBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

    public DepthsPortalBlock(AbstractBlock.Properties builder) {
        super(builder);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
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
                        RegistryKey<World> destination = entity.level.dimension() == Dimensions.DEPTHS ? World.OVERWORLD : Dimensions.DEPTHS;
                        ServerWorld destinationWorld = minecraftserver.getLevel(destination);
                        PointOfInterestType poi = Dimensions.DEPTHS_PORTAL.get();
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
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
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