package net.journey.blocks.containers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.block.BlockMod;

import java.util.Random;

public class BlockObelisk extends BlockMod {

    public BlockObelisk(String name, String finalName) {
        super(name, finalName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World w, BlockPos pos, Random random) {
        if (random.nextInt(15) == 0) {
            for (int i = 0; i < 20; ++i) {
                double d0 = pos.getX();
                double d1 = (double) pos.getY() - rand.nextInt() - 1.0F;
                double d2 = pos.getZ();
                w.spawnParticle(EnumParticleTypes.DRIP_LAVA, d0 * rand.nextFloat(), d1, d2 * rand.nextFloat(), 0.1,
                        0.0D, 0.1);
            }
        }
    }
}
