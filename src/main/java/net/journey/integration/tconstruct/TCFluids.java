package net.journey.integration.tconstruct;

import net.journey.JITL;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;

public class TCFluids {

	public static final Fluid CELESTIUM = addMoltenFluid(EnumJTCMaterials.CELESTIUM, 600);
	public static final Fluid MEKYUM = addMoltenFluid(EnumJTCMaterials.MEKYUM, 600);
	public static final Fluid STORON = addMoltenFluid(EnumJTCMaterials.STORON, 600);
	public static final Fluid KORITE = addMoltenFluid(EnumJTCMaterials.KORITE, 600);

	private static FluidMolten addMoltenFluid(EnumJTCMaterials material, int temperature) {
		FluidMolten fluidMolten = new FluidMolten(material.getName(), material.getColor());
		fluidMolten.setTemperature(temperature);
		fluidMolten.setUnlocalizedName(JITL.MOD_ID + "." + material.getName());

		FluidRegistry.registerFluid(fluidMolten);
		FluidRegistry.addBucketForFluid(fluidMolten);

		return fluidMolten;
	}
}
