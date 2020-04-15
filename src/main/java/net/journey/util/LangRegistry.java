package net.journey.util;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import net.journey.items.ItemModArmor;
import net.minecraft.inventory.EntityEquipmentSlot;

public class LangRegistry {

	public static LangRegistry instance = new LangRegistry();

	public String location = System.getProperty("user.home") + "/Desktop/";
	private BufferedWriter writer;
	public static ArrayList<String> 
		blockUnloc = new ArrayList<String>(), 
		blockFinal = new ArrayList<String>(), 
		itemUnloc = new ArrayList<String>(), 
		itemFinal = new ArrayList<String>(), 
		recordDescUnloc = new ArrayList<String>(), 
		recordDescFinal = new ArrayList<String>(), 
		fileText = new ArrayList<String>();
	
	public static ArrayList<String> 
		mobUnloc = new ArrayList<String>(), 
		mobFinal = new ArrayList<String>(), 
		armourUnloc = new ArrayList<String>(), 
		armorType = new ArrayList<String>(), 
		armorPiece = new ArrayList<String>();

	public LangRegistry() {
		File en_US = new File(location + "en_us.lang");
		try {
			en_US.mkdirs();
			if(en_US.exists()) en_US.delete();
			en_US.createNewFile();
			writer = new BufferedWriter(new FileWriter(en_US));
			//Desktop.getDesktop().open(new File(location));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void creativeTabs() {
		writeToFile("itemGroup.journey.blocks=JITL: Blocks");
		writeToFile("itemGroup.journey.decoration=JITL: Decoration Blocks");
		writeToFile("itemGroup.journey.items=JITL: Items");
		writeToFile("itemGroup.journey.tools=JITL: Tools");
		writeToFile("itemGroup.journey.weapons=JITL: Weapons");
		writeToFile("itemGroup.journey.util=JITL: Utilities");
		writeToFile("itemGroup.journey.misc=JITL: Miscellaneous");
		writeToFile("itemGroup.journey.armor=JITL: Armor");
		writeToFile("itemGroup.journey.spawners=JITL: Spawners");
		writeToFile("itemGroup.journey.crops=JITL: Crops");
		writeToFile("itemGroup.journey.machineBlocks=JITL: Machine Blocks");
		writeToFile("itemGroup.journey.portalBlocks=JITL: Portal Blocks");
		writeToFile("itemGroup.journey.hammers=JITL: Hammers");
	}
	
	public void achievements() {
		writeToFile("achievement.sapphireSword=Time for a Journey");
		writeToFile("achievement.ore=Unfamiliar Substance");
		writeToFile("achievement.gem=Dungeon Looter");
		writeToFile("achievement.boil=Burning Horizons");
		writeToFile("achievement.netherKill=Bad Cows Go to the Nether");
		writeToFile("achievement.giantMushroom=Mushrooms, Tigers, and Bears! Oh My!");
		writeToFile("achievement.fl=Colder than Ice");
		writeToFile("achievement.euca=Hostile Royality");
		writeToFile("achievement.depths=Dark, dark depths");
		writeToFile("achievement.corba=Overgrown Wasteland");
		writeToFile("achievement.terrania=The Enchanted Jungle");
		writeToFile("achievement.cloudia=Ornate Sky Paradise");
		writeToFile("achievement.cave=Agressive Caverns");
		writeToFile("achievement.pet=Tiny Companion");
		writeToFile("achievement.roc=Giant Birds are Pets too...");
		
		writeToFile("achievement.blazier=Fire Brawl");
		writeToFile("achievement.eudor=Killer of Kings");
		writeToFile("achievement.corallator=Cooking with Metal");
		writeToFile("achievement.netherBeast=Nether Bullfighting");
		writeToFile("achievement.witherBeast=Witherer of the Withering");
		writeToFile("achievement.soul=Soul Reclaimer");
		writeToFile("achievement.scale=Gone Fishin'");
		writeToFile("achievement.thunderbird=Rough Flight");
		writeToFile("achievement.logger=Planting the Trees");
		writeToFile("achievement.sentry=Reforestation");
		writeToFile("achievement.terra=Hero or Enemy?");
		writeToFile("achievement.sky=Plunderer of the Sky");
	}
	
	public void achievementDescription() {
		writeToFile("achievement.sapphireSword.desc=Craft a Sapphire Sword");
		writeToFile("achievement.ore.desc=Obtain Sapphire");
		writeToFile("achievement.gem.desc=Obtain a dungeon gemstone");
		writeToFile("achievement.boil.desc=Unlock the Boiling Point Dimension");
		writeToFile("achievement.netherKill.desc=Kill a Hell Cow");
		writeToFile("achievement.giantMushroom.desc=Kill a Hongo");
		writeToFile("achievement.fl.desc=Unlock the Frozen Lands Dimension");
		writeToFile("achievement.euca.desc=Unlock the Euca Dimension");
		writeToFile("achievement.depths.desc=Unlock the Depths Dimension");
		writeToFile("achievement.corba.desc=Unlock the Corba Dimension");
		writeToFile("achievement.terrania.desc=Unlock the Terrania Dimension");
		writeToFile("achievement.cloudia.desc=Unlock the Cloudia Dimension");
		writeToFile("achievement.cave.desc=Kill an Underground Mob");
		writeToFile("achievement.pet.desc=Tame a Ferret");
		writeToFile("achievement.roc.desc=Spawn a Pet Roc");
		
		writeToFile("achievement.blazier.desc=Kill the Blazier Boss");
		writeToFile("achievement.eudor.desc=Kill the Eudor Boss");
		writeToFile("achievement.corallator.desc=Kill the Corallator Boss");
		writeToFile("achievement.netherBeast.desc=Kill the Nether Beast Boss");
		writeToFile("achievement.witherBeast.desc=Kill the Withering Beast Boss");
		writeToFile("achievement.soul.desc=Kill the Soul Watcher Boss");
		writeToFile("achievement.scale.desc=Kill the Scale Boss");
		writeToFile("achievement.thunderbird.desc=Kill the Thunderbird Boss");
		writeToFile("achievement.logger.desc=Kill the Logger Boss");
		writeToFile("achievement.sentry.desc=Kill the Sentry King Boss");
		writeToFile("achievement.terra.desc=Kill the Terranian Protector Boss");
		writeToFile("achievement.sky.desc=Kill the Sky Stalker Boss");
	}
	
	public void misc() {
		writeToFile("item.record.underwaterWorld.desc=Chris Geddes - Underwater World");
		writeToFile("item.record.blueWater.desc=Chris Geddes - Blue Water");
		writeToFile("item.record.raceStar.desc=Chris Geddes - Race Star");
		writeToFile("item.record.compBegins.desc=Chris Geddes - Comp Begins");
		writeToFile("item.record.deepBlue.desc=Chris Geddes - Deep Blue");
		writeToFile("item.record.raceShore.desc=Chris Geddes - Race Shore");
		writeToFile("item.record.untitled_disk.desc=Dizzlepop12 - untitled_disc");
		writeToFile("item.record.bog.desc=Dizzlepop12 - Bog");
		writeToFile("item.record.clouds.desc=Dizzlepop12 - Clouds");
		writeToFile("item.record.stalactite.desc=Dizzlepop12 - Stalactite");
		writeToFile("item.record.gold.desc=The_SlayerMC - Gold");
		writeToFile("item.record.gate.desc=The_SlayerMC - Gate");
		writeToFile("item.record.journey.desc=Dizzlepop12 - Journey Into the Light");
		writeToFile("item.record.sizzle.desc=Dizzlepop12 - Sizzle");
		writeToFile("item.record.firefly.desc=Dizzlepop12 - Firefly");
		writeToFile("item.record.snowbells.desc=Dizzlepop12 - Snow Bells");
		writeToFile("enchantment.Hot Touch=Hot Touch");
		writeToFile("enchantment.Water Walker=Water Walker");
		writeToFile("journey.knowledgeTable=Knowledge Table");
		writeToFile("journey.summoningTable=Summoning Table");
		writeToFile("journey.efficiency=Efficiency");
		writeToFile("journey.usesRemaining=Uses Remaining");
		writeToFile("journey.infinite=Infinite");
		writeToFile("journey.uses=Uses");
		writeToFile("journey.essence=Essence");
		writeToFile("journey.dark=Dark Energy");
		writeToFile("journey.rangedDamage=Ranged Damage");
		writeToFile("journey.unbreakable=Unbreakable");
		writeToFile("journey.hit=On hit");
		writeToFile("journey.wither=Withers foe for");
		writeToFile("journey.seconds=seconds");
		writeToFile("journey.bossSpawn=Spawns boss");
		writeToFile("journey.petSpawn=Spawns pet");
		writeToFile("journey.time=Time: ");
		writeToFile("mage.deals=I have some great deals!");
		writeToFile("mage.valuables=I've got some nice valuables!");
		writeToFile("mage.greetings=Greetings, weary traveller!");
		writeToFile("smith.deals=I have some great deals!");
		writeToFile("smith.valuables=I've got some nice valuables!");
		writeToFile("smith.greetings=Greetings, weary traveller!");
		writeToFile("elf.welcome=Welcome welcome!");
		writeToFile("elf.hello=Hello!");
		writeToFile("elf.good=Good to see you!");
	}

	public void register() {
		block();
		item();
		mob();
		armour();
		creativeTabs();
		misc();
		achievements();
		achievementDescription();
		closeFile();
	}

	public static void addBlock(String unloc, String finalName) {
		blockUnloc.add(unloc);
		blockFinal.add(finalName);
	}

	public static void addItem(String unloc, String finalName) {
		itemUnloc.add(unloc);
		itemFinal.add(finalName);
	}

	public static void addMob(String unloc, String finalName) {
		mobUnloc.add(unloc);
		mobFinal.add(finalName);
	}

	public static void addArmour(ItemModArmor a, EnumArmor ar, EntityEquipmentSlot t) {
		armourUnloc.add(a.getUnlocalizedName());
		EntityEquipmentSlot HEAD = EntityEquipmentSlot.HEAD, BODY = EntityEquipmentSlot.CHEST, LEGS = EntityEquipmentSlot.LEGS, BOOTS = EntityEquipmentSlot.FEET;
		armorType.add(ar.getFinalName());
		String part = t == HEAD ? "Helmet" : t == BODY ? "Chestplate" : t == LEGS ? "Leggings" : t == BOOTS ? "Boots" : "UNKNOWN";
		armorPiece.add(part);
	}

	public void block() {
		for(int i = 0; i < blockUnloc.size(); i++)
			writeToFile("tile." + blockUnloc.get(i) + ".name=" + blockFinal.get(i));
	}

	public void mob() {
		for(int i = 0; i < mobUnloc.size(); i++)
			writeToFile("entity.journey." + mobUnloc.get(i) + ".name=" + mobFinal.get(i));
	}

	public void item() {
		for(int i = 0; i < itemUnloc.size(); i++)
			writeToFile("item." + itemUnloc.get(i) + ".name=" + itemFinal.get(i));
	}
	
	public void armour() {
		for(int i = 0; i < armourUnloc.size(); i++)
			writeToFile(armourUnloc.get(i) + ".name=" + armorType.get(i) + " " + armorPiece.get(i));
	}

	private void writeToFile(String s) {
		try {
			instance.writer.write(s + "\n");
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}

	private void closeFile() {
		try {
			instance.writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}