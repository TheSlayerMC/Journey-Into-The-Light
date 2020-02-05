package net.journey.entity.mob.overworld.underground;

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
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModMob;

public class EntityCaveling extends EntityModMob {

	public EntityCaveling(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize(1.0F, 2.0F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.CavelingDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.CavelingHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.CAVE_MOB;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.ROCK;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.BASE_MOB_HURT;
	}

	@Override
	public Item getItemDropped() {
		return SlayerAPI.toItem(Blocks.STONE);

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
		if(rand.nextInt(15) == 0) dropItem(JourneyItems.caveCrystal, 1);
		if(rand.nextInt(3) == 0) dropItem(JourneyItems.stoneClump, 2);
		if(rand.nextInt(6) == 0) dropItem(JourneyItems.caveDust, 1);
		super.dropFewItems(b, j);
	}
}