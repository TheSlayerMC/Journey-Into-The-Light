package net.journey.entity.mob.depths;

import javax.annotation.Nullable;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityFireBall;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityDarkSorcerer extends EntityModMob implements IRangedAttackMob {

	private final EntityAIAttackRangedBow<EntityDarkSorcerer> aiArrowAttack = new EntityAIAttackRangedBow<EntityDarkSorcerer>(this, 1.0D, 20, 15.0F);
	
	public EntityDarkSorcerer(World par1World) {
		super(par1World);
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		if(par1World != null && !par1World.isRemote) {
			this.setCombatTask();
		}
	}

	public void setCombatTask() {
		if(this.world != null && !this.world.isRemote) {
			this.tasks.removeTask(this.aiArrowAttack);
			ItemStack itemstack = this.getHeldItemMainhand();
			if(itemstack.getItem() == JourneyItems.fireWand) {
				int i = 20;
				if(this.world.getDifficulty() != EnumDifficulty.HARD) {
					i = 40;
				}
				this.aiArrowAttack.setAttackCooldown(i);
				this.tasks.addTask(4, this.aiArrowAttack);
			}
		}
	}
	
	@Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(JourneyItems.fireWand));
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
	public ItemStack getHeldItem(EnumHand hand) {
		return new ItemStack(JourneyItems.fireWand);
	}
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		EntityFireBall b = new EntityFireBall(this.world, this, 10F);
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
		return 	this.isValidLightLevel() && 
				this.world.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).isFullBlock();
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
		return JourneySounds.INSECTO;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.INSECTO_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.INSECTO_HURT;
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(90) == 0) dropItem(JourneyItems.darkOrb, 1);
		super.dropFewItems(b, j);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {

	}
}