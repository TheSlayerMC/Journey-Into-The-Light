package net.journey.integration.tconstruct;

import net.journey.JITL;
import net.minecraftforge.fluids.FluidRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;

public class TCFluids {

	public static final FluidMolten CELESTIUM = addMoltenFluid(EnumJTCMaterials.CELESTIUM, 400);

	private static FluidMolten addMoltenFluid(EnumJTCMaterials material, int temperature) {
		FluidMolten fluidMolten = new FluidMolten(material.getName(), material.getColor());
		fluidMolten.setTemperature(temperature);
		fluidMolten.setUnlocalizedName(JITL.MOD_NAME + "." + material.getName());

		FluidRegistry.registerFluid(fluidMolten);
		FluidRegistry.addBucketForFluid(fluidMolten);
		return fluidMolten;
	}
}
