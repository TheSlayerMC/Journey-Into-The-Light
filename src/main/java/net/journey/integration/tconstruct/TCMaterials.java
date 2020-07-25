package net.journey.integration.tconstruct;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;

public class TCMaterials {

	private static final Material LUNIUM = addMaterial(EnumJTCMaterials.LUNIUM, TCFluids.LUNIUM, "ingotLunium");
	private static final Material SHADIUM = addMaterial(EnumJTCMaterials.SHADIUM, TCFluids.SHADIUM, "ingotShadium");

	private static final Material BLOODCRUST = addMaterial(EnumJTCMaterials.BLOODCRUST, TCFluids.BLOODCRUST, "ingotHellstone");

	private static final Material CELESTIUM = addMaterial(EnumJTCMaterials.CELESTIUM, TCFluids.CELESTIUM, "ingotCelestium");
	private static final Material MEKYUM = addMaterial(EnumJTCMaterials.MEKYUM, TCFluids.MEKYUM, "ingotMekyum");
	private static final Material STORON = addMaterial(EnumJTCMaterials.STORON, TCFluids.STORON, "ingotStoron");
	private static final Material KORITE = addMaterial(EnumJTCMaterials.KORITE, TCFluids.KORITE, "ingotKorite");

	private static final Material FLAIRIUM = addMaterial(EnumJTCMaterials.FLAIRIUM, TCFluids.FLAIRIUM, "ingotFlairium");
	private static final Material DES = addMaterial(EnumJTCMaterials.DES, TCFluids.DES, "ingotDes");
	private static final Material ORBADITE = addMaterial(EnumJTCMaterials.ORBADITE, TCFluids.ORBADITE, "ingotOrbadite");

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
		TinkerRegistry.integrate(LUNIUM, TCFluids.LUNIUM, "Lunium");
		TinkerRegistry.integrate(SHADIUM, TCFluids.SHADIUM, "Shadium");

		TinkerRegistry.integrate(BLOODCRUST, TCFluids.BLOODCRUST, "Hellstone");

		TinkerRegistry.integrate(CELESTIUM, TCFluids.CELESTIUM, "Celestium");
		TinkerRegistry.integrate(MEKYUM, TCFluids.MEKYUM, "Mekyum");
		TinkerRegistry.integrate(STORON, TCFluids.STORON, "Storon");
		TinkerRegistry.integrate(KORITE, TCFluids.KORITE, "Korite");

		TinkerRegistry.integrate(FLAIRIUM, TCFluids.FLAIRIUM, "Flairium");
		TinkerRegistry.integrate(DES, TCFluids.DES, "Des");
		TinkerRegistry.integrate(ORBADITE, TCFluids.ORBADITE, "Orbadite");
	}

	private static void initMaterialStats() {
		TinkerRegistry.addMaterialStats(
				LUNIUM,
				new HeadMaterialStats(1521, 10.0F, 6.5F, 4),
				new HandleMaterialStats(1.0F, 200),
				new ExtraMaterialStats(50),
				new BowMaterialStats(0.5F, 1.3F, 1.0F));
		TinkerRegistry.addMaterialStats(
				SHADIUM,
				new HeadMaterialStats(1521, 10.0F, 6.5F, 4),
				new HandleMaterialStats(1.0F, 200),
				new ExtraMaterialStats(50),
				new BowMaterialStats(0.5F, 1.3F, 1.0F));
		TinkerRegistry.addMaterialStats(
				BLOODCRUST,
				new HeadMaterialStats(1432, 15.0F, 6.5F, 4),
				new HandleMaterialStats(1.0F, 200),
				new ExtraMaterialStats(150),
				new BowMaterialStats(0.7F, 1.0F, 2.0F));
		TinkerRegistry.addMaterialStats(
				CELESTIUM,
				new HeadMaterialStats(2017, 18.0F, 9.5F, 4),
				new HandleMaterialStats(1.2F, 200),
				new ExtraMaterialStats(250),
				new BowMaterialStats(1.2F, 1.0F, 3.0F));
		TinkerRegistry.addMaterialStats(
				MEKYUM,
				new HeadMaterialStats(2017, 18.0F, 9.5F, 4),
				new HandleMaterialStats(1.2F, 200),
				new ExtraMaterialStats(250),
				new BowMaterialStats(1.2F, 1.0F, 3.0F));
		TinkerRegistry.addMaterialStats(
				STORON,
				new HeadMaterialStats(2017, 18.0F, 9.5F, 4),
				new HandleMaterialStats(1.2F, 200),
				new ExtraMaterialStats(250),
				new BowMaterialStats(1.2F, 1.0F, 3.0F));
		TinkerRegistry.addMaterialStats(
				KORITE,
				new HeadMaterialStats(2017, 18.0F, 9.5F, 4),
				new HandleMaterialStats(1.2F, 200),
				new ExtraMaterialStats(250),
				new BowMaterialStats(1.2F, 1.0F, 3.0F));
		TinkerRegistry.addMaterialStats(
				FLAIRIUM,
				new HeadMaterialStats(2130, 21.0F, 10.0F, 4),
				new HandleMaterialStats(1.4F, 200),
				new ExtraMaterialStats(250),
				new BowMaterialStats(1.5F, 1.5F, 4.0F));
		TinkerRegistry.addMaterialStats(
				DES,
				new HeadMaterialStats(2130, 21.0F, 11.0F, 4),
				new HandleMaterialStats(1.6F, 250),
				new ExtraMaterialStats(275),
				new BowMaterialStats(1.7F, 1.6F, 4.5F));
		TinkerRegistry.addMaterialStats(
				ORBADITE,
				new HeadMaterialStats(2115, 20.0F, 13.5F, 5),
				new HandleMaterialStats(1.8F, 250),
				new ExtraMaterialStats(275),
				new BowMaterialStats(2.2F, 1.8F, 5.0F));
	}
}
