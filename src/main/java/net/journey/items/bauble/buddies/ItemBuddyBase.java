package net.journey.items.bauble.buddies;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.journey.entity.base.JEntityBuddy;
import net.journey.items.base.JItem;
import net.journey.items.bauble.ItemBaubleBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBuddyBase extends ItemBaubleBase implements IBauble {

	protected BuddyFactory<JEntityBuddy> buddy;

	public ItemBuddyBase(BuddyFactory<JEntityBuddy> buddy) {
		setMaxStackSize(1);
		setMaxDamage(8);
		this.buddy = buddy;
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {
		return BaubleType.TRINKET;
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		if (!player.world.isRemote && getDamage(itemstack) != 0) {
			World world = player.getEntityWorld();
			EntityPlayer entityPlayer = (EntityPlayer) player;

			JEntityBuddy spawnBuddy = buddy.create(world, entityPlayer, this);
			spawnBuddy.setPosition(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
			world.spawnEntity(spawnBuddy);
		}
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
	}

	@FunctionalInterface
	public interface BuddyFactory<T extends Entity> {
		T create(World world, EntityPlayer owner, JItem bauble);
	}
}
