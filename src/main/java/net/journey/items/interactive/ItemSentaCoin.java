package net.journey.items.interactive;

import net.journey.items.base.JItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSentaCoin extends JItem {

	public ItemSentaCoin(String name, String enName) {
		super(name, enName);
		setMaxStackSize(1);
	}

	@Override
	public void onUpdate(ItemStack s, World w, Entity e, int itemSlot, boolean isSelected) {
		if(!w.isRemote) {
			if(e instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)e;
				//ADD TO PLAYER CURRENCY CAPABILITY
				System.out.println("ITEM ENTERED INVENTORY");
				player.inventory.decrStackSize(itemSlot, 1);
			} else {
				s = null;
			}
		} else {
			if(e instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer)e;
				player.inventory.decrStackSize(itemSlot, 1);
			} else {
				s = null;
			}
		}
	}	
}