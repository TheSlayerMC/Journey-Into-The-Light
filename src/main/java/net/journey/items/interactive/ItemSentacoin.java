package net.journey.items.interactive;

import net.journey.api.capability.PlayerStats;
import net.journey.common.capability.JCapabilityManager;
import net.journey.items.base.JItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSentacoin extends JItem {

	public ItemSentacoin(String name, String enName) {
		super(name, enName);
		setMaxStackSize(1);
	}

	@Override
	public void onUpdate(ItemStack s, World w, Entity e, int itemSlot, boolean isSelected) {
		if(!w.isRemote) {
			if(e instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) e;
				PlayerStats stats = JCapabilityManager.asJourneyPlayer(player).getPlayerStats();
				stats.addSentacoin(1);
				System.out.println(stats.getSentacoinValue());
				player.inventory.decrStackSize(itemSlot, 1);
			}
		}
	}	
}