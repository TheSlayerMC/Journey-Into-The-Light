package net.journey.integration.tconstruct;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;

public class TCMaterials {

	private static final Material CELESTIUM = addMaterial(EnumJTCMaterials.CELESTIUM, TCFluids.CELESTIUM, "ingotCelestium");

	public static void preInit(FMLPreInitializationEvent event) {
		initMaterials();
		initMaterialStats();
	}

	private static Material addMaterial(EnumJTCMaterials material, Fluid fluid, String ingot) {
		Material material1 = new Material(material.getName(), material.getColor());
		material1.addItemIngot(ingot);
		material1.setFluid(fluid);
		return material1;
	}

	private static void initMaterials() {
		TinkerRegistry.integrate(CELESTIUM, TCFluids.CELESTIUM, "Celestium");
	}

	private static void initMaterialStats() {
		TinkerRegistry.addMaterialStats(
				CELESTIUM,
				new HeadMaterialStats(1000, 7.0F, 3.75F, 4),
				new HandleMaterialStats(1.2f, 200),
				new ExtraMaterialStats(250),
				new BowMaterialStats(1.4F, 1.0F, 2.0F));
	}
}
