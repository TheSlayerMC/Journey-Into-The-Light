package net.journey.items;

import net.journey.JourneyTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.slayer.api.item.ItemMod;

public class ItemThrowableArrow extends ItemMod {

	private Class<? extends EntityTippedArrow> entity;
	private float damage;
	private int maxBounces = 0;
	
	public ItemThrowableArrow(String name, String f, float damage, int bounces, Class<? extends EntityTippedArrow> entity) {
		super(name, f);
		this.maxBounces = bounces;
		this.damage = damage;
		this.entity = entity;
		setCreativeTab(JourneyTabs.weapons);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		try {
			if(!world.isRemote) {
				EntityTippedArrow t = entity.getConstructor(World.class, EntityLivingBase.class).newInstance(world, player);
				t.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
				world.spawnEntity(t);
				if(!player.capabilities.isCreativeMode) stack.shrink(1);
		        world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.MASTER, 1, 1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(handIn));	
	}
}