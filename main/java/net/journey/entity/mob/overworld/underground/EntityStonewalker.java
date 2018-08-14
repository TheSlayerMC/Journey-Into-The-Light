package net.journey.entity.mob.overworld.underground;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModMob;

public class EntityStonewalker extends EntityModMob {

	public EntityStonewalker(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(0.8F, 1.5F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.lowJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.overworldHealth;
	}
	
	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.ROCK;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.CAVE_MOB;
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
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(13) == 0) dropItem(JourneyItems.caveCrystal, 1);
		super.dropFewItems(b, j);
		if(rand.nextInt(2) == 0) dropItem(JourneyItems.caveDust, 1);
		super.dropFewItems(b, j);
		if(rand.nextInt(4) == 0) dropItem(JourneyItems.caveDust, 3);
		super.dropFewItems(b, j);
		if(rand.nextInt(15) == 0) dropItem(JourneyItems.sapphire, 4);
		super.dropFewItems(b, j);
		if(rand.nextInt(12) == 0) dropItem(JourneyItems.shadiumIngot, 4);
		super.dropFewItems(b, j);
		if(rand.nextInt(12) == 0) dropItem(JourneyItems.luniumIngot, 4);
		super.dropFewItems(b, j);
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
				this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.STONE;
	}
}