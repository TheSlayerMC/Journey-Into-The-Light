package net.slayer.api.block;

import net.journey.JITL;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.slayer.api.EnumMaterialTypes;
import net.slayer.api.SlayerAPI;

public class BlockModLog extends BlockModPillar {

	public static final PropertyEnum<EnumAxis> LOG_AXIS = PropertyEnum.<EnumAxis>create("axis", EnumAxis.class);

	public BlockModLog(String name, String finalName) {
		super(name, finalName);
        setHardness(2.0F);
		setSoundType(SoundType.WOOD);
	}

	@Override
	public boolean isWood(IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		return this.getStateFromMeta(meta).withProperty(LOG_AXIS, EnumAxis.fromFacingAxis(facing.getAxis()));
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		switch (rot) {
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:
			switch ((EnumAxis) state.getValue(LOG_AXIS)) {
			case X:
				return state.withProperty(LOG_AXIS, EnumAxis.Z);
			case Z:
				return state.withProperty(LOG_AXIS, EnumAxis.X);
			default:
				return state;
			}
		default:
			return state;
		}
	}
	
	public static enum EnumAxis implements IStringSerializable {
		X("x"), Y("y"), Z("z"), NONE("none");

		private final String name;

		private EnumAxis(String name) {
			this.name = name;
		}

		public String toString() {
			return this.name;
		}

		public static EnumAxis fromFacingAxis(EnumFacing.Axis axis) {
			switch (axis) {
			case X:
				return X;
			case Y:
				return Y;
			case Z:
				return Z;
			default:
				return NONE;
			}
		}

		public String getName() {
			return this.name;
		}
	}
	
	public void registerItemModel(Item itemBlock) {
		JITL.proxy.registerItemRenderer(itemBlock, 0, name);
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}

	public Block addName(String name) {
		JourneyBlocks.blockName.add(SlayerAPI.PREFIX + name);
		return this;
	}
}