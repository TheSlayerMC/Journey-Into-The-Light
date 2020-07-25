package net.journey.blocks.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class JFluid extends Fluid {

	public JFluid(String fluidName, ResourceLocation still, ResourceLocation flowing) {
		super(fluidName, still, flowing);
		setUnlocalizedName(fluidName);
	}
}
