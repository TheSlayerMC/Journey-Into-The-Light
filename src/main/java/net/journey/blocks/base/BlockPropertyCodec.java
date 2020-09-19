package net.journey.blocks.base;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public class BlockPropertyCodec {
	/**
	 * Encodes state with single direction and boolean property.
	 * You shouldn't do any overwriting operation with returned value except for its saving.
	 */
	public static int encode(IBlockState state, PropertyDirection directionProp, PropertyBool boolProp) {
		EnumFacing facing = state.getValue(directionProp);
		boolean boolVal = state.getValue(boolProp);

		int meta = facing.getIndex();
		meta = meta << 1;

		meta = meta | (boolVal ? 1 : 0);
		return meta;
	}

	/**
	 * Decodes state with single direction and boolean property, which is encoded via {@link #encode(IBlockState, PropertyDirection, PropertyBool)}
	 * You shouldn't do any overwriting operation before providing {@code encodedVal}, just pass the value, that was come from world storage.
	 */
	public static IBlockState decode(int encodedVal, IBlockState state, PropertyDirection directionProp, PropertyBool boolProp) {
		EnumFacing facing = EnumFacing.byIndex(encodedVal >> 1 & 0b111);
		boolean boolVal = (encodedVal & 1) == 0;

		return state.withProperty(directionProp, facing).withProperty(boolProp, boolVal);
	}
}
