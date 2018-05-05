package net.journey.items;

import net.journey.JourneyTabs;
import net.journey.entity.projectile.EntityPower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.slayer.api.item.ItemMod;

public class ItemPower extends ItemMod {

	private float damage;

	public ItemPower(String name, String f, float dam) {
		super(name, f, JourneyTabs.util);
		damage = dam;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		EntityPower power = new EntityPower(world, player, damage);
		if(!world.isRemote) world.spawnEntityInWorld(power);
		return stack;
	}
}