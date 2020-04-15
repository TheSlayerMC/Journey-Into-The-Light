package net.journey.blocks;

import net.journey.entity.mob.corba.EntityOverseerElder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.block.BlockMod;

public class BlockTrap extends BlockMod {

    public BlockTrap(String name, String finalName) {
        super(name, finalName);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public void onEntityCollision(World w, BlockPos pos, IBlockState state, Entity entityIn) {
        EntityOverseerElder EntityOverseerElder = new EntityOverseerElder(w);
        if (!w.isRemote) {
            try {
                w.spawnEntity(EntityOverseerElder);
                EntityOverseerElder.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}