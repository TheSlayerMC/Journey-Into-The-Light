package net.journey.util;

import net.journey.dimension.nether.JNWorldGenerator;
import net.journey.dimension.nether.biomes.BiomeRegister;
import net.journey.dimension.nether.biomes.NetherBiome;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Config {

    public static Configuration cfg;
    public static boolean keepLoadingEuca, keepLoadingTerrania, keepLoadingDepths, keepLoadingBoil, keepLoadingFrozen, boilBlockSpawnSmoke;
    public static boolean keepLoadingCorba, keepLoadingWastelands, keepLoadingCloudia, keepLoadingSenterian, keepLoadingWither;
    public static boolean showManaBar;
    public static boolean changeMainMenu;
    public static boolean spawnSwordParticles, showEntityHealth;
    public static int eucaSilverBiome, eucaBiome;
    public static int euca, depths, boil, frozen, corba, wastelands, cloudia, terrania, senterian, wither;
    public static String depthsBiome, boilBiome, frozenBiome, corbaBiome, wastelandsBiome, cloudiaBiome, terraniaBiome, senterianBiome;
    public static int baseMobID, baseProjectileID, baseEntityListID, entityHealthDistance;
    public static int towerDungeon, mageHouse, blacksmithHouse, rockiteDungeon;
    private static boolean[] registerBiomes;
    private static int indexBiome;
    private static int biomeSizeXZ;
    private static int biomeSizeY;
    public static int maxHealthNormal, maxHealthSentry;
    private static boolean hasCleaningPass;
    
    public static int shadiumOreTrys, shadiumOreGenAmount, shadiumOreGenMaxY;
    public static int luniumOreTrys, luniumOreGenAmount, luniumOreGenMaxY;
    public static int sapphireOreTrys, sapphireOreGenAmount, sapphireOreGenMaxY;
    public static int iridiumOreTrys, irdiumOreGenAmount, iridiumOreGenMaxY;
    
    public static int blaziumOreTrys, blaziumOreGenAmount;
    public static int ashualOreTrys, ashualOreGenAmount;

    
    public static int flairiumOreTrys, flairiumOreGenAmount;
    public static int desOreTrys, desOreGenAmount;
    public static int gorbiteOreTrys, gorbiteOreGenAmount;
    public static int orbaditeOreTrys, orbaditeOreGenAmount;
    public static int celestiumOreTrys, celestiumOreGenAmount;
    public static int storonOreTrys, storonOreGenAmount;
    public static int koriteOreTrys, koriteOreGenAmount;
    public static int mekyumOreTrys, mekyumOreGenAmount;
    public static int luniteOreTrys, luniteOreGenAmount;

    public static void init(FMLPreInitializationEvent event) {
        cfg = new Configuration(new File(event.getModConfigurationDirectory() + "/Journey.cfg"));
        cfg.load();
        dimensionInit();
        miscInit();
        cfg.save();
    }

    public static void dimensionInit() {
        maxHealthNormal = cfg.get("Maximum Health", "Max health achievable with normal battle hearts:", 60).getInt();
        maxHealthSentry = cfg.get("Maximum Health", "Max health achievable with Sentry hearts:", 70).getInt();
        
        eucaBiome = cfg.get("Dimension", "Euca biome ID", 40).getInt();
        eucaSilverBiome = cfg.get("Dimension", "Euca Silver biome ID", 60).getInt();
        euca = cfg.get("Dimension", "Euca ID", 20).getInt();
        keepLoadingEuca = cfg.get("Dimension", "Keep loading Euca", false).getBoolean();
        depthsBiome = cfg.get("Dimension", "Depths biome ID", 61).getString();
        depths = cfg.get("Dimension", "Depths ID", 21).getInt();
        keepLoadingDepths = cfg.get("Dimension", "Keep loading Depths", false).getBoolean();
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
        keepLoadingFrozen = cfg.get("Dimension", "Keep loading Frozen Lands", false).getBoolean();
        keepLoadingBoil = cfg.get("Dimension", "Keep loading Boiling Point", false).getBoolean();
        keepLoadingCorba = cfg.get("Dimension", "Keep loading Corba", false).getBoolean();
        keepLoadingWastelands = cfg.get("Dimension", "Keep loading Wastelands", false).getBoolean();
        keepLoadingCloudia = cfg.get("Dimension", "Keep loading Cloudia", false).getBoolean();
        keepLoadingTerrania = cfg.get("Dimension", "Keep loading Terrania", false).getBoolean();
        keepLoadingSenterian = cfg.get("Dimension", "Keep loading Senterian Labirynth", false).getBoolean();
        keepLoadingSenterian = cfg.get("Dimension", "Keep loading the Withanian Lands", false).getBoolean();
        boilBlockSpawnSmoke = cfg.get("Dimension", "Boiling Point blocks spawn smoke (More lag)", true).getBoolean();
        spawnSwordParticles = cfg.get("Items", "Swords spawn particles", true).getBoolean();
        baseMobID = cfg.get("Entity", "The starting ID for the mobs (only gets greater the more mobs this mod has registered)", 350).getInt();
        baseProjectileID = cfg.get("Entity", "The starting ID for the projectiles (only gets greater the more projectiles this mod has registered)", 230).getInt();
        baseEntityListID = cfg.get("Entity", "The starting 'Entity List ID'", 2650).getInt();
        entityHealthDistance = cfg.get("Entity", "The distance the player can see the mobs health", 10).getInt();
        showEntityHealth = cfg.get("Entity", "Show the health bar above the entitys head?", true).getBoolean();

        showManaBar = cfg.get("Gui", "Show Mana Bar?", true).getBoolean();
        changeMainMenu = cfg.get("Gui", "Use custom title screen?", true).getBoolean();

        towerDungeon = cfg.get("Generation", "Dungeon tower spawn rate (The higher, the rarer)", 16).getInt();
        rockiteDungeon = cfg.get("Generation", "Rockit dungeon spawn rate (The higher, the rarer)", 64).getInt();
        mageHouse = cfg.get("Generation", "Mage House spawn rate (The higher, the rarer)", 40).getInt();
        blacksmithHouse = cfg.get("Generation", "Blacksmith House spawn rate (The higher, the rarer)", 40).getInt();

        shadiumOreTrys = cfg.get("Ore Generation", "Shadium Ore Trys Per Chunk", 2).getInt();
        shadiumOreGenAmount = cfg.get("Ore Generation", "Shadium Ore Vein Max Amount", 6).getInt();
        shadiumOreGenMaxY = cfg.get("Ore Generation", "Shadium Spawn Height", 13).getInt();
        
        luniumOreTrys = cfg.get("Ore Generation", "Lunium Ore Trys Per Chunk", 2).getInt();
        luniumOreGenAmount = cfg.get("Ore Generation", "Lunium Ore Vein Max Amount", 7).getInt();
        luniumOreGenMaxY = cfg.get("Ore Generation", "Lunium Spawn Height", 26).getInt();
        
        sapphireOreTrys = cfg.get("Ore Generation", "Sapphire Ore Trys Per Chunk", 2).getInt();
        sapphireOreGenAmount = cfg.get("Ore Generation", "Sapphire Ore Vein Max Amount", 5).getInt();
        sapphireOreGenMaxY = cfg.get("Ore Generation", "Sapphire Spawn Height", 24).getInt();
        
        iridiumOreTrys = cfg.get("Ore Generation", "Iridium Ore Trys Per Chunk", 2).getInt();
        irdiumOreGenAmount = cfg.get("Ore Generation", "Iridium Ore Vein Max Amount", 4).getInt();
        iridiumOreGenMaxY = cfg.get("Ore Generation", "Iridium Spawn Height", 16).getInt();
        
        ashualOreTrys = cfg.get("Ore Generation", "Ashual Ore Trys Per Chunk", 10).getInt();
        ashualOreGenAmount = cfg.get("Ore Generation", "Ashual Ore Vein Max Amount", 7).getInt();
        
        blaziumOreTrys = cfg.get("Ore Generation", "Blazium Ore Trys Per Chunk", 10).getInt();
        blaziumOreGenAmount = cfg.get("Ore Generation", "Blazium Ore Vein Max Amount", 7).getInt();
        
        flairiumOreTrys = cfg.get("Ore Generation", "Flairium Ore Trys Per Chunk", 25).getInt();
        flairiumOreGenAmount = cfg.get("Ore Generation", "Flairium Ore Vein Max Amount", 8).getInt();
        
        desOreTrys = cfg.get("Ore Generation", "Des Ore Trys Per Chunk", 25).getInt();
        desOreGenAmount = cfg.get("Ore Generation", "Des Ore Vein Max Amount", 8).getInt();
        
        gorbiteOreTrys = cfg.get("Ore Generation", "Gorbite Ore Trys Per Chunk", 10).getInt();
        gorbiteOreGenAmount = cfg.get("Ore Generation", "Gorbite Ore Vein Max Amount", 6).getInt();
        
        orbaditeOreTrys = cfg.get("Ore Generation", "Orbadite Ore Trys Per Chunk", 10).getInt();
        orbaditeOreGenAmount = cfg.get("Ore Generation", "Orbadite Ore Vein Max Amount", 6).getInt();
        
        celestiumOreTrys = cfg.get("Ore Generation", "Celestium Ore Trys Per Chunk", 30).getInt();
        celestiumOreGenAmount = cfg.get("Ore Generation", "Celestium Ore Vein Max Amount", 10).getInt();
        
        storonOreTrys = cfg.get("Ore Generation", "Storon Ore Trys Per Chunk", 30).getInt();
        storonOreGenAmount = cfg.get("Ore Generation", "Storon Ore Vein Max Amount", 6).getInt();
        
        koriteOreTrys = cfg.get("Ore Generation", "Korite Ore Trys Per Chunk", 30).getInt();
        koriteOreGenAmount = cfg.get("Ore Generation", "Korite Ore Vein Max Amount", 10).getInt();
        
        mekyumOreTrys = cfg.get("Ore Generation", "Mekyum Ore Trys Per Chunk", 30).getInt();
        mekyumOreGenAmount = cfg.get("Ore Generation", "Mekyum Ore Vein Max Amount", 10).getInt();
        
        luniteOreTrys = cfg.get("Ore Generation", "Lunite Ore Trys Per Chunk", 30).getInt();
        luniteOreGenAmount = cfg.get("Ore Generation", "Lunite Ore Vein Max Amount", 40).getInt();
        
        List<Boolean> items = new ArrayList<Boolean>();
        biomeSizeXZ = cfg.getInt("BiomeSizeXZ", "Nether", 512, 1, 4096, "The horizontal Nether biome size");
        biomeSizeY = cfg.getInt("BiomeSizeY", "Nether", 32, 1, 4096, "The vertical Nether biome size");
        hasCleaningPass = cfg.getBoolean("SecondPass", "Nether", false, "Enable second pass for smooth Nether terrain?");
        for (Field f : BiomeRegister.class.getDeclaredFields())
            if (f.getType().isAssignableFrom(NetherBiome.class))
                items.add(cfg.getBoolean(f.getName().toLowerCase(), "Nether", true, "Enable Nether biomes?"));
        registerBiomes = new boolean[items.size()];
        for (int i = 0; i < items.size(); i++)
            registerBiomes[i] = items.get(i);
        items.clear();
        resetBiomeIndex();
    }

    public static void postBiomeInit() {
        // Plant density
        JNWorldGenerator.setDensity(
                cfg.getFloat("GlobalDensity", "Generator", 0.5F, 0, 0.5F, "Global plant density, multiplied on other"));
        for (NetherBiome biome : BiomeRegister.getBiomes()) {
            biome.setDensity(cfg.getFloat(biome.getName().replace(" ", "") + "Density", "Generator", 0.5F, 0, 0.5F,
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

    public static void miscInit() {
    }
}