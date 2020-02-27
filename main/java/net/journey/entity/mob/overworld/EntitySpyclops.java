package net.journey.entity.mob.overworld;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntitySpyclops extends EntityModMob {
	
	public EntitySpyclops(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(1.0F, 2.0F);
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
	public double setAttackDamage(MobStats s) {
		return MobStats.SpyclopseDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.SpyclopseHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.SPYCLOPS;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.SPYCLOPS;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.SPYCLOPS_HURT;
	}

	@Override
	public Item getItemDropped() {
		return JourneyItems.spyclopseEye;
	}
}