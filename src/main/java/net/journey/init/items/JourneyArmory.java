package net.journey.init.items;

import net.journey.enums.EnumArmor;
import net.journey.init.JourneyTabs;
import net.journey.init.Registrar;
import net.journey.items.ItemModArmor;
import net.journey.items.tool.ItemBonemealHoe;
import net.journey.items.tool.ItemMultiTool;
import net.journey.util.JourneyToolMaterial;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.slayer.api.item.ItemModAxe;
import net.slayer.api.item.ItemModHoe;
import net.slayer.api.item.ItemModPickaxe;
import net.slayer.api.item.ItemModShovel;

public class JourneyArmory {

    public static final EntityEquipmentSlot HEAD = EntityEquipmentSlot.HEAD, BODY = EntityEquipmentSlot.CHEST, LEGS = EntityEquipmentSlot.LEGS, BOOTS = EntityEquipmentSlot.FEET;

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
    public static Item nethicPickaxe;
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
    public static Item nethicShovel;
    public static Item koriteShovel;
    public static Item storonShovel;
    public static Item mekyumShovel;

    public static Item bedrockShovel;

    public static Item hellstoneAxe;
    public static Item shadiumAxe;
    public static Item celestiumAxe;
    public static Item luniumAxe;
    public static Item flairiumAxe;
    public static Item sapphireAxe;
    public static Item gorbiteAxe;
    public static Item orbaditeAxe;
    public static Item desAxe;
    public static Item nethicAxe;
    public static Item koriteAxe;
    public static Item storonAxe;
    public static Item mekyumAxe;

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

    public static Item hoeOfEternalLife;
    public static Item hoeOfEarthLoving;
    public static Item multiToolOfEternalSmelting;
    public static Item slimyPickaxe;
    public static Item pickaxeOfGoodFortune;

    public static Item maskOfHellmetal;

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

    public static void init() {
        hellstoneMultiTool = Registrar.regAndSetupItem("hellstoneMultiTool", "Bloodcrust Multi Tool", new ItemMultiTool(JourneyToolMaterial.HELLSTONE_MULTI_TOOL, 1750), JourneyTabs.TOOLS);
        shadiumMultiTool = Registrar.regAndSetupItem("shadiumMultiTool", "Shadium Multi Tool", new ItemMultiTool(JourneyToolMaterial.SHADIUM_MULTI_TOOL, 1670), JourneyTabs.TOOLS);
        celestiumMultiTool = Registrar.regAndSetupItem("celestiumMultiTool", "Celestium Multi Tool", new ItemMultiTool(JourneyToolMaterial.CELESTIUM_MULTI_TOOL, 1820), JourneyTabs.TOOLS);
        luniumMultiTool = Registrar.regAndSetupItem("luniumMultiTool", "Lunium Multi Tool", new ItemMultiTool(JourneyToolMaterial.LUNIUM_MULTI_TOOL, 1670), JourneyTabs.TOOLS);
        flairiumMultiTool = Registrar.regAndSetupItem("flairiumMultiTool", "Flairium Multi Tool", new ItemMultiTool(JourneyToolMaterial.FLAIRIUM_MULTI_TOOL, 1202), JourneyTabs.TOOLS);
        sapphireMultiTool = Registrar.regAndSetupItem("sapphireMultiTool", "Sapphire Multi Tool", new ItemMultiTool(JourneyToolMaterial.SAPPHIRE_MULTI_TOOL, 2456), JourneyTabs.TOOLS);
        gorbiteMultiTool = Registrar.regAndSetupItem("gorbiteMultiTool", "Gorbite Multi Tool", new ItemMultiTool(JourneyToolMaterial.GORBITE_MULTI_TOOL, 2115), JourneyTabs.TOOLS);
        orbaditeMultiTool = Registrar.regAndSetupItem("orbaditeMultiTool", "Orbadite Multi Tool", new ItemMultiTool(JourneyToolMaterial.ORBADITE_MULTI_TOOL, 2115), JourneyTabs.TOOLS);
        desMultiTool = Registrar.regAndSetupItem("desMultiTool", "Des Multi Tool", new ItemMultiTool(JourneyToolMaterial.DES_MULTI_TOOL, 2102), JourneyTabs.TOOLS);
        koriteMultiTool = Registrar.regAndSetupItem("koriteMultiTool", "Korite Multi Tool", new ItemMultiTool(JourneyToolMaterial.KORITE_MULTI_TOOL, 1820), JourneyTabs.TOOLS);
        storonMultiTool = Registrar.regAndSetupItem("storonMultiTool", "Storon Multi Tool", new ItemMultiTool(JourneyToolMaterial.KORITE_MULTI_TOOL, 1820), JourneyTabs.TOOLS);
        mekyumMultiTool = Registrar.regAndSetupItem("mekyumMultiTool", "Mekyum Multi Tool", new ItemMultiTool(JourneyToolMaterial.KORITE_MULTI_TOOL, 1820), JourneyTabs.TOOLS);
        woodMultiTool = Registrar.regAndSetupItem("woodMultiTool", "Wooden Multi Tool", new ItemMultiTool(JourneyToolMaterial.WOOD_MULTI_TOOL, 60), JourneyTabs.TOOLS);
        stoneMultiTool = Registrar.regAndSetupItem("stoneMultiTool", "Stone Multi Tool", new ItemMultiTool(JourneyToolMaterial.STONE_MULTI_TOOL, 132), JourneyTabs.TOOLS);
        ironMultiTool = Registrar.regAndSetupItem("ironMultiTool", "Iron Multi Tool", new ItemMultiTool(JourneyToolMaterial.IRON_MULTI_TOOL, 251), JourneyTabs.TOOLS);
        goldMultiTool = Registrar.regAndSetupItem("goldMultiTool", "Gold Multi Tool", new ItemMultiTool(JourneyToolMaterial.GOLD_MULTI_TOOL, 33), JourneyTabs.TOOLS);
        diamondMultiTool = Registrar.regAndSetupItem("diamondMultiTool", "Diamond Multi Tool", new ItemMultiTool(JourneyToolMaterial.DIAMOND_MULTI_TOOL, 1562), JourneyTabs.TOOLS);
        multiToolOfEternalSmelting = Registrar.regAndSetupItem("multiToolOfEternalSmelting", "Multi Tool of Eternal Smelting", new ItemMultiTool(JourneyToolMaterial.SMELTING_TOOL, 512), JourneyTabs.TOOLS);

        sapphirePickaxe = Registrar.regAndSetupItem("sapphirePickaxe", "Sapphire Pickaxe", new ItemModPickaxe(JourneyToolMaterial.SAPPHIRE), JourneyTabs.TOOLS);
        shadiumPickaxe = Registrar.regAndSetupItem("shadiumPickaxe", "Shadium Pickaxe", new ItemModPickaxe(JourneyToolMaterial.SHADIUM), JourneyTabs.TOOLS);
        luniumPickaxe = Registrar.regAndSetupItem("luniumPickaxe", "Lunium Pickaxe", new ItemModPickaxe(JourneyToolMaterial.LUNIUM), JourneyTabs.TOOLS);
        hellstonePickaxe = Registrar.regAndSetupItem("hellstonePickaxe", "Bloodcrust Pickaxe", new ItemModPickaxe(JourneyToolMaterial.HELLSTONE), JourneyTabs.TOOLS);
        nethicPickaxe = Registrar.regAndSetupItem("nethicPickaxe", "Nethic Pickaxe", new ItemModPickaxe(JourneyToolMaterial.NETHIC), JourneyTabs.TOOLS);
        nethicAxe = Registrar.regAndSetupItem("nethicaxe", "Nethic Axe", new ItemModAxe(JourneyToolMaterial.NETHIC), JourneyTabs.TOOLS);
        koritePickaxe = Registrar.regAndSetupItem("koritePickaxe", "Korite Pickaxe", new ItemModPickaxe(JourneyToolMaterial.KORITE), JourneyTabs.TOOLS);
        storonPickaxe = Registrar.regAndSetupItem("storonPickaxe", "Storon Pickaxe", new ItemModPickaxe(JourneyToolMaterial.KORITE), JourneyTabs.TOOLS);
        mekyumPickaxe = Registrar.regAndSetupItem("mekyumPickaxe", "Mekyum Pickaxe", new ItemModPickaxe(JourneyToolMaterial.KORITE), JourneyTabs.TOOLS);
        celestiumPickaxe = Registrar.regAndSetupItem("celestiumPickaxe", "Celestium Pickaxe", new ItemModPickaxe(JourneyToolMaterial.CELESTIUM), JourneyTabs.TOOLS);
        flairiumPickaxe = Registrar.regAndSetupItem("flairiumPickaxe", "Flairium Pickaxe", new ItemModPickaxe(JourneyToolMaterial.FLAIRIUM), JourneyTabs.TOOLS);
        desPickaxe = Registrar.regAndSetupItem("desPickaxe", "Des Pickaxe", new ItemModPickaxe(JourneyToolMaterial.DES), JourneyTabs.TOOLS);
        gorbitePickaxe = Registrar.regAndSetupItem("gorbitePickaxe", "Gorbite Pickaxe", new ItemModPickaxe(JourneyToolMaterial.GORBITE), JourneyTabs.TOOLS);
        orbaditePickaxe = Registrar.regAndSetupItem("orbaditePickaxe", "Orbadite Pickaxe", new ItemModPickaxe(JourneyToolMaterial.ORBADITE), JourneyTabs.TOOLS);
        slimyPickaxe = Registrar.regAndSetupItem("slimy_pickaxe", "Slimy Pickaxe", new ItemModPickaxe(JourneyToolMaterial.SLIMY_PICK), JourneyTabs.TOOLS);
        pickaxeOfGoodFortune = Registrar.regAndSetupItem("pickaxe_of_good_fortune", "Pickaxe of Good Fortune", new ItemModPickaxe(JourneyToolMaterial.SLIMY_PICK), JourneyTabs.TOOLS);

        hellstoneShovel = Registrar.regAndSetupItem("hellstoneShovel", "Bloodcrust Shovel", new ItemModShovel(JourneyToolMaterial.HELLSTONE), JourneyTabs.TOOLS);
        shadiumShovel = Registrar.regAndSetupItem("shadiumShovel", "Shadium Shovel", new ItemModShovel(JourneyToolMaterial.SHADIUM), JourneyTabs.TOOLS);
        celestiumShovel = Registrar.regAndSetupItem("celestiumShovel", "Celestium Shovel", new ItemModShovel(JourneyToolMaterial.CELESTIUM), JourneyTabs.TOOLS);
        luniumShovel = Registrar.regAndSetupItem("luniumShovel", "Lunium Shovel", new ItemModShovel(JourneyToolMaterial.LUNIUM), JourneyTabs.TOOLS);
        flairiumShovel = Registrar.regAndSetupItem("flairiumShovel", "Flairium Shovel", new ItemModShovel(JourneyToolMaterial.FLAIRIUM), JourneyTabs.TOOLS);
        sapphireShovel = Registrar.regAndSetupItem("sapphireShovel", "Sapphire Shovel", new ItemModShovel(JourneyToolMaterial.SAPPHIRE), JourneyTabs.TOOLS);
        gorbiteShovel = Registrar.regAndSetupItem("gorbiteShovel", "Gorbite Shovel", new ItemModShovel(JourneyToolMaterial.GORBITE), JourneyTabs.TOOLS);
        orbaditeShovel = Registrar.regAndSetupItem("orbaditeShovel", "Orbadite Shovel", new ItemModShovel(JourneyToolMaterial.ORBADITE), JourneyTabs.TOOLS);
        desShovel = Registrar.regAndSetupItem("desShovel", "Des Shovel", new ItemModShovel(JourneyToolMaterial.DES), JourneyTabs.TOOLS);
        nethicShovel = Registrar.regAndSetupItem("nethicShovel", "Nethic Shovel", new ItemModShovel(JourneyToolMaterial.NETHIC), JourneyTabs.TOOLS);
        koriteShovel = Registrar.regAndSetupItem("koriteShovel", "Korite Shovel", new ItemModShovel(JourneyToolMaterial.KORITE), JourneyTabs.TOOLS);
        storonShovel = Registrar.regAndSetupItem("storonShovel", "Storon Shovel", new ItemModShovel(JourneyToolMaterial.KORITE), JourneyTabs.TOOLS);
        mekyumShovel = Registrar.regAndSetupItem("mekyumShovel", "Mekyum Shovel", new ItemModShovel(JourneyToolMaterial.KORITE), JourneyTabs.TOOLS);

        bedrockShovel = Registrar.regAndSetupItem("bedrock_shovel", "Bedrock Shovel", new ItemModShovel(JourneyToolMaterial.BEDROCK_SHOVEL), JourneyTabs.TOOLS);

        hellstoneAxe = Registrar.regAndSetupItem("hellstoneAxe", "Bloodcrust Axe", new ItemModAxe(JourneyToolMaterial.HELLSTONE), JourneyTabs.TOOLS);
        shadiumAxe = Registrar.regAndSetupItem("shadiumAxe", "Shadium Axe", new ItemModAxe(JourneyToolMaterial.SHADIUM), JourneyTabs.TOOLS);
        celestiumAxe = Registrar.regAndSetupItem("celestiumAxe", "Celestium Axe", new ItemModAxe(JourneyToolMaterial.CELESTIUM), JourneyTabs.TOOLS);
        luniumAxe = Registrar.regAndSetupItem("luniumAxe", "Lunium Axe", new ItemModAxe(JourneyToolMaterial.LUNIUM), JourneyTabs.TOOLS);
        flairiumAxe = Registrar.regAndSetupItem("flairiumAxe", "Flairium Axe", new ItemModAxe(JourneyToolMaterial.FLAIRIUM), JourneyTabs.TOOLS);
        sapphireAxe = Registrar.regAndSetupItem("sapphireAxe", "Sapphire Axe", new ItemModAxe(JourneyToolMaterial.SAPPHIRE), JourneyTabs.TOOLS);
        gorbiteAxe = Registrar.regAndSetupItem("gorbiteAxe", "Gorbite Axe", new ItemModAxe(JourneyToolMaterial.GORBITE), JourneyTabs.TOOLS);
        orbaditeAxe = Registrar.regAndSetupItem("orbaditeAxe", "Orbadite Axe", new ItemModAxe(JourneyToolMaterial.ORBADITE), JourneyTabs.TOOLS);
        desAxe = Registrar.regAndSetupItem("desAxe", "Des Axe", new ItemModAxe(JourneyToolMaterial.DES), JourneyTabs.TOOLS);
        koriteAxe = Registrar.regAndSetupItem("koriteAxe", "Korite Axe", new ItemModAxe(JourneyToolMaterial.KORITE), JourneyTabs.TOOLS);
        storonAxe = Registrar.regAndSetupItem("storonAxe", "Storon Axe", new ItemModAxe(JourneyToolMaterial.KORITE), JourneyTabs.TOOLS);
        mekyumAxe = Registrar.regAndSetupItem("mekyumAxe", "Mekyum Axe", new ItemModAxe(JourneyToolMaterial.KORITE), JourneyTabs.TOOLS);

        hellstoneHoe = Registrar.regAndSetupItem("hellstoneHoe", "Bloodcrust Hoe", new ItemModHoe(JourneyToolMaterial.HELLSTONE), JourneyTabs.TOOLS);
        shadiumHoe = Registrar.regAndSetupItem("shadiumHoe", "Shadium Hoe", new ItemModHoe(JourneyToolMaterial.SHADIUM), JourneyTabs.TOOLS);
        celestiumHoe = Registrar.regAndSetupItem("celestiumHoe", "Celestium Hoe", new ItemModHoe(JourneyToolMaterial.CELESTIUM), JourneyTabs.TOOLS);
        luniumHoe = Registrar.regAndSetupItem("luniumHoe", "Lunium Hoe", new ItemModHoe(JourneyToolMaterial.LUNIUM), JourneyTabs.TOOLS);
        flairiumHoe = Registrar.regAndSetupItem("flairiumHoe", "Flairium Hoe", new ItemModHoe(JourneyToolMaterial.FLAIRIUM), JourneyTabs.TOOLS);
        sapphireHoe = Registrar.regAndSetupItem("sapphireHoe", "Sapphire Hoe", new ItemModHoe(JourneyToolMaterial.SAPPHIRE), JourneyTabs.TOOLS);
        gorbiteHoe = Registrar.regAndSetupItem("gorbiteHoe", "Gorbite Hoe", new ItemModHoe(JourneyToolMaterial.GORBITE), JourneyTabs.TOOLS);
        orbaditeHoe = Registrar.regAndSetupItem("orbaditeHoe", "Orbadite Hoe", new ItemModHoe(JourneyToolMaterial.ORBADITE), JourneyTabs.TOOLS);
        desHoe = Registrar.regAndSetupItem("desHoe", "Des Hoe", new ItemModHoe(JourneyToolMaterial.DES), JourneyTabs.TOOLS);
        koriteHoe = Registrar.regAndSetupItem("koriteHoe", "Korite Hoe", new ItemModHoe(JourneyToolMaterial.KORITE), JourneyTabs.TOOLS);
        storonHoe = Registrar.regAndSetupItem("storonHoe", "Storon Hoe", new ItemModHoe(JourneyToolMaterial.KORITE), JourneyTabs.TOOLS);
        mekyumHoe = Registrar.regAndSetupItem("mekyumHoe", "Mekyum Hoe", new ItemModHoe(JourneyToolMaterial.KORITE), JourneyTabs.TOOLS);
        hoeOfEternalLife = Registrar.regAndSetupItem("hoeofeternallife", "Hoe Of Eternal Life", new ItemBonemealHoe(JourneyToolMaterial.HOEOFLIFE), JourneyTabs.TOOLS);
        hoeOfEarthLoving = Registrar.regAndSetupItem("hoe_of_earth_loving", "Hoe of Earth Loving", new ItemBonemealHoe(JourneyToolMaterial.HOEOFEARTH), JourneyTabs.TOOLS);

        maskOfHellmetal = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.HELL_METAL, HEAD), "Mask of %material%");

        hellstoneHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.HELLSTONE, HEAD));
        hellstoneChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.HELLSTONE, BODY));
        hellstoneLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.HELLSTONE, LEGS));
        hellstoneBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.HELLSTONE, BOOTS));

        flairiumHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FLAIRIUM, HEAD));
        flairiumChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FLAIRIUM, BODY));
        flairiumLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FLAIRIUM, LEGS));
        flairiumBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FLAIRIUM, BOOTS));

        celestiumHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CELESTIUM, HEAD));
        celestiumChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CELESTIUM, BODY));
        celestiumLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CELESTIUM, LEGS));
        celestiumBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CELESTIUM, BOOTS));

        luniumHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LUNIUM, HEAD));
        luniumChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LUNIUM, BODY));
        luniumLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LUNIUM, LEGS));
        luniumBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LUNIUM, BOOTS));

        shadiumHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.SHADIUM, HEAD));
        shadiumChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.SHADIUM, BODY));
        shadiumLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.SHADIUM, LEGS));
        shadiumBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.SHADIUM, BOOTS));

        sapphireHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.SAPPHIRE, HEAD));
        sapphireChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.SAPPHIRE, BODY));
        sapphireLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.SAPPHIRE, LEGS));
        sapphireBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.SAPPHIRE, BOOTS));

        gorbiteHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.GORBITE, HEAD));
        gorbiteChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.GORBITE, BODY));
        gorbiteLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.GORBITE, LEGS));
        gorbiteBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.GORBITE, BOOTS));

        orbaditeHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.ORBADITE, HEAD));
        orbaditeChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.ORBADITE, BODY));
        orbaditeLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.ORBADITE, LEGS));
        orbaditeBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.ORBADITE, BOOTS));

        flameHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FLAME, HEAD));
        flameChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FLAME, BODY));
        flameLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FLAME, LEGS));
        flameBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FLAME, BOOTS));

        twilightHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.TWILIGHT, HEAD));
        twilightChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.TWILIGHT, BODY));
        twilightLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.TWILIGHT, LEGS));
        twilightBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.TWILIGHT, BOOTS));

        leapersHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LEAPERS, HEAD));
        leapersChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LEAPERS, BODY));
        leapersLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LEAPERS, LEGS));
        leapersBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LEAPERS, BOOTS));

        snakeskinHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.SNAKESKIN, HEAD));
        snakeskinChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.SNAKESKIN, BODY));
        snakeskinLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.SNAKESKIN, LEGS));
        snakeskinBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.SNAKESKIN, BOOTS));

        treehuggersHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.TREEHUGGERS, HEAD));
        treehuggersChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.TREEHUGGERS, BODY));
        treehuggersLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.TREEHUGGERS, LEGS));
        treehuggersBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.TREEHUGGERS, BOOTS));

        charskullHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CHAR_SKULL, HEAD));
        charskullChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CHAR_SKULL, BODY));
        charskullLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CHAR_SKULL, LEGS));
        charskullBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CHAR_SKULL, BOOTS));

        bronzedHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BRONZED, HEAD));
        bronzedChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BRONZED, BODY));
        bronzedLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BRONZED, LEGS));
        bronzedBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BRONZED, BOOTS));

        golditeHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.GOLDITE, HEAD));
        golditeChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.GOLDITE, BODY));
        golditeLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.GOLDITE, LEGS));
        golditeBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.GOLDITE, BOOTS));

        corbarkHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CORBARK, HEAD));
        corbarkChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CORBARK, BODY));
        corbarkLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CORBARK, LEGS));
        corbarkBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CORBARK, BOOTS));

        crystalFlakeHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CRYSTAL_FLAKE, HEAD));
        crystalFlakeChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CRYSTAL_FLAKE, BODY));
        crystalFlakeLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CRYSTAL_FLAKE, LEGS));
        crystalFlakeBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.CRYSTAL_FLAKE, BOOTS));

        darklyHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.DARKLY, HEAD));
        darklyChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.DARKLY, BODY));
        darklyLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.DARKLY, LEGS));
        darklyBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.DARKLY, BOOTS));

        depthsHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.DEPTHS, HEAD));
        depthsChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.DEPTHS, BODY));
        depthsLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.DEPTHS, LEGS));
        depthsBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.DEPTHS, BOOTS));

        enlightenerHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.ENLIGHTENER, HEAD));
        enlightenerChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.ENLIGHTENER, BODY));
        enlightenerLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.ENLIGHTENER, LEGS));
        enlightenerBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.ENLIGHTENER, BOOTS));

        fireboundHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FIREBOUND, HEAD));
        fireboundChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FIREBOUND, BODY));
        fireboundLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FIREBOUND, LEGS));
        fireboundBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FIREBOUND, BOOTS));

        frostbittenHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FROSTBITTEN, HEAD));
        frostbittenChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FROSTBITTEN, BODY));
        frostbittenLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FROSTBITTEN, LEGS));
        frostbittenBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.FROSTBITTEN, BOOTS));

        hollowHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.HOLLOW, HEAD));
        hollowChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.HOLLOW, BODY));
        hollowLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.HOLLOW, LEGS));
        hollowBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.HOLLOW, BOOTS));

        lightstoneHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LIGHTSTONE, HEAD));
        lightstoneChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LIGHTSTONE, BODY));
        lightstoneLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LIGHTSTONE, LEGS));
        lightstoneBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LIGHTSTONE, BOOTS));

        livegreenHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LIVEGREEN, HEAD));
        livegreenChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LIVEGREEN, BODY));
        livegreenLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LIVEGREEN, LEGS));
        livegreenBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.LIVEGREEN, BOOTS));

        starlightHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.STARLIGHT, HEAD));
        starlightChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.STARLIGHT, BODY));
        starlightLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.STARLIGHT, LEGS));
        starlightBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.STARLIGHT, BOOTS));

        bloodcrustHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BLOODCRUST, HEAD));
        bloodcrustChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BLOODCRUST, BODY));
        bloodcrustLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BLOODCRUST, LEGS));
        bloodcrustBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BLOODCRUST, BOOTS));

        blazehornHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BLAZEHORN, HEAD));
        blazehornChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BLAZEHORN, BODY));
        blazehornLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BLAZEHORN, LEGS));
        blazehornBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BLAZEHORN, BOOTS));

        bleedrockHelmet = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BLEEDROCK, HEAD));
        bleedrockChest = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BLEEDROCK, BODY));
        bleedrockLegs = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BLEEDROCK, LEGS));
        bleedrockBoots = Registrar.regAndSetupArmor(new ItemModArmor(EnumArmor.BLEEDROCK, BOOTS));
    }
}