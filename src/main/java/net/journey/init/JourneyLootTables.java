package net.journey.init;

import net.journey.JITL;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class JourneyLootTables {

	public static final ResourceLocation TEST_CHEST = register("chests/test");

	public static final ResourceLocation BOIL_CHEST = register("chests/boilportal");
	public static final ResourceLocation NETHER_DUNGEON_CHEST = register("chests/netherdungeon");
	public static final ResourceLocation EUCA_SMITH_CHEST = register("chests/eucasmith");
	public static final ResourceLocation FROZEN_MERCH_CHEST = register("chests/frozenmerchant");

	public static final ResourceLocation LOOT_BASIC = register("loot/loot_basic");
	public static final ResourceLocation LOOT_GOLD = register("loot/loot_gold");
	public static final ResourceLocation LOOT_DIAMOND = register("loot/loot_diamond");
	public static final ResourceLocation LOOT_OVERGROWN = register("loot/loot_overgrown");
	public static final ResourceLocation LOOT_SEEDS = register("loot/seeds");
	public static final ResourceLocation LOOT_PRESENT = register("loot/loot_present");

	public static final ResourceLocation DEPTHS_SHRINE_CHEST = register("chests/depthsshrine");
	public static final ResourceLocation TERRANIA_TREE_HUT_CHEST = register("chests/terraniatreehut");
	public static final ResourceLocation ROCKITE_CHEST_LOOT = register("chests/overworld/rockite_loot");
	public static final ResourceLocation ANCIENT_CHEST_LOOT = register("chests/overworld/ancient_loot");
	public static final ResourceLocation BOIL_DUNGEON_CHEST = register("chests/boil/boil_dungeon");
	public static final ResourceLocation SENTRY_BASE_CHEST_LOOT = register("chests/senterian/sentry_dungeon");
	public static final ResourceLocation ICE_GOLEM_LOOT = register("chests/frozen/ice_golem_loot");
	public static final ResourceLocation GOLDITE_DUNGEON_LOOT = register("chests/euca/goldite_dungeon_loot");

	//Boss
	public static final ResourceLocation NETHER_BEAST = register("mobs/boss/nether_beast");
	public static final ResourceLocation BLAZIER = register("mobs/boss/blazier");
	public static final ResourceLocation CALCIA = register("mobs/boss/calcia");
	public static final ResourceLocation CORALLATOR = register("mobs/boss/corallator");
	public static final ResourceLocation EUDOR = register("mobs/boss/eudor");
	public static final ResourceLocation LOGGER = register("mobs/boss/logger");
	public static final ResourceLocation SCALE = register("mobs/boss/scale");
	public static final ResourceLocation SENTRY_KING = register("mobs/boss/sentry_king");
	public static final ResourceLocation SKY_STALKER = register("mobs/boss/sky_stalker");
	public static final ResourceLocation SOUL_WATCHER = register("mobs/boss/soul_watcher");
	public static final ResourceLocation TEMPLE_GUARDIAN = register("mobs/boss/temple_guardian");
	public static final ResourceLocation TERRANIAN_PROTECTOR = register("mobs/boss/terranian_protector");
	public static final ResourceLocation THUNDERBIRD = register("mobs/boss/thunderbird");
	public static final ResourceLocation WITHERING_BEAST = register("mobs/boss/withering_beast");


	//Overworld
	public static final ResourceLocation BIG_HONGO = register("mobs/overworld/big_hongo");
	public static final ResourceLocation BOOM_BOOM = register("mobs/overworld/boom_boom");
	public static final ResourceLocation DUNEWORM = register("mobs/overworld/duneworm");
	public static final ResourceLocation FIRE_MAGE = register("mobs/overworld/fire_mage");
	public static final ResourceLocation FLORO = register("mobs/overworld/floro");
	public static final ResourceLocation ICE_MAGE = register("mobs/overworld/ice_mage");
	public static final ResourceLocation MEDIUM_HONGO = register("mobs/overworld/medium_hongo");
    public static final ResourceLocation ROBOT = register("mobs/overworld/robot");
    public static final ResourceLocation SAND_CRAWLER = register("mobs/overworld/sand_crawler");
    public static final ResourceLocation SMALL_HONGO = register("mobs/overworld/small_hongo");
    public static final ResourceLocation SPECTRE = register("mobs/overworld/spectre");
    public static final ResourceLocation SPYCLOPSE = register("mobs/overworld/spyclopse");
    public static final ResourceLocation SWAMP_FLY = register("mobs/overworld/swamp_fly");
    public static final ResourceLocation TURDUCKEN = register("mobs/overworld/turducken");
    public static final ResourceLocation WRAITH = register("mobs/overworld/wraith");
    public static final ResourceLocation BLIZZARD = register("mobs/overworld/blizzard");
    public static final ResourceLocation JUNGLE_GOLEM = register("mobs/overworld/jungle_golem");
    public static final ResourceLocation JUNGLE_SPIDER = register("mobs/overworld/jungle_spider");
    public static final ResourceLocation JUNGLE_TURTLE = register("mobs/overworld/jungle_turtle");
    public static final ResourceLocation BLUE_HONGLOW = register("mobs/overworld/blue_honglow");
    public static final ResourceLocation CAVELING = register("mobs/overworld/caveling");
    public static final ResourceLocation CAVE_MAGE = register("mobs/overworld/cave_mage");
    public static final ResourceLocation CAVURN = register("mobs/overworld/cavurn");
    public static final ResourceLocation GREEN_HONGLOW = register("mobs/overworld/green_honglow");
    public static final ResourceLocation RED_HONGLOW = register("mobs/overworld/red_honglow");
    public static final ResourceLocation ROCKITE = register("mobs/overworld/rockite");
    public static final ResourceLocation STONEWALKER = register("mobs/overworld/stonewalker");

    //nether
    public static final ResourceLocation HELLBOT = register("mobs/nether/hellbot");
    public static final ResourceLocation HELL_COW = register("mobs/nether/hell_cow");
    public static final ResourceLocation HELL_TURTLE = register("mobs/nether/hell_turtle");
    public static final ResourceLocation INFERNO_BLAZE = register("mobs/nether/inferno_blaze");
    public static final ResourceLocation LAVA_SNAKE = register("mobs/nether/lava_snake");
    public static final ResourceLocation MINI_GHAST = register("mobs/nether/mini_ghast");
    public static final ResourceLocation REAPER = register("mobs/nether/reaper");
    public static final ResourceLocation WITHERSPINE = register("mobs/nether/witherspine");

    //End
    public static final ResourceLocation ENDER_CRAWLER = register("mobs/end/ender_crawler");
    public static final ResourceLocation ENDER_LEAPER = register("mobs/end/ender_leaper");

    
    //Boil
    public static final ResourceLocation BURNING_LIGHT = register("mobs/boil/burning_light");
    public static final ResourceLocation EXPOSED_FLAME = register("mobs/boil/exposed_flame");
    public static final ResourceLocation FLAME_LOTUS = register("mobs/boil/flame_lotus");
    public static final ResourceLocation FRIGHTENER = register("mobs/boil/frightener");
    public static final ResourceLocation HELLWING = register("mobs/boil/hellwing");
    public static final ResourceLocation MAGMA_BLAZE = register("mobs/boil/magma_blaze");
    public static final ResourceLocation MAGMA_GIANT = register("mobs/boil/magma_giant");
    public static final ResourceLocation OBSERVER = register("mobs/boil/observer");
    public static final ResourceLocation SCREAMER = register("mobs/boil/screamer");

    //Buca
    public static final ResourceLocation DYNASTER = register("mobs/euca/dynaster");
    public static final ResourceLocation EUCA_CHARGER = register("mobs/euca/euca_charger");
    public static final ResourceLocation EUCA_FIGHTER = register("mobs/euca/euca_fighter");
    public static final ResourceLocation GOLDBOT = register("mobs/euca/goldbot");
    public static final ResourceLocation GOLDER = register("mobs/euca/golder");
    public static final ResourceLocation GOLDITE_MAGE = register("mobs/euca/goldite_mage");
    public static final ResourceLocation INSECTO = register("mobs/euca/insecto");
    public static final ResourceLocation PSYOLLUM = register("mobs/euca/psyollum");
    public static final ResourceLocation SHIMMERER = register("mobs/euca/shimmerer");
    public static final ResourceLocation SILVERBOT = register("mobs/euca/silverbot");

    //Frozen
    public static final ResourceLocation CRYSTAL_CLUSTER = register("mobs/frozen/crystal_cluster");
    public static final ResourceLocation FROZEN_FROSTBITER = register("mobs/frozen/frozen_frostbiter");
    public static final ResourceLocation FROZEN_TROLL = register("mobs/frozen/frozen_troll");
    public static final ResourceLocation ICE_GOLEM = register("mobs/frozen/ice_golem");
    public static final ResourceLocation ICEMAN = register("mobs/frozen/iceman");
    public static final ResourceLocation PERMAFRAUST = register("mobs/frozen/permafraust");
    public static final ResourceLocation SHATTERER = register("mobs/frozen/shatterer");
    public static final ResourceLocation SHIVERING_BUSHWALKER = register("mobs/frozen/shivering_bushwalker");
    public static final ResourceLocation SHIVERING_SHRIEKER = register("mobs/frozen/shivering_shrieker");

    //Depths
    public static final ResourceLocation DARKENER = register("mobs/depths/darkener");
    public static final ResourceLocation DARK_FISH = register("mobs/depths/dark_fish");
    public static final ResourceLocation DARKNESS_CRAWLER = register("mobs/depths/darkness_crawler");
    public static final ResourceLocation DARK_SORCERER = register("mobs/depths/dark_sorcerer");
    public static final ResourceLocation DEPTHS_BEAST = register("mobs/depths/depths_beast");
    public static final ResourceLocation DEPTHS_HUNTER = register("mobs/depths/depths_hunter");
    public static final ResourceLocation LIGHTENER = register("mobs/depths/lightener");
    public static final ResourceLocation SPIKED_BEAST = register("mobs/depths/spiked_beast");

    //Corba
    public static final ResourceLocation LEAF_BLOWER = register("mobs/corba/leaf_blower");
    public static final ResourceLocation NATURE_MAGE = register("mobs/corba/nature_mage");
    public static final ResourceLocation OVERSEER = register("mobs/corba/overseer");
    public static final ResourceLocation OVERSEER_ELDER = register("mobs/corba/overseer_elder");
    public static final ResourceLocation SURFACE_SEER = register("mobs/corba/surface_seer");
    public static final ResourceLocation TREE_GOLEM = register("mobs/corba/tree_golem");
    public static final ResourceLocation WOOD_CREATURE = register("mobs/corba/wood_creature");

    //Terrania
    public static final ResourceLocation ARANA_KING = register("mobs/terrania/arana_king");
    public static final ResourceLocation PURPLIAN = register("mobs/terrania/purplian");
    public static final ResourceLocation TERRAGROW = register("mobs/terrania/terragrow");
    public static final ResourceLocation TERRALIGHT = register("mobs/terrania/terralight");
    public static final ResourceLocation TERRA_SCATTERER = register("mobs/terrania/terra_scatterer");
    public static final ResourceLocation TERRASHROOM = register("mobs/terrania/terrashroom");
    public static final ResourceLocation TERRA_SLUG = register("mobs/terrania/terra_slug");

    //Cloudia
    public static final ResourceLocation CLOUD_FLOWER = register("mobs/cloudia/cloud_flower");
    public static final ResourceLocation CLOUD_GHOST = register("mobs/cloudia/cloud_ghost");
    public static final ResourceLocation SKY_EEL = register("mobs/cloudia/sky_eel");
    public static final ResourceLocation STARLIGHT_GOLEM = register("mobs/cloudia/starlight_golem");
    public static final ResourceLocation STARLIGHT_TRANSPORTER = register("mobs/cloudia/starlight_transporter");
    public static final ResourceLocation STARLIGHT_WALKER = register("mobs/cloudia/starlight_walker");
    
    //Senterian
    public static final ResourceLocation MINI_SENTRY_LORD = register("mobs/senterian/mini_sentry_lord");
    public static final ResourceLocation MINI_SENTRY_STALKER = register("mobs/senterian/mini_sentry_stalker");
    public static final ResourceLocation MINI_SENTRY_WALKER = register("mobs/senterian/mini_sentry_walker");
    public static final ResourceLocation SENTRY_LORD = register("mobs/senterian/sentry_lord");
    public static final ResourceLocation SENTRY_STALKER = register("mobs/senterian/sentry_stalker");
    public static final ResourceLocation SENTRY_WALKER = register("mobs/senterian/sentry_walker");
    public static final ResourceLocation SENTRY_BLOCK = register("mobs/senterian/sentry_block");

    public static final ResourceLocation VANILLA_SIMPLE_DUNGEON = LootTableList.CHESTS_SIMPLE_DUNGEON;

    private static ResourceLocation register(String path) {
	    return LootTableList.register(new ResourceLocation(JITL.MOD_ID, path));
	}
}