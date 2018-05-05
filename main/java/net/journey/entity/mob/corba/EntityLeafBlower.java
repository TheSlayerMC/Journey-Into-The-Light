package net.journey.entity.mob.corba;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityLeafBlower extends EntityModMob{

	public EntityLeafBlower(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.2F, 2.5F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.corbaHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.BUSH;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.BUSH_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.BUSH_DEATH;
	}
	
	@Override
	public Item getItemDropped() {
		return null; 
	
	}
	
	@Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(this.world.isDaytime() && !this.world.isRemote) {
            float var1 = getBrightness();
        }
        
        List<Entity> e = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());        
        for(Entity entity : e) {
        	if(entity instanceof EntityPlayer && canEntityBeSeen(entity)) ((EntityPlayer)entity).addPotionEffect(PotionEffects.setPotionEffect(PotionEffects.poison, 60, 1));
        }        
    }
		
	@Override
	protected void dropFewItems(boolean b, int j) {
		Item it = getItemDropped();
		this.dropItem(it, 1);
		if(rand.nextInt(6) == 0) dropItem(JourneyItems.corbaStick, 2);
		super.dropFewItems(b, j);
		if(rand.nextInt(12) == 0) dropItem(JourneyItems.corbaStick, 4);
		super.dropFewItems(b, j); 
		if(rand.nextInt(6) == 0) dropItem(JourneyItems.enchantedLeaf, 2);
		super.dropFewItems(b, j); 
		if(rand.nextInt(12) == 0) dropItem(JourneyItems.enchantedLeaf, 4);
		super.dropFewItems(b, j); 
		if(rand.nextInt(24) == 0) dropItem(JourneyItems.natureTablet, 2);
		super.dropFewItems(b, j); 
		if(rand.nextInt(48) == 0) dropItem(JourneyItems.natureTablet, 4);
			super.dropFewItems(b, j); 
	}
}