package net.jitl.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.IPlantable;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class VolcanicRockBlock extends Block {

    public VolcanicRockBlock(Properties properties) {
        super(properties);
    }

    EndPortalFrameBlock

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {
        if(rand.nextInt(10) == 0) {
            level.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.75D, (double)pos.getZ() + 0.5D, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.5F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.6F, false);
        }

        if(rand.nextInt(5) == 0) {
            for(int i = 0; i < rand.nextInt(1) + 1; ++i) {
                 level.addParticle(ParticleTypes.LAVA, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.75D, (double)pos.getZ() + 0.5D, (double)(rand.nextFloat() / 2.0F), 5.0E-5D, (double)(rand.nextFloat() / 2.0F));
            }
        }
        if(rand.nextInt(5) == 0) {
            for(int i = 0; i < rand.nextInt(1) + 1; ++i) {
                level.addParticle(ParticleTypes.LAVA, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.75D, (double)pos.getZ() + 0.5D, (double)(rand.nextFloat() / 2.0F), 5.0E-5D, (double)(rand.nextFloat() / 2.0F));
            }
        }

        for(int i = 0; i < rand.nextInt(2) + 2; ++i) {
            SimpleParticleType simpleparticletype = ParticleTypes.CAMPFIRE_COSY_SMOKE;
            level.addAlwaysVisibleParticle(simpleparticletype, true, (double) pos.getX() + 0.5D + rand.nextDouble() / 3.0D * (double) (rand.nextBoolean() ? 1 : -1), (double) pos.getY() + rand.nextDouble() + rand.nextDouble(), (double) pos.getZ() + 0.5D + rand.nextDouble() / 3.0D * (double) (rand.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
            level.addParticle(ParticleTypes.SMOKE, (double) pos.getX() + 0.5D + rand.nextDouble() / 4.0D * (double) (rand.nextBoolean() ? 1 : -1), (double) pos.getY() + 0.65D, (double) pos.getZ() + 0.5D + rand.nextDouble() / 4.0D * (double) (rand.nextBoolean() ? 1 : -1), 0.0D, 0.005D, 0.0D);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSupportCenter(level, pos.below(), Direction.UP);
    }



    @Override
    public boolean isCollisionShapeFullBlock(BlockState state_, BlockGetter level_, BlockPos pos_) {
        return super.isCollisionShapeFullBlock(state_, level_, pos_);
    }

    @Override
    public boolean canBeReplaced(BlockState state_, Fluid fluid_) {
        return super.canBeReplaced(state_, fluid_);
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state_) {
        return true;
    }
}
