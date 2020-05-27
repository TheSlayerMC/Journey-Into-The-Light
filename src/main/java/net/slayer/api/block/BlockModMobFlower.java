package net.slayer.api.block;

import net.journey.entity.mob.terrania.mob.EntityTerragrow;
import net.journey.init.JourneyTabs;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.EnumMaterialTypes;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockModMobFlower extends BlockModFlower implements IPlantable {

    public BlockModMobFlower(String name, String finalName) {
        super(name, finalName);
      
    }
    
    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
        if (!worldIn.isRemote) {
            EntityTerragrow terra = new EntityTerragrow(worldIn);
            worldIn.spawnEntity(terra);
            terra.setPosition(pos.getX(), pos.getY() + 0, pos.getZ());
            worldIn.setBlockState(pos.add(0, 0, 0), Blocks.AIR.getDefaultState());
        }
    }
}