package net.journey.items.bauble.buddies;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.entity.base.JEntityBuddy;
import net.journey.items.base.JItem;
import net.journey.items.bauble.ItemBaubleBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBuddyBase extends ItemBaubleBase implements IBauble {

	protected Class<? extends JEntityBuddy> buddy;

	public ItemBuddyBase(Class<? extends JEntityBuddy> buddy) {
		setMaxStackSize(1);
		this.buddy = buddy;
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {
		return BaubleType.TRINKET;
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		if (!player.world.isRemote) {
			World world = player.getEntityWorld();
			EntityPlayer entityPlayer = (EntityPlayer) player;
			try {
				JEntityBuddy spawnBuddy = buddy.getConstructor(World.class, EntityPlayer.class, JItem.class).newInstance(world, entityPlayer, this);
				spawnBuddy.posX = entityPlayer.posX;
				spawnBuddy.posY = entityPlayer.posY;
				spawnBuddy.posZ = entityPlayer.posZ;
				world.spawnEntity(spawnBuddy);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
	}
}
