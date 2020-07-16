package net.journey.integration.tconstruct;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;

public class TCMaterials {

	private static final Material CELESTIUM = addMaterial(EnumJTCMaterials.CELESTIUM, TCFluids.CELESTIUM, "ingotCelestium");
	private static final Material MEKYUM = addMaterial(EnumJTCMaterials.MEKYUM, TCFluids.MEKYUM, "ingotMekyum");
	private static final Material STORON = addMaterial(EnumJTCMaterials.STORON, TCFluids.STORON, "ingotStoron");
	private static final Material KORITE = addMaterial(EnumJTCMaterials.KORITE, TCFluids.KORITE, "ingotKorite");


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
		TinkerRegistry.integrate(MEKYUM, TCFluids.MEKYUM, "Mekyum");
		TinkerRegistry.integrate(STORON, TCFluids.STORON, "Storon");
		TinkerRegistry.integrate(KORITE, TCFluids.KORITE, "Korite");
	}

	private static void initMaterialStats() {
		TinkerRegistry.addMaterialStats(
				CELESTIUM,
				new HeadMaterialStats(3000, 18.0F, 9.5F, 4),
				new HandleMaterialStats(1.2F, 200),
				new ExtraMaterialStats(250),
				new BowMaterialStats(1.2F, 1.0F, 2.0F));
		TinkerRegistry.addMaterialStats(
				MEKYUM,
				new HeadMaterialStats(3000, 18.0F, 9.5F, 4),
				new HandleMaterialStats(1.2F, 200),
				new ExtraMaterialStats(250),
				new BowMaterialStats(1.2F, 1.0F, 2.0F));
		TinkerRegistry.addMaterialStats(
				STORON,
				new HeadMaterialStats(3000, 18.0F, 9.5F, 4),
				new HandleMaterialStats(1.2F, 200),
				new ExtraMaterialStats(250),
				new BowMaterialStats(1.2F, 1.0F, 2.0F));
		TinkerRegistry.addMaterialStats(
				KORITE,
				new HeadMaterialStats(3000, 18.0F, 9.5F, 4),
				new HandleMaterialStats(1.2F, 200),
				new ExtraMaterialStats(250),
				new BowMaterialStats(1.2F, 1.0F, 2.0F));
	}
}
