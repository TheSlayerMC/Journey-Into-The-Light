package net.journey.entity;

import net.journey.api.entity.ISettingsConsumer.EntitySettings;

public class MobStats {
    public static final double standardMovementSpeed = 0.26D, standardFollowRange = 10D, flying_follow = 100D, boss_follow = 150D, standardKnockBackResistance = 0.0D;

    //Overworld
    public static final EntitySettings BIG_HONGO = new EntitySettings(40, 8.25);
    public static final EntitySettings BOOM = new EntitySettings(20, 0).setMovementSpeed(0.200000011920929);
    public static final EntitySettings DUNEWORM = new EntitySettings(25, 6);
    public static final EntitySettings FIRE_MAGE = new EntitySettings(25, 0);
    public static final EntitySettings FLORO = new EntitySettings(25, 0);
    public static final EntitySettings ICE_MAGE = new EntitySettings(25, 0);
    public static final EntitySettings MEDIUM_HONGO = new EntitySettings(30, 5);
    public static final EntitySettings ROBOT = new EntitySettings(20, 4);
    public static final EntitySettings SAND_CRAWLER = new EntitySettings(20, 4);
    public static final EntitySettings SMALL_HONGO = new EntitySettings(20, 3);
    public static final EntitySettings SPECTRE = new EntitySettings(25, 4);
    public static final EntitySettings SPYCLOPSE = new EntitySettings(30, 3);
    public static double SwampFlyHealth = 10.0D;
    public static final EntitySettings TURDUCKEN = new EntitySettings(20, 5);
    public static final EntitySettings WRAITH = new EntitySettings(30, 6);
    public static final EntitySettings BLIZZARD = new EntitySettings(20, 0);
    public static final EntitySettings JUNGLE_GOLEM = new EntitySettings(20, 4);
    public static final EntitySettings JUNGLE_TURTLE = new EntitySettings(30, 4).setKnockbackResistance(1);
    public static final EntitySettings JUNGLE_SPIDER = new EntitySettings(18, 4);
    public static final EntitySettings BLUE_HONGLOW = new EntitySettings(20, 4);
    public static final EntitySettings CAVELING = new EntitySettings(25, 4);
    public static final EntitySettings CAVE_MAGE = new EntitySettings(30, 0);
    public static final EntitySettings CAVURN = new EntitySettings(30, 0);
    public static final EntitySettings GREEN_HONGLOW = new EntitySettings(20, 4);
    public static final EntitySettings HONGLOW = new EntitySettings(20, 4);
    public static final EntitySettings STONE_WALKER = new EntitySettings(30, 3);
    public static final EntitySettings ROCKITE_SMASHER = new EntitySettings(175, 10);

    //Nether
    public static final EntitySettings HELLBOT = new EntitySettings(30, 8);
    public static final EntitySettings HELL_COW = new EntitySettings(35, 7).setMovementSpeed(0.3F);
    public static final EntitySettings HELL_TURTLE = new EntitySettings(40, 7).setKnockbackResistance(1);
    public static final EntitySettings INFERNO_BLAZE = new EntitySettings(30, 8);
    public static double LavaSnakeHealth = 30.0D;
    public static double MiniGhastHealth = 20.0D;
    public static final EntitySettings REAPER = new EntitySettings(35, 7).setMovementSpeed(0.3);
    public static final EntitySettings WITHERSPINE = new EntitySettings(40, 8);

    //End
    public static final EntitySettings ENDER_CRAWLER = new EntitySettings(40, 7);
    public static final EntitySettings ENDER_LEAPER = new EntitySettings(40, 6);

    //Boiling
    public static final EntitySettings BURNING_LIGHT = new EntitySettings(45, 8);
    public static final EntitySettings EXPOSED_FLAME = new EntitySettings(45, 9);
    public static final EntitySettings FRIGHTENER = new EntitySettings(40, 9);
    public static final EntitySettings HELLWING = new EntitySettings(40, 8);
    public static final EntitySettings MAGMA_BLAZE = new EntitySettings(40, 8);
    public static final EntitySettings MAGMA_GIANT = new EntitySettings(50, 10);
    public static final EntitySettings OBSERVER = new EntitySettings(58, 8);
    public static double PhoenixHealth = 46.0D, PhoenixDamage = 8.0D;
    public static final EntitySettings SCREAMER = new EntitySettings(48, 9);
    public static final EntitySettings FLAME_LOTUS = new EntitySettings(0, 0).setMovementSpeed(0);//TODO why 0???

    //Euca
    public static final EntitySettings DYNASTER = new EntitySettings(46, 7);
    public static final EntitySettings EUCA_CHARGER = new EntitySettings(44, 8).setMovementSpeed(0.5);
    public static final EntitySettings EUCA_FIGHTER = new EntitySettings(46, 8);
    public static final EntitySettings GOLDBOT = new EntitySettings(30, 7);
    public static final EntitySettings GOLDER = new EntitySettings(45, 8);
    public static final EntitySettings GOLDITE_MAGE = new EntitySettings(40, 0);
    public static double GoldwingHealth = 45.0D, GoldwingDamage = 7.0D;
    public static final EntitySettings INSECTO = new EntitySettings(42, 9);
    public static final EntitySettings PSYOLLOM = new EntitySettings(44, 10);
    public static double ShimmerHealth = 46.0D;
    public static final EntitySettings SILVERBOT = new EntitySettings(30, 7);

    //Frozen
    public static double CrystalClusterHealth = 44.0D, CrystalClusterDamage = 7.0D;
    public static final EntitySettings FROZEN_FROSTBITER = new EntitySettings(45, 8);
    public static final EntitySettings FROZEN_TROLL = new EntitySettings(42, 10);
    public static final EntitySettings PERMAFRAUST = new EntitySettings(44, 10);
    public static double ShattererHealth = 45.0D, ShattererDamage = 9.0D;
    public static final EntitySettings SHIVERING_BUSHWALKER = new EntitySettings(42, 9);
    public static final EntitySettings SHIVERING_SHRIEKER = new EntitySettings(40, 8);
    public static double ShiverwingHealth = 40.0D, ShiverwingDamage = 8.0D;
    public static final EntitySettings ICE_GOLEM = new EntitySettings(250, 20);
    public static final EntitySettings ICEMAN = new EntitySettings(25, 0);
    //Depths
    public static double DarkenerHealth = 55.0D, DarkenerDamage = 11.0D;
    public static double DarkfishHealth = 55.0D, DarkfishDamage = 10.0D;
    public static final EntitySettings DARKNESS_CRAWLER = new EntitySettings(58, 11);
    public static final EntitySettings DARK_SORCERER = new EntitySettings(50, 0);
    public static final EntitySettings DEPTHS_BEAST = new EntitySettings(60, 15);
    public static final EntitySettings DEPTHS_HUNTER = new EntitySettings(58, 13);
    public static double LightenerHealth = 50.0D, LightenerDamage = 10.0D;
    public static double RocHealth = 55.0D, RocDamage = 10.0D;
    public static final EntitySettings SPIKED_BEAST = new EntitySettings(55, 11);

    //Corba
    public static final EntitySettings LEAF_BLOWER = new EntitySettings(65, 13);
    public static final EntitySettings NATURE_MAGE = new EntitySettings(60, 13);
    public static double OverseerHealth = 62.0D, OverseerDamage = 14.0D;
    public static double OverseerElderHealth = 68.0D, OverseerElderDamage = 16.0D;
    public static double SurfaceSeerHealth = 65.0D, SurfaceSeerDamage = 15.0D;
    public static double TreeGolemHealth = 62.0D, TreeGolemDamage = 14.0D;
    public static final EntitySettings TREE_GOLEM = new EntitySettings(62, 14);
    public static final EntitySettings WOOD_CREATURE = new EntitySettings(62, 12);
    public static double WoodpeckerHealth = 60.0D, WoodpeckerDamage = 12.0D;

    //Terrania
    public static final EntitySettings PURPLIAN = new EntitySettings(75, 16);
    public static final EntitySettings TERRAGROW = new EntitySettings(75, 17);
    public static double TerralightHealth = 75.0D, TerralightDamage = 18.0D;
    public static final EntitySettings TERRA_SCATTERER = new EntitySettings(75, 17);
    public static final EntitySettings TERRASHROOM = new EntitySettings(75, 16);
    public static final EntitySettings TERRASLUG = new EntitySettings(75, 15);

    //Cloudia
    public static double CloudFlyerHealth = 70.0D, CloudFlyerDamage = 15.0D;
    public static final EntitySettings CLOUD_GHOST = new EntitySettings(75, 18);
    public static double SkyEelHealth = 70.0D, SkyEelDamage = 13.0D;
    public static final EntitySettings STARLIGHT_GOLEM = new EntitySettings(75, 17);
    public static final EntitySettings STARLIGHT_TRANSPORTER = new EntitySettings(75, 18);
    public static final EntitySettings STARLIGHT_WALKER = new EntitySettings(75, 17);
    public static final EntitySettings CLOUD_FLOWER = new EntitySettings(75, 0).setMovementSpeed(0);

    //Senterian
    public static final EntitySettings MINI_SENTRY_LORD = new EntitySettings(20, 5);
    public static final EntitySettings SENTRY_LORD = new EntitySettings(75, 16);
    public static final EntitySettings SENTRY_WALKER = new EntitySettings(75, 17);
    public static final EntitySettings MINI_SENTRY_WALKER = new EntitySettings(20, 5);
    public static final EntitySettings SENTRY_STALKER = new EntitySettings(75, 18);
    public static final EntitySettings MINI_SENTRY_STALKER = new EntitySettings(20, 5);
    public static final EntitySettings SENTRY_BLOCK = new EntitySettings(75, 0).setMovementSpeed(0);

    //Misc
    public static double EucaHopperHealth = 40.0D, EucaHopperDamage = 7.5D;
    public static double EucaHopperTameHealth = 40.0D, EucaHopperTameDamage = 7.5D;
    public static double TameRocHealth = 60.0D, TameRocDamage = 7.0D;

    //Boss
    public static double soulWatcherHealth = 700.0D, soulWatcherDamage = 10.0D, soulWatcherSpeed = standardMovementSpeed, soulWatcherFollowRange = boss_follow;
    public static final EntitySettings WITHERING_BEAST = new EntitySettings(750, 10);
    public static final EntitySettings NETHER_BEAST = new EntitySettings(600, 10);
    public static final EntitySettings CALCIA = new EntitySettings(650, 10).setKnockbackResistance(1);
    public static final EntitySettings EUDOR = new EntitySettings(900, 10).setKnockbackResistance(1);
    public static final EntitySettings FOURFA = new EntitySettings(900, 10).setKnockbackResistance(1);
    public static final EntitySettings GUARDIAN_OF_DESTRUCTION = new EntitySettings(1500, 15).setKnockbackResistance(1);
    public static double sentryHeartHealth = 1500.0D, sentryHeartDamage = 15.0D, sentryHeartSpeed = standardMovementSpeed, sentryHeartFollowRange = boss_follow;
    public static final EntitySettings BLAZIER = new EntitySettings(850, 20);
    public static double scaleHealth = 1560.0D, scaleDamage = 20.0D;
    public static final EntitySettings SENTRY_KING = new EntitySettings(2500, 20).setKnockbackResistance(1);
    public static final EntitySettings TEMPLE_GUARDIAN = new EntitySettings(275, 0);
    public static double templeGuardianHealth = 275.0D, templeGuardianDamage = 0.0D, templeGuardianSpeed = standardMovementSpeed, templeGuardianFollowRange = boss_follow;
    public static final EntitySettings WRAITH_BOSS = new EntitySettings(50, 10);
    public static final EntitySettings THUNDERBIRD = new EntitySettings(2000, 10);
    public static final EntitySettings LOGGER = new EntitySettings(2350, 10);
    public static final EntitySettings CORALLATOR = new EntitySettings(950, 10);
    public static double skyStalkerHealth = 3500.0D, skyStalkerDamage = 10.0D, skyStalkerSpeed = standardMovementSpeed, skyStalkerFollowRange = boss_follow;
    public static double terranianHealth = 2500.0D, terranianDamage = 10.0D, terranianSpeed = standardMovementSpeed, terranianFollowRange = boss_follow;
    public static final EntitySettings SENTRY_HEART = new EntitySettings(5000, 0).setMovementSpeed(0).setKnockbackResistance(1);//TODO change
}
