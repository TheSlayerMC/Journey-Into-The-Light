package net.jitl.common.block;

import net.jitl.init.JBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

public class AncientCatalystBlock extends Block {

    public AncientCatalystBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        Random random = new Random();
        int check_radius = 4;
        int totalSockets = 0;

        for (int x = -check_radius; x <= check_radius; x++) {
            for (int z = -check_radius; z <= check_radius; z++) {
                    final BlockPos pos1 = pos.offset(x, 0, z);
                    final Block block = worldIn.getBlockState(pos1).getBlock();
                    if(block == JBlocks.ANCIENT_SOCKET && worldIn.getBlockState(pos1).getValue(AncientSocketBlock.INSERTED)) {
                        totalSockets++;
                    }
            }
        }
        if(totalSockets >= 4) {
            worldIn.removeBlock(pos, false);
            for(int i = 0; i < 50; i++)
                worldIn.addParticle(ParticleTypes.SMOKE,pos.getX() + random.nextDouble(), pos.getY()  + random.nextDouble(), pos.getZ() + random.nextDouble(),
                    random.nextGaussian() * 0.05D, 0.15D, random.nextGaussian() * 0.05D);
            worldIn.playSound(player, pos, SoundEvents.IRON_DOOR_OPEN, SoundCategory.BLOCKS, 1.0F, 0.2F);

        }
        return ActionResultType.sidedSuccess(worldIn.isClientSide);
    }
}
