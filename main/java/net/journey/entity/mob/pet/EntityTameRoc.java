package net.journey.entity.mob.pet;

import java.util.UUID;

import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModTameable;

public class EntityTameRoc extends EntityModTameable {
	
	private float headRotationCourse;
    private float headRotationCourseOld;
	private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.<Float>createKey(EntityTameRoc.class, DataSerializers.FLOAT);

	public EntityTameRoc(World w) {
		super(w);		
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0F, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0F, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0F));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		this.setSize(1.0F, 1.0F);
	}
	
	public EntityTameRoc(World w, EntityPlayer player) {
		super(w);
		this.setSize(1.0F, 1.0F);
		this.setOwnerId(player.getUniqueID());
		this.setTamed(true);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(MobStats.baseNetherHealth);
		if(this.isTamed()) this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(MobStats.baseNetherHealth);
		else this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(MobStats.baseNetherHealth);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}

	@Override
	public EntityTameRoc createChild(EntityAgeable ageable) {
		EntityTameRoc hopper = new EntityTameRoc(this.world);
		UUID uuid = this.getOwnerId();

		if (uuid != null) {
			hopper.setOwnerId(uuid);
			hopper.setTamed(true);
		}

		return hopper;
	}

	@Override
	protected void updateAITasks() {
		this.dataManager.set(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
	}
    
	@Override
	public void setAttackTarget(EntityLivingBase par1EntityLivingBase) {
		super.setAttackTarget(par1EntityLivingBase);
		if(par1EntityLivingBase == null) this.setAngry(false);
		else if(!this.isTamed()) this.setAngry(true);
	}
	
	@Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.world.isRemote && !this.hasPath() && this.onGround) {
        this.world.setEntityState(this, (byte)8);
        }
        if (!this.world.isRemote && this.getAttackTarget() == null && this.isAngry()) {
        this.setAngry(false);
        }
    }

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(DATA_HEALTH_ID, Float.valueOf(this.getHealth()));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("Angry", this.isAngry());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.setAngry(par1NBTTagCompound.getBoolean("Angry"));
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if(this.isEntityInvulnerable(par1DamageSource)) return false;
		else {
			Entity entity = par1DamageSource.getImmediateSource();
			this.aiSit.setSitting(false);
			if(entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))            
				par2 = (par2 + 1.0F) / 2.0F;
			return super.attackEntityFrom(par1DamageSource, par2);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		int i = this.isTamed() ? 10 : 5;
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this),i);
		
	}

	@Override
	public void setTamed(boolean par1) {
		super.setTamed(par1);
		if(par1) this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(MobStats.baseNetherHealth);
		else this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(MobStats.baseNetherHealth);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack itemstack = player.getHeldItem(hand);

		if (this.isTamed()) {
			if (!itemstack.isEmpty()) {
				if (itemstack.getItem() == Items.APPLE) {
					Item itemfood = Items.APPLE;

					if (((Float)this.dataManager.get(DATA_HEALTH_ID)).floatValue() < 20.0F) {
						if (!player.capabilities.isCreativeMode) {
							itemstack.shrink(1);
						}
						this.heal(5);
						return true;
					}
				}

				if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
					this.aiSit.setSitting(!this.isSitting());
					this.isJumping = false;
					this.navigator.clearPath();
					this.setAttackTarget((EntityLivingBase)null);
				}
			}
			else if (itemstack.getItem() == Items.BREAD && !this.isAngry()) {
				if (!player.capabilities.isCreativeMode) {
					itemstack.shrink(1);
				}

				if (!this.world.isRemote) {
					if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
						this.setTamedBy(player);
						this.navigator.clearPath();
						this.setAttackTarget((EntityLivingBase)null);
						this.aiSit.setSitting(true);
						this.setHealth(20.0F);
						this.playTameEffect(true);
						this.world.setEntityState(this, (byte)7);
					} else {
						this.playTameEffect(false);
						this.world.setEntityState(this, (byte)6);
					}
				}

				return true;
			}
		}
		return super.processInteract(player, hand);
	}

	@Override
	public boolean isBreedingItem(ItemStack i) {
		return i == null ? false : i.getItem() == Items.BREAD;
	}

	public boolean isAngry() {
		return (((Byte)this.dataManager.get(TAMED)).byteValue() & 2) != 0;
	}

	public void setAngry(boolean angry) {
		byte b0 = ((Byte)this.dataManager.get(TAMED)).byteValue();

		if (angry) {
			this.dataManager.set(TAMED, Byte.valueOf((byte)(b0 | 2)));
		} else {
			this.dataManager.set(TAMED, Byte.valueOf((byte)(b0 & -3)));
		}
	}

	@Override
	protected boolean canDespawn() {
		return !this.isTamed() && this.ticksExisted > 1200;
	}

	public boolean func_142018_a(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase) {
		if(!(par1EntityLivingBase instanceof EntityCreeper) && !(par1EntityLivingBase instanceof EntityGhast)) {
			if(par1EntityLivingBase instanceof EntityTameRoc) {
				EntityTameRoc pig = (EntityTameRoc)par1EntityLivingBase;
				if(pig.isTamed() && pig.getOwner() == par2EntityLivingBase) return false;
			}
			return par1EntityLivingBase instanceof EntityPlayer && par2EntityLivingBase instanceof EntityPlayer && !((EntityPlayer)par2EntityLivingBase).canAttackPlayer((EntityPlayer)par1EntityLivingBase) ? false : !(par1EntityLivingBase instanceof EntityHorse) || !((EntityHorse)par1EntityLivingBase).isTame();
		}
		else return false;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	@Override
	public SoundEvent getAmbientSound() {
		return JourneySounds.BIRD;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource sourceIn) {
		return JourneySounds.BIRD_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return JourneySounds.BIRD_DEATH;
	}
}