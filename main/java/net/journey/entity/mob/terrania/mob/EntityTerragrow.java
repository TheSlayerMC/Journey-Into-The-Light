package net.journey.entity.mob.terrania.mob;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.slayer.api.SlayerAPI;
import net.slayer.api.entity.EntityModMob;

public class EntityTerragrow extends EntityModMob {

	public EntityTerragrow(World w) {
		super(w);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.terraniaHealth;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.WRAITH;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.CREEPER;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.WRAITH_DEATH;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(15) == 0) dropItem(JourneyItems.earthenCrystal, 1);
		if(rand.nextInt(4) == 0) dropItem(JourneyItems.darkTerrarianSoil, 4);
		if(rand.nextInt(2) == 0) dropItem(JourneyItems.darkTerrarianSoil, 2);
		super.dropFewItems(b, j);
	}

	@Override
	public Item getItemDropped() {
		return null;
	}

}