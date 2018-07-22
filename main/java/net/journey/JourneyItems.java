package net.journey;

import java.util.ArrayList;

import net.journey.entity.projectile.EntityBoilingPiercer;
import net.journey.entity.projectile.EntityConjuring;
import net.journey.entity.projectile.EntityCorbaPiercer;
import net.journey.entity.projectile.EntityDarknessArrow;
import net.journey.entity.projectile.EntityDemonicBomb;
import net.journey.entity.projectile.EntityDepthsPiercer;
import net.journey.entity.projectile.EntityDoomsBringer;
import net.journey.entity.projectile.EntityEarthen;
import net.journey.entity.projectile.EntityEnlightenment;
import net.journey.entity.projectile.EntityEucaPiercer;
import net.journey.entity.projectile.EntityEyeBlaster;
import net.journey.entity.projectile.EntityFireBall;
import net.journey.entity.projectile.EntityFireBomb;
import net.journey.entity.projectile.EntityFlameArrow;
import net.journey.entity.projectile.EntityFloroWater;
import net.journey.entity.projectile.EntityFrostbittenPiercer;
import net.journey.entity.projectile.EntityFrostyPiercer;
import net.journey.entity.projectile.EntityFrozenArrow;
import net.journey.entity.projectile.EntityFrozenPiercer;
import net.journey.entity.projectile.EntityGreenpace;
import net.journey.entity.projectile.EntityHellstone;
import net.journey.entity.projectile.EntityIceBall;
import net.journey.entity.projectile.EntityLightningBall;
import net.journey.entity.projectile.EntityNetherPlasma;
import net.journey.entity.projectile.EntityNethicPiercer;
import net.journey.entity.projectile.EntityOvergrown;
import net.journey.entity.projectile.EntityPoisonArrow;
import net.journey.entity.projectile.EntityRock;
import net.journey.entity.projectile.EntityWithic;
import net.journey.entity.projectile.EntityWizardsStar;
import net.journey.enums.EnumKnowledge;
import net.journey.items.ItemCandyCane;
import net.journey.items.ItemChaosCannon;
import net.journey.items.ItemCrackenCanes;
import net.journey.items.ItemCrakeBulb;
import net.journey.items.ItemCreativeHammer;
import net.journey.items.ItemDemonicEye;
import net.journey.items.ItemDetractor;
import net.journey.items.ItemEssencePotion;
import net.journey.items.ItemFlameCoin;
import net.journey.items.ItemFruit;
import net.journey.items.ItemGlowa;
import net.journey.items.ItemGoldenFood;
import net.journey.items.ItemGun;
import net.journey.items.ItemHammer;
import net.journey.items.ItemHealth;
import net.journey.items.ItemHonglow;
import net.journey.items.ItemKnife;
import net.journey.items.ItemKnowledge;
import net.journey.items.ItemModArmor;
import net.journey.items.ItemModBow;
import net.journey.items.ItemModRecord;
import net.journey.items.ItemMultiTool;
import net.journey.items.ItemNetherBossSpawner;
import net.journey.items.ItemPiercer;
import net.journey.items.ItemPresent;
import net.journey.items.ItemSentryEye;
import net.journey.items.ItemSoul;
import net.journey.items.ItemSpecificDimensionSpawner;
import net.journey.items.ItemSpineberries;
import net.journey.items.ItemStaff;
import net.journey.items.ItemTeleport;
import net.journey.items.ItemThrowable;
import net.journey.items.ItemWand;
import net.journey.items.ItemZatPedal;
import net.journey.items.swords.ItemBubbleSword;
import net.journey.items.swords.ItemFireHealthSword;
import net.journey.items.swords.ItemFireSword;
import net.journey.items.swords.ItemFireWitherSword;
import net.journey.items.swords.ItemFreezeSword;
import net.journey.items.swords.ItemHealthSword;
import net.journey.items.swords.ItemLoggersSword;
import net.journey.items.swords.ItemNightvisionHealthSword;
import net.journey.items.swords.ItemNightvisionSword;
import net.journey.items.swords.ItemPoisionSword;
import net.journey.items.swords.ItemPoisonHealthSword;
import net.journey.items.swords.ItemRegenSword;
import net.journey.items.swords.ItemStunSword;
import net.journey.items.swords.ItemStunWitherSword;
import net.journey.items.swords.ItemWitherSword;
import net.journey.util.Config;
import net.journey.util.EnumArmor;
import net.journey.util.JourneyToolMaterial;
import net.journey.util.PotionEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;
import net.slayer.api.item.ItemModFood;
import net.slayer.api.item.ItemModHoe;
import net.slayer.api.item.ItemModPickaxe;
import net.slayer.api.item.ItemModShovel;
import net.slayer.api.item.ItemModSword;

public class JourneyItems {

	public static ToolMaterial hellstoneSwordMat = addToolMaterial(2356, 13F, 10F, true);
	public static ToolMaterial flairiumSwordMat = addToolMaterial(3120, 13F, 25F, true);
	public static ToolMaterial celestiumSwordMat = addToolMaterial(3120, 13F, 20F, true);
	public static ToolMaterial shadiumSwordMat = addToolMaterial(2210, 13F, 8F, true);
	public static ToolMaterial luniumSwordMat = addToolMaterial(2210, 13F, 8F, true);
	public static ToolMaterial sapphiretoolSwordMat = addToolMaterial(1561, 13F, 7F, true);
	public static ToolMaterial gorbiteSwordMat = addToolMaterial(3120, 13F, 25F, true);
	public static ToolMaterial orbaditeSwordMat = addToolMaterial(3120, 13F, 25F, true);
	public static ToolMaterial desSwordMat = addToolMaterial(3120, 13F, 25F, true);
	public static ToolMaterial mekyumSwordMat = addToolMaterial(3120, 13F, 25F, true);
	public static ToolMaterial storonSwordMat = addToolMaterial(3120, 13F, 25F, true);
	public static ToolMaterial koriteSwordMat = addToolMaterial(3120, 13F, 25F, true);

	public static ToolMaterial hellstone = addToolMaterial(1600, 10F, 5F, true);
	public static ToolMaterial flairium = addToolMaterial(3000, 15F, 5F, true);
	public static ToolMaterial lunium = addToolMaterial(3000, 9F, 5F, true);
	public static ToolMaterial celestium = addToolMaterial(1600, 13F, 5F, true);
	public static ToolMaterial mekyum = addToolMaterial(1600, 13F, 5F, true);
	public static ToolMaterial storon = addToolMaterial(1600, 13F, 5F, true);
	public static ToolMaterial korite = addToolMaterial(1600, 13F, 5F, true);
	public static ToolMaterial shadium = addToolMaterial(1600, 10F, 5F, true);
	public static ToolMaterial sapphiretool = addToolMaterial(1561, 8F, 5F, true);
	public static ToolMaterial orbadite = addToolMaterial(3000, 15F, 5F, true);
	public static ToolMaterial gorbite = addToolMaterial(3000, 15F, 5F, true);
	public static ToolMaterial des = addToolMaterial(3000, 15F, 5F, true);

	public static ToolMaterial hellstoneMulti = addToolMaterial(3000, 12F, 5F, false);
	public static ToolMaterial flairiumMulti = addToolMaterial(3000, 15F, 5F, false);
	public static ToolMaterial luniumMulti = addToolMaterial(3000, 9F, 5F, false);
	public static ToolMaterial celestiumMulti = addToolMaterial(3000, 13F, 5F, false);
	public static ToolMaterial mekyumMulti = addToolMaterial(3000, 13F, 5F, false);
	public static ToolMaterial storonMulti = addToolMaterial(3000, 13F, 5F, false);
	public static ToolMaterial koriteMulti = addToolMaterial(3000, 13F, 5F, false);
	public static ToolMaterial shadiumMulti = addToolMaterial(3000, 10F, 5F, false);
	public static ToolMaterial sapphireMulti = addToolMaterial(3000, 8F, 5F, false);
	public static ToolMaterial orbaditeMulti = addToolMaterial(3000, 15F, 5F, false);
	public static ToolMaterial gorbiteMulti = addToolMaterial(3000, 15F, 5F, false);
	public static ToolMaterial desMulti = addToolMaterial(3000, 15F, 5F, false);
	public static ToolMaterial smeltingMulti = addToolMaterial(3000, 15F, 5F, false);
	//TODO MATERIALS
	public static ToolMaterial woodMulti = addToolMaterial(60, 2F, 5F, false);
	public static ToolMaterial stoneMulti = addToolMaterial(132, 4F, 5F, false);
	public static ToolMaterial ironMulti = addToolMaterial(251, 8F, 5F, false);
	public static ToolMaterial goldMulti = addToolMaterial(33, 10F, 5F, false);
	public static ToolMaterial diamondMulti = addToolMaterial(1562, 13F, 5F, false);

	public static ArrayList<String> itemNames = new ArrayList<String>();
	public static ArrayList<Item> items = new ArrayList<Item>();

	public static final EntityEquipmentSlot HEAD = EntityEquipmentSlot.HEAD, BODY = EntityEquipmentSlot.CHEST, LEGS = EntityEquipmentSlot.LEGS, BOOTS = EntityEquipmentSlot.FEET;

	public static Item heartSml;
	public static Item heartMed;
	public static Item heartLrg;
	public static Item heartUlt;
	public static Item heartSentry;
	public static Item hellstoneIngot;
	public static Item shadiumIngot;
	public static Item celestiumIngot;
	public static Item mekyumIngot;
	public static Item koriteIngot;
	public static Item storonIngot;
	public static Item luniumIngot;
	public static Item flairiumIngot;
	public static Item ash;
	public static ItemMod sapphire;
	public static Item blazium;
	public static Item enderilliumShard;
	public static Item orbaditeIngot;
	public static Item gorbiteGem;
	public static Item desIngot;

	public static Item elderKey;
	public static Item boilPowder;
	public static Item blazingFireball;
	public static Item hellTurtleShell;
	public static Item sizzlingEye;

	public static Item sentryEye;
	public static Item boilingSkull;
	public static Item overgrownNatureBall;
	public static Item overseeingTablet;
	public static Item overseeingEye;
	public static Item darkCrystal;
	public static Item blankKnowledge;
	public static Item darkOrb;
	public static Item depthsFlake;
	public static Item beastlyStomach;
	public static Item rocFeather;
	public static Item coldClump;
	public static Item crystalEye;
	public static Item crystalFlake;
	public static Item freezingTablet;
	public static Item frostFlake;
	public static Item frostGem;
	public static Item frozenIceball;
	public static Item snowsheet;
	public static Item gateKeys;
	public static Item goldClump;
	public static Item silverClump;
	public static Item golderDust;
	public static Item shimmerdust;
	public static Item royalDisk;
	public static Item metalDisk;
	public static Item demonicDust;
	public static Item demonicBone;
	public static Item withicDust;
	public static Item cloudiaOrb;
	public static Item fluffyFeather;
	public static Item golemChunk;
	public static Item luniteChunk;
	public static Item corbaStick;
	public static Item spyclopseEye;
	public static Item caveCrystal;
	public static Item caveDust;
	public static Item stoneClump;
	public static Item enchantedLeaf;
	public static Item baseEssence;
	public static Item cloudPuff;
	public static Item collectorRock;
	public static Item natureTablet;
	public static Item horn;
	public static Item scale;
	public static Item reinforcedStoneIngot;
	public static Item reinforcedCrystalIngot;
	public static Item crystalBall;
	public static Item stoneStick;
	public static Item darkTerrarianSoil;
	public static Item earthenCrystal;
	public static Item lightTerrarianSoil;
	public static Item terrastar;
	public static Item purplePowder;
	public static Item hellShards;
	public static Item hellcrustIngot;
	public static Item flamingSprocket;
	public static Item flamingSpring;
	public static Item blood;
	public static Item concentratedBlood;
	public static Item snakeSkin;
	public static Item withicSpine;
	public static Item lostSoul;
	public static Item withicSoul;
	public static Item eucaPortalPiece;
	public static Item eucaPortalPiece_1;
	public static Item eucaPortalPiece_0;
	public static Item flamingHide;
	public static Item boilKey;
	public static Item darkKey;

	public static Item hellstoneDust;
	public static Item shadiumDust;
	public static Item celestiumDust;
	public static Item luniumDust;
	public static Item flairiumDust;
	public static Item ashDust;
	public static Item sapphireDust;
	public static Item enderilliumDust;
	public static Item gorbiteDust;
	public static Item orbaditeDust;
	public static Item diamondDust;
	public static Item goldDust;
	public static Item ironDust;

	public static Item hellstoneClump;
	public static Item shadiumClump;
	public static Item luniumClump;
	public static Item spawnerClump;
	public static Item spawnerBar;

	public static Item flameCoin;
	public static Item essenceDetractor;
	public static Item essenceAttractor;

	public static Item rockChunk;
	public static Item rockShard;
	//public static Item plasmaBall = new ItemMod("plasmaBall");

	public static Item hellstoneMultiTool;
	public static Item shadiumMultiTool;
	public static Item celestiumMultiTool;
	public static Item luniumMultiTool;
	public static Item flairiumMultiTool;
	public static Item sapphireMultiTool;
	public static Item gorbiteMultiTool;
	public static Item orbaditeMultiTool;
	public static Item desMultiTool;
	public static Item koriteMultiTool;
	public static Item storonMultiTool;
	public static Item mekyumMultiTool;
	public static Item woodMultiTool;
	public static Item stoneMultiTool;
	public static Item ironMultiTool;
	public static Item goldMultiTool;
	public static Item diamondMultiTool;

	public static Item hellstonePickaxe;
	public static Item shadiumPickaxe;
	public static Item celestiumPickaxe;
	public static Item luniumPickaxe;
	public static Item flairiumPickaxe;
	public static Item sapphirePickaxe;
	public static Item gorbitePickaxe;
	public static Item orbaditePickaxe;
	public static Item desPickaxe;
	public static Item koritePickaxe;
	public static Item storonPickaxe;
	public static Item mekyumPickaxe;

	public static Item hellstoneShovel;
	public static Item shadiumShovel;
	public static Item celestiumShovel;
	public static Item luniumShovel;
	public static Item flairiumShovel;
	public static Item sapphireShovel;
	public static Item gorbiteShovel;
	public static Item orbaditeShovel;
	public static Item desShovel;
	public static Item koriteShovel;
	public static Item storonShovel;
	public static Item mekyumShovel;

	/*public static Item hellstoneAxe = new ItemModAxe("hellstoneAxe", "Bloodcrust Axe", EssenceToolMaterial.HELLSTONE);
	public static Item shadiumAxe = new ItemModAxe("shadiumAxe", "Shadium Axe", EssenceToolMaterial.SHADIUM);
	public static Item celestiumAxe = new ItemModAxe("celestiumAxe", "Celestium Axe", EssenceToolMaterial.CELESTIUM);
	public static Item luniumAxe = new ItemModAxe("luniumAxe", "Lunium Axe", EssenceToolMaterial.LUNIUM);
	public static Item flairiumAxe = new ItemModAxe("flairiumAxe", "Flairium Axe", EssenceToolMaterial.FLAIRIUM);
	public static Item sapphireAxe = new ItemModAxe("sapphireAxe", "Sapphire Axe", EssenceToolMaterial.SAPPHIRE);
	public static Item gorbiteAxe = new ItemModAxe("gorbiteAxe", "Gorbite Axe", EssenceToolMaterial.GORBITE);
	public static Item orbaditeAxe = new ItemModAxe("orbaditeAxe", "Orbadite Axe", EssenceToolMaterial.ORBADITE);
	public static Item desAxe = new ItemModAxe("desAxe", "Des Axe", EssenceToolMaterial.DES);
	public static Item koriteAxe = new ItemModAxe("koriteAxe", "Korite Axe", EssenceToolMaterial.KORITE);
	public static Item storonAxe = new ItemModAxe("storonAxe", "Storon Axe", EssenceToolMaterial.KORITE);
	public static Item mekyumAxe = new ItemModAxe("mekyumAxe", "Mekyum Axe", EssenceToolMaterial.KORITE);*/

	public static Item hellstoneHoe;
	public static Item shadiumHoe;
	public static Item celestiumHoe;
	public static Item luniumHoe;
	public static Item flairiumHoe;
	public static Item sapphireHoe;
	public static Item gorbiteHoe;
	public static Item orbaditeHoe;
	public static Item desHoe;
	public static Item koriteHoe;
	public static Item storonHoe;
	public static Item mekyumHoe;

	public static Item multiToolOfEternalSmelting;

	public static Item hellstoneSword;
	public static Item shadiumSword;
	public static Item celestiumSword;
	public static Item luniumSword;
	public static Item flairiumSword;
	public static Item desSword;
	public static Item sapphireSword;
	public static Item gorbiteSword;
	public static Item orbaditeSword;
	public static Item poisonSword;
	public static Item cloudSlicer;
	public static Item dragonsTooth;
	public static Item netherBeastSword;
	public static Item witheringBeastSword;
	public static Item calciaSword;
	public static Item championsSword;
	public static Item theWraith;
	public static Item bubbleSword;
	public static Item boilingBlade;
	public static Item loggersSword;
	public static Item naturesBlade;
	public static Item depthsDarksword;
	public static Item depthsSlayer;
	public static Item snowShoveler;
	public static Item frostySword;
	public static Item frostbittenSword;
	public static Item treeHugger;
	public static Item coreMender;
	public static Item royalBlade;
	public static Item royalStabber;
	public static Item rocSword;
	public static Item swordOfTheThunderbird;
	public static Item bloodwieldSword;
	public static Item charredBlade;
	public static Item sizzlerSword;
	public static Item fluffyBlade;
	public static Item golemSword;
	public static Item thunderblade;
	public static Item sentrySword;
	public static Item crystalBlade;
	public static Item starlightBlade;
	public static Item koriteSword;
	public static Item storonSword;
	public static Item mekyumSword;
	public static Item pedalSword;
	public static Item withicBlade;
	public static Item reinforcedCrystalSword;
	public static Item reinforcedStoneSword;
	public static Item terralightBlade;
	public static Item terranaSword;
	public static Item terrolicaSword;
	public static Item voliteSword;
	public static Item kingsSword;
	public static Item demonicSword;
	public static Item vinestrandBlade;
	public static Item darkPineSword;
	public static Item healersBlade;
	public static Item terronicBlade;
	public static Item developerSword;

	public static Item slugSlime;

	public static Item eucaPortalGem;
	public static Item depthsPortalGem;
	public static Item corbaPortalGem;
	public static Item terraniaPortalGem;
	public static Item cloudiaPortalGem;
	//public static Item wastelandPortalGem = new ItemMod("wastelandPortalGem");

	public static Item calciaOrb;
	public static Item netherBeastOrb;
	public static Item witheringBeastOrb;
	public static Item eudorOrb;
	public static Item blazierOrb;
	public static Item rocSpawnEgg;
	public static Item soulWatcherOrb;
	public static Item sentryKingOrb;
	public static Item loggerOrb;
	public static Item thunderbirdOrb;
	public static Item mysteriousDisk;
	public static Item corallatorOrb;
	public static Item scaleOrb;
	public static Item enchantedTerrastar;

	public static Item weakDarkEnergyPotion;
	public static Item strongDarkEnergyPotion;
	public static Item weakEssencePotion;
	public static Item strongEssencePotion;

	public static Item hellstoneHelmet;
	public static Item hellstoneChest;
	public static Item hellstoneLegs;
	public static Item hellstoneBoots;

	public static Item flairiumHelmet;
	public static Item flairiumChest;
	public static Item flairiumLegs;
	public static Item flairiumBoots;

	public static Item celestiumHelmet;
	public static Item celestiumChest;
	public static Item celestiumLegs;
	public static Item celestiumBoots;

	public static Item luniumHelmet;
	public static Item luniumChest;
	public static Item luniumLegs;
	public static Item luniumBoots;

	public static Item shadiumHelmet;
	public static Item shadiumChest;
	public static Item shadiumLegs;
	public static Item shadiumBoots;

	public static Item sapphireHelmet;
	public static Item sapphireChest;
	public static Item sapphireLegs;
	public static Item sapphireBoots;

	public static Item gorbiteHelmet;
	public static Item gorbiteChest;
	public static Item gorbiteLegs;
	public static Item gorbiteBoots;

	public static Item orbaditeHelmet;
	public static Item orbaditeChest;
	public static Item orbaditeLegs;
	public static Item orbaditeBoots;

	public static Item flameHelmet;
	public static Item flameChest;
	public static Item flameLegs;
	public static Item flameBoots;

	public static Item twilightHelmet;
	public static Item twilightChest;
	public static Item twilightLegs;
	public static Item twilightBoots;

	public static Item leapersHelmet;
	public static Item leapersChest;
	public static Item leapersLegs;
	public static Item leapersBoots;

	public static Item snakeskinHelmet;
	public static Item snakeskinChest;
	public static Item snakeskinLegs;
	public static Item snakeskinBoots;

	public static Item treehuggersHelmet;
	public static Item treehuggersChest;
	public static Item treehuggersLegs;
	public static Item treehuggersBoots;

	public static Item charskullHelmet;
	public static Item charskullChest;
	public static Item charskullLegs;
	public static Item charskullBoots;

	public static Item bronzedHelmet;
	public static Item bronzedChest;
	public static Item bronzedLegs;
	public static Item bronzedBoots;

	public static Item golditeHelmet;
	public static Item golditeChest;
	public static Item golditeLegs;
	public static Item golditeBoots;

	public static Item corbarkHelmet;
	public static Item corbarkChest;
	public static Item corbarkLegs;
	public static Item corbarkBoots;

	public static Item crystalFlakeHelmet;
	public static Item crystalFlakeChest;
	public static Item crystalFlakeLegs;
	public static Item crystalFlakeBoots;

	public static Item darklyHelmet;
	public static Item darklyChest;
	public static Item darklyLegs;
	public static Item darklyBoots;

	public static Item depthsHelmet;
	public static Item depthsChest;
	public static Item depthsLegs;
	public static Item depthsBoots;

	public static Item enlightenerHelmet;
	public static Item enlightenerChest;
	public static Item enlightenerLegs;
	public static Item enlightenerBoots;

	public static Item fireboundHelmet;
	public static Item fireboundChest;
	public static Item fireboundLegs;
	public static Item fireboundBoots;

	public static Item frostbittenHelmet;
	public static Item frostbittenChest;
	public static Item frostbittenLegs;
	public static Item frostbittenBoots;

	public static Item hollowHelmet;
	public static Item hollowChest;
	public static Item hollowLegs;
	public static Item hollowBoots;

	public static Item lightstoneHelmet;
	public static Item lightstoneChest;
	public static Item lightstoneLegs;
	public static Item lightstoneBoots;

	public static Item livegreenHelmet;
	public static Item livegreenChest;
	public static Item livegreenLegs;
	public static Item livegreenBoots;

	public static Item starlightHelmet;
	public static Item starlightChest;
	public static Item starlightLegs;
	public static Item starlightBoots;

	public static Item bloodcrustHelmet;
	public static Item bloodcrustChest;
	public static Item bloodcrustLegs;
	public static Item bloodcrustBoots;

	public static Item blazehornHelmet;
	public static Item blazehornChest;
	public static Item blazehornLegs;
	public static Item blazehornBoots;

	public static Item bleedrockHelmet;
	public static Item bleedrockChest;
	public static Item bleedrockLegs;
	public static Item bleedrockBoots;

	/*public static Item rockyBattleaxe = new ItemBattleAxe("rockyBattleaxe", "Rocky Battleaxe", EssenceToolMaterial.ROCKY_BATTLEAXE);
	public static Item crystalizedBattleaxe = new ItemBattleAxe("crystalizedBattleaxe", "Crystalized Battleaxe", EssenceToolMaterial.CRYSTAL_BATTLEAXE);
	public static Item backBiter = new ItemBattleAxe("backBiter", "Back Biter", EssenceToolMaterial.BACK_BITER);
	public static Item dawnBreaker = new ItemBattleAxe("dawnBreaker", "Dawn Breaker", EssenceToolMaterial.DAWN_BREAKER);
	public static Item tempestBattleaxe = new ItemBattleAxe("tempestBattleaxe", "Tempest Battleaxe", EssenceToolMaterial.TEMPEST_BATTLEAXE);
	public static Item bronzedBattleaxe = new ItemBattleAxe("bronzedBattleaxe", "Bronzed Battleaxe", EssenceToolMaterial.BRONZED_BATTLEAXE);
	public static Item celestiteBattleaxe = new ItemBattleAxe("celestiteBattleaxe", "Celestite Battleaxe", EssenceToolMaterial.CELESTITE_BATTLEAXE);
	public static Item storumBattleaxe = new ItemBattleAxe("storumBattleaxe", "Storum Battleaxe", EssenceToolMaterial.STORUM_BATTLEAXE);
	public static Item celekiumBattleaxe = new ItemBattleAxe("celekiumBattleaxe", "Celekium Battleaxe", EssenceToolMaterial.CELEKIUM_BATTLEAXE);
	public static Item thunderbirdBattleaxe = new ItemBattleAxe("thunderbirdBattleaxe", "Thunderbird Battleaxe", EssenceToolMaterial.THUNDERBIRD_BATTLEAXE);
	 */
	public static Item staffOfCrystal;
	public static Item staffOfDivineStone;
	public static Item staffOfHellstone;
	public static Item doomsBringer;
	public static Item conjuringStaff;
	public static Item staffOfEnlightenment;
	public static Item staffOfGreenpace;
	public static Item wizardsStar;
	public static Item teleportationStaff;
	public static Item overgrownStaff;

	//public static Item flameArrow = new ItemMod("flameArrow", EssenceTabs.ranged);
	public static Item essenceArrow;

	public static Item flameBow;
	public static Item poisonBow;
	public static Item darknessBow;
	public static Item frozenBow;
	public static Item staringBow;
	public static Item deathPiercerBow;
	public static Item fusionBow;
	public static Item springBow;
	public static Item starlightBow;
	public static Item wastefulBow;
	public static Item flamingBow;
	public static Item blazingBow;
	public static Item darkEnforcer;
	public static Item depthsBow;
	public static Item frostbittenBow;
	public static Item frostyBow;
	public static Item charredBow;
	public static Item fluffyBow;
	public static Item golemBow;
	public static Item loggersBow;
	public static Item overgrownBow;
	public static Item overseerBow;
	public static Item woodlandBow;
	public static Item rocsWing;
	public static Item scaleBow;
	public static Item mantleBow;
	public static Item coreExpender;
	public static Item royalBow;
	public static Item darkTerraBow;
	public static Item lavenderBow;
	public static Item terralightBow;
	public static Item terrianBow;

	public static Item fireWand;
	public static Item iceWand;
	public static Item lightningWand;

	public static Item hammerCreative;
	public static Item earthenHammer;
	public static Item flamingHammer;
	public static Item nethicHammer;
	public static Item withicHammer;
	public static Item royalHammer;
	public static Item overgrownHammer;
	public static Item rockyHammer;
	public static Item crystalizedHammer;

	public static Item chaosCannon;
	public static Item rockLauncher;
	public static Item netherPlasma;
	public static Item oceanPlasma;
	public static Item forestPlasma;
	public static Item eyeBlaster;

	public static Item greenGem;
	public static Item purpleGem;
	public static Item blueGem;
	public static Item yellowGem;

	public static Item eucaTablet;

	public static Item wandBase;
	public static Item staffBase;

	public static Item frostyGift;

	public static Item firestoneClump;

	//TODO
	public static Item friedFlamingGhastTentacale;
	public static Item flamingGhastTentacle;
	public static Item friedGhastTentacale;
	public static Item ghastTentacle;
	public static Item friedEgg;
	public static Item floroPedal;
	public static Item tomato;
	public static Item airMelon;
	public static Item glowshroom;
	public static Item terrashroom;
	public static Item corveggies;
	public static Item crackenCanes;
	public static Item crakeBulb;
	public static Item spineberries;
	public static Item zatPedal;
	public static Item glowa;
	public static Item mintCandyCane;
	public static Item fruityCandyCane;
	public static Item cherryCandyCane;
	public static Item peppermint;
	public static Item jellyBeans;
	public static Item chocolate;
	public static Item vanillaWafer;
	public static Item bleedheart;

	public static Item sizzleberry;
	public static Item bradberry;
	public static Item tangleberry;
	public static Item juiceberry;
	public static Item bogberry;

	public static Item goldenSteak;
	public static Item goldenSteakOP;

	public static Item goldenPotato;
	public static Item goldenPotatoOP;

	public static Item goldenPork;
	public static Item goldenPorkOP;

	public static Item goldenFish;
	public static Item goldenFishOP;

	public static Item goldenChicken;
	public static Item goldenChickenOP;

	public static Item goldenRabbit;
	public static Item goldenRabbitOP;

	public static Item goldenMutton;
	public static Item goldenMuttonOP;

	public static Item goldenWing;
	public static Item goldenWingOP;

	public static Item eucaMeat;
	public static Item rocMeat;
	public static Item cookedRocMeat;
	public static Item hongoShroom;
	public static Item greenHonglowShroom;
	public static Item redHonglowShroom;
	public static Item blueHonglowShroom;
	public static Item honglowShroom;
	public static Item snakeFlesh;
	public static Item flamingBeef;
	public static Item flamingBeefCooked;

	public static Item underwaterWorldRecord;
	public static Item blueWater;
	public static Item raceStar;
	public static Item compBegins;
	public static Item deepBlue;
	public static Item raceShore;

	public static Item demonicEye;

	public static ItemKnowledge overworldKnowledge;
	public static ItemKnowledge netherKnowledge;
	public static ItemKnowledge endKnowledge;
	public static ItemKnowledge boilKnowledge;
	public static ItemKnowledge frozenKnowledge;
	public static ItemKnowledge eucaKnowledge;
	public static ItemKnowledge depthsKnowledge;
	public static ItemKnowledge corbaKnowledge;
	public static ItemKnowledge cloudiaKnowledge;
	//public static ItemKnowledge wastelandsKnowledge = new ItemKnowledge("wastelandsKnowledge", EnumKnowledge.WASTELANDS);
	//public static ItemKnowledge lithiumKnowledge = new ItemKnowledge("lithiumKnowledge", EnumKnowledge.LITHIUM);
	//public static ItemKnowledge libraryKnowledge = new ItemKnowledge("libraryKnowledge", EnumKnowledge.LIBRARY);
	//public static ItemKnowledge blazeKnowledge = new ItemKnowledge("blazeKnowledge", EnumKnowledge.BLAZE);
	//public static ItemKnowledge witherKnowledge = new ItemKnowledge("witherKnowledge", EnumKnowledge.WITHER);

	public static ItemMod boilingPointEssence;
	public static ItemMod cloudiaEssence;
	public static ItemMod corbaEssence;
	public static ItemMod depthsEssence;
	public static ItemMod eucaEssence;
	public static ItemMod frozenLandsEssence;
	//public static ItemMod wastelandsEssence = new ItemMod("wastelandsEssence");
	//public static ItemMod lithiumEssence = new ItemMod("lithiumEssence");
	//public static ItemMod nethicEssence = new ItemMod("nethicEssence");
	//public static ItemMod subterrianEssence = new ItemMod("subterrianEssence");
	//public static ItemMod withicEssence = new ItemMod("withicEssence");
	//public static ItemMod blazesEssence = new ItemMod("blazesEssence");

	//TODO
	public static Item demonicBomb;
	public static Item fireBomb;

	public static Item boilingPiercer;
	public static Item nethicPiercer;
	public static Item frozenPiercer;
	public static Item eucaPiercer;
	public static Item depthsPiercer;
	public static Item corbaPiercer;
	public static Item frostbittenPiercer;
	public static Item frostyPiercer;
	public static Item sunsetPiercer;
	public static Item skyPiercer;

	public static Item moltenKnife;
	public static Item aquaticKnife;
	public static Item bloodKnife;
	public static Item charredKnife;
	public static Item sizzlingKnife;
	public static Item iridium;
	
	public static void init() {
		heartSml = new ItemHealth("heartSml", "Heart: Tier 1", 1, 2, 0.2F, false, false, 60, false, true);
		heartMed = new ItemHealth("heartMed", "Heart: Tier 2", 2, 2, 0.2F, false, false, 60, false, true);
		heartLrg = new ItemHealth("heartLrg", "Heart: Tier 3", 4, 2, 0.2F, false, false, 60, false, true);
		heartUlt = new ItemHealth("heartUlt", "Heart: Ultimate", 8, 2, 0.2F, false, false, 60, false, true);
		heartSentry = new ItemHealth("heartSen", "Sentry's Heart", 10, 2, 0.2F, false, false, 20, true, false);
		hellstoneIngot = new ItemMod("hellstoneIngot", "Bloodcrust Ingot");
		shadiumIngot = new ItemMod("shadiumIngot", "Shadium Ingot");
		celestiumIngot = new ItemMod("celestiumIngot", "Celestium Ingot");
		mekyumIngot = new ItemMod("mekyumIngot", "Mekyum Ingot");
		koriteIngot = new ItemMod("koriteIngot", "Korite Ingot");
		storonIngot = new ItemMod("storonIngot", "Storon Ingot");
		luniumIngot = new ItemMod("luniumIngot", "Lunium Ingot");
		flairiumIngot = new ItemMod("flairiumIngot", "Flairium Ingot");
		ash = new ItemMod("ash", "Ash");
		sapphire = new ItemMod("sapphire", "Sapphire Gem");
		blazium = new ItemMod("blazium", "Blazium Gem");
		enderilliumShard = new ItemMod("enderilliumShard", "Enderillium Shard");
		orbaditeIngot = new ItemMod("orbaditeIngot", "Orbadite Ingot");
		gorbiteGem = new ItemMod("gorbiteGem", "Gorbite Gem");
		desIngot = new ItemMod("desIngot", "Des Ingot");

		elderKey = new ItemMod("elderKey", "Elder Key").setMaxStackSize(1);
		boilPowder = new ItemMod("boilPowder", "Boiling Powder");
		blazingFireball = new ItemMod("blazingFireball", "Blazing Fireball");
		hellTurtleShell = new ItemMod("hellTurtleShell", "Hell Turtle Shell");
		sizzlingEye = new ItemMod("sizzlingEye", "Sizzling Eye");

		sentryEye = new ItemSentryEye("sentryEye", "Sentry Eye");
		boilingSkull = new ItemMod("boilingskull", "Boiling Skull");
		overgrownNatureBall = new ItemMod("overgrownNatureBall", "Overgrown Natureball");
		overseeingTablet = new ItemMod("overseeingTablet", "Overseeing Tablet");
		overseeingEye = new ItemMod("overseeingEye", "Overseeing Eye");
		darkCrystal = new ItemMod("darkCrystal", "Dark Crystal");
		blankKnowledge = new ItemMod("blankKnowledge", "Blank Knowledge");
		darkOrb = new ItemMod("darkOrb", "Dark Orb");
		depthsFlake = new ItemMod("depthsFlake", "Depths Flake");
		beastlyStomach = new ItemMod("beastlyStomach", "Beastly Stomach");
		rocFeather = new ItemMod("rocFeather", "Essence Feather");
		coldClump = new ItemMod("coldClump", "Cold Clump");
		crystalEye = new ItemMod("crystalEye", "Crystal Eye");
		crystalFlake = new ItemMod("crystalFlake", "Crystal Flake");
		freezingTablet = new ItemMod("freezingTablet", "Freezing Tablet");
		frostFlake = new ItemMod("frostFlake", "Frost Flake");
		frostGem = new ItemMod("frostGem", "Frost Gem");
		frozenIceball = new ItemMod("frozenIceball", "Frozen Iceball");
		snowsheet = new ItemMod("snowsheet", "Snowsheet");
		gateKeys = new ItemMod("gateKeys", "Gate Keys");
		goldClump = new ItemMod("goldClump", "Gold Clump");
		silverClump = new ItemMod("silverClump", "Silver Clump");
		golderDust = new ItemMod("golderDust", "Golder Dust");
		shimmerdust = new ItemMod("shimmerdust", "Shimmer Dust");
		royalDisk = new ItemMod("royalDisk", "Royal Disk");
		metalDisk = new ItemMod("metalDisk", "Metal Disk");
		demonicDust = new ItemMod("demonicDust", "Demonic Dust");
		demonicBone = new ItemMod("demonicBone", "Demonic Bone");
		withicDust = new ItemMod("withicDust", "Withic Dust");
		cloudiaOrb = new ItemMod("cloudiaOrb", "Cloudia Orb");
		fluffyFeather = new ItemMod("fluffyFeather", "Fluffy Feather");
		golemChunk = new ItemMod("golemChunk", "Golem Chunk");
		luniteChunk = new ItemMod("luniteChunk", "Lunite Chunk");
		corbaStick = new ItemMod("corbaStick", "Corba Stick");
		spyclopseEye = new ItemMod("spyclopseEye", "Spyclopse Eye");
		caveCrystal = new ItemMod("caveCrystal", "Cave Crystal");
		caveDust = new ItemMod("caveDust", "Cave Dust");
		stoneClump = new ItemMod("stoneClump", "Stone Clump");
		enchantedLeaf = new ItemMod("enchantedLeaf", "Enchanted Leaf");
		baseEssence = new ItemMod("baseEssence", "Base Essence");
		cloudPuff = new ItemMod("cloudPuff", "Cloud Puff");
		collectorRock = new ItemMod("collectorRock", "Collector Rock");
		natureTablet = new ItemMod("natureTablet", "Nature Tablet");
		horn = new ItemMod("horn", "Horn");
		scale = new ItemMod("scale", "Scale");
		reinforcedStoneIngot = new ItemMod("reinforcedStoneIngot", "Reinforced Stone Ingot");
		reinforcedCrystalIngot = new ItemMod("reinforcedCrystalIngot", "Reinforced Crystal Ingot");
		crystalBall = new ItemMod("crystalBall", "Crystal Ball");
		stoneStick = new ItemMod("stoneStick", "Stone Stick");
		darkTerrarianSoil = new ItemMod("darkTerrarianSoil", "Dark Terrarian Soil");
		earthenCrystal = new ItemMod("earthenCrystal", "EarthenCrystal");
		lightTerrarianSoil = new ItemMod("lightTerrarianSoil", "Light Terrarian Soil");
		terrastar = new ItemMod("terrastar", "Terrastar");
		purplePowder = new ItemMod("purplePowder", "Purple Powder");
		hellShards = new ItemMod("hellShards", "Hell Shards");
		hellcrustIngot = new ItemMod("hellcrustIngot", "Hellcrust Ingot");
		flamingSprocket = new ItemMod("flamingSprocket", "Flaming Sprocket");
		flamingSpring = new ItemMod("flamingSpring", "Flaming Spring");
		blood = new ItemMod("blood", "Blood");
		concentratedBlood = new ItemMod("concentratedBlood", "Concentrated Blood");
		snakeSkin = new ItemMod("snakeSkin", "Snake Skin");
		withicSpine = new ItemMod("withicSpine", "Withic Spine");
		lostSoul = new ItemSoul("lostSoul", "Lost Soul");
		withicSoul = new ItemMod("withicSoul", "Withic Soul");
		eucaPortalPiece = new ItemMod("eucaPortalPiece", "Euca Portal Piece");
		eucaPortalPiece_1 = new ItemMod("eucaPortalPiece_1", "Euca Portal Piece");
		eucaPortalPiece_0 = new ItemMod("eucaPortalPiece_0", "Euca Portal Piece");
		flamingHide = new ItemMod("flamingHide", "Flaming Hide");
		boilKey = new ItemMod("boilKey", "Boil Key").setMaxStackSize(1);;
		darkKey = new ItemMod("darkkey", "Dark Key").setMaxStackSize(1);;

		hellstoneDust = new ItemMod("hellstoneDust", "Bloodcrust Dust");
		shadiumDust = new ItemMod("shadiumDust", "Shadium Dust");
		celestiumDust = new ItemMod("celestiumDust", "Celestium Dust");
		luniumDust = new ItemMod("luniumDust", "Lunium Dust");
		flairiumDust = new ItemMod("flairiumDust", "Flairium Dust");
		ashDust = new ItemMod("ashDust", "Ash Dust");
		sapphireDust = new ItemMod("sapphireDust", "Sapphire Dust");
		enderilliumDust = new ItemMod("enderilliumDust", "Enderillium Dust");
		gorbiteDust = new ItemMod("gorbiteDust", "Gorbite Dust");
		orbaditeDust = new ItemMod("orbaditeDust", "Orbadite Dust");
		diamondDust = new ItemMod("diamondDust", "Diamond Dust");
		goldDust = new ItemMod("goldDust", "Gold Dust");
		ironDust = new ItemMod("ironDust", "Iron Dust");

		hellstoneClump = new ItemMod("hellstoneClump", "Bloodcrust Clump");
		shadiumClump = new ItemMod("shadiumClump", "Shadium Clump");
		luniumClump = new ItemMod("luniumClump", "Lunium Clump");
		spawnerClump = new ItemMod("spawnerClump", "Spawner Clump");
		spawnerBar = new ItemMod("spawnerBar", "Spawner Bar");

		flameCoin = new ItemFlameCoin("flameCoin", "Flame Coin");
		essenceDetractor = new ItemDetractor("essenceDetractor", "Essence Detractor", 1, false, true);
		essenceAttractor = new ItemDetractor("essenceAttractor", "Essence Attractor", 1, true, false);

		rockChunk = new ItemMod("rockChunk", "Rock Chunk");
		rockShard = new ItemMod("rockShard", "Rock Shard");
		//plasmaBall = new ItemMod("plasmaBall");

		hellstoneMultiTool = new ItemMultiTool("hellstoneMultiTool", "Bloodcrust Multi Tool", JourneyToolMaterial.HELLSTONE_MULTI_TOOL, 1750);
		shadiumMultiTool = new ItemMultiTool("shadiumMultiTool", "Shadium Multi Tool", JourneyToolMaterial.SHADIUM_MULTI_TOOL, 1670);
		celestiumMultiTool = new ItemMultiTool("celestiumMultiTool", "Celestium Multi Tool", JourneyToolMaterial.CELESTIUM_MULTI_TOOL, 1820);
		luniumMultiTool = new ItemMultiTool("luniumMultiTool", "Lunium Multi Tool", JourneyToolMaterial.LUNIUM_MULTI_TOOL, 1670);
		flairiumMultiTool = new ItemMultiTool("flairiumMultiTool", "Flairium Multi Tool", JourneyToolMaterial.FLAIRIUM_MULTI_TOOL, 1202);
		sapphireMultiTool = new ItemMultiTool("sapphireMultiTool", "Sapphire Multi Tool", JourneyToolMaterial.SAPPHIRE_MULTI_TOOL, 2456);
		gorbiteMultiTool = new ItemMultiTool("gorbiteMultiTool", "Gorbite Multi Tool", JourneyToolMaterial.GORBITE_MULTI_TOOL, 2115);
		orbaditeMultiTool = new ItemMultiTool("orbaditeMultiTool", "Orbadite Multi Tool", JourneyToolMaterial.ORBADITE_MULTI_TOOL, 2115);
		desMultiTool = new ItemMultiTool("desMultiTool", "Des Multi Tool", JourneyToolMaterial.DES_MULTI_TOOL, 2102);
		koriteMultiTool = new ItemMultiTool("koriteMultiTool", "Korite Multi Tool", JourneyToolMaterial.KORITE_MULTI_TOOL, 1820);
		storonMultiTool = new ItemMultiTool("storonMultiTool", "Storon Multi Tool", JourneyToolMaterial.KORITE_MULTI_TOOL, 1820);
		mekyumMultiTool = new ItemMultiTool("mekyumMultiTool", "Mekyum Multi Tool", JourneyToolMaterial.KORITE_MULTI_TOOL, 1820);
		woodMultiTool = new ItemMultiTool("woodMultiTool", "Wooden Multi Tool", JourneyToolMaterial.WOOD_MULTI_TOOL, 60);
		stoneMultiTool = new ItemMultiTool("stoneMultiTool", "Stone Multi Tool", JourneyToolMaterial.STONE_MULTI_TOOL, 132);
		ironMultiTool = new ItemMultiTool("ironMultiTool", "Iron Multi Tool", JourneyToolMaterial.IRON_MULTI_TOOL, 251);
		goldMultiTool = new ItemMultiTool("goldMultiTool", "Gold Multi Tool", JourneyToolMaterial.GOLD_MULTI_TOOL, 33);
		diamondMultiTool = new ItemMultiTool("diamondMultiTool", "Diamond Multi Tool", JourneyToolMaterial.DIAMOND_MULTI_TOOL, 1562);

		hellstonePickaxe = new ItemModPickaxe("hellstonePickaxe", "Bloodcrust Pickaxe", JourneyToolMaterial.HELLSTONE);
		shadiumPickaxe = new ItemModPickaxe("shadiumPickaxe", "Shadium Pickaxe", JourneyToolMaterial.SHADIUM);
		celestiumPickaxe = new ItemModPickaxe("celestiumPickaxe", "Celestium Pickaxe", JourneyToolMaterial.CELESTIUM);
		luniumPickaxe = new ItemModPickaxe("luniumPickaxe", "Lunium Pickaxe", JourneyToolMaterial.LUNIUM);
		flairiumPickaxe = new ItemModPickaxe("flairiumPickaxe", "Flairium Pickaxe", JourneyToolMaterial.FLAIRIUM);
		sapphirePickaxe = new ItemModPickaxe("sapphirePickaxe", "Sapphire Pickaxe", JourneyToolMaterial.SAPPHIRE);
		gorbitePickaxe = new ItemModPickaxe("gorbitePickaxe", "Gorbite Pickaxe", JourneyToolMaterial.GORBITE);
		orbaditePickaxe = new ItemModPickaxe("orbaditePickaxe", "Orbadite Pickaxe", JourneyToolMaterial.ORBADITE);
		desPickaxe = new ItemModPickaxe("desPickaxe", "Des Pickaxe", JourneyToolMaterial.DES);
		koritePickaxe = new ItemModPickaxe("koritePickaxe", "Korite Pickaxe", JourneyToolMaterial.KORITE);
		storonPickaxe = new ItemModPickaxe("storonPickaxe", "Storon Pickaxe", JourneyToolMaterial.KORITE);
		mekyumPickaxe = new ItemModPickaxe("mekyumPickaxe", "Mekyum Pickaxe", JourneyToolMaterial.KORITE);

		hellstoneShovel = new ItemModShovel("hellstoneShovel", "Bloodcrust Shovel", JourneyToolMaterial.HELLSTONE);
		shadiumShovel = new ItemModShovel("shadiumShovel", "Shadium Shovel", JourneyToolMaterial.SHADIUM);
		celestiumShovel = new ItemModShovel("celestiumShovel", "Celestium Shovel", JourneyToolMaterial.CELESTIUM);
		luniumShovel = new ItemModShovel("luniumShovel", "Lunium Shovel", JourneyToolMaterial.LUNIUM);
		flairiumShovel = new ItemModShovel("flairiumShovel", "Flairium Shovel", JourneyToolMaterial.FLAIRIUM);
		sapphireShovel = new ItemModShovel("sapphireShovel", "Sapphire Shovel", JourneyToolMaterial.SAPPHIRE);
		gorbiteShovel = new ItemModShovel("gorbiteShovel", "Gorbite Shovel", JourneyToolMaterial.GORBITE);
		orbaditeShovel = new ItemModShovel("orbaditeShovel", "Orbadite Shovel", JourneyToolMaterial.ORBADITE);
		desShovel = new ItemModShovel("desShovel", "Des Shovel", JourneyToolMaterial.DES);
		koriteShovel = new ItemModShovel("koriteShovel", "Korite Shovel", JourneyToolMaterial.KORITE);
		storonShovel = new ItemModShovel("storonShovel", "Storon Shovel", JourneyToolMaterial.KORITE);
		mekyumShovel = new ItemModShovel("mekyumShovel", "Mekyum Shovel", JourneyToolMaterial.KORITE);

		/*hellstoneAxe = new ItemModAxe("hellstoneAxe", "Bloodcrust Axe", EssenceToolMaterial.HELLSTONE);
		shadiumAxe = new ItemModAxe("shadiumAxe", "Shadium Axe", EssenceToolMaterial.SHADIUM);
		celestiumAxe = new ItemModAxe("celestiumAxe", "Celestium Axe", EssenceToolMaterial.CELESTIUM);
		luniumAxe = new ItemModAxe("luniumAxe", "Lunium Axe", EssenceToolMaterial.LUNIUM);
		flairiumAxe = new ItemModAxe("flairiumAxe", "Flairium Axe", EssenceToolMaterial.FLAIRIUM);
		sapphireAxe = new ItemModAxe("sapphireAxe", "Sapphire Axe", EssenceToolMaterial.SAPPHIRE);
		gorbiteAxe = new ItemModAxe("gorbiteAxe", "Gorbite Axe", EssenceToolMaterial.GORBITE);
		orbaditeAxe = new ItemModAxe("orbaditeAxe", "Orbadite Axe", EssenceToolMaterial.ORBADITE);
		desAxe = new ItemModAxe("desAxe", "Des Axe", EssenceToolMaterial.DES);
		koriteAxe = new ItemModAxe("koriteAxe", "Korite Axe", EssenceToolMaterial.KORITE);
		storonAxe = new ItemModAxe("storonAxe", "Storon Axe", EssenceToolMaterial.KORITE);
		mekyumAxe = new ItemModAxe("mekyumAxe", "Mekyum Axe", EssenceToolMaterial.KORITE);*/

		hellstoneHoe = new ItemModHoe("hellstoneHoe", "Bloodcrust Hoe", JourneyToolMaterial.HELLSTONE);
		shadiumHoe = new ItemModHoe("shadiumHoe", "Shadium Hoe", JourneyToolMaterial.SHADIUM);
		celestiumHoe = new ItemModHoe("celestiumHoe", "Celestium Hoe", JourneyToolMaterial.CELESTIUM);
		luniumHoe = new ItemModHoe("luniumHoe", "Lunium Hoe", JourneyToolMaterial.LUNIUM);
		flairiumHoe = new ItemModHoe("flairiumHoe", "Flairium Hoe", JourneyToolMaterial.FLAIRIUM);
		sapphireHoe = new ItemModHoe("sapphireHoe", "Sapphire Hoe", JourneyToolMaterial.SAPPHIRE);
		gorbiteHoe = new ItemModHoe("gorbiteHoe", "Gorbite Hoe", JourneyToolMaterial.GORBITE);
		orbaditeHoe = new ItemModHoe("orbaditeHoe", "Orbadite Hoe", JourneyToolMaterial.ORBADITE);
		desHoe = new ItemModHoe("desHoe", "Des Hoe", JourneyToolMaterial.DES);
		koriteHoe = new ItemModHoe("koriteHoe", "Korite Hoe", JourneyToolMaterial.KORITE);
		storonHoe = new ItemModHoe("storonHoe", "Storon Hoe", JourneyToolMaterial.KORITE);
		mekyumHoe = new ItemModHoe("mekyumHoe", "Storon Hoe", JourneyToolMaterial.KORITE);

		multiToolOfEternalSmelting = new ItemMultiTool("multiToolOfEternalSmelting", "Multi Tool of Eternal Smelting", JourneyToolMaterial.SMELTING_TOOL, 512);

		hellstoneSword = new ItemFireSword("hellstoneSword", "Bloodcrust Sword", JourneyToolMaterial.HELLSTONE_SWORD);
		shadiumSword = new ItemFreezeSword("shadiumSword", "Shadium Sword", JourneyToolMaterial.SHADIUM_SWORD);
		celestiumSword = new ItemModSword("celestiumSword", "Celestium Sword", JourneyToolMaterial.CELESTIUM_SWORD);
		luniumSword = new ItemModSword("luniumSword", "Lunium Sword", JourneyToolMaterial.LUNIUM_SWORD);
		flairiumSword = new ItemFireSword("flairiumSword", "Flairium Sword", JourneyToolMaterial.FLAIRIUM_SWORD);
		desSword = new ItemModSword("desSword", "Des Sword", JourneyToolMaterial.DES_SWORD);
		sapphireSword = new ItemModSword("sapphireSword", "Sapphire Sword", JourneyToolMaterial.SAPPHIRE_SWORD);
		gorbiteSword = new ItemModSword("gorbiteSword", "Gorbite Sword", JourneyToolMaterial.GORBITE_SWORD);
		orbaditeSword = new ItemModSword("orbaditeSword", "Orbadite Sword", JourneyToolMaterial.ORBADITE_SWORD);
		poisonSword = new ItemPoisionSword("poisonSword", "Poison Sword", JourneyToolMaterial.POISON_SWORD);
		cloudSlicer = new ItemModSword("cloudSlicer", "Cloud Slicer", JourneyToolMaterial.CLOUD_SLICER);
		dragonsTooth = new ItemModSword("dragonsTooth", "Dragons Tooth", JourneyToolMaterial.DRAGONS_TOOTH);
		netherBeastSword = new ItemRegenSword("netherBeastSword", "Netherbeast Sword", JourneyToolMaterial.NETHER_BEAST_SWORD);
		witheringBeastSword = new ItemWitherSword("witheringBeastSword", "Witheringbeast Sword", JourneyToolMaterial.WITHERING_BEAST_SWORD);
		calciaSword = new ItemModSword("calciaSword", "Calcia Sword", JourneyToolMaterial.CALCIA_SWORD);
		championsSword = new ItemModSword("championsSword", "Champions Sword", JourneyToolMaterial.CHAMPIONS_SWORD);
		theWraith = new ItemModSword("theWraith", "The Wraith", JourneyToolMaterial.THE_WRAITH);
		bubbleSword = new ItemBubbleSword("bubbleSword", "Bubble Sword", JourneyToolMaterial.BUBBLE_SWORD);
		boilingBlade = new ItemFireSword("boilingBlade", "Boiling Blade", JourneyToolMaterial.BOILING_BLADE);
		loggersSword = new ItemLoggersSword("loggersSword", "Loggers Sword", JourneyToolMaterial.LOGGERS_SWORD);
		naturesBlade = new ItemStunWitherSword("naturesBlade", "Natures Blade", JourneyToolMaterial.NATURES_BLADE);
		depthsDarksword = new ItemStunWitherSword("depthsDarksword", "Depths Darksword", JourneyToolMaterial.DEPTHS_DARKSWORD);
		depthsSlayer = new ItemStunSword("depthsSlayer", "Depths Slayer", JourneyToolMaterial.DEPTHS_SLAYER);
		snowShoveler = new ItemStunWitherSword("snowShoveler", "Snow Shoveler", JourneyToolMaterial.SNOW_SHOVELER);
		frostySword = new ItemStunSword("frostySword", "Frosty Sword", JourneyToolMaterial.FROSTY_SWORD);
		frostbittenSword = new ItemStunSword("frostbittenSword", "Frostbitten Sword", JourneyToolMaterial.FROSTBITTEN_SWORD);
		treeHugger = new ItemPoisionSword("treeHugger", "Tree Hugger", JourneyToolMaterial.TREE_HUGGER);
		coreMender = new ItemFireSword("coreMender", "Core Mender", JourneyToolMaterial.CORE_MENDER);
		royalBlade = new ItemPoisionSword("royalBlade", "Royal Blade", JourneyToolMaterial.ROYAL_BLADE);
		royalStabber = new ItemPoisionSword("royalStabber", "Royal Stabber", JourneyToolMaterial.ROYAL_STABBER);
		rocSword = new ItemModSword("rocSword", "Roc Sword", JourneyToolMaterial.ROC_SWORD);
		swordOfTheThunderbird = new ItemModSword("swordOfTheThunderbird", "Sword of the Thunderbird", JourneyToolMaterial.SWORD_THUNDERBIRD);
		bloodwieldSword = new ItemHealthSword("bloodWieldSword", "Blood Wielder", JourneyToolMaterial.BLOODWIELD_SWORD, 1);
		charredBlade = new ItemWitherSword("charredBlade", "Charred Blade", JourneyToolMaterial.CHARRED_BLADE);
		sizzlerSword = new ItemFireWitherSword("sizzlerSword", "Sizzler Sword", JourneyToolMaterial.SIZZLER_SWORD);
		fluffyBlade = new ItemNightvisionHealthSword("fluffyBlade", "Fluffy Blade", JourneyToolMaterial.FLUFFY_BLADE);
		golemSword = new ItemModSword("golemSword", "Golem Sword", JourneyToolMaterial.GOLEM_SWORD);
		thunderblade = new ItemPoisonHealthSword("thunderblade", "Thunder Blade", JourneyToolMaterial.THUNDERBLADE, 1.5F);
		sentrySword = new ItemFireHealthSword("sentrySword", "Sentry Sword", JourneyToolMaterial.SENTRY_SWORD, 2);
		crystalBlade = new ItemModSword("crystalBlade", "Crystal Blade", JourneyToolMaterial.CRYSTAL_BLADE);
		starlightBlade = new ItemModSword("starlightBlade", "Starlight Blade", JourneyToolMaterial.STARLIGHT_BLADE);
		koriteSword = new ItemModSword("koriteSword", "Korite Sword", JourneyToolMaterial.KORITE_SWORD);
		storonSword = new ItemModSword("storonSword", "Storon Sword", JourneyToolMaterial.KORITE_SWORD);
		mekyumSword = new ItemModSword("mekyumSword", "Mekyum Sword", JourneyToolMaterial.KORITE_SWORD);
		pedalSword = new ItemModSword("pedalSword", "Pedal Sword", JourneyToolMaterial.PEDAL_SWORD);
		withicBlade = new ItemModSword("withicBlade", "Withic Blade", JourneyToolMaterial.WITHIC_BLADE);
		reinforcedCrystalSword = new ItemModSword("reinforcedCrystalSword", "Reinforced Crystal Sword", JourneyToolMaterial.RE_CRYSTAL_SWORD);
		reinforcedStoneSword = new ItemModSword("reinforcedStoneSword", "Reinforced Stone Sword", JourneyToolMaterial.RE_STONE_SWORD);
		terralightBlade = new ItemModSword("terralightBlade", "Terralight Blade", JourneyToolMaterial.TERRALIGHT_BLADE);
		terranaSword = new ItemModSword("terranaSword", "Terrana Sword", JourneyToolMaterial.TERRANA_SWORD);
		terrolicaSword = new ItemNightvisionSword("terrolicaSword", "Terrolica Sword", JourneyToolMaterial.TERROLICA_SWORD);
		voliteSword = new ItemModSword("voliteSword", "Volite Sword", JourneyToolMaterial.VOLITE_SWORD);
		kingsSword = new ItemFireHealthSword("kingsSword", "Kings Sword", JourneyToolMaterial.KINGS_SWORD, 1);
		demonicSword = new ItemWitherSword("demonicSword", "Wither Sword", JourneyToolMaterial.DEMONIC_SWORD);
		vinestrandBlade = new ItemPoisionSword("vinestrandBlade", "Vinestrand Blade", JourneyToolMaterial.VINESTRAND_BLADE);
		darkPineSword = new ItemWitherSword("darkPineSword", "Dark Pine Sword", JourneyToolMaterial.DARK_PINE_SWORD);

		healersBlade = new ItemHealthSword("healersBlade", "Healers Blade", JourneyToolMaterial.HEALERS_BLADE, 1);
		terronicBlade = new ItemHealthSword("terronicBlade", "Terronic Blade", JourneyToolMaterial.TERRONIC_BLADE, 2);

		developerSword = new ItemModSword("developerSword", "Creative Sword", JourneyToolMaterial.DEVELOPER_SWORD).setCreativeTab(JourneyTabs.util);

		slugSlime = new ItemMod("slugSlime", "Slug Slime");


		eucaPortalGem = new ItemMod("eucaPortalGem", "Euca Portal Gem");
		depthsPortalGem = new ItemMod("depthsPortalGem", "Depths Portal Gem");
		corbaPortalGem = new ItemMod("corbaPortalGem", "Corba Portal Gem");
		terraniaPortalGem = new ItemMod("terraniaPortalGem", "Terrania Portal Gem");
		cloudiaPortalGem = new ItemMod("cloudiaPortalGem", "Cloudia Portal Gem");
		//wastelandPortalGem = new ItemMod("wastelandPortalGem");

		calciaOrb = new ItemNetherBossSpawner("calciaOrb", "Calcia Orb");
		netherBeastOrb = new ItemNetherBossSpawner("netherBeastOrb", "Nether Beast Orb");
		witheringBeastOrb = new ItemNetherBossSpawner("witheringBeastOrb", "Withering Soul");
		eudorOrb = new ItemSpecificDimensionSpawner(Config.euca, "eudorOrb", "Valuable Crown", "Euca");
		blazierOrb = new ItemSpecificDimensionSpawner(Config.boil, "blazierOrb", "Burning Fireball", "Boiling Point");
		rocSpawnEgg = new ItemSpecificDimensionSpawner(0, "rocPetSpawnEgg", "Pet Roc Egg", "Overworld");
		soulWatcherOrb = new ItemNetherBossSpawner("soulWatcherOrb", "Soulless Eye");
		sentryKingOrb = new ItemSpecificDimensionSpawner(Config.corba, "sentryKingOrb", "Eye of the Sentry", "Corba");
		loggerOrb = new ItemSpecificDimensionSpawner(Config.corba, "loggerOrb", "Enchanted Log", "Corba");
		thunderbirdOrb = new ItemSpecificDimensionSpawner(Config.depths, "thunderbirdOrb", "Leader's Pearl", "Depths");
		mysteriousDisk = new ItemSpecificDimensionSpawner(Config.cloudia, "mysteriousDisk", "Mysterious Disk", "Cloudia");
		corallatorOrb = new ItemSpecificDimensionSpawner(Config.euca, "corallatorOrb", "Gem of Peculiar Smelting", "Euca");
		scaleOrb = new ItemSpecificDimensionSpawner(Config.depths, "scaleOrb", "Aquatic Egg", "Depths");
		enchantedTerrastar = new ItemSpecificDimensionSpawner(Config.terrania, "enchantedTerrastar", "Enchanted Terrastar", "Terrania");

		weakDarkEnergyPotion = new ItemEssencePotion("weakDarkEnergyPotion", "Weak Dark Energy Potion", false, false);
		strongDarkEnergyPotion = new ItemEssencePotion("strongDarkEnergyPotion", "Strong Dark Energy Potion", true, false);
		weakEssencePotion = new ItemEssencePotion("weakEssencePotion", "Weak Essence Potion", false, true);
		strongEssencePotion = new ItemEssencePotion("strongEssencePotion", "Strong Essence Potion", true, true);

		/*hellstoneHelmet = new ItemModArmor(EnumArmor.HELLSTONE, HEAD);
		hellstoneChest = new ItemModArmor(EnumArmor.HELLSTONE, BODY);
		hellstoneLegs = new ItemModArmor(EnumArmor.HELLSTONE, LEGS);
		hellstoneBoots = new ItemModArmor(EnumArmor.HELLSTONE, BOOTS);

		flairiumHelmet = new ItemModArmor(EnumArmor.FLAIRIUM, HEAD);
		flairiumChest = new ItemModArmor(EnumArmor.FLAIRIUM, BODY);
		flairiumLegs = new ItemModArmor(EnumArmor.FLAIRIUM, LEGS);
		flairiumBoots = new ItemModArmor(EnumArmor.FLAIRIUM, BOOTS);

		celestiumHelmet = new ItemModArmor(EnumArmor.CELESTIUM, HEAD);
		celestiumChest = new ItemModArmor(EnumArmor.CELESTIUM, BODY);
		celestiumLegs = new ItemModArmor(EnumArmor.CELESTIUM, LEGS);
		celestiumBoots = new ItemModArmor(EnumArmor.CELESTIUM, BOOTS);

		luniumHelmet = new ItemModArmor(EnumArmor.LUNIUM, HEAD);
		luniumChest = new ItemModArmor(EnumArmor.LUNIUM, BODY);
		luniumLegs = new ItemModArmor(EnumArmor.LUNIUM, LEGS);
		luniumBoots = new ItemModArmor(EnumArmor.LUNIUM, BOOTS);

		shadiumHelmet = new ItemModArmor(EnumArmor.SHADIUM, HEAD);
		shadiumChest = new ItemModArmor(EnumArmor.SHADIUM, BODY);
		shadiumLegs = new ItemModArmor(EnumArmor.SHADIUM, LEGS);
		shadiumBoots = new ItemModArmor(EnumArmor.SHADIUM, BOOTS);

		sapphireHelmet = new ItemModArmor(EnumArmor.SAPPHIRE, HEAD);
		sapphireChest = new ItemModArmor(EnumArmor.SAPPHIRE, BODY);
		sapphireLegs = new ItemModArmor(EnumArmor.SAPPHIRE, LEGS);
		sapphireBoots = new ItemModArmor(EnumArmor.SAPPHIRE, BOOTS);

		gorbiteHelmet = new ItemModArmor(EnumArmor.GORBITE, HEAD);
		gorbiteChest = new ItemModArmor(EnumArmor.GORBITE, BODY);
		gorbiteLegs = new ItemModArmor(EnumArmor.GORBITE, LEGS);
		gorbiteBoots = new ItemModArmor(EnumArmor.GORBITE, BOOTS);

		orbaditeHelmet = new ItemModArmor(EnumArmor.ORBADITE, HEAD);
		orbaditeChest = new ItemModArmor(EnumArmor.ORBADITE, BODY);
		orbaditeLegs = new ItemModArmor(EnumArmor.ORBADITE, LEGS);
		orbaditeBoots = new ItemModArmor(EnumArmor.ORBADITE, BOOTS);

		flameHelmet = new ItemModArmor(EnumArmor.FLAME, HEAD);
		flameChest = new ItemModArmor(EnumArmor.FLAME, BODY);
		flameLegs = new ItemModArmor(EnumArmor.FLAME, LEGS);
		flameBoots = new ItemModArmor(EnumArmor.FLAME, BOOTS);

		twilightHelmet = new ItemModArmor(EnumArmor.TWILIGHT, HEAD);
		twilightChest = new ItemModArmor(EnumArmor.TWILIGHT, BODY);
		twilightLegs = new ItemModArmor(EnumArmor.TWILIGHT, LEGS);
		twilightBoots = new ItemModArmor(EnumArmor.TWILIGHT, BOOTS);

		leapersHelmet = new ItemModArmor(EnumArmor.LEAPERS, HEAD);
		leapersChest = new ItemModArmor(EnumArmor.LEAPERS, BODY);
		leapersLegs = new ItemModArmor(EnumArmor.LEAPERS, LEGS);
		leapersBoots = new ItemModArmor(EnumArmor.LEAPERS, BOOTS);

		snakeskinHelmet = new ItemModArmor(EnumArmor.SNAKESKIN, HEAD);
		snakeskinChest = new ItemModArmor(EnumArmor.SNAKESKIN, BODY);
		snakeskinLegs = new ItemModArmor(EnumArmor.SNAKESKIN, LEGS);
		snakeskinBoots = new ItemModArmor(EnumArmor.SNAKESKIN, BOOTS);

		treehuggersHelmet = new ItemModArmor(EnumArmor.TREEHUGGERS, HEAD);
		treehuggersChest = new ItemModArmor(EnumArmor.TREEHUGGERS, BODY);
		treehuggersLegs = new ItemModArmor(EnumArmor.TREEHUGGERS, LEGS);
		treehuggersBoots = new ItemModArmor(EnumArmor.TREEHUGGERS, BOOTS);

		charskullHelmet = new ItemModArmor(EnumArmor.CHAR_SKULL, HEAD);
		charskullChest = new ItemModArmor(EnumArmor.CHAR_SKULL, BODY);
		charskullLegs = new ItemModArmor(EnumArmor.CHAR_SKULL, LEGS);
		charskullBoots = new ItemModArmor(EnumArmor.CHAR_SKULL, BOOTS);

		bronzedHelmet = new ItemModArmor(EnumArmor.BRONZED, HEAD);
		bronzedChest = new ItemModArmor(EnumArmor.BRONZED, BODY);
		bronzedLegs = new ItemModArmor(EnumArmor.BRONZED, LEGS);
		bronzedBoots = new ItemModArmor(EnumArmor.BRONZED, BOOTS);

		golditeHelmet = new ItemModArmor(EnumArmor.GOLDITE, HEAD);
		golditeChest = new ItemModArmor(EnumArmor.GOLDITE, BODY);
		golditeLegs = new ItemModArmor(EnumArmor.GOLDITE, LEGS);
		golditeBoots = new ItemModArmor(EnumArmor.GOLDITE, BOOTS);

		corbarkHelmet = new ItemModArmor(EnumArmor.CORBARK, HEAD);
		corbarkChest = new ItemModArmor(EnumArmor.CORBARK, BODY);
		corbarkLegs = new ItemModArmor(EnumArmor.CORBARK, LEGS);
		corbarkBoots = new ItemModArmor(EnumArmor.CORBARK, BOOTS);

		crystalFlakeHelmet = new ItemModArmor(EnumArmor.CRYSTAL_FLAKE, HEAD);
		crystalFlakeChest = new ItemModArmor(EnumArmor.CRYSTAL_FLAKE, BODY);
		crystalFlakeLegs = new ItemModArmor(EnumArmor.CRYSTAL_FLAKE, LEGS);
		crystalFlakeBoots = new ItemModArmor(EnumArmor.CRYSTAL_FLAKE, BOOTS);

		darklyHelmet = new ItemModArmor(EnumArmor.DARKLY, HEAD);
		darklyChest = new ItemModArmor(EnumArmor.DARKLY, BODY);
		darklyLegs = new ItemModArmor(EnumArmor.DARKLY, LEGS);
		darklyBoots = new ItemModArmor(EnumArmor.DARKLY, BOOTS);

		depthsHelmet = new ItemModArmor(EnumArmor.DEPTHS, HEAD);
		depthsChest = new ItemModArmor(EnumArmor.DEPTHS, BODY);
		depthsLegs = new ItemModArmor(EnumArmor.DEPTHS, LEGS);
		depthsBoots = new ItemModArmor(EnumArmor.DEPTHS, BOOTS);

		enlightenerHelmet = new ItemModArmor(EnumArmor.ENLIGHTENER, HEAD);
		enlightenerChest = new ItemModArmor(EnumArmor.ENLIGHTENER, BODY);
		enlightenerLegs = new ItemModArmor(EnumArmor.ENLIGHTENER, LEGS);
		enlightenerBoots = new ItemModArmor(EnumArmor.ENLIGHTENER, BOOTS);

		fireboundHelmet = new ItemModArmor(EnumArmor.FIREBOUND, HEAD);
		fireboundChest = new ItemModArmor(EnumArmor.FIREBOUND, BODY);
		fireboundLegs = new ItemModArmor(EnumArmor.FIREBOUND, LEGS);
		fireboundBoots = new ItemModArmor(EnumArmor.FIREBOUND, BOOTS);

		frostbittenHelmet = new ItemModArmor(EnumArmor.FROSTBITTEN, HEAD);
		frostbittenChest = new ItemModArmor(EnumArmor.FROSTBITTEN, BODY);
		frostbittenLegs = new ItemModArmor(EnumArmor.FROSTBITTEN, LEGS);
		frostbittenBoots = new ItemModArmor(EnumArmor.FROSTBITTEN, BOOTS);

		hollowHelmet = new ItemModArmor(EnumArmor.HOLLOW, HEAD);
		hollowChest = new ItemModArmor(EnumArmor.HOLLOW, BODY);
		hollowLegs = new ItemModArmor(EnumArmor.HOLLOW, LEGS);
		hollowBoots = new ItemModArmor(EnumArmor.HOLLOW, BOOTS);

		lightstoneHelmet = new ItemModArmor(EnumArmor.LIGHTSTONE, HEAD);
		lightstoneChest = new ItemModArmor(EnumArmor.LIGHTSTONE, BODY);
		lightstoneLegs = new ItemModArmor(EnumArmor.LIGHTSTONE, LEGS);
		lightstoneBoots = new ItemModArmor(EnumArmor.LIGHTSTONE, BOOTS);

		livegreenHelmet = new ItemModArmor(EnumArmor.LIVEGREEN, HEAD);
		livegreenChest = new ItemModArmor(EnumArmor.LIVEGREEN, BODY);
		livegreenLegs = new ItemModArmor(EnumArmor.LIVEGREEN, LEGS);
		livegreenBoots = new ItemModArmor(EnumArmor.LIVEGREEN, BOOTS);

		starlightHelmet = new ItemModArmor(EnumArmor.STARLIGHT, HEAD);
		starlightChest = new ItemModArmor(EnumArmor.STARLIGHT, BODY);
		starlightLegs = new ItemModArmor(EnumArmor.STARLIGHT, LEGS);
		starlightBoots = new ItemModArmor(EnumArmor.STARLIGHT, BOOTS);

		bloodcrustHelmet = new ItemModArmor(EnumArmor.BLOODCRUST, HEAD);
		bloodcrustChest = new ItemModArmor(EnumArmor.BLOODCRUST, BODY);
		bloodcrustLegs = new ItemModArmor(EnumArmor.BLOODCRUST, LEGS);
		bloodcrustBoots = new ItemModArmor(EnumArmor.BLOODCRUST, BOOTS);

		blazehornHelmet = new ItemModArmor(EnumArmor.BLAZEHORN, HEAD);
		blazehornChest = new ItemModArmor(EnumArmor.BLAZEHORN, BODY);
		blazehornLegs = new ItemModArmor(EnumArmor.BLAZEHORN, LEGS);
		blazehornBoots = new ItemModArmor(EnumArmor.BLAZEHORN, BOOTS);

		bleedrockHelmet = new ItemModArmor(EnumArmor.BLEEDROCK, HEAD);
		bleedrockChest = new ItemModArmor(EnumArmor.BLEEDROCK, BODY);
		bleedrockLegs = new ItemModArmor(EnumArmor.BLEEDROCK, LEGS);
		bleedrockBoots = new ItemModArmor(EnumArmor.BLEEDROCK, BOOTS); */

		/*rockyBattleaxe = new ItemBattleAxe("rockyBattleaxe", "Rocky Battleaxe", EssenceToolMaterial.ROCKY_BATTLEAXE);
		crystalizedBattleaxe = new ItemBattleAxe("crystalizedBattleaxe", "Crystalized Battleaxe", EssenceToolMaterial.CRYSTAL_BATTLEAXE);
		backBiter = new ItemBattleAxe("backBiter", "Back Biter", EssenceToolMaterial.BACK_BITER);
		dawnBreaker = new ItemBattleAxe("dawnBreaker", "Dawn Breaker", EssenceToolMaterial.DAWN_BREAKER);
		tempestBattleaxe = new ItemBattleAxe("tempestBattleaxe", "Tempest Battleaxe", EssenceToolMaterial.TEMPEST_BATTLEAXE);
		bronzedBattleaxe = new ItemBattleAxe("bronzedBattleaxe", "Bronzed Battleaxe", EssenceToolMaterial.BRONZED_BATTLEAXE);
		celestiteBattleaxe = new ItemBattleAxe("celestiteBattleaxe", "Celestite Battleaxe", EssenceToolMaterial.CELESTITE_BATTLEAXE);
		storumBattleaxe = new ItemBattleAxe("storumBattleaxe", "Storum Battleaxe", EssenceToolMaterial.STORUM_BATTLEAXE);
		celekiumBattleaxe = new ItemBattleAxe("celekiumBattleaxe", "Celekium Battleaxe", EssenceToolMaterial.CELEKIUM_BATTLEAXE);
		thunderbirdBattleaxe = new ItemBattleAxe("thunderbirdBattleaxe", "Thunderbird Battleaxe", EssenceToolMaterial.THUNDERBIRD_BATTLEAXE);
		 */
		staffOfCrystal = new ItemStaff("staffOfCrystal", "Staff of Crystal", false, 3, 1000, 10, false, EntityIceBall.class);
		staffOfDivineStone = new ItemStaff("staffOfDivineStone", "Staff of Divine Stone", false, 3, 1000, 9, false, EntityRock.class);
		staffOfHellstone = new ItemStaff("staffOfHellstone", "Staff of Hellstone", false, 3, 1000, 7, false, EntityHellstone.class);
		doomsBringer = new ItemStaff("doomsBringer", "Dooms Bringer", false, 3, 1000, 12, false, EntityDoomsBringer.class);
		conjuringStaff = new ItemStaff("conjuringStaff", "Conjuring Staff", false, 3, 1000, 18, false, EntityConjuring.class);
		staffOfEnlightenment = new ItemStaff("staffOfEnlightenment", "Staff Of Enlightenment", true, 3, 1000, 14, false, EntityEnlightenment.class);
		staffOfGreenpace = new ItemStaff("staffOfGreenpace", "Staff Of Greenpace", true, 3, 1000, 10, false, EntityGreenpace.class);
		wizardsStar = new ItemStaff("wizardsStar", "Wizards Star", true, 3, 1000, 5, false, EntityWizardsStar.class);
		teleportationStaff = new ItemTeleport("teleportationStaff", "Teleportation Staff");
		overgrownStaff = new ItemStaff("overgrownStaff", "Overgrown Staff", true, 3, 1000, 5, false, EntityOvergrown.class);

		//flameArrow = new ItemMod("flameArrow", EssenceTabs.ranged);
		essenceArrow = new ItemMod("essenceArrow", "Essence Arrow", JourneyTabs.misc);

		flameBow = new ItemModBow("flameBow", "Flame Bow", 384, 3, essenceArrow, "Sets enemies ablaze", EntityFlameArrow.class);
		poisonBow = new ItemModBow("poisonBow", "Poison Bow", 384, 3, essenceArrow, "Poisons enemies", EntityPoisonArrow.class);
		darknessBow = new ItemModBow("darknessBow", "Darkness Bow", 384, 3, essenceArrow, "Withers foe", EntityDarknessArrow.class);
		frozenBow = new ItemModBow("frozenBow", "Frozen Bow", 384, 3, essenceArrow, "Slows enemies", EntityFrozenArrow.class);
		staringBow = new ItemModBow("staringBow", "Staring Bow", 456, 4, essenceArrow, "Sets enemies ablaze", EntityFlameArrow.class);
		deathPiercerBow = new ItemModBow("deathPiercerBow", "Death Piercer Bow", 892, 6, essenceArrow, 0, "Withers foe", EntityDarknessArrow.class);
		fusionBow = new ItemModBow("fusionBow", "Fusion Bow", 456, 4, essenceArrow, "Withers foe", EntityDarknessArrow.class);
		springBow = new ItemModBow("springBow", "Spring Bow", 384, 5, essenceArrow, "Sets enemies ablaze", EntityFlameArrow.class);
		starlightBow = new ItemModBow("starlightBow", "Starlight Bow", 384, 6, essenceArrow, "Poisons enemies", EntityPoisonArrow.class);
		wastefulBow = new ItemModBow("wastefulBow", "Wasteful Bow", 892, 5, essenceArrow, "Poisons enemies", EntityPoisonArrow.class);
		flamingBow = new ItemModBow("flamingBow", "Flaming Bow", 384, 4, essenceArrow, "Sets enemies ablaze", EntityFlameArrow.class);
		blazingBow = new ItemModBow("blazingBow", "Blazing Bow", 456, 4, essenceArrow, "Sets enemies ablaze", EntityFlameArrow.class);
		darkEnforcer = new ItemModBow("darkEnforcer", "Dark Enforcer", 384, 4, essenceArrow, "Withers foe", EntityDarknessArrow.class);
		depthsBow = new ItemModBow("depthsBow", "Depths Bow", 892, 4, essenceArrow, "Withers foe", EntityDarknessArrow.class);
		frostbittenBow = new ItemModBow("frostbittenBow", "Frostbitten Bow", 384, 4, essenceArrow, "Slows enemies", EntityFrozenArrow.class);
		frostyBow = new ItemModBow("frostyBow", "Frosty Bow", 456, 4, essenceArrow, "Slows enemies", EntityFrozenArrow.class);
		charredBow = new ItemModBow("charredBow", "Charred Bow", 384, 5, essenceArrow, "Withers enemies", EntityDarknessArrow.class);
		fluffyBow = new ItemModBow("fluffyBow", "Fluffy Bow", 892, 9, essenceArrow, "Slows enemies", EntityFrozenArrow.class);
		golemBow = new ItemModBow("golemBow", "Golem Bow", 456, 7, essenceArrow, "Withers foe", EntityDarknessArrow.class);
		loggersBow = new ItemModBow("loggersBow", "Loggers Bow", 384, 6, essenceArrow, "Poisons enemies", EntityPoisonArrow.class);
		overgrownBow = new ItemModBow("overgrownBow", "Overgrown Bow", 384, 7, essenceArrow, "Poisons enemies", EntityPoisonArrow.class);
		overseerBow = new ItemModBow("overseerBow", "Overseer Bow", 384, 7, essenceArrow, "Withers foe", EntityDarknessArrow.class);
		woodlandBow = new ItemModBow("woodlandBow", "Woodland Bow", 456, 7, essenceArrow, "Poisons enemies", EntityPoisonArrow.class);
		rocsWing = new ItemModBow("rocsWing", "Rocs Wing", 892, 6, essenceArrow, "Withers foe", EntityDarknessArrow.class);
		scaleBow = new ItemModBow("scaleBow", "Scale Bow", 456, 7, essenceArrow, "Withers foe", EntityDarknessArrow.class);
		mantleBow = new ItemModBow("mantleBow", "Mantle Bow", 384, 5, essenceArrow, "Sets enemies ablaze", EntityFlameArrow.class);
		coreExpender = new ItemModBow("coreExpender", "Core Expender", 384, 6, essenceArrow, "Withers foe", EntityFlameArrow.class);
		royalBow = new ItemModBow("royalBow", "Royal Bow", 384, 5, essenceArrow, "Withers foe", EntityDarknessArrow.class);
		darkTerraBow = new ItemModBow("darkTerraBow", "Dark Terra Bow", 456, 5, essenceArrow, "Withers foe", EntityDarknessArrow.class);
		lavenderBow = new ItemModBow("lavenderBow", "Lavender Bow", 892, 6, essenceArrow, "Slows enemies", EntityFrozenArrow.class);
		terralightBow = new ItemModBow("terralightBow", "Terralight Bow", 384, 7, essenceArrow, "Poisons enemies", EntityPoisonArrow.class);
		terrianBow = new ItemModBow("terrianBow", "Terrian Bow", 384, 6, essenceArrow, "Poisons enemies", EntityPoisonArrow.class);


		fireWand = new ItemWand("fireWand", "Fire Wand", false, 5, 600, 8, false, EntityFireBall.class);
		iceWand = new ItemWand("iceWand", "Ice Wand", true, 5, 600, 8, false, EntityIceBall.class);
		lightningWand = new ItemWand("lightningWand", "Lightning Wand", false, 10, 600, 8, false, EntityLightningBall.class);

		hammerCreative = new ItemCreativeHammer("hammerCreative", "Hammer of Creative Spellbinding", JourneyToolMaterial.CREATIVE, false, EntityLightningBall.class);
		earthenHammer = new ItemHammer("earthenHammer", "Earthen Hammer", JourneyToolMaterial.EARTHEN_HAMMER, false, EntityEarthen.class, false, true, 4, 4, 642);
		flamingHammer = new ItemHammer("flamingHammer", "Flaming Hammer", JourneyToolMaterial.FLAMING_HAMMER, false, EntityFireBall.class, false, true, 10, 4, 1230);
		nethicHammer = new ItemHammer("nethicHammer", "Nethic Hammer", JourneyToolMaterial.NETHIC_HAMMER, false, EntityFireBall.class, false, true, 8, 4, 825);
		withicHammer = new ItemHammer("withicHammer", "Withic Hammer", JourneyToolMaterial.WITHIC_HAMMER, false, EntityWithic.class, false, true, 9, 4, 1230);
		royalHammer = new ItemHammer("royalHammer", "Royal Hammer", JourneyToolMaterial.ROYAL_HAMMER, false, EntityFireBall.class, false, true, 12, 4, 1320);
		overgrownHammer = new ItemHammer("overgrownHammer", "Overgrown Hammer", JourneyToolMaterial.OVERGROWN_HAMMER, false, EntityOvergrown.class, false, true, 12, 4, 1320);
		rockyHammer = new ItemHammer("rockyHammer", "Rocky Hammer", JourneyToolMaterial.ROCKY_HAMMER, false, EntityRock.class, false, true, 6, 4, 2230);
		crystalizedHammer = new ItemHammer("crystalizedHammer", "Crystalized Hammer", JourneyToolMaterial.CRYSTAL_HAMMER, false, EntityIceBall.class, false, true, 7, 4, 3320);

		chaosCannon = new ItemChaosCannon("chaosCannon", "Chaos Cannon", 6, 4, "Shoots a bouncing projectile");
		rockLauncher = new ItemGun("rockLauncher", "Rock Launcher", 4, "Stuns mobs for 10 seconds", EntityRock.class);
		netherPlasma = new ItemGun("netherPlasma", "Nether Plasma", 10, "Burns mobs for 10 seconds", EntityNetherPlasma.class);
		oceanPlasma = new ItemGun("oceanPlasma", "Ocean Plasma", 4, "Harms mobs", EntityFloroWater.class);
		forestPlasma = new ItemGun("forestPlasma", "Forest Plasma", 4, "Poisons Mobs for 10 seconds", EntityOvergrown.class);
		eyeBlaster = new ItemGun("eyeBlaster", "Eye Blaster", 12, "Harms and burns mobs for 10 seconds", EntityEyeBlaster.class);

		greenGem = new ItemMod("greenGem", "Green Gem");
		purpleGem = new ItemMod("purpleGem", "Purple Gem");
		blueGem = new ItemMod("blueGem", "Blue Gem");
		yellowGem = new ItemMod("yellowGem", "Yellow Gem");

		eucaTablet = new ItemMod("eucaTablet", "Euca Tablet");

		wandBase = new ItemMod("wandBase", "Wand Base");
		staffBase = new ItemMod("staffBase", "Staff Base");

		frostyGift = new ItemPresent("frostyGift", "Frosty Gift");

		firestoneClump = new ItemMod("firestoneClump", "Firestone Clump");
		iridium = new ItemMod("iridium", "Iridium");

		//TODO
		friedFlamingGhastTentacale = new ItemModFood("friedFlamingGhastTentacle", "Fried Flaming Tentacale", 4, 0.6F, 10, true).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 5, 1), 1.0F);
		flamingGhastTentacle = new ItemModFood("flamingGhastTentacle", "Flaming Ghast Tentacale", 1, 0.6F, 10, true).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 5, 1), 1.0F);
		friedGhastTentacale = new ItemModFood("friedGhastTentacle", "Fried Ghast Tentacale", 4, 0.6F, 10, true);
		ghastTentacle = new ItemModFood("ghastTentacle", "Ghast Tentacale", 1, 0.6F, 10, true);
		friedEgg = new ItemModFood("friedEgg", "Fried Egg", 2, 0.6F, 10, false);
		floroPedal = new ItemModFood("floroPedal", "Floro Pedal", 3, 0.6F, 10, false);
		tomato = new ItemModFood("tomato", "Tomato", 3, 0.6F, 10, false);
		airMelon = new ItemModFood("airMelon", "Air Melon", 10, 3.0F, 2, false).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.digSpeed, 10, 40), 1.0F);
		glowshroom = new ItemModFood("glowshroom", "Glowshroom", 4, 0.6F, 10, false).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 10, 1), 1.0F).setAlwaysEdible();
		terrashroom = new ItemModFood("terrashroom", "Terrashroom", 8, 0.6F, 10, false).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 10, 1), 1.0F).setAlwaysEdible();
		corveggies = new ItemModFood("corveggies", "Corveggies", 4, 0.6F, 10, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.saturation, 20, 1), 1.0F).setAlwaysEdible();
		crackenCanes = new ItemCrackenCanes("crackenCanes", "Kracken Canes", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 20, 1), 1.0F).setAlwaysEdible();
		crakeBulb = new ItemCrakeBulb("crakeBulb", "Crake Bulb", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.waterBreathing, 20, 1), 1.0F).setAlwaysEdible();
		spineberries = new ItemSpineberries("spineBerries", "Spine Berries", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.absorption, 20, 1), 1.0F).setAlwaysEdible();
		zatPedal = new ItemZatPedal("zatPedal", "Zat Pedal", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 20, 1), 1.0F).setAlwaysEdible();
		glowa = new ItemGlowa("glowa", "Glowa", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 5, 1), 1.0F).setAlwaysEdible();
		mintCandyCane = new ItemCandyCane("mintCandyCane", "Mint Candy Cane", 3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.digSpeed, 10, 1), 1.0F).setAlwaysEdible();
		fruityCandyCane = new ItemCandyCane("fruityCandyCane", "Fruity Candy Cane", 3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 10, 1), 1.0F).setAlwaysEdible();
		cherryCandyCane = new ItemCandyCane("cherryCandyCane", "Cherry Candy Cane", 3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 10, 1), 1.0F).setAlwaysEdible();
		peppermint = new ItemModFood("peppermint", "Peppermint", 1, 0.1F, 2, false);
		jellyBeans = new ItemModFood("jellyBeans", "Jelly Beans", 1, 0.1F, 2, false);
		chocolate = new ItemModFood("chocolate", "Chocolate Bar", 2, 0.1F, 2, false);
		vanillaWafer = new ItemModFood("vanillaWafer", "Vanilla Wafer", 1, 0.1F, 2, false);
		bleedheart = new ItemFruit("bleedheart", "Bleedheart Fruit", 2, 0.1F, false, JourneyBlocks.bleedheartFruit, JourneyBlocks.sizzlerWoodLog);

		sizzleberry = new ItemModFood("sizzleberry", "Sizzleberry", 1, 4.0F, 5, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 20, 1), 1.0F).setAlwaysEdible();
		bradberry = new ItemModFood("bradberry", "Bradberry", 1, 8, 4, false);
		tangleberry = new ItemModFood("tangleberry", "Tangleberry", 2, 4, 1, false);
		juiceberry = new ItemModFood("juiceberry", "Juiceberry", 1, 6, 1, false);
		bogberry = new ItemModFood("bogberry", "Bogberry", 2, 6, 3, false);

		goldenSteak = new ItemGoldenFood("normalGoldenSteak", "Golden Steak", 6, 1.2F, false, false);
		goldenSteakOP = new ItemGoldenFood("OPGoldenSteak", "Golden Steak", 10, 2.2F, false, true);

		goldenPotato = new ItemGoldenFood("normalGoldenPotato", "Golden Potato", 3, 2.0F, false, false);
		goldenPotatoOP = new ItemGoldenFood("OPGoldenPotato", "Golden Potato", 6, 2.5F, false, true);

		goldenPork = new ItemGoldenFood("normalGoldenPork", "Golden Porkchop", 10, 1.0F, false, false);
		goldenPorkOP = new ItemGoldenFood("OPGoldenPork", "Golden Porkchop", 12, 1.2F, false, true);

		goldenFish = new ItemGoldenFood("normalGoldenFish", "Golden Fish", 6, 2.0F, false, false);
		goldenFishOP = new ItemGoldenFood("OPGoldenFish", "Golden Fish", 8, 2.5F, false, true);

		goldenChicken = new ItemGoldenFood("normalGoldenChicken", "Golden Chicken", 2, 3.0F, false, false);
		goldenChickenOP = new ItemGoldenFood("OPGoldenChicken", "Golden Chicken", 4, 3.5F, false, true);

		goldenRabbit = new ItemGoldenFood("normalGoldenRabbit", "Golden Rabbit", 2, 3.5F, false, false);
		goldenRabbitOP = new ItemGoldenFood("OPGoldenRabbit", "Golden Rabbit", 4, 4.0F, false, true);

		goldenMutton = new ItemGoldenFood("normalGoldenMutton", "Golden Mutton", 2, 3.5F, false, false);
		goldenMuttonOP = new ItemGoldenFood("OPGoldenMutton", "Golden Mutton", 4, 4.0F, false, true);

		goldenWing = new ItemGoldenFood("normalGoldenWing", "Golden Wing", 2, 4.5F, false, false);
		goldenWingOP = new ItemGoldenFood("OPGoldenWing", "Golden Wing", 4, 5.0F, false, true);

		eucaMeat = new ItemModFood("eucaMeat", "Euca Meat", 6, 0.6F, 10, true);
		rocMeat = new ItemModFood("rocMeat", "Bird Wing", 4, 0.6F, 10, true);
		cookedRocMeat = new ItemModFood("cookedRocMeat", "Cooked Bird Wing", 10, 0.6F, 10, true);
		hongoShroom = new ItemModFood("hongoShroom", "Hongoshroom", 4, 1.2F, 10, false);
		greenHonglowShroom = new ItemHonglow("greenHonglowShroom", "Green Honglowshroom", 2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 10, 1), 1.0F).setAlwaysEdible();
		redHonglowShroom = new ItemHonglow("redHonglowShroom", "Red Honglowshroom", 2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 10, 1), 1.0F).setAlwaysEdible();
		blueHonglowShroom = new ItemHonglow("blueHonglowShroom", "Blue Honglowshroom", 2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 10, 1), 1.0F).setAlwaysEdible();
		honglowShroom = new ItemHonglow("honglowShroom", "Honglowshroom", 6, 1.2F, false, false).setAlwaysEdible();
		snakeFlesh = new ItemZatPedal("snakeFlesh", "Snake Flesh", 6, 1.2F, false, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 60, 1), 1.0F).setAlwaysEdible();
		flamingBeef = new ItemModFood("flamingBeef", "Raw Flaming Beef", 6, 1.2F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 90, 1), 1.0F).setAlwaysEdible();
		flamingBeefCooked = new ItemModFood("flamingBeefCooked", "Cooked Flaming Beef", 16, 1.2F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 100, 2), 2.0F).setAlwaysEdible();

		underwaterWorldRecord = new ItemModRecord("underwaterWorld", "Underwater World", null);
		blueWater = new ItemModRecord("blueWater", "Blue Water", null);
		raceStar = new ItemModRecord("raceStar", "Race Star", null);
		compBegins = new ItemModRecord("compBegins", "Compition Begins", null);
		deepBlue = new ItemModRecord("deepBlue", "Deep Blue", null);
		raceShore = new ItemModRecord("raceShore", "Race to Shore", null);

		demonicEye = new ItemDemonicEye("demonicEye", "Demonic Eye");

		overworldKnowledge = new ItemKnowledge("overworldKnowledge", "Overworld Knowledge", EnumKnowledge.OVERWORLD);
		netherKnowledge = new ItemKnowledge("netherKnowledge", "Nether Knowledge", EnumKnowledge.NETHER);
		endKnowledge = new ItemKnowledge("endKnowledge", "End knowledge", EnumKnowledge.END);
		boilKnowledge = new ItemKnowledge("boilingKnowledge", "Boiling Knowledge", EnumKnowledge.BOIL);
		frozenKnowledge = new ItemKnowledge("frozenKnowledge", "Frozen Knowledge", EnumKnowledge.FROZEN);
		eucaKnowledge = new ItemKnowledge("eucaKnowledge", "Euca Knowledge", EnumKnowledge.EUCA);
		depthsKnowledge = new ItemKnowledge("depthsKnowledge", "Depths Knowledge", EnumKnowledge.DEPTHS);
		corbaKnowledge = new ItemKnowledge("corbaKnowledge", "Corba Knowledge", EnumKnowledge.CORBA);
		cloudiaKnowledge = new ItemKnowledge("cloudiaKnowledge", "Cloudia Knowledge", EnumKnowledge.CLOUDIA);
		//public static final ItemKnowledge wastelandsKnowledge = new ItemKnowledge("wastelandsKnowledge", EnumKnowledge.WASTELANDS);
		//public static final ItemKnowledge lithiumKnowledge = new ItemKnowledge("lithiumKnowledge", EnumKnowledge.LITHIUM);
		//public static final ItemKnowledge libraryKnowledge = new ItemKnowledge("libraryKnowledge", EnumKnowledge.LIBRARY);
		//public static final ItemKnowledge blazeKnowledge = new ItemKnowledge("blazeKnowledge", EnumKnowledge.BLAZE);
		//public static final ItemKnowledge witherKnowledge = new ItemKnowledge("witherKnowledge", EnumKnowledge.WITHER);

		boilingPointEssence = new ItemMod("boilingPointEssence", "Boiling Point Essence");
		cloudiaEssence = new ItemMod("cloudiaEssence", "Cloudia Essence");
		corbaEssence = new ItemMod("corbaEssence", "Corba Essence");
		depthsEssence = new ItemMod("depthsEssence", "Depths Essence");
		eucaEssence = new ItemMod("eucaEssence", " Euca Essence");
		frozenLandsEssence = new ItemMod("frozenLandsEssence", "Frozen Lands Essence");
		//public static final ItemMod wastelandsEssence = new ItemMod("wastelandsEssence");
		//public static final ItemMod lithiumEssence = new ItemMod("lithiumEssence");
		//public static final ItemMod nethicEssence = new ItemMod("nethicEssence");
		//public static final ItemMod subterrianEssence = new ItemMod("subterrianEssence");
		//public static final ItemMod withicEssence = new ItemMod("withicEssence");
		//public static final ItemMod blazesEssence = new ItemMod("blazesEssence");

		//TODO
		demonicBomb = new ItemThrowable("demonicBomb", "Demonic Bomb", 8F, EntityDemonicBomb.class);
		fireBomb = new ItemThrowable("fireBomb", "Fire Bomb", 12F, EntityFireBomb.class);

		boilingPiercer = new ItemPiercer("boilingPiercer", "Boiling Piercer", 12F, 6, EntityBoilingPiercer.class);
		nethicPiercer = new ItemPiercer("nethicPiercer", "Nethic Piercer", 11F, 6, EntityNethicPiercer.class);
		frozenPiercer = new ItemPiercer("frozenPiercer", "Frozen Piercer", 9F, 6, EntityFrozenPiercer.class);
		eucaPiercer = new ItemPiercer("eucaPiercer", "Euca Piercer", 12F, 6, EntityEucaPiercer.class);
		depthsPiercer = new ItemPiercer("depthsPiercer", "Depths Piercer", 21F, 6, EntityDepthsPiercer.class);
		corbaPiercer = new ItemPiercer("corbaPiercer", "Corba Piercer", 29F, 6, EntityCorbaPiercer.class);
		frostbittenPiercer = new ItemPiercer("frostbittenPiercer", "Frostbitten Piercer", 10F, 6, EntityFrostbittenPiercer.class);
		frostyPiercer = new ItemPiercer("frostyPiercer", "Frosty Piercer", 9F, 6, EntityFrostyPiercer.class);
		sunsetPiercer = new ItemPiercer("sunsetPiercer", "Sunset Piercer", 10F, 6, EntityFrostyPiercer.class);
		skyPiercer = new ItemPiercer("skyPiercer", "Sky Piercer", 10F, 6, EntityFrostyPiercer.class);

		moltenKnife = new ItemKnife("moltenKnife", "Molten Knife", 10F, EntityFrostyPiercer.class);
		aquaticKnife = new ItemThrowable("aquaticKnife", "Aquatic Knife", 6F, EntityFrostyPiercer.class);
		bloodKnife = new ItemThrowable("bloodKnife", "Blood Knife", 6F, EntityFrostyPiercer.class);
		charredKnife = new ItemThrowable("charredKnife", "Charred Knife", 6F, EntityFrostyPiercer.class);
		sizzlingKnife = new ItemThrowable("sizzlingKnife", "Sizzling Knife", 6F, EntityFrostyPiercer.class);
	}

	public static ToolMaterial addToolMaterial(int uses, float efficiency, float dam, boolean breakable) {
		return EnumHelper.addToolMaterial("tool", 3, breakable ? uses : -1, efficiency, dam, 30);
	}
}