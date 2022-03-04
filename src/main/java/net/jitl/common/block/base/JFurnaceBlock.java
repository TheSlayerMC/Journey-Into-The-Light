package net.jitl.common.block.base;

import net.jitl.common.tile.base.JFurnaceTile;
import net.jitl.core.init.JTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class JFurnaceBlock extends AbstractFurnaceBlock {

    public JFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void openContainer(Level l, BlockPos b, Player p) {
        BlockEntity e = l.getBlockEntity(b);
        if(e instanceof JFurnaceTile) {
            p.openMenu((MenuProvider)e);
            p.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p, BlockState s) {
        return new JFurnaceTile(p, s);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level l, BlockState s, BlockEntityType<T> e) {
        return createFurnaceTicker(l, e, JTiles.JFURNACE);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {
        if(state.getValue(LIT)) {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY();
            double d2 = (double)pos.getZ() + 0.5D;
            if(rand.nextDouble() < 0.1D) {
                level.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }
            Direction direction = state.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d4 = rand.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
            double d6 = rand.nextDouble() * 6.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
            level.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            level.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }
}
