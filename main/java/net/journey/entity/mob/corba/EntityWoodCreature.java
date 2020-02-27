package net.journey.entity.mob.corba;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityWoodCreature extends EntityModMob{

	public EntityWoodCreature(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.2F, 1.5F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.WoodCreatureDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.WoodCreatureHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.BUSH;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.BUSH_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.BUSH_DEATH;
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
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(1) == 0) dropItem(JourneyItems.enchantedLeaf, 2);
		super.dropFewItems(b, j);
		if(rand.nextInt(5) == 0) dropItem(JourneyItems.enchantedLeaf, 4);
		super.dropFewItems(b, j);
		if(rand.nextInt(5) == 0) dropItem(JourneyItems.corbaStick, 1);
		super.dropFewItems(b, j);
		if(rand.nextInt(10) == 0) dropItem(JourneyItems.corbaStick, 2);
		super.dropFewItems(b, j);
	}
}