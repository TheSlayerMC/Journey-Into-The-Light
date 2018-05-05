package net.journey.util;

import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {

	public static Configuration cfg;

	public static void init(FMLPreInitializationEvent event) {
		cfg = new Configuration(new File(event.getModConfigurationDirectory() + "/JourneyIntoTheLight.cfg"));
		cfg.load();
		dimensionInit();
		miscInit();
		cfg.save();
	}

	public static boolean keepLoadingEuca, keepLoadingTerrania, keepLoadingDepths, keepLoadingBoil, keepLoadingFrozen, keepLoadingGolden, reRenderPlayerStats, spawnNetherBossesInNether, showDimensionChange, showDeathMessage, boilBlockSpawnSmoke;
	public static boolean keepLoadingCorba, keepLoadingWastelands, keepLoadingCloudia, keepLoadingSenterian, keepLoadingWither;
	public static boolean spawnSwordParticles, showEntityHealth;

	public static int euca, depths, boil, frozen, corba, wastelands, cloudia, terrania, golden, senterian, wither;
	public static int eucaBiome, depthsBiome, boilBiome, frozenBiome, corbaBiome, wastelandsBiome, cloudiaBiome, terraniaBiome, goldenBiome, senterianBiome, witherBiome;
	public static int witherSandsBiome, lushLandsBiome;

	public static int baseMobID, baseProjectileID, baseEntityListID, entityHealthDistance;

	public static void dimensionInit() {
		witherSandsBiome = cfg.get("Biome", "Withering Sands biome ID", 190).getInt();
		lushLandsBiome = cfg.get("Biome", "Lush Lands biome ID", 190).getInt();
		eucaBiome = cfg.get("Dimension", "Euca biome ID", 60).getInt();
		euca = cfg.get("Dimension", "Euca ID", 20).getInt();
		keepLoadingEuca = cfg.get("Dimension", "Keep loading Euca", true).getBoolean(true);
		depthsBiome = cfg.get("Dimension", "Depths biome ID", 61).getInt();
		depths = cfg.get("Dimension", "Depths ID", 21).getInt();
		keepLoadingDepths = cfg.get("Dimension", "Keep loading Depths", true).getBoolean(true);
		boilBiome = cfg.get("Dimension", "Boiling Point biome ID", 62).getInt();
		boil = cfg.get("Dimension", "Boiling Point ID", 22).getInt();
		frozenBiome = cfg.get("Dimension", "Frozen Lands biome ID", 63).getInt();
		frozen = cfg.get("Dimension", "Frozen Lands ID", 23).getInt();
		corba = cfg.get("Dimension", "Corba ID", 24).getInt();
		corbaBiome = cfg.get("Dimension", "Corba biome ID", 64).getInt();
		wastelands = cfg.get("Dimension", "Wastelands ID", 25).getInt();
		wastelandsBiome = cfg.get("Dimension", "Wastelands biome ID", 65).getInt();
		cloudia = cfg.get("Dimension", "Cloudia ID", 26).getInt();
		cloudiaBiome = cfg.get("Dimension", "Cloudia biome ID", 66).getInt();
		terrania = cfg.get("Dimension", "Terrania ID", 27).getInt();
		terraniaBiome = cfg.get("Dimension", "Terrania biome ID", 67).getInt();
		golden = cfg.get("Dimension", "Golden Grains ID", 28).getInt();
		goldenBiome = cfg.get("Dimension", "Golden Grains biome ID", 68).getInt();
		senterian = cfg.get("Dimension", "Senterian Labirynth ID", 29).getInt();
		senterianBiome = cfg.get("Dimension", "Senterian Labirynth biome ID", 69).getInt();
		wither = cfg.get("Dimension", "Withanian Lands ID", 30).getInt();
		witherBiome = cfg.get("Dimension", "Withanian Lands biome ID", 70).getInt();
		keepLoadingFrozen = cfg.get("Dimension", "Keep loading Frozen Lands", true).getBoolean(true);
		keepLoadingBoil = cfg.get("Dimension", "Keep loading Boiling Point", true).getBoolean(true);
		keepLoadingCorba = cfg.get("Dimension", "Keep loading Corba", true).getBoolean(true);
		keepLoadingWastelands = cfg.get("Dimension", "Keep loading Wastelands", true).getBoolean(true);
		keepLoadingCloudia = cfg.get("Dimension", "Keep loading Cloudia", true).getBoolean(true);
		keepLoadingTerrania = cfg.get("Dimension", "Keep loading Terrania", true).getBoolean(true);
		keepLoadingGolden = cfg.get("Dimension", "Keep loading Golden Grains", true).getBoolean(true);
		keepLoadingSenterian = cfg.get("Dimension", "Keep loading Senterian Labirynth", true).getBoolean(true);
		keepLoadingSenterian = cfg.get("Dimension", "Keep loading the Withanian Lands", true).getBoolean(true);
		boilBlockSpawnSmoke = cfg.get("Dimension", "Boiling Point blocks spawn smoke (More lag)", true).getBoolean(true);
		spawnSwordParticles = cfg.get("Items", "Swords spawn particles", true).getBoolean(true);
		//spawnNetherBossesInNether = cfg.get("Dimension", "Spawn the Nether Bosses (with the orbs) only in the Nether", true).getBoolean(true);
		showDimensionChange = cfg.get("Dimension", "Show the dimension change chat", true).getBoolean(true);
		reRenderPlayerStats = cfg.get("Gui", "Re-render the players stats in the top right corner", true).getBoolean(true);
		showDeathMessage = cfg.get("Gui", "Tell you where you died after death?", true).getBoolean(true);
		baseMobID = cfg.get("Entity", "The starting ID for the mobs (only gets greater the more mobs this mod has registered)", 350).getInt();
		baseProjectileID = cfg.get("Entity", "The starting ID for the projectiles (only gets greater the more projectiles this mod has registered)", 230).getInt();
		baseEntityListID = cfg.get("Entity", "The starting 'Entity List ID'", 2650).getInt();
		entityHealthDistance = cfg.get("Entity", "The distance the player can see the mobs health", 10).getInt();
		showEntityHealth = cfg.get("Entity", "Show the health bar above the entitys head?", true).getBoolean(true);
	}

	public static void miscInit() {

	}
}