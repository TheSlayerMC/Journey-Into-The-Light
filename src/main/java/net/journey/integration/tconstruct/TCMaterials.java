package net.journey.integration.tconstruct;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;

public class TCMaterials {

	private static final Material CELESTIUM = addMaterial(EnumJTCMaterials.CELESTIUM, TCFluids.CELESTIUM, "ingotCelestium");

	public static void preInit(FMLPreInitializationEvent event) {
		TinkerRegistry.integrate(CELESTIUM, TCFluids.CELESTIUM, "Celestium");
	}

	private static Material addMaterial(EnumJTCMaterials material, Fluid fluid, String ingot) {
		Material material1 = new Material(material.getName(), material.getColor());
		material1.addItemIngot(ingot);
		material1.setFluid(fluid);
		return material1;
	}
}
