package net.journey.client.render;

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
    public static final ResourceLocation cloudaltar = addModel("blocks/cloudaltar");
    public static final ResourceLocation senterianAltar = addModel("blocks/senterianaltar");
    public static final ResourceLocation journeyChest = addModel("blocks/journeychest");
    public static final ResourceLocation eucaChest = addModel("blocks/eucachest");
    public static final ResourceLocation empty = addProjectile("empty");
    public static final ResourceLocation templeBall = addProjectile("templeBall");
    public static final ResourceLocation essenceArrow = addProjectile("essenceArrow");
    public static final ResourceLocation moltenKnife = new ResourceLocation(SlayerAPI.PREFIX + "textures/items/moltenknife.png");
    public static final ResourceLocation rockChunk = new ResourceLocation(SlayerAPI.PREFIX + "textures/items/rockchunk.png");
    public static final ResourceLocation plasma = new ResourceLocation(SlayerAPI.PREFIX + "textures/items/plasmaBall.png");

    //NPC
    public static final ResourceLocation mage = addMob("mage");
    public static final ResourceLocation blacksmith = addMob("blacksmith");
    public static final ResourceLocation boilTrader = addMob("boiltrader");
    public static final ResourceLocation alloyMender = addMob("alloymender");
    public static final ResourceLocation frozenMerchant = addMob("frozenmerchant");
    public static final ResourceLocation escapedConvict = addMob("escapedconvict");
    public static final ResourceLocation blueTordo = addMob("bluetordo");
    public static final ResourceLocation redTordo = addMob("redtordo");
    public static final ResourceLocation greenTordo = addMob("greentordo");
    public static final ResourceLocation staringGuardian = addMob("staringguardian");
    public static final ResourceLocation terranianEnchanter = addMob("terraniantrader_enchanter");
    public static final ResourceLocation terranianTrader = addMob("terraniantrader");
    public static final ResourceLocation overgrownMerchant = addMob("overgrownmerchant");
    public static final ResourceLocation rockiteGolem = addMob("rockitegolem");

    //Nether
    public static final ResourceLocation infernoBlaze = addMob("infernoblaze");
    public static final ResourceLocation hellbot = addMob("hellBot");
    public static final ResourceLocation witherspine = addMob("witherspine");
    public static final ResourceLocation hellCow = addMob("hellcow");
    public static final ResourceLocation reaper = addMob("reaper");
    public static final ResourceLocation miniGhast = addMob("minighast");
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
    public static final ResourceLocation swampFly = addMob("swampfly");
    public static final ResourceLocation blizzard = addMob("blizzard");
    public static final ResourceLocation jungleGolem = addMob("junglegolem");
    public static final ResourceLocation jungleTurtle = addMob("jungleturtle");
    public static final ResourceLocation jungleSpider = addMob("junglespider");

    // public static final ResourceLocation wraith = addMob("wraith");
    public static final ResourceLocation bigHongo = addMob("bigHongo");
    public static final ResourceLocation mediumHongo = addMob("mediumhongo");
    public static final ResourceLocation smallHongo = addMob("smallhongo");
    public static final ResourceLocation bunny = addMob("bunny");
    public static final ResourceLocation sandCrawler = addMob("sandcrawler");
    public static final ResourceLocation turtle = addMob("turtle");
    public static final ResourceLocation waterFisherman = addMob("waterfisherman");
    public static final ResourceLocation fireMage = addMob("firemage");
    public static final ResourceLocation iceMage = addMob("icemage");
    public static final ResourceLocation caveMage = addMob("cavemage");
    public static final ResourceLocation cavurn = addMob("cavurn");
    public static final ResourceLocation caveling = addMob("caveling");
    public static final ResourceLocation stonewalker = addMob("stonewalker");
    public static final ResourceLocation wraith = addMob("wraith");
    public static final ResourceLocation turducken = addMob("turducken");
    public static final ResourceLocation greenHonglow = addMob("greenhonglow");
    public static final ResourceLocation redHonglow = addMob("redhonglow");
    public static final ResourceLocation blueHonglow = addMob("bluehonglow");

    //Euca
    public static final ResourceLocation insecto = addMob("insecto");
    public static final ResourceLocation psyollom = addMob("psyollom");
    public static final ResourceLocation eucaFighter = addMob("eucafighter");
    public static final ResourceLocation eucaHopper = addMob("eucahopper");
    public static final ResourceLocation eucaHopperTamed = addMob("eucahoppertamed");
    public static final ResourceLocation eucaCharger = addMob("eucacharger");
    public static final ResourceLocation dynaster = addMob("dynaster");
    public static final ResourceLocation golder = addMob("golder");
    public static final ResourceLocation golditeMage = addMob("golditemage");
    public static final ResourceLocation tinbot = addMob("tinbot");
    public static final ResourceLocation goldbot = addMob("goldbot");
    public static final ResourceLocation shimmerer = addMob("shimmerer");
    public static final ResourceLocation shimmererProjectile = addProjectile("shimmerer");
    public static final ResourceLocation goldwing = addMob("goldwing");

    //Depths
    public static final ResourceLocation depthsBeast = addMob("depthsbeast");
    public static final ResourceLocation darknessCrawler = addMob("darknesscrawler");
    public static final ResourceLocation spikedBeast = addMob("spikedbeast");
    public static final ResourceLocation depthsHunter = addMob("depthshunter");
    public static final ResourceLocation roc = addMob("roc");
    public static final ResourceLocation darkener = addMob("darkener");
    public static final ResourceLocation lightener = addMob("lightener");
    public static final ResourceLocation darkSorcerer = addMob("darksorcerer");
    public static final ResourceLocation darkfish = addMob("darkfish");

    //Corba
    public static final ResourceLocation leafBlower = addMob("leafblower");
    public static final ResourceLocation surfaceSeer = addMob("surfaceseer");
    public static final ResourceLocation treeGolem = addMob("treegolem");
    public static final ResourceLocation woodCreature = addMob("woodcreature");
    public static final ResourceLocation overseer = addMob("overseer");
    public static final ResourceLocation overseerElder = addMob("overseerelder");
    public static final ResourceLocation natureMage = addMob("naturemage");
    public static final ResourceLocation woodpecker = addMob("woodpecker");

    //Frozen
    public static final ResourceLocation frozenTroll = addMob("frozentroll");
    public static final ResourceLocation permafraust = addMob("permafraust");
    public static final ResourceLocation shiveringBushwalker = addMob("shiveringbushwalker");
    public static final ResourceLocation crystalCluster = addMob("crystalcluster");
    public static final ResourceLocation shiverwolf = addMob("shiverwolf");
    public static final ResourceLocation shiveringShrieker = addMob("shiveringshrieker");
    public static final ResourceLocation frozenFrostbiter = addMob("frozenfrostbiter");
    public static final ResourceLocation shiverwing = addMob("shiverwing");
    public static final ResourceLocation iceman = addMob("iceman");
    public static final ResourceLocation iceGolem = addMob("icegolem");

    //Boil
    public static final ResourceLocation magmaBlaze = addMob("magmablaze");
    public static final ResourceLocation crisp = addMob("crisp");
    public static final ResourceLocation burntMiner = addMob("burntminer");
    public static final ResourceLocation exposedFlame = addMob("exposedflame");
    public static final ResourceLocation burningLight = addMob("burninglight");
    public static final ResourceLocation ashHoarder = addMob("ashhoarder");
    public static final ResourceLocation burntAsh = addMob("burntash");
    public static final ResourceLocation magmaGiant = addMob("magmagiant");
    public static final ResourceLocation frightener = addMob("frightener");
    public static final ResourceLocation hellwing = addMob("hellwing");
    public static final ResourceLocation observer = addMob("observer");
    public static final ResourceLocation screamer = addMob("screamer");
    public static final ResourceLocation flameLotus = addMob("flamelotus");

    //Cloudia
    public static final ResourceLocation cloudGhost = addMob("cloudghost");
    public static final ResourceLocation starlightVillager = addMob("starlightvillager");
    public static final ResourceLocation starlightBlacksmith = addMob("starlightblacksmith");
    public static final ResourceLocation cloudFlyer = addMob("cloudflyer");
    public static final ResourceLocation starlightGolem = addMob("starlightgolem");
    public static final ResourceLocation starlightTransporter = addMob("starlighttransporter");
    public static final ResourceLocation starlightWalker = addMob("starlightwalker");
    public static final ResourceLocation skyEel = addMob("skyeel");
    public static final ResourceLocation cloudFlower = addMob("cloudflower");

    //Boss
    public static final ResourceLocation netherBeast = addMob("netherbeast");
    public static final ResourceLocation witheringBeast = addMob("witheringbeast");
    public static final ResourceLocation calcia = addMob("calcia");
    public static final ResourceLocation blazier = addMob("blazier");
    public static final ResourceLocation sentryKing = addMob("sentryking");
    public static final ResourceLocation thunderbird = addMob("thunderbird");
    public static final ResourceLocation templeGuardian = addMob("templeguardian");
    public static final ResourceLocation logger = addMob("logger");
    public static final ResourceLocation corallator = addMob("corallator");
    public static final ResourceLocation skyStalker = addMob("skystalker");
    public static final ResourceLocation eudor = addMob("eudor");
    public static final ResourceLocation soulWatcher = addMob("soulwatcher");
    public static final ResourceLocation scale = addMob("scale");
    public static final ResourceLocation terranianProtector = addMob("terranianprotector");

    //Terrania
    public static final ResourceLocation terralight = addMob("terralight");
    public static final ResourceLocation terraScatterer = addMob("terrascatterer");
    public static final ResourceLocation purplian = addMob("purplian");
    public static final ResourceLocation terraslug = addMob("terraslug");
    public static final ResourceLocation terragrow = addMob("terragrow");
    public static final ResourceLocation terrashroom = addMob("terrashroom");

    //Senterian
    public static final ResourceLocation sentryBlock = addMob("sentryblock");
    public static final ResourceLocation sentryBlockAwake = addMob("sentryblockawake");
    public static final ResourceLocation sentryLord = addMob("sentrylord");
    public static final ResourceLocation sentryStalker = addMob("sentrystalker");
    public static final ResourceLocation sentryWalker = addMob("sentrywalker");

    //Pets
    public static final ResourceLocation tameRoc = addMob("tameRoc");
    public static final ResourceLocation dunewerm = addMob("dunewerm");
    public static final ResourceLocation ferret = addMob("ferret");
    public static final ResourceLocation detractor = addProjectile("detractor");


    private static ResourceLocation addMob(String tex) {
        return new ResourceLocation(prefix + tex.toLowerCase() + ".png");
    }

    private static ResourceLocation addProjectile(String tex) {
        return new ResourceLocation(projectilePrefix + tex.toLowerCase() + ".png");
    }

    private static ResourceLocation addModel(String tex) {
        return new ResourceLocation(modelPrefix + tex.toLowerCase() + ".png");
    }

    private static ResourceLocation addSlot(String tex) {
        return new ResourceLocation(slotPrefix + tex.toLowerCase() + ".png");
    }

}