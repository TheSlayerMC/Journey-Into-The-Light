package net.journey.entity.mob.nether;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.enums.EnumSounds;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityWitherspine extends EntityModMob {

	public EntityWitherspine(World par1World) {
		super(par1World);
		addAttackingAI();
		this.isImmuneToFire = true;
		setSize(0.7F, 4.2F);
	}
	
	//@Override
	//public boolean getCanSpawnHere() {
	//	return super.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.nether_brick;
	//}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.highNetherHealth;
	}
	
	@Override
	public double getMoveSpeed() {
		return 20.0D;
	}

	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.EMPTY;
	}

    protected String getLivingSound()
    {
        return "mob.wither.idle";
    }
    
    protected String getHurtSound()
    {
        return "mob.skeleton.hurt";
    }
    
	protected String getDeathSound()
    {
        return "mob.skeleton.death";
    }
    
	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.EMPTY;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.EMPTY;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(5) == 0) dropItem(JourneyItems.withicDust, 1);
		super.dropFewItems(b, j);
		if(rand.nextInt(4) == 0) dropItem(JourneyItems.blood, 1);
		super.dropFewItems(b, j);
		if(rand.nextInt(6) == 0) dropItem(JourneyItems.withicDust, 2);
		super.dropFewItems(b, j);
		if(rand.nextInt(22) == 0) dropItem(JourneyItems.withicSpine, 1);
		super.dropFewItems(b, j);
		if(rand.nextInt(33) == 0) dropItem(JourneyItems.withicSpine, 2);
		super.dropFewItems(b, j);
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
}