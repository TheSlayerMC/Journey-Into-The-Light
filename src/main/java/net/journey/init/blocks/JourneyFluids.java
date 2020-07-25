package net.journey.init.blocks;

import net.journey.JITL;
import net.journey.blocks.fluid.JFluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class JourneyFluids {

	public static final Fluid FLUID_ACID = new JFluid(
			"senterian_acid",
			new ResourceLocation(JITL.MOD_ID, "blocks/senterian_acid"),
			new ResourceLocation(JITL.MOD_ID, "blocks/senterian_acid")
	).setTemperature(1000).setLuminosity(1).setViscosity(100);

	public static void init() {
		addFluid(FLUID_ACID);
	}

	private static void addFluid(Fluid fluid) {
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);
	}
}
