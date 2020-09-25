package net.journey.items.bauble.buddies;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.items.base.JItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBuddyBase extends JItem implements IBauble {

	EntityLivingBase buddy;
	World world;

	public boolean isEquipped;

	public ItemBuddyBase() {
		setMaxStackSize(1);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {
		return BaubleType.TRINKET;
	}

	public EntityLivingBase setBuddy(EntityLivingBase buddy) {
		this.buddy = buddy;
		buddy.setWorld(world);
		return buddy;
	}

	public EntityLivingBase getBuddy(World world) {
		return buddy;
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		this.world = player.getEntityWorld();

		buddy.posX = player.posX;
		buddy.posY = player.posY;
		buddy.posZ = player.posZ;

		world.spawnEntity(buddy);
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		this.world = player.getEntityWorld();

		buddy.setDead();
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		this.world = player.getEntityWorld();

		if (!isEquipped) {
			buddy.setDead();
		}
	}
}
