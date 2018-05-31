package net.journey.client;

import java.util.List;

import net.journey.JourneyItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.slayer.api.SlayerAPI;

public class ArmorDescription {

	public static void add(ItemStack item, List<String> list) {
		Item i = item.getItem();
		if(i == JourneyItems.hellstoneHelmet || i == JourneyItems.hellstoneChest || i == JourneyItems.hellstoneLegs || i == JourneyItems.hellstoneBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Fire Protection");
		}

		if(i == JourneyItems.shadiumHelmet || i == JourneyItems.shadiumChest || i == JourneyItems.shadiumLegs || i == JourneyItems.shadiumBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Water Breathing");
		}

		if(i == JourneyItems.celestiumHelmet || i == JourneyItems.celestiumChest || i == JourneyItems.celestiumLegs || i == JourneyItems.celestiumBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Jump 3 Blocks High");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Run Faster");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "No Fall Damage");
		}
		
		if(i == JourneyItems.luniumHelmet || i == JourneyItems.luniumChest || i == JourneyItems.luniumLegs || i == JourneyItems.luniumBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Adds +4 Melee Damage");
		}
		
		if(i == JourneyItems.flairiumHelmet || i == JourneyItems.flairiumChest || i == JourneyItems.flairiumLegs || i == JourneyItems.flairiumBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance");
		}
		
		if(i == JourneyItems.sapphireHelmet || i == JourneyItems.sapphireChest || i == JourneyItems.sapphireLegs || i == JourneyItems.sapphireBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Water breathing");
		}
		
		if(i == JourneyItems.flameHelmet || i == JourneyItems.flameChest || i == JourneyItems.flameLegs || i == JourneyItems.flameBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Sets foe on fire when hit");
		}
		
		if(i == JourneyItems.twilightHelmet || i == JourneyItems.twilightChest || i == JourneyItems.twilightLegs || i == JourneyItems.twilightBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants Night Vision");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance");
		}
		
		if(i == JourneyItems.snakeskinHelmet || i == JourneyItems.snakeskinChest || i == JourneyItems.snakeskinLegs || i == JourneyItems.snakeskinBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants speed");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance while in lava");
		}
			
		if(i == JourneyItems.leapersHelmet || i == JourneyItems.leapersChest || i == JourneyItems.leapersLegs || i == JourneyItems.leapersBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants increased jump height");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants water breathing");
		}
		
		if(i == JourneyItems.treehuggersHelmet || i == JourneyItems.treehuggersChest || i == JourneyItems.treehuggersLegs || i == JourneyItems.treehuggersBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards poison effects");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards wither effects");
		}

		if(i == JourneyItems.charskullHelmet || i == JourneyItems.charskullChest || i == JourneyItems.charskullLegs || i == JourneyItems.charskullBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants damage boost");
		}
		
		if(i == JourneyItems.golditeHelmet || i == JourneyItems.golditeChest || i == JourneyItems.golditeLegs || i == JourneyItems.golditeBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "No fall damage");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants speed II");
		}
		
		if(i == JourneyItems.bronzedHelmet || i == JourneyItems.bronzedChest || i == JourneyItems.bronzedLegs || i == JourneyItems.bronzedBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants +6 melee damage");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Slows enemies when hit");
		}
		
		if(i == JourneyItems.corbarkHelmet || i == JourneyItems.corbarkChest || i == JourneyItems.corbarkLegs || i == JourneyItems.corbarkBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Stuns Enemies when hit");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Poisons Enemies briefly when hit");
		}
			
		if(i == JourneyItems.blazehornHelmet || i == JourneyItems.blazehornChest || i == JourneyItems.blazehornLegs || i == JourneyItems.blazehornBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants +3 Melee damage");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance when in lava");
		}
		
		if(i == JourneyItems.crystalFlakeHelmet || i == JourneyItems.crystalFlakeChest || i == JourneyItems.crystalFlakeLegs || i == JourneyItems.crystalFlakeBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants +3 Melee damage");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants water breathing");
		}
		
		if(i == JourneyItems.frostbittenHelmet || i == JourneyItems.frostbittenChest || i == JourneyItems.frostbittenLegs || i == JourneyItems.frostbittenBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants +4 Melee damage");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants absorption");
		}
		
		if(i == JourneyItems.darklyHelmet || i == JourneyItems.darklyChest || i == JourneyItems.darklyLegs || i == JourneyItems.darklyBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants night vision");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants +7 attack damage");
		}
		
		if(i == JourneyItems.depthsHelmet || i == JourneyItems.depthsChest || i == JourneyItems.depthsLegs || i == JourneyItems.depthsBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants night vision");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants water breathing");
		}
		
		if(i == JourneyItems.hollowHelmet || i == JourneyItems.hollowChest || i == JourneyItems.hollowLegs || i == JourneyItems.hollowBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Withers away mobs when hit");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants night vision");
		}
		
		if(i == JourneyItems.lightstoneHelmet || i == JourneyItems.lightstoneChest || i == JourneyItems.lightstoneLegs || i == JourneyItems.lightstoneBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards poison effects");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards wither effects");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants night vision");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants speed boost");
		}
		
		if(i == JourneyItems.enlightenerHelmet || i == JourneyItems.enlightenerChest || i == JourneyItems.enlightenerLegs || i == JourneyItems.enlightenerBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants jump boost");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants regeneration");
		}
		
		if(i == JourneyItems.livegreenHelmet || i == JourneyItems.livegreenChest || i == JourneyItems.livegreenLegs || i == JourneyItems.livegreenBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Poisons enemies");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards poison effects");
		}
		
		if(i == JourneyItems.fireboundHelmet || i == JourneyItems.fireboundChest || i == JourneyItems.fireboundLegs || i == JourneyItems.fireboundBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants jump boost");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance");
		}
		
		if(i == JourneyItems.starlightHelmet || i == JourneyItems.starlightChest || i == JourneyItems.starlightLegs || i == JourneyItems.starlightBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Player can jump 6 blocks high");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "No fall damage");
		}
		
		if(i == JourneyItems.bloodcrustHelmet || i == JourneyItems.bloodcrustChest || i == JourneyItems.bloodcrustLegs || i == JourneyItems.bloodcrustBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants fire resistance");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards Wither effects");
		}
		
		if(i == JourneyItems.bleedrockHelmet || i == JourneyItems.bleedrockChest || i == JourneyItems.bleedrockLegs || i == JourneyItems.bleedrockBoots) {
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants immunity towards Wither effects");
			list.add(SlayerAPI.Colour.AQUA + "Full set: " + SlayerAPI.Colour.GOLD + "Grants strength");
		}
	}
}