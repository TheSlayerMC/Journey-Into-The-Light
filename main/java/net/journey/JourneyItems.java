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
import net.journey.items.ItemBleedheart;
import net.journey.items.ItemCandyCane;
import net.journey.items.ItemChaosCannon;
import net.journey.items.ItemCrackenCanes;
import net.journey.items.ItemCrakeBulb;
import net.journey.items.ItemCreativeHammer;
import net.journey.items.ItemDemonicEye;
import net.journey.items.ItemDetractor;
import net.journey.items.ItemEssencePotion;
import net.journey.items.ItemFlameCoin;
import net.journey.items.ItemGlowa;
import net.journey.items.ItemGoldenFood;
import net.journey.items.ItemGun;
import net.journey.items.ItemHammer;
import net.journey.items.ItemHealth;
import net.journey.items.ItemHonglow;
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
import net.journey.util.EssenceToolMaterial;
import net.journey.util.PotionEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
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

	public static final Item heartSml = new ItemHealth("heartSml", "Heart: Tier 1", 1, 2, 0.2F, false, false, 60, false, true);
	public static final Item heartMed = new ItemHealth("heartMed", "Heart: Tier 2", 2, 2, 0.2F, false, false, 60, false, true);
	public static final Item heartLrg = new ItemHealth("heartLrg", "Heart: Tier 3", 4, 2, 0.2F, false, false, 60, false, true);
	public static final Item heartUlt = new ItemHealth("heartUlt", "Heart: Ultimate", 8, 2, 0.2F, false, false, 60, false, true);
	public static final Item heartSentry = new ItemHealth("heartSen", "Sentry's Heart", 10, 2, 0.2F, false, false, 20, true, false);
	public static final Item hellstoneIngot = new ItemMod("hellstoneIngot", "Bloodcrust Ingot");
	public static final Item shadiumIngot = new ItemMod("shadiumIngot", "Shadium Ingot");
	public static final Item celestiumIngot = new ItemMod("celestiumIngot", "Celestium Ingot");
	public static final Item mekyumIngot = new ItemMod("mekyumIngot", "Mekyum Ingot");
	public static final Item koriteIngot = new ItemMod("koriteIngot", "Korite Ingot");
	public static final Item storonIngot = new ItemMod("storonIngot", "Storon Ingot");
	public static final Item luniumIngot = new ItemMod("luniumIngot", "Lunium Ingot");
	public static final Item flairiumIngot = new ItemMod("flairiumIngot", "Flairium Ingot");
	public static final Item ash = new ItemMod("ash", "Ash");
	public static final Item sapphire = new ItemMod("sapphire", "Sapphire Gem");
	public static final Item blazium = new ItemMod("blazium", "Blazium Gem");
	public static final Item enderilliumShard = new ItemMod("enderilliumShard", "Enderillium Shard");
	public static final Item orbaditeIngot = new ItemMod("orbaditeIngot", "Orbadite Ingot");
	public static final Item gorbiteGem = new ItemMod("gorbiteGem", "Gorbite Gem");
	public static final Item desIngot = new ItemMod("desIngot", "Des Ingot");

	public static final Item elderKey = new ItemMod("elderKey", "Elder Key").setMaxStackSize(1);
	public static final Item boilPowder = new ItemMod("boilPowder", "Boiling Powder");
	public static final Item blazingFireball = new ItemMod("blazingFireball", "Blazing Fireball");
	public static final Item hellTurtleShell = new ItemMod("hellTurtleShell", "Hell Turtle Shell");
	public static final Item sizzlingEye = new ItemMod("sizzlingEye", "Sizzling Eye");

	public static final Item sentryEye = new ItemSentryEye("sentryEye", "Sentry Eye");
	public static final Item boilingSkull = new ItemMod("boilingSkull", "Boiling Skull");
	public static final Item overgrownNatureBall = new ItemMod("overgrownNatureBall", "Overgrown Natureball");
	public static final Item overseeingTablet = new ItemMod("overseeingTablet", "Overseeing Tablet");
	public static final Item overseeingEye = new ItemMod("overseeingEye", "Overseeing Eye");
	public static final Item darkCrystal = new ItemMod("darkCrystal", "Dark Crystal");
	public static final Item blankKnowledge = new ItemMod("blankKnowledge", "Blank Knowledge");
	public static final Item darkOrb = new ItemMod("darkOrb", "Dark Orb");
	public static final Item depthsFlake = new ItemMod("depthsFlake", "Depths Flake");
	public static final Item beastlyStomach = new ItemMod("beastlyStomach", "Beastly Stomach");
	public static final Item rocFeather = new ItemMod("rocFeather", "Essence Feather");
	public static final Item coldClump = new ItemMod("coldClump", "Cold Clump");
	public static final Item crystalEye = new ItemMod("crystalEye", "Crystal Eye");
	public static final Item crystalFlake = new ItemMod("crystalFlake", "Crystal Flake");
	public static final Item freezingTablet = new ItemMod("freezingTablet", "Freezing Tablet");
	public static final Item frostFlake = new ItemMod("frostFlake", "Frost Flake");
	public static final Item frostGem = new ItemMod("frostGem", "Frost Gem");
	public static final Item frozenIceball = new ItemMod("frozenIceball", "Frozen Iceball");
	public static final Item snowsheet = new ItemMod("snowsheet", "Snowsheet");
	public static final Item gateKeys = new ItemMod("gateKeys", "Gate Keys");
	public static final Item goldClump = new ItemMod("goldClump", "Gold Clump");
	public static final Item silverClump = new ItemMod("silverClump", "Silver Clump");
	public static final Item golderDust = new ItemMod("golderDust", "Golder Dust");
	public static final Item shimmerdust = new ItemMod("shimmerdust", "Shimmer Dust");
	public static final Item royalDisk = new ItemMod("royalDisk", "Royal Disk");
	public static final Item metalDisk = new ItemMod("metalDisk", "Metal Disk");
	public static final Item demonicDust = new ItemMod("demonicDust", "Demonic Dust");
	public static final Item demonicBone = new ItemMod("demonicBone", "Demonic Bone");
	public static final Item withicDust = new ItemMod("withicDust", "Withic Dust");
	public static final Item cloudiaOrb = new ItemMod("cloudiaOrb", "Cloudia Orb");
	public static final Item fluffyFeather = new ItemMod("fluffyFeather", "Fluffy Feather");
	public static final Item golemChunk = new ItemMod("golemChunk", "Golem Chunk");
	public static final Item luniteChunk = new ItemMod("luniteChunk", "Lunite Chunk");
	public static final Item corbaStick = new ItemMod("corbaStick", "Corba Stick");
	public static final Item spyclopseEye = new ItemMod("spyclopseEye", "Spyclopse Eye");
	public static final Item caveCrystal = new ItemMod("caveCrystal", "Cave Crystal");
	public static final Item caveDust = new ItemMod("caveDust", "Cave Dust");
	public static final Item stoneClump = new ItemMod("stoneClump", "Stone Clump");
	public static final Item enchantedLeaf = new ItemMod("enchantedLeaf", "Enchanted Leaf");
	public static final Item baseEssence = new ItemMod("baseEssence", "Base Essence");
	public static final Item cloudPuff = new ItemMod("cloudPuff", "Cloud Puff");
	public static final Item collectorRock = new ItemMod("collectorRock", "Collector Rock");
	public static final Item natureTablet = new ItemMod("natureTablet", "Nature Tablet");
	public static final Item horn = new ItemMod("horn", "Horn");
	public static final Item scale = new ItemMod("scale", "Scale");
	public static final Item reinforcedStoneIngot = new ItemMod("reinforcedStoneIngot", "Reinforced Stone Ingot");
	public static final Item reinforcedCrystalIngot = new ItemMod("reinforcedCrystalIngot", "Reinforced Crystal Ingot");
	public static final Item crystalBall = new ItemMod("crystalBall", "Crystal Ball");
	public static final Item stoneStick = new ItemMod("stoneStick", "Stone Stick");
	public static final Item darkTerrarianSoil = new ItemMod("darkTerrarianSoil", "Dark Terrarian Soil");
	public static final Item earthenCrystal = new ItemMod("earthenCrystal", "EarthenCrystal");
	public static final Item lightTerrarianSoil = new ItemMod("lightTerrarianSoil", "Light Terrarian Soil");
	public static final Item terrastar = new ItemMod("terrastar", "Terrastar");
	public static final Item purplePowder = new ItemMod("purplePowder", "Purple Powder");
	public static final Item hellShards = new ItemMod("hellShards", "Hell Shards");
	public static final Item hellcrustIngot = new ItemMod("hellcrustIngot", "Hellcrust Ingot");
	public static final Item flamingSprocket = new ItemMod("flamingSprocket", "Flaming Sprocket");
	public static final Item flamingSpring = new ItemMod("flamingSpring", "Flaming Spring");
	public static final Item blood = new ItemMod("blood", "Blood");
	public static final Item concentratedBlood = new ItemMod("concentratedBlood", "Concentrated Blood");
	public static final Item snakeSkin = new ItemMod("snakeSkin", "Snake Skin");
	public static final Item withicSpine = new ItemMod("withicSpine", "Withic Spine");
	public static final Item lostSoul = new ItemSoul("lostSoul", "Lost Soul");
	public static final Item withicSoul = new ItemMod("withicSoul", "Withic Soul");
	public static final Item eucaPortalPiece = new ItemMod("eucaPortalPiece", "Euca Portal Piece");
	public static final Item eucaPortalPiece_1 = new ItemMod("eucaPortalPiece_1", "Euca Portal Piece");
	public static final Item eucaPortalPiece_0 = new ItemMod("eucaPortalPiece_0", "Euca Portal Piece");
	public static final Item flamingHide = new ItemMod("flamingHide", "Flaming Hide");
	public static final Item boilKey = new ItemMod("boilKey", "Boil Key").setMaxStackSize(1);;
	public static final Item darkKey = new ItemMod("darkkey", "Dark Key").setMaxStackSize(1);;

	public static final Item hellstoneDust = new ItemMod("hellstoneDust", "Bloodcrust Dust");
	public static final Item shadiumDust = new ItemMod("shadiumDust", "Shadium Dust");
	public static final Item celestiumDust = new ItemMod("celestiumDust", "Celestium Dust");
	public static final Item luniumDust = new ItemMod("luniumDust", "Lunium Dust");
	public static final Item flairiumDust = new ItemMod("flairiumDust", "Flairium Dust");
	public static final Item ashDust = new ItemMod("ashDust", "Ash Dust");
	public static final Item sapphireDust = new ItemMod("sapphireDust", "Sapphire Dust");
	public static final Item enderilliumDust = new ItemMod("enderilliumDust", "Enderillium Dust");
	public static final Item gorbiteDust = new ItemMod("gorbiteDust", "Gorbite Dust");
	public static final Item orbaditeDust = new ItemMod("orbaditeDust", "Orbadite Dust");
	public static final Item diamondDust = new ItemMod("diamondDust", "Diamond Dust");
	public static final Item goldDust = new ItemMod("goldDust", "Gold Dust");
	public static final Item ironDust = new ItemMod("ironDust", "Iron Dust");

	public static final Item hellstoneClump = new ItemMod("hellstoneClump", "Bloodcrust Clump");
	public static final Item shadiumClump = new ItemMod("shadiumClump", "Shadium Clump");
	public static final Item luniumClump = new ItemMod("luniumClump", "Lunium Clump");
	public static final Item spawnerClump = new ItemMod("spawnerClump", "Spawner Clump");
	public static final Item spawnerBar = new ItemMod("spawnerBar", "Spawner Bar");

	public static final Item flameCoin = new ItemFlameCoin("flameCoin", "Flame Coin");
	public static final Item essenceDetractor = new ItemDetractor("essenceDetractor", "Essence Detractor", 1, false, true);
	public static final Item essenceAttractor = new ItemDetractor("essenceAttractor", "Essence Attractor", 1, true, false);

	public static final Item rockChunk = new ItemMod("rockChunk", "Rock Chunk");
	public static final Item rockShard = new ItemMod("rockShard", "Rock Shard");
	//public static final Item plasmaBall = new ItemMod("plasmaBall");

	public static final Item hellstoneMultiTool = new ItemMultiTool("hellstoneMultiTool", "Bloodcrust Multi Tool", EssenceToolMaterial.HELLSTONE_MULTI_TOOL, 1750);
	public static final Item shadiumMultiTool = new ItemMultiTool("shadiumMultiTool", "Shadium Multi Tool", EssenceToolMaterial.SHADIUM_MULTI_TOOL, 1670);
	public static final Item celestiumMultiTool = new ItemMultiTool("celestiumMultiTool", "Celestium Multi Tool", EssenceToolMaterial.CELESTIUM_MULTI_TOOL, 1820);
	public static final Item luniumMultiTool = new ItemMultiTool("luniumMultiTool", "Lunium Multi Tool", EssenceToolMaterial.LUNIUM_MULTI_TOOL, 1670);
	public static final Item flairiumMultiTool = new ItemMultiTool("flairiumMultiTool", "Flairium Multi Tool", EssenceToolMaterial.FLAIRIUM_MULTI_TOOL, 1202);
	public static final Item sapphireMultiTool = new ItemMultiTool("sapphireMultiTool", "Sapphire Multi Tool", EssenceToolMaterial.SAPPHIRE_MULTI_TOOL, 2456);
	public static final Item gorbiteMultiTool = new ItemMultiTool("gorbiteMultiTool", "Gorbite Multi Tool", EssenceToolMaterial.GORBITE_MULTI_TOOL, 2115);
	public static final Item orbaditeMultiTool = new ItemMultiTool("orbaditeMultiTool", "Orbadite Multi Tool", EssenceToolMaterial.ORBADITE_MULTI_TOOL, 2115);
	public static final Item desMultiTool = new ItemMultiTool("desMultiTool", "Des Multi Tool", EssenceToolMaterial.DES_MULTI_TOOL, 2102);
	public static final Item koriteMultiTool = new ItemMultiTool("koriteMultiTool", "Korite Multi Tool", EssenceToolMaterial.KORITE_MULTI_TOOL, 1820);
	public static final Item storonMultiTool = new ItemMultiTool("storonMultiTool", "Storon Multi Tool", EssenceToolMaterial.KORITE_MULTI_TOOL, 1820);
	public static final Item mekyumMultiTool = new ItemMultiTool("mekyumMultiTool", "Mekyum Multi Tool", EssenceToolMaterial.KORITE_MULTI_TOOL, 1820);
	public static final Item woodMultiTool = new ItemMultiTool("woodMultiTool", "Wooden Multi Tool", EssenceToolMaterial.WOOD_MULTI_TOOL, 60);
	public static final Item stoneMultiTool = new ItemMultiTool("stoneMultiTool", "Stone Multi Tool", EssenceToolMaterial.STONE_MULTI_TOOL, 132);
	public static final Item ironMultiTool = new ItemMultiTool("ironMultiTool", "Iron Multi Tool", EssenceToolMaterial.IRON_MULTI_TOOL, 251);
	public static final Item goldMultiTool = new ItemMultiTool("goldMultiTool", "Gold Multi Tool", EssenceToolMaterial.GOLD_MULTI_TOOL, 33);
	public static final Item diamondMultiTool = new ItemMultiTool("diamondMultiTool", "Diamond Multi Tool", EssenceToolMaterial.DIAMOND_MULTI_TOOL, 1562);

	public static final Item hellstonePickaxe = new ItemModPickaxe("hellstonePickaxe", "Bloodcrust Pickaxe", EssenceToolMaterial.HELLSTONE);
	public static final Item shadiumPickaxe = new ItemModPickaxe("shadiumPickaxe", "Shadium Pickaxe", EssenceToolMaterial.SHADIUM);
	public static final Item celestiumPickaxe = new ItemModPickaxe("celestiumPickaxe", "Celestium Pickaxe", EssenceToolMaterial.CELESTIUM);
	public static final Item luniumPickaxe = new ItemModPickaxe("luniumPickaxe", "Lunium Pickaxe", EssenceToolMaterial.LUNIUM);
	public static final Item flairiumPickaxe = new ItemModPickaxe("flairiumPickaxe", "Flairium Pickaxe", EssenceToolMaterial.FLAIRIUM);
	public static final Item sapphirePickaxe = new ItemModPickaxe("sapphirePickaxe", "Sapphire Pickaxe", EssenceToolMaterial.SAPPHIRE);
	public static final Item gorbitePickaxe = new ItemModPickaxe("gorbitePickaxe", "Gorbite Pickaxe", EssenceToolMaterial.GORBITE);
	public static final Item orbaditePickaxe = new ItemModPickaxe("orbaditePickaxe", "Orbadite Pickaxe", EssenceToolMaterial.ORBADITE);
	public static final Item desPickaxe = new ItemModPickaxe("desPickaxe", "Des Pickaxe", EssenceToolMaterial.DES);
	public static final Item koritePickaxe = new ItemModPickaxe("koritePickaxe", "Korite Pickaxe", EssenceToolMaterial.KORITE);
	public static final Item storonPickaxe = new ItemModPickaxe("storonPickaxe", "Storon Pickaxe", EssenceToolMaterial.KORITE);
	public static final Item mekyumPickaxe = new ItemModPickaxe("mekyumPickaxe", "Mekyum Pickaxe", EssenceToolMaterial.KORITE);

	public static final Item hellstoneShovel = new ItemModShovel("hellstoneShovel", "Bloodcrust Shovel", EssenceToolMaterial.HELLSTONE);
	public static final Item shadiumShovel = new ItemModShovel("shadiumShovel", "Shadium Shovel", EssenceToolMaterial.SHADIUM);
	public static final Item celestiumShovel = new ItemModShovel("celestiumShovel", "Celestium Shovel", EssenceToolMaterial.CELESTIUM);
	public static final Item luniumShovel = new ItemModShovel("luniumShovel", "Lunium Shovel", EssenceToolMaterial.LUNIUM);
	public static final Item flairiumShovel = new ItemModShovel("flairiumShovel", "Flairium Shovel", EssenceToolMaterial.FLAIRIUM);
	public static final Item sapphireShovel = new ItemModShovel("sapphireShovel", "Sapphire Shovel", EssenceToolMaterial.SAPPHIRE);
	public static final Item gorbiteShovel = new ItemModShovel("gorbiteShovel", "Gorbite Shovel", EssenceToolMaterial.GORBITE);
	public static final Item orbaditeShovel = new ItemModShovel("orbaditeShovel", "Orbadite Shovel", EssenceToolMaterial.ORBADITE);
	public static final Item desShovel = new ItemModShovel("desShovel", "Des Shovel", EssenceToolMaterial.DES);
	public static final Item koriteShovel = new ItemModShovel("koriteShovel", "Korite Shovel", EssenceToolMaterial.KORITE);
	public static final Item storonShovel = new ItemModShovel("storonShovel", "Storon Shovel", EssenceToolMaterial.KORITE);
	public static final Item mekyumShovel = new ItemModShovel("mekyumShovel", "Mekyum Shovel", EssenceToolMaterial.KORITE);

	/*public static final Item hellstoneAxe = new ItemModAxe("hellstoneAxe", "Bloodcrust Axe", EssenceToolMaterial.HELLSTONE);
	public static final Item shadiumAxe = new ItemModAxe("shadiumAxe", "Shadium Axe", EssenceToolMaterial.SHADIUM);
	public static final Item celestiumAxe = new ItemModAxe("celestiumAxe", "Celestium Axe", EssenceToolMaterial.CELESTIUM);
	public static final Item luniumAxe = new ItemModAxe("luniumAxe", "Lunium Axe", EssenceToolMaterial.LUNIUM);
	public static final Item flairiumAxe = new ItemModAxe("flairiumAxe", "Flairium Axe", EssenceToolMaterial.FLAIRIUM);
	public static final Item sapphireAxe = new ItemModAxe("sapphireAxe", "Sapphire Axe", EssenceToolMaterial.SAPPHIRE);
	public static final Item gorbiteAxe = new ItemModAxe("gorbiteAxe", "Gorbite Axe", EssenceToolMaterial.GORBITE);
	public static final Item orbaditeAxe = new ItemModAxe("orbaditeAxe", "Orbadite Axe", EssenceToolMaterial.ORBADITE);
	public static final Item desAxe = new ItemModAxe("desAxe", "Des Axe", EssenceToolMaterial.DES);
	public static final Item koriteAxe = new ItemModAxe("koriteAxe", "Korite Axe", EssenceToolMaterial.KORITE);
	public static final Item storonAxe = new ItemModAxe("storonAxe", "Storon Axe", EssenceToolMaterial.KORITE);
	public static final Item mekyumAxe = new ItemModAxe("mekyumAxe", "Mekyum Axe", EssenceToolMaterial.KORITE);*/

	public static final Item hellstoneHoe = new ItemModHoe("hellstoneHoe", "Bloodcrust Hoe", EssenceToolMaterial.HELLSTONE);
	public static final Item shadiumHoe = new ItemModHoe("shadiumHoe", "Shadium Hoe", EssenceToolMaterial.SHADIUM);
	public static final Item celestiumHoe = new ItemModHoe("celestiumHoe", "Celestium Hoe", EssenceToolMaterial.CELESTIUM);
	public static final Item luniumHoe = new ItemModHoe("luniumHoe", "Lunium Hoe", EssenceToolMaterial.LUNIUM);
	public static final Item flairiumHoe = new ItemModHoe("flairiumHoe", "Flairium Hoe", EssenceToolMaterial.FLAIRIUM);
	public static final Item sapphireHoe = new ItemModHoe("sapphireHoe", "Sapphire Hoe", EssenceToolMaterial.SAPPHIRE);
	public static final Item gorbiteHoe = new ItemModHoe("gorbiteHoe", "Gorbite Hoe", EssenceToolMaterial.GORBITE);
	public static final Item orbaditeHoe = new ItemModHoe("orbaditeHoe", "Orbadite Hoe", EssenceToolMaterial.ORBADITE);
	public static final Item desHoe = new ItemModHoe("desHoe", "Des Hoe", EssenceToolMaterial.DES);
	public static final Item koriteHoe = new ItemModHoe("koriteHoe", "Korite Hoe", EssenceToolMaterial.KORITE);
	public static final Item storonHoe = new ItemModHoe("storonHoe", "Storon Hoe", EssenceToolMaterial.KORITE);
	public static final Item mekyumHoe = new ItemModHoe("mekyumHoe", "Storon Hoe", EssenceToolMaterial.KORITE);

	public static final Item multiToolOfEternalSmelting = new ItemMultiTool("multiToolOfEternalSmelting", "Multi Tool of Eternal Smelting", EssenceToolMaterial.SMELTING_TOOL, 512);

	public static final Item hellstoneSword = new ItemFireSword("hellstoneSword", "Bloodcrust Sword", EssenceToolMaterial.HELLSTONE_SWORD);
	public static final Item shadiumSword = new ItemFreezeSword("shadiumSword", "Shadium Sword", EssenceToolMaterial.SHADIUM_SWORD);
	public static final Item celestiumSword = new ItemModSword("celestiumSword", "Celestium Sword", EssenceToolMaterial.CELESTIUM_SWORD);
	public static final Item luniumSword = new ItemModSword("luniumSword", "Lunium Sword", EssenceToolMaterial.LUNIUM_SWORD);
	public static final Item flairiumSword = new ItemFireSword("flairiumSword", "Flairium Sword", EssenceToolMaterial.FLAIRIUM_SWORD);
	public static final Item desSword = new ItemModSword("desSword", "Des Sword", EssenceToolMaterial.DES_SWORD);
	public static final Item sapphireSword = new ItemModSword("sapphireSword", "Sapphire Sword", EssenceToolMaterial.SAPPHIRE_SWORD);
	public static final Item gorbiteSword = new ItemModSword("gorbiteSword", "Gorbite Sword", EssenceToolMaterial.GORBITE_SWORD);
	public static final Item orbaditeSword = new ItemModSword("orbaditeSword", "Orbadite Sword", EssenceToolMaterial.ORBADITE_SWORD);
	public static final Item poisonSword = new ItemPoisionSword("poisonSword", "Poison Sword", EssenceToolMaterial.POISON_SWORD);
	public static final Item cloudSlicer = new ItemModSword("cloudSlicer", "Cloud Slicer", EssenceToolMaterial.CLOUD_SLICER);
	public static final Item dragonsTooth = new ItemModSword("dragonsTooth", "Dragons Tooth", EssenceToolMaterial.DRAGONS_TOOTH);
	public static final Item netherBeastSword = new ItemRegenSword("netherBeastSword", "Netherbeast Sword", EssenceToolMaterial.NETHER_BEAST_SWORD);
	public static final Item witheringBeastSword = new ItemWitherSword("witheringBeastSword", "Witheringbeast Sword", EssenceToolMaterial.WITHERING_BEAST_SWORD);
	public static final Item calciaSword = new ItemModSword("calciaSword", "Calcia Sword", EssenceToolMaterial.CALCIA_SWORD);
	public static final Item championsSword = new ItemModSword("championsSword", "Champions Sword", EssenceToolMaterial.CHAMPIONS_SWORD);
	public static final Item theWraith = new ItemModSword("theWraith", "The Wraith", EssenceToolMaterial.THE_WRAITH);
	public static final Item bubbleSword = new ItemBubbleSword("bubbleSword", "Bubble Sword", EssenceToolMaterial.BUBBLE_SWORD);
	public static final Item boilingBlade = new ItemFireSword("boilingBlade", "Boiling Blade", EssenceToolMaterial.BOILING_BLADE);
	public static final Item loggersSword = new ItemLoggersSword("loggersSword", "Loggers Sword", EssenceToolMaterial.LOGGERS_SWORD);
	public static final Item naturesBlade = new ItemStunWitherSword("naturesBlade", "Natures Blade", EssenceToolMaterial.NATURES_BLADE);
	public static final Item depthsDarksword = new ItemStunWitherSword("depthsDarksword", "Depths Darksword", EssenceToolMaterial.DEPTHS_DARKSWORD);
	public static final Item depthsSlayer = new ItemStunSword("depthsSlayer", "Depths Slayer", EssenceToolMaterial.DEPTHS_SLAYER);
	public static final Item snowShoveler = new ItemStunWitherSword("snowShoveler", "Snow Shoveler", EssenceToolMaterial.SNOW_SHOVELER);
	public static final Item frostySword = new ItemStunSword("frostySword", "Frosty Sword", EssenceToolMaterial.FROSTY_SWORD);
	public static final Item frostbittenSword = new ItemStunSword("frostbittenSword", "Frostbitten Sword", EssenceToolMaterial.FROSTBITTEN_SWORD);
	public static final Item treeHugger = new ItemPoisionSword("treeHugger", "Tree Hugger", EssenceToolMaterial.TREE_HUGGER);
	public static final Item coreMender = new ItemFireSword("coreMender", "Core Mender", EssenceToolMaterial.CORE_MENDER);
	public static final Item royalBlade = new ItemPoisionSword("royalBlade", "Royal Blade", EssenceToolMaterial.ROYAL_BLADE);
	public static final Item royalStabber = new ItemPoisionSword("royalStabber", "Royal Stabber", EssenceToolMaterial.ROYAL_STABBER);
	public static final Item rocSword = new ItemModSword("rocSword", "Roc Sword", EssenceToolMaterial.ROC_SWORD);
	public static final Item swordOfTheThunderbird = new ItemModSword("swordOfTheThunderbird", "Sword of the Thunderbird", EssenceToolMaterial.SWORD_THUNDERBIRD);
	public static final Item bloodwieldSword = new ItemHealthSword("bloodWieldSword", "Blood Wielder", EssenceToolMaterial.BLOODWIELD_SWORD, 1);
	public static final Item charredBlade = new ItemWitherSword("charredBlade", "Charred Blade", EssenceToolMaterial.CHARRED_BLADE);
	public static final Item sizzlerSword = new ItemFireWitherSword("sizzlerSword", "Sizzler Sword", EssenceToolMaterial.SIZZLER_SWORD);
	public static final Item fluffyBlade = new ItemNightvisionHealthSword("fluffyBlade", "Fluffy Blade", EssenceToolMaterial.FLUFFY_BLADE);
	public static final Item golemSword = new ItemModSword("golemSword", "Golem Sword", EssenceToolMaterial.GOLEM_SWORD);
	public static final Item thunderblade = new ItemPoisonHealthSword("thunderblade", "Thunder Blade", EssenceToolMaterial.THUNDERBLADE, 1.5F);
	public static final Item sentrySword = new ItemFireHealthSword("sentrySword", "Sentry Sword", EssenceToolMaterial.SENTRY_SWORD, 2);
	public static final Item crystalBlade = new ItemModSword("crystalBlade", "Crystal Blade", EssenceToolMaterial.CRYSTAL_BLADE);
	public static final Item starlightBlade = new ItemModSword("starlightBlade", "Starlight Blade", EssenceToolMaterial.STARLIGHT_BLADE);
	public static final Item koriteSword = new ItemModSword("koriteSword", "Korite Sword", EssenceToolMaterial.KORITE_SWORD);
	public static final Item storonSword = new ItemModSword("storonSword", "Storon Sword", EssenceToolMaterial.KORITE_SWORD);
	public static final Item mekyumSword = new ItemModSword("mekyumSword", "Mekyum Sword", EssenceToolMaterial.KORITE_SWORD);
	public static final Item pedalSword = new ItemModSword("pedalSword", "Pedal Sword", EssenceToolMaterial.PEDAL_SWORD);
	public static final Item withicBlade = new ItemModSword("withicBlade", "Withic Blade", EssenceToolMaterial.WITHIC_BLADE);
	public static final Item reinforcedCrystalSword = new ItemModSword("reinforcedCrystalSword", "Reinforced Crystal Sword", EssenceToolMaterial.RE_CRYSTAL_SWORD);
	public static final Item reinforcedStoneSword = new ItemModSword("reinforcedStoneSword", "Reinforced Stone Sword", EssenceToolMaterial.RE_STONE_SWORD);
	public static final Item terralightBlade = new ItemModSword("terralightBlade", "Terralight Blade", EssenceToolMaterial.TERRALIGHT_BLADE);
	public static final Item terranaSword = new ItemModSword("terranaSword", "Terrana Sword", EssenceToolMaterial.TERRANA_SWORD);
	public static final Item terrolicaSword = new ItemNightvisionSword("terrolicaSword", "Terrolica Sword", EssenceToolMaterial.TERROLICA_SWORD);
	public static final Item voliteSword = new ItemModSword("voliteSword", "Volite Sword", EssenceToolMaterial.VOLITE_SWORD);
	public static final Item kingsSword = new ItemFireHealthSword("kingsSword", "Kings Sword", EssenceToolMaterial.KINGS_SWORD, 1);
	public static final Item demonicSword = new ItemWitherSword("demonicSword", "Wither Sword", EssenceToolMaterial.DEMONIC_SWORD);
	public static final Item vinestrandBlade = new ItemPoisionSword("vinestrandBlade", "Vinestrand Blade", EssenceToolMaterial.VINESTRAND_BLADE);
	public static final Item darkPineSword = new ItemWitherSword("darkPineSword", "Dark Pine Sword", EssenceToolMaterial.DARK_PINE_SWORD);

	public static final Item healersBlade = new ItemHealthSword("healersBlade", "Healers Blade", EssenceToolMaterial.HEALERS_BLADE, 1);
	public static final Item terronicBlade = new ItemHealthSword("terronicBlade", "Terronic Blade", EssenceToolMaterial.TERRONIC_BLADE, 2);

	public static final Item developerSword = new ItemModSword("developerSword", "Creative Sword", EssenceToolMaterial.DEVELOPER_SWORD).setCreativeTab(JourneyTabs.util);

	public static final Item slugSlime = new ItemMod("slugSlime", "Slug Slime");


	public static final Item eucaPortalGem = new ItemMod("eucaPortalGem", "Euca Portal Gem");
	public static final Item depthsPortalGem = new ItemMod("depthsPortalGem", "Depths Portal Gem");
	public static final Item corbaPortalGem = new ItemMod("corbaPortalGem", "Corba Portal Gem");
	public static final Item terraniaPortalGem = new ItemMod("terraniaPortalGem", "Terrania Portal Gem");
	public static final Item cloudiaPortalGem = new ItemMod("cloudiaPortalGem", "Cloudia Portal Gem");
	//public static final Item wastelandPortalGem = new ItemMod("wastelandPortalGem");

	public static final Item calciaOrb = new ItemNetherBossSpawner("calciaOrb", "Calcia Orb");
	public static final Item netherBeastOrb = new ItemNetherBossSpawner("netherBeastOrb", "Nether Beast Orb");
	public static final Item witheringBeastOrb = new ItemNetherBossSpawner("witheringBeastOrb", "Withering Soul");
	public static final Item eudorOrb = new ItemSpecificDimensionSpawner(Config.euca, "eudorOrb", "Valuable Crown", "Euca");
	public static final Item blazierOrb = new ItemSpecificDimensionSpawner(Config.boil, "blazierOrb", "Burning Fireball", "Boiling Point");
	public static final Item rocSpawnEgg = new ItemSpecificDimensionSpawner(0, "rocPetSpawnEgg", "Pet Roc Egg", "Overworld");
	public static final Item soulWatcherOrb = new ItemNetherBossSpawner("soulWatcherOrb", "Soulless Eye");
	public static final Item sentryKingOrb = new ItemSpecificDimensionSpawner(Config.corba, "sentryKingOrb", "Eye of the Sentry", "Corba");
	public static final Item loggerOrb = new ItemSpecificDimensionSpawner(Config.corba, "loggerOrb", "Enchanted Log", "Corba");
	public static final Item thunderbirdOrb = new ItemSpecificDimensionSpawner(Config.depths, "thunderbirdOrb", "Leader's Pearl", "Depths");
	public static final Item mysteriousDisk = new ItemSpecificDimensionSpawner(Config.cloudia, "mysteriousDisk", "Mysterious Disk", "Cloudia");
	public static final Item corallatorOrb = new ItemSpecificDimensionSpawner(Config.euca, "corallatorOrb", "Gem of Peculiar Smelting", "Euca");
	public static final Item scaleOrb = new ItemSpecificDimensionSpawner(Config.depths, "scaleOrb", "Aquatic Egg", "Depths");
	public static final Item enchantedTerrastar = new ItemSpecificDimensionSpawner(Config.terrania, "enchantedTerrastar", "Enchanted Terrastar", "Terrania");

	public static final Item weakDarkEnergyPotion = new ItemEssencePotion("weakDarkEnergyPotion", "Weak Dark Energy Potion", false, false);
	public static final Item strongDarkEnergyPotion = new ItemEssencePotion("strongDarkEnergyPotion", "Strong Dark Energy Potion", true, false);
	public static final Item weakEssencePotion = new ItemEssencePotion("weakEssencePotion", "Weak Essence Potion", false, true);
	public static final Item strongEssencePotion = new ItemEssencePotion("strongEssencePotion", "Strong Essence Potion", true, true);

	public static final Item hellstoneHelmet = new ItemModArmor(EnumArmor.HELLSTONE, HEAD);
	public static final Item hellstoneChest = new ItemModArmor(EnumArmor.HELLSTONE, BODY);
	public static final Item hellstoneLegs = new ItemModArmor(EnumArmor.HELLSTONE, LEGS);
	public static final Item hellstoneBoots = new ItemModArmor(EnumArmor.HELLSTONE, BOOTS);

	public static final Item flairiumHelmet = new ItemModArmor(EnumArmor.FLAIRIUM, HEAD);
	public static final Item flairiumChest = new ItemModArmor(EnumArmor.FLAIRIUM, BODY);
	public static final Item flairiumLegs = new ItemModArmor(EnumArmor.FLAIRIUM, LEGS);
	public static final Item flairiumBoots = new ItemModArmor(EnumArmor.FLAIRIUM, BOOTS);

	public static final Item celestiumHelmet = new ItemModArmor(EnumArmor.CELESTIUM, HEAD);
	public static final Item celestiumChest = new ItemModArmor(EnumArmor.CELESTIUM, BODY);
	public static final Item celestiumLegs = new ItemModArmor(EnumArmor.CELESTIUM, LEGS);
	public static final Item celestiumBoots = new ItemModArmor(EnumArmor.CELESTIUM, BOOTS);

	public static final Item luniumHelmet = new ItemModArmor(EnumArmor.LUNIUM, HEAD);
	public static final Item luniumChest = new ItemModArmor(EnumArmor.LUNIUM, BODY);
	public static final Item luniumLegs = new ItemModArmor(EnumArmor.LUNIUM, LEGS);
	public static final Item luniumBoots = new ItemModArmor(EnumArmor.LUNIUM, BOOTS);

	public static final Item shadiumHelmet = new ItemModArmor(EnumArmor.SHADIUM, HEAD);
	public static final Item shadiumChest = new ItemModArmor(EnumArmor.SHADIUM, BODY);
	public static final Item shadiumLegs = new ItemModArmor(EnumArmor.SHADIUM, LEGS);
	public static final Item shadiumBoots = new ItemModArmor(EnumArmor.SHADIUM, BOOTS);

	public static final Item sapphireHelmet = new ItemModArmor(EnumArmor.SAPPHIRE, HEAD);
	public static final Item sapphireChest = new ItemModArmor(EnumArmor.SAPPHIRE, BODY);
	public static final Item sapphireLegs = new ItemModArmor(EnumArmor.SAPPHIRE, LEGS);
	public static final Item sapphireBoots = new ItemModArmor(EnumArmor.SAPPHIRE, BOOTS);

	public static final Item gorbiteHelmet = new ItemModArmor(EnumArmor.GORBITE, HEAD);
	public static final Item gorbiteChest = new ItemModArmor(EnumArmor.GORBITE, BODY);
	public static final Item gorbiteLegs = new ItemModArmor(EnumArmor.GORBITE, LEGS);
	public static final Item gorbiteBoots = new ItemModArmor(EnumArmor.GORBITE, BOOTS);

	public static final Item orbaditeHelmet = new ItemModArmor(EnumArmor.ORBADITE, HEAD);
	public static final Item orbaditeChest = new ItemModArmor(EnumArmor.ORBADITE, BODY);
	public static final Item orbaditeLegs = new ItemModArmor(EnumArmor.ORBADITE, LEGS);
	public static final Item orbaditeBoots = new ItemModArmor(EnumArmor.ORBADITE, BOOTS);

	public static final Item flameHelmet = new ItemModArmor(EnumArmor.FLAME, HEAD);
	public static final Item flameChest = new ItemModArmor(EnumArmor.FLAME, BODY);
	public static final Item flameLegs = new ItemModArmor(EnumArmor.FLAME, LEGS);
	public static final Item flameBoots = new ItemModArmor(EnumArmor.FLAME, BOOTS);

	public static final Item twilightHelmet = new ItemModArmor(EnumArmor.TWILIGHT, HEAD);
	public static final Item twilightChest = new ItemModArmor(EnumArmor.TWILIGHT, BODY);
	public static final Item twilightLegs = new ItemModArmor(EnumArmor.TWILIGHT, LEGS);
	public static final Item twilightBoots = new ItemModArmor(EnumArmor.TWILIGHT, BOOTS);

	public static final Item leapersHelmet = new ItemModArmor(EnumArmor.LEAPERS, HEAD);
	public static final Item leapersChest = new ItemModArmor(EnumArmor.LEAPERS, BODY);
	public static final Item leapersLegs = new ItemModArmor(EnumArmor.LEAPERS, LEGS);
	public static final Item leapersBoots = new ItemModArmor(EnumArmor.LEAPERS, BOOTS);

	public static final Item snakeskinHelmet = new ItemModArmor(EnumArmor.SNAKESKIN, HEAD);
	public static final Item snakeskinChest = new ItemModArmor(EnumArmor.SNAKESKIN, BODY);
	public static final Item snakeskinLegs = new ItemModArmor(EnumArmor.SNAKESKIN, LEGS);
	public static final Item snakeskinBoots = new ItemModArmor(EnumArmor.SNAKESKIN, BOOTS);

	public static final Item treehuggersHelmet = new ItemModArmor(EnumArmor.TREEHUGGERS, HEAD);
	public static final Item treehuggersChest = new ItemModArmor(EnumArmor.TREEHUGGERS, BODY);
	public static final Item treehuggersLegs = new ItemModArmor(EnumArmor.TREEHUGGERS, LEGS);
	public static final Item treehuggersBoots = new ItemModArmor(EnumArmor.TREEHUGGERS, BOOTS);

	public static final Item charskullHelmet = new ItemModArmor(EnumArmor.CHAR_SKULL, HEAD);
	public static final Item charskullChest = new ItemModArmor(EnumArmor.CHAR_SKULL, BODY);
	public static final Item charskullLegs = new ItemModArmor(EnumArmor.CHAR_SKULL, LEGS);
	public static final Item charskullBoots = new ItemModArmor(EnumArmor.CHAR_SKULL, BOOTS);

	public static final Item bronzedHelmet = new ItemModArmor(EnumArmor.BRONZED, HEAD);
	public static final Item bronzedChest = new ItemModArmor(EnumArmor.BRONZED, BODY);
	public static final Item bronzedLegs = new ItemModArmor(EnumArmor.BRONZED, LEGS);
	public static final Item bronzedBoots = new ItemModArmor(EnumArmor.BRONZED, BOOTS);

	public static final Item golditeHelmet = new ItemModArmor(EnumArmor.GOLDITE, HEAD);
	public static final Item golditeChest = new ItemModArmor(EnumArmor.GOLDITE, BODY);
	public static final Item golditeLegs = new ItemModArmor(EnumArmor.GOLDITE, LEGS);
	public static final Item golditeBoots = new ItemModArmor(EnumArmor.GOLDITE, BOOTS);

	public static final Item corbarkHelmet = new ItemModArmor(EnumArmor.CORBARK, HEAD);
	public static final Item corbarkChest = new ItemModArmor(EnumArmor.CORBARK, BODY);
	public static final Item corbarkLegs = new ItemModArmor(EnumArmor.CORBARK, LEGS);
	public static final Item corbarkBoots = new ItemModArmor(EnumArmor.CORBARK, BOOTS);

	public static final Item crystalFlakeHelmet = new ItemModArmor(EnumArmor.CRYSTAL_FLAKE, HEAD);
	public static final Item crystalFlakeChest = new ItemModArmor(EnumArmor.CRYSTAL_FLAKE, BODY);
	public static final Item crystalFlakeLegs = new ItemModArmor(EnumArmor.CRYSTAL_FLAKE, LEGS);
	public static final Item crystalFlakeBoots = new ItemModArmor(EnumArmor.CRYSTAL_FLAKE, BOOTS);

	public static final Item darklyHelmet = new ItemModArmor(EnumArmor.DARKLY, HEAD);
	public static final Item darklyChest = new ItemModArmor(EnumArmor.DARKLY, BODY);
	public static final Item darklyLegs = new ItemModArmor(EnumArmor.DARKLY, LEGS);
	public static final Item darklyBoots = new ItemModArmor(EnumArmor.DARKLY, BOOTS);

	public static final Item depthsHelmet = new ItemModArmor(EnumArmor.DEPTHS, HEAD);
	public static final Item depthsChest = new ItemModArmor(EnumArmor.DEPTHS, BODY);
	public static final Item depthsLegs = new ItemModArmor(EnumArmor.DEPTHS, LEGS);
	public static final Item depthsBoots = new ItemModArmor(EnumArmor.DEPTHS, BOOTS);

	public static final Item enlightenerHelmet = new ItemModArmor(EnumArmor.ENLIGHTENER, HEAD);
	public static final Item enlightenerChest = new ItemModArmor(EnumArmor.ENLIGHTENER, BODY);
	public static final Item enlightenerLegs = new ItemModArmor(EnumArmor.ENLIGHTENER, LEGS);
	public static final Item enlightenerBoots = new ItemModArmor(EnumArmor.ENLIGHTENER, BOOTS);

	public static final Item fireboundHelmet = new ItemModArmor(EnumArmor.FIREBOUND, HEAD);
	public static final Item fireboundChest = new ItemModArmor(EnumArmor.FIREBOUND, BODY);
	public static final Item fireboundLegs = new ItemModArmor(EnumArmor.FIREBOUND, LEGS);
	public static final Item fireboundBoots = new ItemModArmor(EnumArmor.FIREBOUND, BOOTS);

	public static final Item frostbittenHelmet = new ItemModArmor(EnumArmor.FROSTBITTEN, HEAD);
	public static final Item frostbittenChest = new ItemModArmor(EnumArmor.FROSTBITTEN, BODY);
	public static final Item frostbittenLegs = new ItemModArmor(EnumArmor.FROSTBITTEN, LEGS);
	public static final Item frostbittenBoots = new ItemModArmor(EnumArmor.FROSTBITTEN, BOOTS);

	public static final Item hollowHelmet = new ItemModArmor(EnumArmor.HOLLOW, HEAD);
	public static final Item hollowChest = new ItemModArmor(EnumArmor.HOLLOW, BODY);
	public static final Item hollowLegs = new ItemModArmor(EnumArmor.HOLLOW, LEGS);
	public static final Item hollowBoots = new ItemModArmor(EnumArmor.HOLLOW, BOOTS);

	public static final Item lightstoneHelmet = new ItemModArmor(EnumArmor.LIGHTSTONE, HEAD);
	public static final Item lightstoneChest = new ItemModArmor(EnumArmor.LIGHTSTONE, BODY);
	public static final Item lightstoneLegs = new ItemModArmor(EnumArmor.LIGHTSTONE, LEGS);
	public static final Item lightstoneBoots = new ItemModArmor(EnumArmor.LIGHTSTONE, BOOTS);

	public static final Item livegreenHelmet = new ItemModArmor(EnumArmor.LIVEGREEN, HEAD);
	public static final Item livegreenChest = new ItemModArmor(EnumArmor.LIVEGREEN, BODY);
	public static final Item livegreenLegs = new ItemModArmor(EnumArmor.LIVEGREEN, LEGS);
	public static final Item livegreenBoots = new ItemModArmor(EnumArmor.LIVEGREEN, BOOTS);

	public static final Item starlightHelmet = new ItemModArmor(EnumArmor.STARLIGHT, HEAD);
	public static final Item starlightChest = new ItemModArmor(EnumArmor.STARLIGHT, BODY);
	public static final Item starlightLegs = new ItemModArmor(EnumArmor.STARLIGHT, LEGS);
	public static final Item starlightBoots = new ItemModArmor(EnumArmor.STARLIGHT, BOOTS);

	public static final Item bloodcrustHelmet = new ItemModArmor(EnumArmor.BLOODCRUST, HEAD);
	public static final Item bloodcrustChest = new ItemModArmor(EnumArmor.BLOODCRUST, BODY);
	public static final Item bloodcrustLegs = new ItemModArmor(EnumArmor.BLOODCRUST, LEGS);
	public static final Item bloodcrustBoots = new ItemModArmor(EnumArmor.BLOODCRUST, BOOTS);

	public static final Item blazehornHelmet = new ItemModArmor(EnumArmor.BLAZEHORN, HEAD);
	public static final Item blazehornChest = new ItemModArmor(EnumArmor.BLAZEHORN, BODY);
	public static final Item blazehornLegs = new ItemModArmor(EnumArmor.BLAZEHORN, LEGS);
	public static final Item blazehornBoots = new ItemModArmor(EnumArmor.BLAZEHORN, BOOTS);

	public static final Item bleedrockHelmet = new ItemModArmor(EnumArmor.BLEEDROCK, HEAD);
	public static final Item bleedrockChest = new ItemModArmor(EnumArmor.BLEEDROCK, BODY);
	public static final Item bleedrockLegs = new ItemModArmor(EnumArmor.BLEEDROCK, LEGS);
	public static final Item bleedrockBoots = new ItemModArmor(EnumArmor.BLEEDROCK, BOOTS);

	/*public static final Item rockyBattleaxe = new ItemBattleAxe("rockyBattleaxe", "Rocky Battleaxe", EssenceToolMaterial.ROCKY_BATTLEAXE);
	public static final Item crystalizedBattleaxe = new ItemBattleAxe("crystalizedBattleaxe", "Crystalized Battleaxe", EssenceToolMaterial.CRYSTAL_BATTLEAXE);
	public static final Item backBiter = new ItemBattleAxe("backBiter", "Back Biter", EssenceToolMaterial.BACK_BITER);
	public static final Item dawnBreaker = new ItemBattleAxe("dawnBreaker", "Dawn Breaker", EssenceToolMaterial.DAWN_BREAKER);
	public static final Item tempestBattleaxe = new ItemBattleAxe("tempestBattleaxe", "Tempest Battleaxe", EssenceToolMaterial.TEMPEST_BATTLEAXE);
	public static final Item bronzedBattleaxe = new ItemBattleAxe("bronzedBattleaxe", "Bronzed Battleaxe", EssenceToolMaterial.BRONZED_BATTLEAXE);
	public static final Item celestiteBattleaxe = new ItemBattleAxe("celestiteBattleaxe", "Celestite Battleaxe", EssenceToolMaterial.CELESTITE_BATTLEAXE);
	public static final Item storumBattleaxe = new ItemBattleAxe("storumBattleaxe", "Storum Battleaxe", EssenceToolMaterial.STORUM_BATTLEAXE);
	public static final Item celekiumBattleaxe = new ItemBattleAxe("celekiumBattleaxe", "Celekium Battleaxe", EssenceToolMaterial.CELEKIUM_BATTLEAXE);
	public static final Item thunderbirdBattleaxe = new ItemBattleAxe("thunderbirdBattleaxe", "Thunderbird Battleaxe", EssenceToolMaterial.THUNDERBIRD_BATTLEAXE);
*/
	public static final Item staffOfCrystal = new ItemStaff("staffOfCrystal", "Staff of Crystal", false, 3, 1000, 10, false, EntityIceBall.class);
	public static final Item staffOfDivineStone = new ItemStaff("staffOfDivineStone", "Staff of Divine Stone", false, 3, 1000, 9, false, EntityRock.class);
	public static final Item staffOfHellstone = new ItemStaff("staffOfHellstone", "Staff of Hellstone", false, 3, 1000, 7, false, EntityHellstone.class);
	public static final Item doomsBringer = new ItemStaff("doomsBringer", "Dooms Bringer", false, 3, 1000, 12, false, EntityDoomsBringer.class);
	public static final Item conjuringStaff = new ItemStaff("conjuringStaff", "Conjuring Staff", false, 3, 1000, 18, false, EntityConjuring.class);
	public static final Item staffOfEnlightenment = new ItemStaff("staffOfEnlightenment", "Staff Of Enlightenment", true, 3, 1000, 14, false, EntityEnlightenment.class);
	public static final Item staffOfGreenpace = new ItemStaff("staffOfGreenpace", "Staff Of Greenpace", true, 3, 1000, 10, false, EntityGreenpace.class);
	public static final Item wizardsStar = new ItemStaff("wizardsStar", "Wizards Star", true, 3, 1000, 5, false, EntityWizardsStar.class);
	public static final Item teleportationStaff = new ItemTeleport("teleportationStaff", "Teleportation Staff");
	public static final Item overgrownStaff = new ItemStaff("overgrownStaff", "Overgrown Staff", true, 3, 1000, 5, false, EntityOvergrown.class);

	/*public static final Item blueEgg = new ItemMod("blueEgg", EssenceTabs.misc);
	public static final Item redEgg = new ItemMod("redEgg", EssenceTabs.misc);
	public static final Item greenEgg = new ItemMod("greenEgg", EssenceTabs.misc);
	public static final Item orangeEgg = new ItemMod("orangeEgg", EssenceTabs.misc);
	public static final Item purpleEgg = new ItemMod("purpleEgg", EssenceTabs.misc);
	public static final Item yellowEgg = new ItemMod("yellowEgg", EssenceTabs.misc);
	public static final Item pinkEgg = new ItemMod("pinkEgg", EssenceTabs.misc);
	public static final Item cyanEgg = new ItemMod("cyanEgg", EssenceTabs.misc);

	public static final Item incubatedBlueEgg = new ItemEgg("incubatedBlueEgg");
	public static final Item incubatedRedEgg = new ItemEgg("incubatedRedEgg");
	public static final Item incubatedGreenEgg = new ItemEgg("incubatedGreenEgg");
	public static final Item incubatedOrangeEgg = new ItemEgg("incubatedOrangeEgg");
	public static final Item incubatedPurpleEgg = new ItemEgg("incubatedPurpleEgg");
	public static final Item incubatedYellowEgg = new ItemEgg("incubatedYellowEgg");
	public static final Item incubatedPinkEgg = new ItemEgg("incubatedPinkEgg");
	public static final Item incubatedCyanEgg = new ItemEgg("incubatedCyanEgg");*/

	//public static final Item flameArrow = new ItemMod("flameArrow", EssenceTabs.ranged);
	public static final Item essenceArrow = new ItemMod("essenceArrow", "Essence Arrow", JourneyTabs.misc);

	public static final Item flameBow = new ItemModBow("flameBow", "Flame Bow", 384, 3, essenceArrow,"Sets enemies ablaze", EntityFlameArrow.class);
	public static final Item poisonBow = new ItemModBow("poisonBow", "Poison Bow", 384, 3, essenceArrow,"Poisons enemies", EntityPoisonArrow.class);
	public static final Item darknessBow = new ItemModBow("darknessBow", "Darkness Bow", 384, 3, essenceArrow,"Withers foe", EntityDarknessArrow.class);
	public static final Item frozenBow = new ItemModBow("frozenBow", "Frozen Bow", 384, 3, essenceArrow,"Slows enemies", EntityFrozenArrow.class);
	public static final Item staringBow = new ItemModBow("staringBow", "Staring Bow", 456, 4, essenceArrow, "Sets enemies ablaze", EntityFlameArrow.class);
	public static final Item deathPiercerBow = new ItemModBow("deathPiercerBow", "Death Piercer Bow", 892, 6, essenceArrow, 0, "Withers foe", EntityDarknessArrow.class);
	public static final Item fusionBow = new ItemModBow("fusionBow", "Fusion Bow", 456, 4, essenceArrow,"Withers foe", EntityDarknessArrow.class);
	public static final Item springBow = new ItemModBow("springBow", "Spring Bow", 384, 5, essenceArrow,"Sets enemies ablaze", EntityFlameArrow.class);
	public static final Item starlightBow = new ItemModBow("starlightBow", "Starlight Bow", 384, 6, essenceArrow,"Poisons enemies", EntityPoisonArrow.class);
	public static final Item wastefulBow = new ItemModBow("wastefulBow", "Wasteful Bow", 892, 5, essenceArrow,"Poisons enemies", EntityPoisonArrow.class);
	public static final Item flamingBow = new ItemModBow("flamingBow", "Flaming Bow", 384, 4, essenceArrow,"Sets enemies ablaze", EntityFlameArrow.class);
	public static final Item blazingBow = new ItemModBow("blazingBow", "Blazing Bow", 456, 4, essenceArrow,"Sets enemies ablaze", EntityFlameArrow.class);
	public static final Item darkEnforcer = new ItemModBow("darkEnforcer", "Dark Enforcer", 384, 4, essenceArrow,"Withers foe", EntityDarknessArrow.class);
	public static final Item depthsBow = new ItemModBow("depthsBow", "Depths Bow", 892, 4, essenceArrow,"Withers foe", EntityDarknessArrow.class);
	public static final Item frostbittenBow = new ItemModBow("frostbittenBow", "Frostbitten Bow", 384, 4, essenceArrow,"Slows enemies", EntityFrozenArrow.class);
	public static final Item frostyBow = new ItemModBow("frostyBow", "Frosty Bow", 456, 4, essenceArrow,"Slows enemies", EntityFrozenArrow.class);
	public static final Item charredBow = new ItemModBow("charredBow", "Charred Bow", 384, 5, essenceArrow,"Withers enemies", EntityDarknessArrow.class);
	public static final Item fluffyBow = new ItemModBow("fluffyBow", "Fluffy Bow", 892, 9, essenceArrow,"Slows enemies", EntityFrozenArrow.class);
	public static final Item golemBow = new ItemModBow("golemBow", "Golem Bow", 456, 7, essenceArrow,"Withers foe", EntityDarknessArrow.class);
	public static final Item loggersBow = new ItemModBow("loggersBow", "Loggers Bow", 384, 6, essenceArrow,"Poisons enemies", EntityPoisonArrow.class);
	public static final Item overgrownBow = new ItemModBow("overgrownBow", "Overgrown Bow", 384, 7, essenceArrow,"Poisons enemies", EntityPoisonArrow.class);
	public static final Item overseerBow = new ItemModBow("overseerBow", "Overseer Bow", 384, 7, essenceArrow,"Withers foe", EntityDarknessArrow.class);
	public static final Item woodlandBow = new ItemModBow("woodlandBow", "Woodland Bow", 456, 7, essenceArrow,"Poisons enemies", EntityPoisonArrow.class);
	public static final Item rocsWing = new ItemModBow("rocsWing", "Rocs Wing", 892, 6, essenceArrow,"Withers foe", EntityDarknessArrow.class);
	public static final Item scaleBow = new ItemModBow("scaleBow", "Scale Bow", 456, 7, essenceArrow,"Withers foe", EntityDarknessArrow.class);
	public static final Item mantleBow = new ItemModBow("mantleBow", "Mantle Bow", 384, 5, essenceArrow,"Sets enemies ablaze", EntityFlameArrow.class);
	public static final Item coreExpender = new ItemModBow("coreExpender", "Core Expender", 384, 6, essenceArrow,"Withers foe", EntityFlameArrow.class);
	public static final Item royalBow = new ItemModBow("royalBow", "Royal Bow", 384, 5, essenceArrow,"Withers foe", EntityDarknessArrow.class);
	public static final Item darkTerraBow = new ItemModBow("darkTerraBow", "Dark Terra Bow", 456, 5, essenceArrow,"Withers foe", EntityDarknessArrow.class);
	public static final Item lavenderBow = new ItemModBow("lavenderBow", "Lavender Bow", 892, 6, essenceArrow,"Slows enemies", EntityFrozenArrow.class);
	public static final Item terralightBow = new ItemModBow("terralightBow", "Terralight Bow", 384, 7, essenceArrow,"Poisons enemies", EntityPoisonArrow.class);
	public static final Item terrianBow = new ItemModBow("terrianBow", "Terrian Bow", 384, 6, essenceArrow,"Poisons enemies", EntityPoisonArrow.class);

	//public static final Item backpack = new ItemBackpack("backpack");

	public static final Item fireWand = new ItemWand("fireWand", "Fire Wand", false, 5, 600, 8, false, EntityFireBall.class);
	public static final Item iceWand = new ItemWand("iceWand", "Ice Wand", true, 5, 600, 8, false, EntityIceBall.class);
	public static final Item lightningWand = new ItemWand("lightningWand", "Lightning Wand", false, 10, 600, 8, false, EntityLightningBall.class);

	public static final Item hammerCreative = new ItemCreativeHammer("hammerCreative", "Hammer of Creative Spellbinding", EssenceToolMaterial.CREATIVE, false, EntityLightningBall.class);
	public static final Item earthenHammer = new ItemHammer("earthenHammer", "Earthen Hammer", EssenceToolMaterial.EARTHEN_HAMMER, false, EntityEarthen.class, false, true, 4, 4, 642);
	public static final Item flamingHammer = new ItemHammer("flamingHammer", "Flaming Hammer", EssenceToolMaterial.FLAMING_HAMMER, false, EntityFireBall.class, false, true, 10, 4, 1230);
	public static final Item nethicHammer = new ItemHammer("nethicHammer", "Nethic Hammer", EssenceToolMaterial.NETHIC_HAMMER, false, EntityFireBall.class, false, true, 8, 4, 825);
	public static final Item withicHammer = new ItemHammer("withicHammer", "Withic Hammer", EssenceToolMaterial.WITHIC_HAMMER, false, EntityWithic.class, false, true, 9, 4, 1230);
	public static final Item royalHammer = new ItemHammer("royalHammer", "Royal Hammer", EssenceToolMaterial.ROYAL_HAMMER, false, EntityFireBall.class, false, true, 12, 4, 1320);
	public static final Item overgrownHammer = new ItemHammer("overgrownHammer", "Overgrown Hammer", EssenceToolMaterial.OVERGROWN_HAMMER, false, EntityOvergrown.class, false, true, 12, 4, 1320);
	public static final Item rockyHammer = new ItemHammer("rockyHammer", "Rocky Hammer", EssenceToolMaterial.ROCKY_HAMMER, false, EntityRock.class, false, true, 6, 4, 2230);
	public static final Item crystalizedHammer = new ItemHammer("crystalizedHammer", "Crystalized Hammer", EssenceToolMaterial.CRYSTAL_HAMMER, false, EntityIceBall.class, false, true, 7, 4, 3320);

	public static final Item chaosCannon = new ItemChaosCannon("chaosCannon", "Chaos Cannon", 6, 4, "Shoots a bouncing projectile");
	public static final Item rockLauncher = new ItemGun("rockLauncher", "Rock Launcher", 4, "Stuns mobs for 10 seconds", EntityRock.class);
	public static final Item netherPlasma = new ItemGun("netherPlasma", "Nether Plasma", 10, "Burns mobs for 10 seconds", EntityNetherPlasma.class);
	public static final Item oceanPlasma = new ItemGun("oceanPlasma", "Ocean Plasma", 4, "Harms mobs", EntityFloroWater.class);
	public static final Item forestPlasma = new ItemGun("forestPlasma", "Forest Plasma", 4, "Poisons Mobs for 10 seconds", EntityOvergrown.class);
	public static final Item eyeBlaster = new ItemGun("eyeBlaster", "Eye Blaster", 12, "Harms and burns mobs for 10 seconds", EntityEyeBlaster.class);

	public static final Item greenGem = new ItemMod("greenGem", "Green Gem");
	public static final Item purpleGem = new ItemMod("purpleGem", "Purple Gem");
	public static final Item blueGem = new ItemMod("blueGem", "Blue Gem");
	public static final Item yellowGem = new ItemMod("yellowGem", "Yellow Gem");

	public static final Item eucaTablet = new ItemMod("eucaTablet", "Euca Tablet");

	public static final Item wandBase = new ItemMod("wandBase", "Wand Base");
	public static final Item staffBase = new ItemMod("staffBase", "Staff Base");

	public static final Item frostyGift = new ItemPresent("frostyGift", "Frosty Gift");

	public static final Item firestoneClump = new ItemMod("firestoneClump", "Firestone Clump");

	//TODO
	public static final Item friedFlamingGhastTentacale = new ItemModFood("friedFlamingGhastTentacle", "Fried Flaming Tentacale", 4, 0.6F, 10, true).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 5, 1), 1.0F);
	public static final Item flamingGhastTentacle = new ItemModFood("flamingGhastTentacle", "Flaming Ghast Tentacale", 1, 0.6F, 10, true).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 5, 1), 1.0F);
	public static final Item friedGhastTentacale = new ItemModFood("friedGhastTentacle", "Fried Ghast Tentacale", 4, 0.6F, 10, true);
	public static final Item ghastTentacle = new ItemModFood("ghastTentacle", "Ghast Tentacale", 1, 0.6F, 10, true);
	public static final Item friedEgg = new ItemModFood("friedEgg", "Fried Egg", 2, 0.6F, 10, false);
	public static final Item floroPedal = new ItemModFood("floroPedal", "Floro Pedal", 3, 0.6F, 10, false);
	public static final Item tomato = new ItemModFood("tomato", "Tomato", 3, 0.6F, 10, false);
	public static final Item airMelon = new ItemModFood("airMelon", "Air Melon", 10, 3.0F, 2, false).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.digSpeed, 10, 40), 1.0F);
	public static final Item glowshroom = new ItemModFood("glowshroom", "Glowshroom", 4, 0.6F, 10, false).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 10, 1), 1.0F).setAlwaysEdible();
	public static final Item terrashroom = new ItemModFood("terrashroom", "Terrashroom", 8, 0.6F, 10, false).setAlwaysEdible().setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 10, 1), 1.0F).setAlwaysEdible();
	public static final Item corveggies = new ItemModFood("corveggies", "Corveggies", 4, 0.6F, 10, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.saturation, 20, 1), 1.0F).setAlwaysEdible();
	public static final Item crackenCanes = new ItemCrackenCanes("crackenCanes", "Kracken Canes", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 20, 1), 1.0F).setAlwaysEdible();
	public static final Item crakeBulb = new ItemCrakeBulb("crakeBulb"," Crake Bulb", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.waterBreathing, 20, 1), 1.0F).setAlwaysEdible();
	public static final Item spineberries = new ItemSpineberries("spineBerries", "Spine Berries", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.absorption, 20, 1), 1.0F).setAlwaysEdible();
	public static final Item zatPedal = new ItemZatPedal("zatPedal", "Zat Pedal", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 20, 1), 1.0F).setAlwaysEdible();
	public static final Item glowa = new ItemGlowa("glowa", "Glowa", 4, 3.0F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.nightVision, 5, 1), 1.0F).setAlwaysEdible();
	public static final Item mintCandyCane = new ItemCandyCane("mintCandyCane", "Mint Candy Cane", 3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.digSpeed, 10, 1), 1.0F).setAlwaysEdible();
	public static final Item fruityCandyCane = new ItemCandyCane("fruityCandyCane", "Fruity Candy Cane", 3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.damageBoost, 10, 1), 1.0F).setAlwaysEdible();
	public static final Item cherryCandyCane = new ItemCandyCane("cherryCandyCane", "Cherry Candy Cane", 3, 1.6F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 10, 1), 1.0F).setAlwaysEdible();
	public static final Item peppermint = new ItemModFood("peppermint", "Peppermint", 1, 0.1F, 2, false);
	public static final Item jellyBeans = new ItemModFood("jellyBeans", "Jelly Beans", 1, 0.1F, 2, false);
	public static final Item chocolate = new ItemModFood("chocolate", "Chocolate Bar", 2, 0.1F, 2, false);
	public static final Item vanillaWafer = new ItemModFood("vanillaWafer", "Vanilla Wafer", 1, 0.1F, 2, false);
	public static final Item bleedheart = new ItemBleedheart("bleedheart", "Bleedheart Fruit", 2, 0.1F, false);

	public static final Item sizzleberry = new ItemModFood("sizzleberry", "Sizzleberry", 1, 4.0F, 5, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 20, 1), 1.0F).setAlwaysEdible();
	public static final Item bradberry = new ItemModFood("bradberry", "Bradberry", 1, 8, 4, false);
	public static final Item tangleberry = new ItemModFood("tangleberry", "Tangleberry", 2, 4, 1, false);
	public static final Item juiceberry = new ItemModFood("juiceberry", "Juiceberry", 1, 6, 1, false);
	public static final Item bogberry = new ItemModFood("bogberry", "Bogberry", 2, 6, 3, false);

	public static final Item goldenSteak = new ItemGoldenFood("normalGoldenSteak", "Golden Steak", 6, 1.2F, false, false);
	public static final Item goldenSteakOP = new ItemGoldenFood("OPGoldenSteak", "Golden Steak", 10, 2.2F, false, true);

	public static final Item goldenPotato = new ItemGoldenFood("normalGoldenPotato", "Golden Potato", 3, 2.0F, false, false);
	public static final Item goldenPotatoOP = new ItemGoldenFood("OPGoldenPotato", "Golden Potato", 6, 2.5F, false, true);

	public static final Item goldenPork = new ItemGoldenFood("normalGoldenPork", "Golden Porkchop", 10, 1.0F, false, false);
	public static final Item goldenPorkOP = new ItemGoldenFood("OPGoldenPork", "Golden Porkchop", 12, 1.2F, false, true);

	public static final Item goldenFish = new ItemGoldenFood("normalGoldenFish", "Golden Fish", 6, 2.0F, false, false);
	public static final Item goldenFishOP = new ItemGoldenFood("OPGoldenFish", "Golden Fish", 8, 2.5F, false, true);

	public static final Item goldenChicken = new ItemGoldenFood("normalGoldenChicken", "Golden Chicken", 2, 3.0F, false, false);
	public static final Item goldenChickenOP = new ItemGoldenFood("OPGoldenChicken", "Golden Chicken", 4, 3.5F, false, true);

	public static final Item goldenRabbit = new ItemGoldenFood("normalGoldenRabbit", "Golden Rabbit", 2, 3.5F, false, false);
	public static final Item goldenRabbitOP = new ItemGoldenFood("OPGoldenRabbit", "Golden Rabbit", 4, 4.0F, false, true);

	public static final Item goldenMutton = new ItemGoldenFood("normalGoldenMutton", "Golden Mutton", 2, 3.5F, false, false);
	public static final Item goldenMuttonOP = new ItemGoldenFood("OPGoldenMutton", "Golden Mutton", 4, 4.0F, false, true);

	public static final Item goldenWing = new ItemGoldenFood("normalGoldenWing", "Golden Wing", 2, 4.5F, false, false);
	public static final Item goldenWingOP = new ItemGoldenFood("OPGoldenWing", "Golden Wing", 4, 5.0F, false, true);

	public static final Item eucaMeat = new ItemModFood("eucaMeat", "Euca Meat", 6, 0.6F, 10, true);
	public static final Item rocMeat = new ItemModFood("rocMeat", "Bird Wing", 4, 0.6F, 10, true);
	public static final Item cookedRocMeat = new ItemModFood("cookedRocMeat", "Cooked Bird Wing", 10, 0.6F, 10, true);
	public static final Item hongoShroom = new ItemModFood("hongoShroom", "Hongoshroom", 4, 1.2F, 10, false);
	public static final Item greenHonglowShroom = new ItemHonglow("greenHonglowShroom", "Green Honglowshroom", 2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.jump, 10, 1), 1.0F).setAlwaysEdible();
	public static final Item redHonglowShroom = new ItemHonglow("redHonglowShroom", "Red Honglowshroom", 2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.regeneration, 10, 1), 1.0F).setAlwaysEdible();
	public static final Item blueHonglowShroom = new ItemHonglow("blueHonglowShroom", "Blue Honglowshroom", 2, 1.2F, false, false).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSpeed, 10, 1), 1.0F).setAlwaysEdible();
	public static final Item honglowShroom = new ItemHonglow("honglowShroom", "Honglowshroom", 6, 1.2F, false, false).setAlwaysEdible();
	public static final Item snakeFlesh = new ItemZatPedal("snakeFlesh", "Snake Flesh", 6, 1.2F, false, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 60, 1), 1.0F).setAlwaysEdible();
	public static final Item flamingBeef = new ItemModFood("flamingBeef", "Raw Flaming Beef", 6, 1.2F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 90, 1), 1.0F).setAlwaysEdible();
	public static final Item flamingBeefCooked = new ItemModFood("flamingBeefCooked", "Cooked Flaming Beef", 16, 1.2F, true).setPotionEffect(PotionEffects.setPotionEffect(PotionEffects.fireResistance, 100, 2), 2.0F).setAlwaysEdible();

	public static final Item underwaterWorldRecord = new ItemModRecord("underwaterWorld", "Underwater World", null);
	public static final Item blueWater = new ItemModRecord("blueWater", "Blue Water", null);
	public static final Item raceStar = new ItemModRecord("raceStar", "Race Star", null);
	public static final Item compBegins = new ItemModRecord("compBegins", "Compition Begins", null);
	public static final Item deepBlue = new ItemModRecord("deepBlue", "Deep Blue", null);
	public static final Item raceShore = new ItemModRecord("raceShore", "Race to Shore", null);

	public static final Item demonicEye = new ItemDemonicEye("demonicEye", "Demonic Eye");

	public static final ItemKnowledge overworldKnowledge = new ItemKnowledge("overworldKnowledge", "Overworld Knowledge", EnumKnowledge.OVERWORLD);
	public static final ItemKnowledge netherKnowledge = new ItemKnowledge("netherKnowledge", "Nether Knowledge", EnumKnowledge.NETHER);
	public static final ItemKnowledge endKnowledge = new ItemKnowledge("endKnowledge", "End knowledge", EnumKnowledge.END);
	public static final ItemKnowledge boilKnowledge = new ItemKnowledge("boilingKnowledge", "Boiling Knowledge", EnumKnowledge.BOIL);
	public static final ItemKnowledge frozenKnowledge = new ItemKnowledge("frozenKnowledge", "Frozen Knowledge", EnumKnowledge.FROZEN);
	public static final ItemKnowledge eucaKnowledge = new ItemKnowledge("eucaKnowledge", "Euca Knowledge", EnumKnowledge.EUCA);
	public static final ItemKnowledge depthsKnowledge = new ItemKnowledge("depthsKnowledge", "Depths Knowledge", EnumKnowledge.DEPTHS);
	public static final ItemKnowledge corbaKnowledge = new ItemKnowledge("corbaKnowledge", "Corba Knowledge", EnumKnowledge.CORBA);
	public static final ItemKnowledge cloudiaKnowledge = new ItemKnowledge("cloudiaKnowledge", "Cloudia Knowledge", EnumKnowledge.CLOUDIA);
	//public static final ItemKnowledge wastelandsKnowledge = new ItemKnowledge("wastelandsKnowledge", EnumKnowledge.WASTELANDS);
	//public static final ItemKnowledge lithiumKnowledge = new ItemKnowledge("lithiumKnowledge", EnumKnowledge.LITHIUM);
	//public static final ItemKnowledge libraryKnowledge = new ItemKnowledge("libraryKnowledge", EnumKnowledge.LIBRARY);
	//public static final ItemKnowledge blazeKnowledge = new ItemKnowledge("blazeKnowledge", EnumKnowledge.BLAZE);
	//public static final ItemKnowledge witherKnowledge = new ItemKnowledge("witherKnowledge", EnumKnowledge.WITHER);

	public static final ItemMod boilingPointEssence = new ItemMod("boilingPointEssence", "Boiling Point Essence");
	public static final ItemMod cloudiaEssence = new ItemMod("cloudiaEssence", "Cloudia Essence");
	public static final ItemMod corbaEssence = new ItemMod("corbaEssence", "Corba Essence");
	public static final ItemMod depthsEssence = new ItemMod("depthsEssence", "Depths Essence");
	public static final ItemMod eucaEssence = new ItemMod("eucaEssence", " Euca Essence");
	public static final ItemMod frozenLandsEssence = new ItemMod("frozenLandsEssence", "Frozen Lands Essence");
	//public static final ItemMod wastelandsEssence = new ItemMod("wastelandsEssence");
	//public static final ItemMod lithiumEssence = new ItemMod("lithiumEssence");
	//public static final ItemMod nethicEssence = new ItemMod("nethicEssence");
	//public static final ItemMod subterrianEssence = new ItemMod("subterrianEssence");
	//public static final ItemMod withicEssence = new ItemMod("withicEssence");
	//public static final ItemMod blazesEssence = new ItemMod("blazesEssence");

	//TODO
	public static final Item demonicBomb = new ItemThrowable("demonicBomb", "Demonic Bomb", 8F, EntityDemonicBomb.class);
	public static final Item fireBomb = new ItemThrowable("fireBomb", "Fire Bomb", 12F, EntityFireBomb.class);

	public static final Item boilingPiercer = new ItemPiercer("boilingPiercer", "Boiling Piercer", 12F, 6, EntityBoilingPiercer.class);
	public static final Item nethicPiercer = new ItemPiercer("nethicPiercer", "Nethic Piercer", 11F, 6, EntityNethicPiercer.class);
	public static final Item frozenPiercer = new ItemPiercer("frozenPiercer", "Frozen Piercer", 9F, 6, EntityFrozenPiercer.class);
	public static final Item eucaPiercer = new ItemPiercer("eucaPiercer", "Euca Piercer", 12F, 6, EntityEucaPiercer.class);
	public static final Item depthsPiercer = new ItemPiercer("depthsPiercer", "Depths Piercer", 21F, 6, EntityDepthsPiercer.class);
	public static final Item corbaPiercer = new ItemPiercer("corbaPiercer", "Corba Piercer", 29F, 6, EntityCorbaPiercer.class);
	public static final Item frostbittenPiercer = new ItemPiercer("frostbittenPiercer", "Frostbitten Piercer", 10F, 6, EntityFrostbittenPiercer.class);
	public static final Item frostyPiercer = new ItemPiercer("frostyPiercer", "Frosty Piercer", 9F, 6, EntityFrostyPiercer.class);
	public static final Item sunsetPiercer = new ItemPiercer("sunsetPiercer", "Sunset Piercer", 10F, 6, EntityFrostyPiercer.class);
	public static final Item skyPiercer = new ItemPiercer("skyPiercer", "Sky Piercer", 10F, 6, EntityFrostyPiercer.class);

	/**
	public static final Item moltenKnife = new ItemKnife("moltenKnife", "Molten Knife", 10F, EntityFrostyPiercer.class);
	public static final Item royalKnife = new ItemThrowable("royalKnife", "Royal Knife", 8F, EntityRoyalKnife.class);
	public static final Item aquaticKnife = new ItemThrowable("aquaticKnife", "Aquatic Knife", 6F, EntityFrostyPiercer.class);
	public static final Item bloodKnife = new ItemThrowable("bloodKnife", "Blood Knife", 6F, EntityFrostyPiercer.class);
	public static final Item charredKnife = new ItemThrowable("charredKnife", "Charred Knife", 6F, EntityFrostyPiercer.class);
	public static final Item sizzlingKnife = new ItemThrowable("sizzlingKnife", "Sizzling Knife", 6F, EntityFrostyPiercer.class);

	/**
	public static final Item weakPower = new ItemPower("weakPower", 10F);
	 */


public static ToolMaterial addToolMaterial(int uses, float efficiency, float dam, boolean breakable) {
	return EnumHelper.addToolMaterial("tool", 3, breakable ? uses : -1, efficiency, dam, 30);
}

}