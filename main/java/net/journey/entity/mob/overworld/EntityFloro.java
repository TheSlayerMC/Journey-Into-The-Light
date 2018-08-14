package net.journey.entity.mob.overworld;

import javax.annotation.Nullable;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityFloroWater;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityFloro extends EntityModMob implements IRangedAttackMob {

	private int ticks;
	private final EntityAIAttackRangedBow<EntityFloro> aiArrowAttack = new EntityAIAttackRangedBow<EntityFloro>(this, 1.0D, 20, 15.0F);

	public EntityFloro(World par1World) {
		super(par1World);
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
		if(par1World != null && !par1World.isRemote) {
			this.setCombatTask();
		}
	}
	
	public void setCombatTask() {
		this.tasks.removeTask(this.aiArrowAttack); {
			this.tasks.addTask(4, this.aiArrowAttack);
		}
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
        EntityFloroWater b = new EntityFloroWater(this.world, this, 8F);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - b.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        b.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
		JourneySounds.playSound(JourneySounds.MAGIC_SPARKLE, world, this);
        this.world.spawnEntity(b);
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
		return JourneySounds.HONGO;
	}
	
	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(6) == 0) dropItem(JourneyItems.floroPedal, 4);
		super.dropFewItems(b, j);
		if(rand.nextInt(3) == 0) dropItem(JourneyItems.floroPedal, 2);
		super.dropFewItems(b, j);
		if(rand.nextInt(1) == 0) dropItem(JourneyItems.floroPedal, 1);
		super.dropFewItems(b, j);
	
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
		
	}
}