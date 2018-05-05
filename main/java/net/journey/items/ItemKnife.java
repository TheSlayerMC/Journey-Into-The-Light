package net.journey.items;

import net.journey.JourneyTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.slayer.api.item.ItemMod;

public class ItemKnife extends ItemMod {

	private Class<? extends EntityThrowable> entity;
	private float damage = 0;
	
	public ItemKnife(String name, String f, float damage, Class<? extends EntityThrowable> entity) {
		super(name, f);
		this.damage = damage;
		this.entity = entity;
		setCreativeTab(JourneyTabs.piercers);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		try {
			if(!world.isRemote) {
				world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
				world.spawnEntityInWorld(entity.getConstructor(World.class, EntityLivingBase.class, float.class, int.class).newInstance(world, player, damage));
				if(!player.capabilities.isCreativeMode) stack.stackSize--;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stack;
	}
}