package net.journey.entity;

public class MobStats {
    public static final double STANDARD_MOVEMENT_SPEED = 0.26D, STANDARD_FOLLOW_RANGE = 20D, STANDARD_FLYING_FOLLOW_RANGE = 100D, boss_follow = 150D, STANDARD_KNOCKBACK_RESISTANCE = 0.0D;

    //ToDO get rid of bottom values

    //Overworld
    public static double SwampFlyHealth = 10.0D;

    //Nether
    public static double LavaSnakeHealth = 30.0D;
    public static double MiniGhastHealth = 20.0D;

    //Euca
    public static double ShimmerHealth = 46.0D;

    //Frozen
    public static double CrystalClusterHealth = 44.0D, CrystalClusterDamage = 7.0D;
    public static double ShattererHealth = 45.0D, ShattererDamage = 9.0D;
    //Depths
    public static double DarkenerHealth = 55.0D, DarkenerDamage = 11.0D;
    public static double DarkfishHealth = 55.0D, DarkfishDamage = 10.0D;
    public static double LightenerHealth = 50.0D, LightenerDamage = 10.0D;

    //Corba
    public static double OverseerHealth = 62.0D, OverseerDamage = 14.0D;
    public static double OverseerElderHealth = 68.0D, OverseerElderDamage = 16.0D;
    public static double SurfaceSeerHealth = 65.0D, SurfaceSeerDamage = 15.0D;

    //Terrania
    public static double TerralightHealth = 75.0D, TerralightDamage = 18.0D;

    //Cloudia
    public static double SkyEelHealth = 70.0D, SkyEelDamage = 13.0D;

    //Boss
    public static double soulWatcherHealth = 700.0D, soulWatcherDamage = 10.0D, soulWatcherSpeed = STANDARD_MOVEMENT_SPEED, soulWatcherFollowRange = boss_follow;
    public static double sentryHeartHealth = 1500.0D, sentryHeartDamage = 15.0D, sentryHeartSpeed = STANDARD_MOVEMENT_SPEED, sentryHeartFollowRange = boss_follow;
    public static double scaleHealth = 1560.0D, scaleDamage = 20.0D;
    public static double templeGuardianHealth = 275.0D, templeGuardianDamage = 0.0D, templeGuardianSpeed = STANDARD_MOVEMENT_SPEED, templeGuardianFollowRange = boss_follow;
    public static double skyStalkerHealth = 3500.0D, skyStalkerDamage = 10.0D, skyStalkerSpeed = STANDARD_MOVEMENT_SPEED, skyStalkerFollowRange = boss_follow;
    public static double terranianHealth = 2500.0D, terranianDamage = 10.0D, terranianSpeed = STANDARD_MOVEMENT_SPEED, terranianFollowRange = boss_follow;
}
