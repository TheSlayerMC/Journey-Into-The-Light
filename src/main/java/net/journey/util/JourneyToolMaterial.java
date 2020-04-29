package net.journey.util;

import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.slayer.api.SlayerAPI;

public enum JourneyToolMaterial {
	
    HELLSTONE(JourneyItems.hellstone, JourneyItems.hellstoneIngot, 12),
    FLAIRIUM(JourneyItems.flairium, JourneyItems.flairiumIngot, 16),
    LUNIUM(JourneyItems.lunium, JourneyItems.luniumIngot, 10),
    CELESTIUM(JourneyItems.celestium, JourneyItems.celestiumIngot, 14),
    SHADIUM(JourneyItems.shadium, JourneyItems.shadiumIngot, 10),
    SAPPHIRE(JourneyItems.sapphiretool, JourneyItems.sapphire, 8),
    ORBADITE(JourneyItems.orbadite, JourneyItems.orbaditeIngot, 24),
    GORBITE(JourneyItems.gorbite, JourneyItems.gorbiteGem, 24),
    DES(JourneyItems.des, JourneyItems.desIngot, 20),
    NETHIC(JourneyItems.nethic, JourneyItems.bleedstone, 20),
    KORITE(JourneyItems.korite, JourneyItems.koriteIngot, 14),
    HOEOFLIFE(JourneyItems.hoeoflife, JourneyConsumables.floroPedal, 14),

    CHAMPIONS_SWORD(SlayerAPI.addMeleeMaterial(3000, 14, 25)),
    THE_WRAITH(SlayerAPI.addMeleeMaterial(3000, 17, 25)),

    //overworld
    SAPPHIRE_SWORD(JourneyItems.sapphiretoolSwordMat, JourneyItems.sapphire),
    SHADIUM_SWORD(JourneyItems.shadiumSwordMat, JourneyItems.shadiumIngot),
    LUNIUM_SWORD(JourneyItems.luniumSwordMat, JourneyItems.luniumIngot),
    POISON_SWORD(SlayerAPI.addMeleeMaterial(1500, 7.5F, 25)),
    CLOUD_SLICER(SlayerAPI.addMeleeMaterial(750, 8, 25)),
    DRAGONS_TOOTH(SlayerAPI.addMeleeMaterial(1500, 6.5F, 25)),
    DEMONIC_SWORD(SlayerAPI.addMeleeMaterial(850, 5.5F, 25)),
    PEDAL_SWORD(SlayerAPI.addMeleeMaterial(100, 5.5F, 25)),
    RE_CRYSTAL_SWORD(SlayerAPI.addMeleeMaterial(512, 9.5F, 25)),
    RE_STONE_SWORD(SlayerAPI.addMeleeMaterial(256, 6.5F, 25)),
    CRYSTAL_BLADE(SlayerAPI.addMeleeMaterial(690, 7, 25)),
    
    //frozen
    SNOW_SHOVELER(SlayerAPI.addMeleeMaterial(3000, 9.5F, 25)),
    FROSTBITTEN_SWORD(SlayerAPI.addMeleeMaterial(3000, 9, 25)),
    FROSTY_SWORD(SlayerAPI.addMeleeMaterial(3000, 8, 25)),
    
    //nether
    HELLSTONE_SWORD(JourneyItems.hellstoneSwordMat, JourneyItems.hellstoneIngot),
    WITHIC_BLADE(SlayerAPI.addMeleeMaterial(70, 8, 25)),
    CALCIA_SWORD(SlayerAPI.addMeleeMaterial(3000, 10, 25)),
    NETHER_BEAST_SWORD(SlayerAPI.addMeleeMaterial(3000, 11, 25)),
    WITHERING_BEAST_SWORD(SlayerAPI.addMeleeMaterial(3000, 9.5F, 25)),
    
    //boil
    BOILING_BLADE(SlayerAPI.addMeleeMaterial(3000, 10.5F, 25)),
    SIZZLER_SWORD(SlayerAPI.addMeleeMaterial(3000, 13, 25)),
    BLOODWIELD_SWORD(SlayerAPI.addMeleeMaterial(3000, 11, 25)),
    CHARRED_BLADE(SlayerAPI.addMeleeMaterial(3000, 12, 25)),
    MOLTEN_KNIFE(SlayerAPI.addMeleeMaterial(3000, 11.5F, 25)),

    //euca
    CELESTIUM_SWORD(JourneyItems.celestiumSwordMat, JourneyItems.celestiumIngot),
    KORITE_SWORD(JourneyItems.koriteSwordMat, JourneyItems.koriteIngot),
    MEKYUM_SWORD(JourneyItems.mekyumSwordMat, JourneyItems.mekyumIngot),
    STORON_SWORD(JourneyItems.storonSwordMat, JourneyItems.storonIngot),
    CORE_MENDER(SlayerAPI.addMeleeMaterial(3000, 13.5F, 25)),
    ROYAL_BLADE(SlayerAPI.addMeleeMaterial(3000, 13F, 25)),
    ROYAL_STABBER(SlayerAPI.addMeleeMaterial(3000, 12.5F, 25)),
    KINGS_SWORD(SlayerAPI.addMeleeMaterial(1500, 14F, 25)),
    
    //depths
    FLAIRIUM_SWORD(JourneyItems.flairiumSwordMat, JourneyItems.flairiumIngot),
    DES_SWORD(JourneyItems.desSwordMat, JourneyItems.desIngot),
    DEPTHS_DARKSWORD(SlayerAPI.addMeleeMaterial(3000, 14.5F, 25)),
    DEPTHS_SLAYER(SlayerAPI.addMeleeMaterial(3000, 14, 25)),
    ROC_SWORD(SlayerAPI.addMeleeMaterial(3000, 15.5F, 25)),
    SWORD_THUNDERBIRD(SlayerAPI.addMeleeMaterial(3000, 16.5F, 25)),
    THUNDERBLADE(SlayerAPI.addMeleeMaterial(3000, 15F, 25)),
    BUBBLE_SWORD(SlayerAPI.addMeleeMaterial(3000, 16F, 25)),
    
    //corba
    ORBADITE_SWORD(JourneyItems.orbaditeSwordMat, JourneyItems.orbaditeIngot),
    GORBITE_SWORD(JourneyItems.gorbiteSwordMat, JourneyItems.gorbiteGem),
    VINESTRAND_BLADE(SlayerAPI.addMeleeMaterial(2000, 17F, 25)),
    DARK_PINE_SWORD(SlayerAPI.addMeleeMaterial(2000, 17F, 25)),
    NATURES_BLADE(SlayerAPI.addMeleeMaterial(3000, 17.5F, 25)),
    TREE_HUGGER(SlayerAPI.addMeleeMaterial(3000, 18.5F, 25)),
    HEALERS_BLADE(SlayerAPI.addMeleeMaterial(3000, 13.5F, 25)),
    LOGGERS_SWORD(SlayerAPI.addMeleeMaterial(3000, 20, 25)),
    SENTRY_SWORD(SlayerAPI.addMeleeMaterial(3000, 22, 25)),

    //terrania
    TERRALIGHT_BLADE(SlayerAPI.addMeleeMaterial(3500, 24F, 25)),
    TERRANA_SWORD(SlayerAPI.addMeleeMaterial(3400, 24.5F, 25)),
    TERROLICA_SWORD(SlayerAPI.addMeleeMaterial(3500, 23, 25)),
    VOLITE_SWORD(SlayerAPI.addMeleeMaterial(3500, 22.5F, 25)),
    TERRONIC_BLADE(SlayerAPI.addMeleeMaterial(1000, 18, 25)),
    
    //cloudia
    GOLEM_SWORD(SlayerAPI.addMeleeMaterial(3000, 25, 25)),
    STARLIGHT_BLADE(SlayerAPI.addMeleeMaterial(3000, 26, 25)),
    FLUFFY_BLADE(SlayerAPI.addMeleeMaterial(3000, 28, 25)),

    CREATIVE(SlayerAPI.addMeleeMaterial(1000, 39, 25)),
    EARTHEN_HAMMER(SlayerAPI.addMeleeMaterial(1, 6, 25)),
    FLAMING_HAMMER(SlayerAPI.addMeleeMaterial(1000, 8, 25)),
    NETHIC_HAMMER(SlayerAPI.addMeleeMaterial(1000, 7, 25)),
    WITHIC_HAMMER(SlayerAPI.addMeleeMaterial(1000, 7.5F, 25)),
    ROYAL_HAMMER(SlayerAPI.addMeleeMaterial(1000, 9.5F, 25)),
    OVERGROWN_HAMMER(SlayerAPI.addMeleeMaterial(1000, 12.5F, 25)),
    ROCKY_HAMMER(SlayerAPI.addMeleeMaterial(2230, 6.5F, 25)),
    CRYSTAL_HAMMER(SlayerAPI.addMeleeMaterial(3320, 7.5F, 25)),

    DEVELOPER_SWORD(SlayerAPI.addMeleeMaterial(3000, 9000, 1000)),

    HELLSTONE_MULTI_TOOL(JourneyItems.hellstoneMulti, JourneyItems.hellstoneIngot, 3),
    FLAIRIUM_MULTI_TOOL(JourneyItems.flairiumMulti, JourneyItems.flairiumIngot, 3),
    LUNIUM_MULTI_TOOL(JourneyItems.luniumMulti, JourneyItems.luniumIngot, 3),
    CELESTIUM_MULTI_TOOL(JourneyItems.celestiumMulti, JourneyItems.celestiumIngot, 3),
    SHADIUM_MULTI_TOOL(JourneyItems.shadiumMulti, JourneyItems.shadiumIngot, 3),
    SAPPHIRE_MULTI_TOOL(JourneyItems.sapphireMulti, JourneyItems.sapphire, 3),
    ORBADITE_MULTI_TOOL(JourneyItems.orbaditeMulti, JourneyItems.orbaditeIngot, 3),
    GORBITE_MULTI_TOOL(JourneyItems.gorbiteMulti, JourneyItems.gorbiteGem, 3),
    DES_MULTI_TOOL(JourneyItems.desMulti, JourneyItems.desIngot, 3),
    KORITE_MULTI_TOOL(JourneyItems.koriteMulti, JourneyItems.koriteIngot, 3),
    SMELTING_TOOL(JourneyItems.smeltingMulti, null, 3),
    WOOD_MULTI_TOOL(JourneyItems.woodMulti, SlayerAPI.toItem(Blocks.PLANKS), 0),
    STONE_MULTI_TOOL(JourneyItems.stoneMulti, SlayerAPI.toItem(Blocks.COBBLESTONE), 1),
    IRON_MULTI_TOOL(JourneyItems.ironMulti, Items.IRON_INGOT, 2),
    GOLD_MULTI_TOOL(JourneyItems.goldMulti, Items.GOLD_INGOT, 1),
    DIAMOND_MULTI_TOOL(JourneyItems.diamondMulti, Items.DIAMOND, 3),

    CRYSTAL_BATTLEAXE(SlayerAPI.addAxeMaterial(3600, 3, 13, 17, 25)),
    ROCKY_BATTLEAXE(SlayerAPI.addAxeMaterial(2600, 3, 13, 16, 25)),
    BACK_BITER(SlayerAPI.addAxeMaterial(1300, 3, 13, 15, 25)),
    DAWN_BREAKER(SlayerAPI.addAxeMaterial(1300, 3, 13, 10, 25)),
    TEMPEST_BATTLEAXE(SlayerAPI.addAxeMaterial(1300, 3, 13, 13, 25)),
    BRONZED_BATTLEAXE(SlayerAPI.addAxeMaterial(1300, 3, 13, 16, 25)),
    STORUM_BATTLEAXE(SlayerAPI.addAxeMaterial(1300, 3, 13, 17, 25)),
    CELESTITE_BATTLEAXE(SlayerAPI.addAxeMaterial(1300, 3, 13, 18, 25)),
    CELEKIUM_BATTLEAXE(SlayerAPI.addAxeMaterial(1300, 3, 13, 19, 25)),
    THUNDERBIRD_BATTLEAXE(SlayerAPI.addAxeMaterial(1300, 3, 13, 27, 25));

    private ToolMaterial toolMaterial;
    private Item repairItem;
    private int harvestLevel = 0;

    JourneyToolMaterial(ToolMaterial toolMaterial, Item repair) {
        this.toolMaterial = toolMaterial;
        this.repairItem = repair;
        harvestLevel = 0;
    }

    JourneyToolMaterial(ToolMaterial toolMaterial, Item repair, int level) {
        this.toolMaterial = toolMaterial;
        this.repairItem = repair;
        this.harvestLevel = level;
    }

    JourneyToolMaterial(ToolMaterial toolMaterial) {
        this(toolMaterial, null);
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public Item getRepairItem() {
        return repairItem;
    }

    public ToolMaterial getToolMaterial() {
        return toolMaterial;
    }
}