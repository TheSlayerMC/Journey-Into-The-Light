package net.journey.util;

import net.minecraft.util.ResourceLocation;
import net.slayer.api.SlayerAPI;

public class Textures {
	
	public static final String prefix = SlayerAPI.PREFIX + "textures/models/mobs/";
    public static final String projectilePrefix = SlayerAPI.PREFIX + "textures/projectile/";
    public static final String modelPrefix = SlayerAPI.PREFIX + "textures/models/";
    public static final String slotPrefix = SlayerAPI.PREFIX + "textures/gui/slot";
    public static final String dragonBase = "textures/entities/dragon/";

    //End
    public static final ResourceLocation enderLeaper = addMob("enderLeaper");
    public static final ResourceLocation enderCrawler = addMob("enderCrawler");
    
    //Misc.
    public static final ResourceLocation basic = addProjectile("gray");
    public static final ResourceLocation bouncingProjectile = addProjectile("bouncing");
    public static final ResourceLocation magmaBall = addProjectile("magmaBall");
    public static final ResourceLocation bubble = addProjectile("bubble");
    public static final ResourceLocation grindstone = addModel("blocks/grindstone");
    public static final ResourceLocation journeyChest = addModel("blocks/journeychest");
    public static final ResourceLocation eucaChest = addModel ("blocks/eucachest");
    public static final ResourceLocation empty = addProjectile("empty");
    public static final ResourceLocation templeBall = addProjectile("templeBall");
    public static final ResourceLocation essenceArrow = addProjectile("essenceArrow");
    public static final ResourceLocation rockChunk = new ResourceLocation(SlayerAPI.PREFIX + "textures/items/rockChunk.png");
    public static final ResourceLocation plasma = new ResourceLocation(SlayerAPI.PREFIX + "textures/items/plasmaBall.png");

    //NPC
    public static final ResourceLocation mage = addMob("mage");
    public static final ResourceLocation blacksmith = addMob("blacksmith");
    public static final ResourceLocation boilTrader = addMob("boilTrader");
    public static final ResourceLocation alloyMender = addMob("alloyMender");
    public static final ResourceLocation frozenMerchant = addMob("frozenMerchant");
    public static final ResourceLocation escapedConvict = addMob("escapedConvict");
    public static final ResourceLocation blueTordo = addMob("blueTordo");
    public static final ResourceLocation redTordo = addMob("redTordo");
    public static final ResourceLocation greenTordo = addMob("greenTordo");
    public static final ResourceLocation staringGuardian = addMob("staringGuardian");
	public static final ResourceLocation terranianEnchanter = addMob("terranianTrader_enchanter");
	public static final ResourceLocation terranianTrader = addMob("terranianTrader");
	public static final ResourceLocation overgrownMerchant = addMob("overgrownMerchant");
	public static final ResourceLocation rockiteGolem = addMob("rockiteGolem");
    
	//Nether
	public static final ResourceLocation infernoBlaze = addMob("infernoblaze");
	public static final ResourceLocation hellbot = addMob("hellBot");
	public static final ResourceLocation witherspine = addMob("witherspine");
	public static final ResourceLocation hellCow = addMob("hellCow");
    public static final ResourceLocation reaper = addMob("reaper");
	public static final ResourceLocation miniGhast = addMob("miniGhast");
    public static final ResourceLocation lavasnake = addMob("lavasnake");
    public static final ResourceLocation hellTurtle = addMob("hellturtle");
	
    //Overworld
    public static final ResourceLocation robot = addMob("robot");
    public static final ResourceLocation blank = addMob("blank");
    public static final ResourceLocation spyclops = addMob("spyclops");
    public static final ResourceLocation boom = addMob("boom");
    public static final ResourceLocation boomArmor = addMob("boomArmor");
    public static final ResourceLocation floro = addMob("floro");
    public static final ResourceLocation spectre = addMob("spectre");
	public static final ResourceLocation swampFly = addMob("swampFly");
	public static final ResourceLocation blizzard = addMob("blizzard");
	public static final ResourceLocation jungleGolem = addMob("jungleGolem");
	public static final ResourceLocation jungleTurtle = addMob("jungleturtle");
	public static final ResourceLocation jungleSpider = addMob("junglespider");
    
   // public static final ResourceLocation wraith = addMob("wraith");
    public static final ResourceLocation bigHongo = addMob("bigHongo");
    public static final ResourceLocation mediumHongo = addMob("mediumHongo");
    public static final ResourceLocation smallHongo = addMob("smallHongo");
    public static final ResourceLocation bunny = addMob("bunny");
    public static final ResourceLocation sandCrawler = addMob("sandCrawler");
    public static final ResourceLocation turtle = addMob("turtle");
    public static final ResourceLocation waterFisherman = addMob("waterFisherman");
    public static final ResourceLocation fireMage = addMob("fireMage");
    public static final ResourceLocation iceMage = addMob("iceMage");
    public static final ResourceLocation caveMage = addMob("caveMage");
    public static final ResourceLocation cavurn = addMob("cavurn");
    public static final ResourceLocation caveling = addMob("caveling");
    public static final ResourceLocation stonewalker = addMob("stonewalker");
    public static final ResourceLocation wraith = addMob("wraith");
    public static final ResourceLocation turducken = addMob("turducken");
    public static final ResourceLocation greenHonglow = addMob("greenHonglow");
    public static final ResourceLocation redHonglow = addMob("redHonglow");
    public static final ResourceLocation blueHonglow = addMob("blueHonglow");
    
    //Euca
    public static final ResourceLocation insecto = addMob("insecto");
    public static final ResourceLocation psyollom = addMob("psyollom");
    public static final ResourceLocation eucaFighter = addMob("eucaFighter");    
    public static final ResourceLocation eucaHopper = addMob("eucaHopper");
    public static final ResourceLocation eucaHopperTamed = addMob("eucaHopperTamed");
    public static final ResourceLocation eucaCharger = addMob("eucaCharger");
    public static final ResourceLocation dynaster = addMob("dynaster");
    public static final ResourceLocation golder = addMob("golder");
    public static final ResourceLocation golditeMage = addMob("golditeMage");
    public static final ResourceLocation tinbot = addMob("tinbot");
    public static final ResourceLocation goldbot = addMob("goldbot");
    public static final ResourceLocation shimmerer = addMob("shimmerer");
    public static final ResourceLocation shimmererProjectile = addProjectile("shimmerer");
    public static final ResourceLocation goldwing = addMob("goldwing");

    //Depths
    public static final ResourceLocation depthsBeast = addMob("depthsBeast");
    public static final ResourceLocation darknessCrawler = addMob("darknessCrawler");
    public static final ResourceLocation spikedBeast = addMob("spikedBeast");
    public static final ResourceLocation depthsHunter = addMob("depthsHunter");
    public static final ResourceLocation roc = addMob("roc");
    public static final ResourceLocation darkener = addMob("darkener");
    public static final ResourceLocation lightener = addMob("lightener");
    public static final ResourceLocation darkSorcerer = addMob("darkSorcerer");
    public static final ResourceLocation darkfish = addMob("darkfish");
    
    //Corba
    public static final ResourceLocation leafBlower = addMob("leafBlower");
    public static final ResourceLocation surfaceSeer = addMob("surfaceSeer");
    public static final ResourceLocation treeGolem = addMob("treeGolem");
    public static final ResourceLocation woodCreature = addMob("woodCreature");
    public static final ResourceLocation overseer = addMob("overseer");
    public static final ResourceLocation overseerElder = addMob("overseerElder");
    public static final ResourceLocation natureMage = addMob("natureMage");
    public static final ResourceLocation woodpecker = addMob("woodpecker");
    
    //Frozen
    public static final ResourceLocation frozenTroll = addMob("frozenTroll");
    public static final ResourceLocation permafraust = addMob("permafraust");
    public static final ResourceLocation shiveringBushwalker = addMob("shiveringBushwalker");
    public static final ResourceLocation crystalCluster = addMob("crystalCluster");
    public static final ResourceLocation shiverwolf = addMob("shiverwolf");
    public static final ResourceLocation shiveringShrieker = addMob("shiveringShrieker");
    public static final ResourceLocation frozenFrostbiter = addMob("frozenFrostbiter");
    public static final ResourceLocation shiverwing = addMob("shiverwing");
    
    //Boil
    public static final ResourceLocation magmaBlaze = addMob("magmaBlaze");
    public static final ResourceLocation crisp = addMob("crisp");
    public static final ResourceLocation burntMiner = addMob("burntMiner");
    public static final ResourceLocation exposedFlame = addMob("exposedFlame");
    public static final ResourceLocation burningLight = addMob("burningLight");
    public static final ResourceLocation ashHoarder = addMob("ashHoarder");
    public static final ResourceLocation burntAsh = addMob("burntAsh");
    public static final ResourceLocation magmaGiant = addMob("magmaGiant");
    public static final ResourceLocation frightener = addMob("frightener");
    public static final ResourceLocation hellwing = addMob("hellwing");
    public static final ResourceLocation observer = addMob("observer");
    public static final ResourceLocation screamer = addMob("screamer");
    public static final ResourceLocation phoenix = addMob("phoenix");
    
    //Cloudia
    public static final ResourceLocation cloudGhost = addMob("cloudGhost");
    public static final ResourceLocation starlightVillager = addMob("starlightVillager");
    public static final ResourceLocation starlightBlacksmith = addMob("starlightBlacksmith");
    public static final ResourceLocation cloudFlyer = addMob("cloudFlyer");
    public static final ResourceLocation starlightGolem = addMob("starlightGolem");
    public static final ResourceLocation starlightTransporter = addMob("starlightTransporter");
	public static final ResourceLocation starlightWalker = addMob("starlightWalker");
	public static final ResourceLocation skyEel = addMob("skyEel");
    
    //Boss
    public static final ResourceLocation netherBeast = addMob("netherBeast");
    public static final ResourceLocation witheringBeast = addMob("witheringBeast");
    public static final ResourceLocation calcia = addMob("calcia");
    public static final ResourceLocation blazier = addMob("blazier");
    public static final ResourceLocation sentryKing = addMob("sentryKing");
    public static final ResourceLocation thunderbird = addMob("thunderbird");
    public static final ResourceLocation templeGuardian = addMob("templeGuardian");
    public static final ResourceLocation logger = addMob("logger");
    public static final ResourceLocation corallator = addMob("corallator");
    public static final ResourceLocation skyStalker = addMob("skyStalker");
    public static final ResourceLocation eudor = addMob("eudor");
	public static final ResourceLocation soulWatcher = addMob("soulWatcher");
	public static final ResourceLocation scale = addMob("scale");
	public static final ResourceLocation terranianProtector = addMob("terranianProtector");
	
    //Terrania
	public static final ResourceLocation terralight = addMob("terralight");
	public static final ResourceLocation terraScatterer = addMob("terraScatterer");
	public static final ResourceLocation purplian = addMob("purplian");
	public static final ResourceLocation terraslug = addMob("terraslug");
	public static final ResourceLocation terragrow = addMob("terragrow");
	public static final ResourceLocation terrashroom = addMob("terrashroom");
    
	//Pets
	public static final ResourceLocation tameRoc = addMob("tameRoc");
	public static final ResourceLocation dunewerm = addMob("dunewerm");
	public static final ResourceLocation ferret = addMob("ferret");
	public static final ResourceLocation detractor = addProjectile("detractor");

	
    private static ResourceLocation addMob(String tex){
    	return new ResourceLocation(prefix + tex + ".png");
    }
    
    private static ResourceLocation addProjectile(String tex){
    	return new ResourceLocation(projectilePrefix + tex + ".png");
    }
    
    private static ResourceLocation addModel(String tex){
    	return new ResourceLocation(modelPrefix + tex + ".png");
    }
    
    private static ResourceLocation addSlot(String tex){
    	return new ResourceLocation(slotPrefix + tex + ".png");
    }

}