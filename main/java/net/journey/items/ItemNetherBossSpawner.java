package net.journey.items;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneyTabs;
import net.journey.entity.mob.boss.EntityCalcia;
import net.journey.entity.mob.boss.EntityNetherBeast;
import net.journey.entity.mob.boss.EntitySoulWatcher;
import net.journey.entity.mob.boss.EntityWitheringBeast;
import net.journey.entity.projectile.EntityLightningBall;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.item.ItemMod;

public class ItemNetherBossSpawner extends ItemMod {

	public ItemNetherBossSpawner(String name, String f) {
		super(name, f, JourneyTabs.spawners);
		setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, BlockPos pos, EnumFacing fa, float par8, float par9, float par10) {
		Item item = i.getItem();
		if(!w.isRemote)
			if(w.provider.getDimensionId() == -1) {
				EntityLightningBall light = new EntityLightningBall(w);
				EntityWitheringBeast wither = new EntityWitheringBeast(w);
				EntitySoulWatcher soul = new EntitySoulWatcher(w);
				EntityCalcia calcia = new EntityCalcia(w);
				EntityNetherBeast nether = new EntityNetherBeast(w);

				if(item == JourneyItems.calciaOrb){
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
					SlayerAPI.sendMessageToAll("Calcia has been summoned", true);
					calcia.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(calcia);
				}
				if(item == JourneyItems.netherBeastOrb){
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
					SlayerAPI.sendMessageToAll("The Nether Beast has been summoned", true);
					nether.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(nether);
				}
				if(item == JourneyItems.witheringBeastOrb){
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
					SlayerAPI.sendMessageToAll("The Withering Beast has been summoned", true);
					wither.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(wither);
					if(!p.capabilities.isCreativeMode) i.stackSize--;
				}
				if(item == JourneyItems.soulWatcherOrb){
				    EnumSounds.playSound(EnumSounds.SUMMON, w, p);
					light.setPosition(pos.getX(), pos.getY(), pos.getZ());
					w.spawnEntityInWorld(light);
					SlayerAPI.sendMessageToAll("The Soul Watcher has been summoned", true);
					soul.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
					w.spawnEntityInWorld(soul);
				if(!p.capabilities.isCreativeMode) i.stackSize--;
			} else {
				SlayerAPI.addChatMessage(p, EnumChatFormatting.GREEN + "Cannot be spawned unless in the Nether.");
			}
		}
		return true;
		}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list) {
		Item item = stack.getItem();
		String spawn = "";
		if(item == JourneyItems.calciaOrb) spawn = "Calcia";
		if(item == JourneyItems.netherBeastOrb) spawn = "Nether Beast";
		if(item == JourneyItems.witheringBeastOrb) spawn = "Withering Beast";
		if(item == JourneyItems.soulWatcherOrb) spawn = "Soul Watcher";
		list.add("Spawns the boss: " + spawn);
	}
}