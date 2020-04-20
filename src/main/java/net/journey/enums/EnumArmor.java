package net.journey.enums;

import net.journey.init.items.JourneyItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.slayer.api.SlayerAPI;

public enum EnumArmor {

    BLOODCRUST(SlayerAPI.addArmorMaterial("bloodcrust", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "bloodcrust", false, 50, JourneyItems.hellstoneIngot, "Bloodcrust"),
    HELLSTONE(SlayerAPI.addArmorMaterial("hellstone", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "hellstone", false, 50, JourneyItems.hellstoneIngot, "Hellstone"),
    FLAIRIUM(SlayerAPI.addArmorMaterial("flairium", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "flairium", false, 70, JourneyItems.flairiumIngot, "Flairium"),
    CELESTIUM(SlayerAPI.addArmorMaterial("celestium", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "celestium", false, 65, JourneyItems.celestiumIngot, "Celestium"),
    LUNIUM(SlayerAPI.addArmorMaterial("lunium", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "lunium", false, 60, JourneyItems.luniumIngot, "Lunium"),
    SHADIUM(SlayerAPI.addArmorMaterial("shadium", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "shadium", false, 60, JourneyItems.shadiumIngot, "Shadium"),
    SAPPHIRE(SlayerAPI.addArmorMaterial("sapphire", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "sapphire", false, 60, JourneyItems.sapphire, "Sapphire"),
    ORBADITE(SlayerAPI.addArmorMaterial("orbadite", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "orbadite", false, 86, JourneyItems.orbaditeIngot, "Orbadite"),
    GORBITE(SlayerAPI.addArmorMaterial("gorbite", 3000, new int[]{3, 4, 3, 2,}, 10, 1.5F), "gorbite", false, 86, JourneyItems.gorbiteGem, "Gorbite"),
    FLAME(SlayerAPI.addArmorMaterial("flame", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "flame", false, 76, (Item) null, "Flame"),
    TWILIGHT(SlayerAPI.addArmorMaterial("twilight", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "twilight", false, 66, (Item) null, "Twilight"),
    SNAKESKIN(SlayerAPI.addArmorMaterial("snakeskin", 3000, new int[]{3, 4, 3, 2,}, 10, 1.5F), "snakeskin", false, 66, (Item) null, "Snakeskin"),
    CHAR_SKULL(SlayerAPI.addArmorMaterial("charSkull", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "charSkull", false, 66, (Item) null, "Char Skull"),
    LEAPERS(SlayerAPI.addArmorMaterial("leapers", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "leapers", false, 86, (Item) null, "Leapers"),
    TREEHUGGERS(SlayerAPI.addArmorMaterial("treehuggers", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "treehuggers", false, 86, (Item) null, "Tree Huggers"),
    BRONZED(SlayerAPI.addArmorMaterial("bronzed", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "bronzed", false, 76, (Item) null, "Bronzed"),
    GOLDITE(SlayerAPI.addArmorMaterial("goldite", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "goldite", false, 76, (Item) null, "Goldite"),
    CORBARK(SlayerAPI.addArmorMaterial("corkbark", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "corbark", false, 86, (Item) null, "Corbark"),
    DARKLY(SlayerAPI.addArmorMaterial("darkly", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "darkly", false, 86, (Item) null, "Darkly"),
    DEPTHS(SlayerAPI.addArmorMaterial("depths", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "depths", false, 86, (Item) null, "Depths"),
    ENLIGHTENER(SlayerAPI.addArmorMaterial("enlightener", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "enlightener", false, 96, (Item) null, "Enlightener"),
    FIREBOUND(SlayerAPI.addArmorMaterial("firebound", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "firebound", false, 86, (Item) null, "Firebound"),
    FROSTBITTEN(SlayerAPI.addArmorMaterial("frostbitten", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "frostbitten", false, 66, (Item) null, "Frostbitten"),
    HOLLOW(SlayerAPI.addArmorMaterial("hollow", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "hollow", false, 86, (Item) null, "Hollow"),
    LIGHTSTONE(SlayerAPI.addArmorMaterial("lightstone", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "lightstone", false, 86, (Item) null, "Lightstone"),
    LIVEGREEN(SlayerAPI.addArmorMaterial("livegreen", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "livegreen", false, 86, (Item) null, "Livegreen"),
    STARLIGHT(SlayerAPI.addArmorMaterial("starlight", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "starlight", false, 76, (Item) null, "Starlight"),
    BLEEDROCK(SlayerAPI.addArmorMaterial("bleedrock", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "bleedrock", false, 76, (Item) null, "Bleedrock"),
    BLAZEHORN(SlayerAPI.addArmorMaterial("blazehorn", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "blazehorn", false, 86, (Item) null, "Blazehorn"),

    CRYSTAL_FLAKE(SlayerAPI.addArmorMaterial("crystalflake", 3000, new int[]{3, 4, 3, 2}, 10, 1.5F), "crystalFlake", false, 86, (Item) null, "Crystal Flake");

    private ArmorMaterial armorMaterial;
    private String type, finalName;
    private boolean undamageable;
    private int damageReduction;
    private Item repairItem;

    EnumArmor(ArmorMaterial armorMaterial, String type, boolean undamageable, int damageReduction, Item repair, String finalName) {
        this.armorMaterial = armorMaterial;
        this.type = type;
        this.finalName = finalName;
        this.undamageable = undamageable;
        this.damageReduction = damageReduction;
        this.repairItem = repair;
    }

    EnumArmor(ArmorMaterial armorMaterial, String type, boolean undamageable, int damageReduction, Block repair, String finalName) {
        this.armorMaterial = armorMaterial;
        this.type = type;
        this.finalName = finalName;
        this.undamageable = undamageable;
        this.damageReduction = damageReduction;
        this.repairItem = SlayerAPI.toItem(repair);
    }

    public Item getRepairItem() {
        return repairItem;
    }

    public ArmorMaterial getArmorMaterial() {
        return armorMaterial;
    }

    public String getType() {
        return type;
    }

    public String getFinalName() {
        return finalName;
    }

    public boolean isUndamageable() {
        return undamageable;
    }

    public int getDamageReduction() {
        return damageReduction;
    }
}