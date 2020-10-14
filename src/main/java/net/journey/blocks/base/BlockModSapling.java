package net.journey.blocks.base;

import net.journey.JITL;
import net.journey.api.block.FeatureProvider;
import net.journey.blocks.util.Features;
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
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class BlockModSapling extends BlockModFlower implements IGrowable, FeatureProvider {

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
		if (!TerrainGen.saplingGrowTree(w, r, pos))
			return;
		w.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
		if (!treeStructure.generate(w, r, pos.add(0, 0, 0))) {
			w.setBlockState(pos, this.getDefaultState(), 4);
		}
	}

	@Override
	public @NotNull IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(STAGE, (meta & 8) >> 3);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | state.getValue(STAGE) << 3;
		return i;
	}

	@Override
	protected @NotNull BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, STAGE);
	}

	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	public boolean canGrow(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull IBlockState state, boolean isClient) {
		return true;
	}

	public boolean canUseBonemeal(@NotNull World worldIn, @NotNull Random rand, @NotNull BlockPos pos, @NotNull IBlockState state) {
		return true;
	}

	public void grow(@NotNull World worldIn, @NotNull Random rand, @NotNull BlockPos pos, IBlockState state) {
		if (state.getValue(STAGE) == 0) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		} else {
			generate(worldIn, pos, rand);
		}
	}

	@Override
	public @NotNull Features getExtraFeatures() {
		return Features.Builder.create()
				.setCustomItemModelLocation(JITL.rl("block/sapling/" + Objects.requireNonNull(getRegistryName()).getPath()))
				.build();
	}
}