package net.journey.blocks.base;

import net.journey.JITL;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class JBlockFluid extends BlockFluidClassic {

	public JBlockFluid(String name, Fluid fluid, Material material) {
		super(fluid, material);
		setRegistryName(JITL.MOD_ID, name);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
}
