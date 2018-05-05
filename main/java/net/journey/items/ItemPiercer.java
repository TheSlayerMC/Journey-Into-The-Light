package net.journey.items;

import java.util.List;

import net.journey.JourneyTabs;
import net.journey.client.ItemDescription;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.slayer.api.item.ItemMod;

public class ItemPiercer extends ItemMod {

	private Class<? extends EntityThrowable> entity;
	private float damage;
	private int maxBounces = 0;
	
	public ItemPiercer(String name, String f, float damage, int bounces, Class<? extends EntityThrowable> entity) {
		super(name, f);
		this.maxBounces = bounces;
		this.damage = damage;
		this.entity = entity;
		setCreativeTab(JourneyTabs.piercers);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		try {
			if(!world.isRemote) {
				world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
				world.spawnEntityInWorld(entity.getConstructor(World.class, EntityLivingBase.class, float.class, int.class).newInstance(world, player, damage, maxBounces));
				if(!player.capabilities.isCreativeMode) stack.stackSize--;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stack;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list) {
		ItemDescription.addInformation(stack, player, list);
		list.add(damage + "Ranged Damage");
	}
}