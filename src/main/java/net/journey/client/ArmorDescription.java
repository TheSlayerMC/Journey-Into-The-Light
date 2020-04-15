package net.journey.client;

import net.journey.JourneyArmory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.slayer.api.SlayerAPI;

import java.util.List;

public class ArmorDescription {

    public static void add(ItemStack item, List<String> list) {
        Item i = item.getItem();
        if (i == JourneyArmory.hellstoneHelmet || i == JourneyArmory.hellstoneChest || i == JourneyArmory.hellstoneLegs || i == JourneyArmory.hellstoneBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Fire Protection");
        }

        if (i == JourneyArmory.shadiumHelmet || i == JourneyArmory.shadiumChest || i == JourneyArmory.shadiumLegs || i == JourneyArmory.shadiumBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Water Breathing");
        }

        if (i == JourneyArmory.celestiumHelmet || i == JourneyArmory.celestiumChest || i == JourneyArmory.celestiumLegs || i == JourneyArmory.celestiumBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Jump 3 Blocks High");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Run Faster");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "No Fall Damage");
        }

        if (i == JourneyArmory.luniumHelmet || i == JourneyArmory.luniumChest || i == JourneyArmory.luniumLegs || i == JourneyArmory.luniumBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Adds +4 Melee Damage");
        }

        if (i == JourneyArmory.flairiumHelmet || i == JourneyArmory.flairiumChest || i == JourneyArmory.flairiumLegs || i == JourneyArmory.flairiumBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance");
        }

        if (i == JourneyArmory.sapphireHelmet || i == JourneyArmory.sapphireChest || i == JourneyArmory.sapphireLegs || i == JourneyArmory.sapphireBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Water breathing");
        }

        if (i == JourneyArmory.flameHelmet || i == JourneyArmory.flameChest || i == JourneyArmory.flameLegs || i == JourneyArmory.flameBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Sets foe on fire when hit");
        }

        if (i == JourneyArmory.twilightHelmet || i == JourneyArmory.twilightChest || i == JourneyArmory.twilightLegs || i == JourneyArmory.twilightBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants Night Vision");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance");
        }

        if (i == JourneyArmory.snakeskinHelmet || i == JourneyArmory.snakeskinChest || i == JourneyArmory.snakeskinLegs || i == JourneyArmory.snakeskinBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants speed");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance while in lava");
        }

        if (i == JourneyArmory.leapersHelmet || i == JourneyArmory.leapersChest || i == JourneyArmory.leapersLegs || i == JourneyArmory.leapersBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants increased jump height");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants water breathing");
        }

        if (i == JourneyArmory.treehuggersHelmet || i == JourneyArmory.treehuggersChest || i == JourneyArmory.treehuggersLegs || i == JourneyArmory.treehuggersBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards poison effects");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards wither effects");
        }

        if (i == JourneyArmory.charskullHelmet || i == JourneyArmory.charskullChest || i == JourneyArmory.charskullLegs || i == JourneyArmory.charskullBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants damage boost");
        }

        if (i == JourneyArmory.golditeHelmet || i == JourneyArmory.golditeChest || i == JourneyArmory.golditeLegs || i == JourneyArmory.golditeBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "No fall damage");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants speed II");
        }

        if (i == JourneyArmory.bronzedHelmet || i == JourneyArmory.bronzedChest || i == JourneyArmory.bronzedLegs || i == JourneyArmory.bronzedBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants +6 melee damage");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Slows enemies when hit");
        }

        if (i == JourneyArmory.corbarkHelmet || i == JourneyArmory.corbarkChest || i == JourneyArmory.corbarkLegs || i == JourneyArmory.corbarkBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Stuns Enemies when hit");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Poisons Enemies briefly when hit");
        }

        if (i == JourneyArmory.blazehornHelmet || i == JourneyArmory.blazehornChest || i == JourneyArmory.blazehornLegs || i == JourneyArmory.blazehornBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants +3 Melee damage");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance when in lava");
        }

        if (i == JourneyArmory.crystalFlakeHelmet || i == JourneyArmory.crystalFlakeChest || i == JourneyArmory.crystalFlakeLegs || i == JourneyArmory.crystalFlakeBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants +3 Melee damage");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants water breathing");
        }

        if (i == JourneyArmory.frostbittenHelmet || i == JourneyArmory.frostbittenChest || i == JourneyArmory.frostbittenLegs || i == JourneyArmory.frostbittenBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants +4 Melee damage");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants absorption");
        }

        if (i == JourneyArmory.darklyHelmet || i == JourneyArmory.darklyChest || i == JourneyArmory.darklyLegs || i == JourneyArmory.darklyBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants night vision");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants +7 attack damage");
        }

        if (i == JourneyArmory.depthsHelmet || i == JourneyArmory.depthsChest || i == JourneyArmory.depthsLegs || i == JourneyArmory.depthsBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants night vision");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants water breathing");
        }

        if (i == JourneyArmory.hollowHelmet || i == JourneyArmory.hollowChest || i == JourneyArmory.hollowLegs || i == JourneyArmory.hollowBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Withers away mobs when hit");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants night vision");
        }

        if (i == JourneyArmory.lightstoneHelmet || i == JourneyArmory.lightstoneChest || i == JourneyArmory.lightstoneLegs || i == JourneyArmory.lightstoneBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards poison effects");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards wither effects");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants night vision");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants speed boost");
        }

        if (i == JourneyArmory.enlightenerHelmet || i == JourneyArmory.enlightenerChest || i == JourneyArmory.enlightenerLegs || i == JourneyArmory.enlightenerBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants jump boost");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants regeneration");
        }

        if (i == JourneyArmory.livegreenHelmet || i == JourneyArmory.livegreenChest || i == JourneyArmory.livegreenLegs || i == JourneyArmory.livegreenBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Poisons enemies");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards poison effects");
        }

        if (i == JourneyArmory.fireboundHelmet || i == JourneyArmory.fireboundChest || i == JourneyArmory.fireboundLegs || i == JourneyArmory.fireboundBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants jump boost");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance");
        }

        if (i == JourneyArmory.starlightHelmet || i == JourneyArmory.starlightChest || i == JourneyArmory.starlightLegs || i == JourneyArmory.starlightBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Player can jump 6 blocks high");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "No fall damage");
        }

        if (i == JourneyArmory.bloodcrustHelmet || i == JourneyArmory.bloodcrustChest || i == JourneyArmory.bloodcrustLegs || i == JourneyArmory.bloodcrustBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards Wither effects");
        }

        if (i == JourneyArmory.bleedrockHelmet || i == JourneyArmory.bleedrockChest || i == JourneyArmory.bleedrockLegs || i == JourneyArmory.bleedrockBoots) {
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards Wither effects");
            list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants strength");
        }
    }
}