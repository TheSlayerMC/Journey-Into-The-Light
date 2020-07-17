package net.journey.integration.tconstruct;

import net.journey.JITL;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;

public class TCFluids {

	public static final Fluid LUNIUM = addMoltenFluid(EnumJTCMaterials.LUNIUM, 600);
	public static final Fluid SHADIUM = addMoltenFluid(EnumJTCMaterials.SHADIUM, 650);

	public static final Fluid BLOODCRUST = addMoltenFluid(EnumJTCMaterials.BLOODCRUST, 800);

	public static final Fluid CELESTIUM = addMoltenFluid(EnumJTCMaterials.CELESTIUM, 700);
	public static final Fluid MEKYUM = addMoltenFluid(EnumJTCMaterials.MEKYUM, 700);
	public static final Fluid STORON = addMoltenFluid(EnumJTCMaterials.STORON, 700);
	public static final Fluid KORITE = addMoltenFluid(EnumJTCMaterials.KORITE, 700);

	public static final Fluid FLAIRIUM = addMoltenFluid(EnumJTCMaterials.FLAIRIUM, 1000);
	public static final Fluid DES = addMoltenFluid(EnumJTCMaterials.DES, 800);
	public static final Fluid ORBADITE = addMoltenFluid(EnumJTCMaterials.ORBADITE, 1600);

	private static FluidMolten addMoltenFluid(EnumJTCMaterials material, int temperature) {
		FluidMolten fluidMolten = new FluidMolten(material.getName(), material.getColor());
		fluidMolten.setTemperature(temperature);
		fluidMolten.setUnlocalizedName(JITL.MOD_ID + "." + material.getName());

		FluidRegistry.registerFluid(fluidMolten);
		FluidRegistry.addBucketForFluid(fluidMolten);

		return fluidMolten;
	}
}
