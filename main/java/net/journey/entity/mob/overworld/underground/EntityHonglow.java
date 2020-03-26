package net.journey.entity.mob.overworld.underground;

import net.journey.JourneyConsumables;
import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityHonglow extends EntityModMob {

	public EntityHonglow(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize(1.0F, 2.0F);
	}
	
    @Override
    public float getBrightness() {
    	return 15728880F;
    }
    
    @Override
    public int getBrightnessForRender() {
    	return 2000000;
    }

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.HonglowDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.HonglowHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.HONGO;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.HONGO_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.HONGO_HURT;
	}

	@Override
	public void onDeath(DamageSource d) {
		super.onDeath(d);
		if(d.getImmediateSource() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)d.getImmediateSource();
			//p.triggerAchievement(JourneyAchievements.achievementCave);
		}
	}

	@Override
	public boolean getCanSpawnHere() {
		return this.posY < 40.0D && super.getCanSpawnHere() && 
				this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getMaterial() == Material.ROCK && this.dimension == 0;
	}

	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(1) == 0) dropItem(JourneyConsumables.redHonglowShroom, rand.nextInt(2));
		super.dropFewItems(b, j);
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
}