package net.journey.entity.mob.overworld.underground;

import javax.annotation.Nullable;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityConjuring;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityCavurn extends EntityModMob implements IRangedAttackMob {

	private final EntityAIAttackRangedBow<EntityCavurn> aiArrowAttack = new EntityAIAttackRangedBow<EntityCavurn>(this, 1.0D, 20, 15.0F);

	public EntityCavurn(World par1World) {
		super(par1World);
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		if(par1World != null && !par1World.isRemote) {
			this.setCombatTask();
		}
	}

	public void setCombatTask() {
		this.tasks.removeTask(this.aiArrowAttack);
			this.tasks.addTask(4, this.aiArrowAttack);
		}

	@Override
	@Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        this.setCombatTask();
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());
        return livingdata;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setCombatTask();
    }
	
	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
        super.setItemStackToSlot(slotIn, stack);
        if (!this.world.isRemote && slotIn == EntityEquipmentSlot.MAINHAND) {
            this.setCombatTask();
        }
    }
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        EntityConjuring b = new EntityConjuring(this.world, this, 8F);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - b.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        b.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
		JourneySounds.playSound(JourneySounds.MAGIC_SPARKLE, world, this); 
        this.world.spawnEntity(b);
    }

	@Override
	public double setAttackDamage(MobStats s) {
		return 0;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.overworldHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.BASE_MOB_HURT;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.ROCK;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.CAVE_MOB;
	}
	
	@Override
	public void onDeath(DamageSource d) {
		super.onDeath(d);
		if(d.getImmediateSource() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)d.getImmediateSource();
		//	p.triggerAchievement(JourneyAchievements.achievementCave);
		}
	}

	@Override
	public boolean getCanSpawnHere() {
		return this.posY < 40.0D && super.getCanSpawnHere() && 
				this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.STONE;
	}
	
	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(12) == 0) dropItem(JourneyItems.caveCrystal, 1);
		super.dropFewItems(b, j);
		if(rand.nextInt(2) == 0) dropItem(JourneyItems.caveDust, 1);
		super.dropFewItems(b, j);
		if(rand.nextInt(4) == 0) dropItem(JourneyItems.caveDust, 3);
		super.dropFewItems(b, j);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) { }
}