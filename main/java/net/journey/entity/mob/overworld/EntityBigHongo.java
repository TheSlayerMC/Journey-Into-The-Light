package net.journey.entity.mob.overworld;

import net.journey.JourneyConsumables;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityBigHongo extends EntityModMob {

	public EntityBigHongo(World par1World) {
		super(par1World);
		addAttackingAI();
		this.setSize(1.0F, 2.0F);
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
	public boolean getCanSpawnHere() {
		return 	this.isValidLightLevel() && 
				this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).isFullBlock();
	}

	@Override
	public Item getItemDropped() {
		return JourneyConsumables.hongoShroom;
	}
}