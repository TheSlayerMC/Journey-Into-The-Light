package net.journey.items;

import net.journey.JourneyTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.slayer.api.item.ItemMod;

public class ItemThrowable extends ItemMod {

	private Class<? extends EntityThrowable> entity;
	private float damage = 0;
	
	public ItemThrowable(String name, String f, float damage, Class<? extends EntityThrowable> entity) {
		super(name, f);
		this.damage = damage;
		this.entity = entity;
		setCreativeTab(JourneyTabs.piercers);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		try {
			if(!world.isRemote) {
				world.spawnEntity(entity.getConstructor(World.class, EntityLivingBase.class, float.class).newInstance(world, player, damage));
				if(!player.capabilities.isCreativeMode) stack.shrink(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);	
	}
}