package net.journey.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.journey.dimension.nether.JNWorldGenerator;
import net.journey.dimension.nether.biomes.BiomeRegister;
import net.journey.dimension.nether.biomes.NetherBiome;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {

	public static Configuration cfg;

	public static void init(FMLPreInitializationEvent event) {
		cfg = new Configuration(new File(event.getModConfigurationDirectory() + "/Journey.cfg"));
		cfg.load();
		dimensionInit();
		miscInit();
		cfg.save();
	}

	public static boolean keepLoadingEuca, keepLoadingTerrania, keepLoadingDepths, keepLoadingBoil, keepLoadingFrozen, boilBlockSpawnSmoke;
	public static boolean keepLoadingCorba, keepLoadingWastelands, keepLoadingCloudia, keepLoadingSenterian, keepLoadingWither;
	public static boolean showManaBar;
	public static boolean changeBackground, changeMainMenu;
	
	private static boolean[] registerBiomes;
	private static int indexBiome;
	private static int biomeSizeXZ;
	private static int biomeSizeY;
	private static boolean hasCleaningPass;
	
	public static boolean spawnSwordParticles, showEntityHealth;

	public static int eucaSilverBiome, eucaBiome;
	public static int euca, depths, boil, frozen, corba, wastelands, cloudia, terrania, senterian, wither;
	public static String depthsBiome, boilBiome, frozenBiome, corbaBiome, wastelandsBiome, cloudiaBiome, terraniaBiome, senterianBiome;

	public static int baseMobID, baseProjectileID, baseEntityListID, entityHealthDistance;
	
	public static int towerDungeon, mageHouse, blacksmithHouse;

	public static void dimensionInit() {
		eucaBiome = cfg.get("Dimension", "Euca biome ID", 60).getInt();
		eucaSilverBiome = cfg.get("Dimension", "Euca Silver biome ID", 70).getInt();
		euca = cfg.get("Dimension", "Euca ID", 20).getInt();
		keepLoadingEuca = cfg.get("Dimension", "Keep loading Euca", false).getBoolean(false);
		depthsBiome = cfg.get("Dimension", "Depths biome ID", 61).getString();
		depths = cfg.get("Dimension", "Depths ID", 21).getInt();
		keepLoadingDepths = cfg.get("Dimension", "Keep loading Depths", false).getBoolean(false);
		boilBiome = cfg.get("Dimension", "Boiling Point biome ID", 62).getString();
		boil = cfg.get("Dimension", "Boiling Point ID", 22).getInt();
		frozenBiome = cfg.get("Dimension", "Frozen Lands biome ID", 63).getString();
		frozen = cfg.get("Dimension", "Frozen Lands ID", 23).getInt();
		corba = cfg.get("Dimension", "Corba ID", 24).getInt();
		corbaBiome = cfg.get("Dimension", "Corba biome ID", 64).getString();
		wastelands = cfg.get("Dimension", "Wastelands ID", 25).getInt();
		wastelandsBiome = cfg.get("Dimension", "Wastelands biome ID", 65).getString();
		cloudia = cfg.get("Dimension", "Cloudia ID", 26).getInt();
		cloudiaBiome = cfg.get("Dimension", "Cloudia biome ID", 66).getString();
		terrania = cfg.get("Dimension", "Terrania ID", 27).getInt();
		terraniaBiome = cfg.get("Dimension", "Terrania biome ID", 67).getString();
		senterian = cfg.get("Dimension", "Senterian Labirynth ID", 29).getInt();
		senterianBiome = cfg.get("Dimension", "Senterian Labirynth biome ID", 69).getString();
		wither = cfg.get("Dimension", "Withanian Lands ID", 30).getInt();
		keepLoadingFrozen = cfg.get("Dimension", "Keep loading Frozen Lands", false).getBoolean(false);
		keepLoadingBoil = cfg.get("Dimension", "Keep loading Boiling Point", false).getBoolean(false);
		keepLoadingCorba = cfg.get("Dimension", "Keep loading Corba", false).getBoolean(false);
		keepLoadingWastelands = cfg.get("Dimension", "Keep loading Wastelands", false).getBoolean(false);
		keepLoadingCloudia = cfg.get("Dimension", "Keep loading Cloudia", false).getBoolean(false);
		keepLoadingTerrania = cfg.get("Dimension", "Keep loading Terrania", false).getBoolean(false);
		keepLoadingSenterian = cfg.get("Dimension", "Keep loading Senterian Labirynth", false).getBoolean(false);
		keepLoadingSenterian = cfg.get("Dimension", "Keep loading the Withanian Lands", false).getBoolean(false);
		boilBlockSpawnSmoke = cfg.get("Dimension", "Boiling Point blocks spawn smoke (More lag)", true).getBoolean(true);
		spawnSwordParticles = cfg.get("Items", "Swords spawn particles", true).getBoolean(true);
		baseMobID = cfg.get("Entity", "The starting ID for the mobs (only gets greater the more mobs this mod has registered)", 350).getInt();
		baseProjectileID = cfg.get("Entity", "The starting ID for the projectiles (only gets greater the more projectiles this mod has registered)", 230).getInt();
		baseEntityListID = cfg.get("Entity", "The starting 'Entity List ID'", 2650).getInt();
		entityHealthDistance = cfg.get("Entity", "The distance the player can see the mobs health", 10).getInt();
		showEntityHealth = cfg.get("Entity", "Show the health bar above the entitys head?", true).getBoolean(true);
		
		showManaBar = cfg.get("Gui", "Show Mana Bar?", true).getBoolean(true);
		changeBackground = cfg.get("Gui", "Change loading screen background?", true).getBoolean(true);
		changeMainMenu = cfg.get("Gui", "Use custom title screen?", true).getBoolean(true);
		
		towerDungeon = cfg.get("Generation", "Dungeon tower spawn rate (The higher, the rarer)", 16).getInt();
		mageHouse = cfg.get("Generation", "Mage House spawn rate (The higher, the rarer)", 40).getInt();
		blacksmithHouse = cfg.get("Generation", "Blacksmith House spawn rate (The higher, the rarer)", 40).getInt();

		
		List<Boolean> items= new ArrayList<Boolean>();
		biomeSizeXZ = cfg.getInt("BiomeSizeXZ", "Nether", 512, 1, 4096, "The horizontal Nether biome size");
		biomeSizeY = cfg.getInt("BiomeSizeY", "Nether", 32, 1, 4096, "The vertical Nether biome size");
		hasCleaningPass = cfg.getBoolean("SecondPass", "Nether", true, "Enable second pass for smooth Nether terrain?");
		for(Field f : BiomeRegister.class.getDeclaredFields())
			if(f.getType().isAssignableFrom(NetherBiome.class))
				items.add(cfg.getBoolean(f.getName().toLowerCase(), "Nether", true, "Enable Nether biomes?"));
		registerBiomes = new boolean[items.size()];
		for(int i = 0; i < items.size(); i++)
			registerBiomes[i] = items.get(i);
		items.clear();
		resetBiomeIndex();
	}
	
	public static void postBiomeInit() {
		// Plant density
		JNWorldGenerator.setDensity(
				cfg.getFloat("GlobalDensity", "Generator", 1, 0, 1, "Global plant density, multiplied on other"));
		for (NetherBiome biome : BiomeRegister.getBiomes()) {
			biome.setDensity(cfg.getFloat(biome.getName().replace(" ", "") + "Density", "Generator", 1, 0, 1,
					"Density for " + biome.getName() + " biome"));
		}
	}

	public static boolean mustInitBiome() {
		return registerBiomes[indexBiome++];
	}

	public static void resetBiomeIndex() {
		indexBiome = 0;
	}

	public static int getBiomeSizeXZ() {
		return biomeSizeXZ;
	}

	public static int getBiomeSizeY() {
		return biomeSizeY;
	}

	public static boolean hasCleaningPass() {
		return hasCleaningPass;
	}

	public static void miscInit() {}
}