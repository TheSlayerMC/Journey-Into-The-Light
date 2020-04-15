package net.journey.blocks;

import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.slayer.api.block.BlockModFlower;

import java.util.Random;

public class BlockModSapling extends BlockModFlower implements IGrowable {

    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
    WorldGenerator treeStructure;


    public BlockModSapling(String name, String f1, WorldGenerator treeStructure) {
        super(name, f1);
        this.setTickRandomly(true);
        float f = 0.4F;
        this.treeStructure = treeStructure;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return SAPLING_AABB;
    }

    @Override
    public void updateTick(World w, BlockPos pos, IBlockState s, Random r) {
        if (!w.isRemote) {
            super.updateTick(w, pos, s, r);
            if (w.getLightFromNeighbors(pos.up()) >= 9 && r.nextInt(9) == 0)
                this.grow(w, r, pos, s);
        }
    }

    private void generate(World w, BlockPos pos, Random r) {
        if (!TerrainGen.saplingGrowTree(w, rand, pos))
            return;
        w.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
        if (!treeStructure.generate(w, rand, pos.add(0, 0, 0))) {
            w.setBlockState(pos, this.getDefaultState(), 4);
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        i = i | state.getValue(STAGE).intValue() << 3;
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STAGE);
    }

    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if (state.getValue(STAGE).intValue() == 0) {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        } else {
            generate(worldIn, pos, rand);
        }
    }
}