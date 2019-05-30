package net.journey.entity.mob.overworld;

import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModMob;

public class EntitySandCrawler extends EntityModMob {

	public EntitySandCrawler(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize(1.8F, 1.0F);
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
	public SoundEvent getAmbientSound() {
		return JourneySounds.SAND_CRAWLER;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource sourceIn) {
		return JourneySounds.MAGMA_GIANT_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return JourneySounds.MAGMA_GIANT_HURT;
	}

	@Override
	public Item getDropItem() {
		return null;
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return 
			   this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.GRASS || 
			   		this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.LEAVES || 
			   			this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.SAND || 
			   				this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.DIRT;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		for(int i = 0; i < 32 + rand.nextInt(16); i++)
			this.dropItem(SlayerAPI.toItem(Blocks.SAND), 2);
	}
}