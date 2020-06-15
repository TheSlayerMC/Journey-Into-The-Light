package net.journey.blocks.plant;

import net.journey.blocks.base.JBlockFlower;
import net.journey.entity.mob.terrania.mob.EntityTerragrow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class BlockTerranianFlower extends JBlockFlower {

    public BlockTerranianFlower(String name, String finalName) {
        super(name, finalName);
    }

    @Override
    public void onEntityCollision(World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, @NotNull Entity entity) {
        if (!worldIn.isRemote) {
            EntityTerragrow grow = new EntityTerragrow(worldIn);
            grow.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
            worldIn.spawnEntity(grow);

            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }
}
