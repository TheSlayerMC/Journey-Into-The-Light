package net.journey.blocks;

import net.journey.JourneyBlocks;
import net.journey.entity.mob.boss.EntityFourfa;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockPattern.PatternHelper;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.block.BlockMod;
import net.slayer.api.worldgen.WorldGenAPI;

public class BlockEucaPumpkin extends BlockMod {

	private BlockPattern fourfaBasePattern;
	private BlockPattern fourfaPattern;
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockEucaPumpkin(String name, String f) {
		super(EnumMaterialTypes.GOURD, name, f, 0.5F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setTickRandomly(true);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		this.spawn(worldIn, pos);
	}

	private void spawn(World worldIn, BlockPos pos) {
		spawnEntity(new EntityFourfa(worldIn), getFourfaPattern(), worldIn, pos);
	}

	public void spawnEntity(Entity entity, BlockPattern blockPattern, World worldIn, BlockPos pos) {
		PatternHelper patternhelper;
		int i;
		int j;
		if ((patternhelper = blockPattern.match(worldIn, pos)) != null) {
			for (i = 0; i < blockPattern.getThumbLength(); ++i) {
				BlockWorldState blockworldstate = patternhelper.translateOffset(0, i, 0);
				worldIn.setBlockState(blockworldstate.getPos(), Blocks.AIR.getDefaultState(), 2);
			}
			BlockPos blockpos2 = patternhelper.translateOffset(0, 2, 0).getPos();
			entity.setLocationAndAngles((double)blockpos2.getX() + 0.5D, (double)blockpos2.getY() + 0.05D, (double)blockpos2.getZ() + 0.5D, 0.0F, 0.0F);
			worldIn.spawnEntity(entity);
			
			if(entity instanceof EntityFourfa) WorldGenAPI.addRectangle(2, 2, 3, worldIn, pos.getX() - 1, pos.getY() - 2, pos.getZ() - 1, Blocks.AIR);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

	protected BlockPattern getFourfaPattern() {
		if(this.fourfaPattern == null)
			this.fourfaPattern = FactoryBlockPattern.start().aisle(new String[] {"^^", "OO", "##"}).where('O', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.celestiumOre))).where('^', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.eucaPumpkin))).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(JourneyBlocks.eucaStone))).build();
		return this.fourfaPattern;
	}
}