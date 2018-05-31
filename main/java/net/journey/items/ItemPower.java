package net.journey.items;

import net.journey.JourneyTabs;
import net.journey.entity.projectile.EntityPower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.slayer.api.item.ItemMod;

public class ItemPower extends ItemMod {

	private float damage;

	public ItemPower(String name, String f, float dam) {
		super(name, f, JourneyTabs.util);
		damage = dam;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		EntityPower power = new EntityPower(world, player, damage);
		if(!world.isRemote) world.spawnEntity(power);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));	
	}
}