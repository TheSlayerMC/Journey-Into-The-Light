package net.journey.entity.mob.boiling;

import java.util.List;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.journey.entity.mob.boss.EntityBlazier;
import net.journey.entity.projectile.EntityMagmaFireball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityObserver extends EntityModMob {

	private static final DataParameter<Byte> ON_FIRE = EntityDataManager.<Byte>createKey(EntityObserver.class, DataSerializers.BYTE);
	private float heightOffset = 0.5F;
	private int heightOffsetUpdateTime;
	private int attackTimer;

	public EntityObserver(World w) {
		super(w);
		this.experienceValue = 10;
		this.tasks.addTask(4, new EntityObserver.AIFireballAttack());
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.isImmuneToFire = true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(ON_FIRE, Byte.valueOf((byte)0));
	}

	@Override
	protected void dropFewItems(boolean b, int j) {
		if (rand.nextInt(10) == 0)
			dropItem(JourneyItems.hellcrustIngot, 3);
		if (rand.nextInt(5) == 0)
			dropItem(JourneyItems.hellcrustIngot, 2);
		if (rand.nextInt(3) == 0)
			dropItem(JourneyItems.boilPowder, 8);
		if (rand.nextInt(70) == 0)
			dropItem(JourneyItems.sizzlingEye, 1);
		super.dropFewItems(b, j);
	}

	@Override
	public void onLivingUpdate() {

		if(this.world.isDaytime() && !this.world.isRemote) {
			float var1 = getBrightness();
		}

		List<Entity> e = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());        
		for(Entity entity : e) {
			if(entity instanceof EntityPlayer && canEntityBeSeen(entity)) ((EntityPlayer)entity).setFire(5 + rand.nextInt(7));
		}        

		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.6D;
		}

		if (this.world.isRemote) {
			if (this.rand.nextInt(24) == 0 && !this.isSilent()) {
				this.world.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.AMBIENT, 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
			}

			for (int i = 0; i < 2; ++i) {
				this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}

		super.onLivingUpdate();
	}

	@Override
	protected void updateAITasks() {
		if (this.isWet()) {
			this.attackEntityFrom(DamageSource.DROWN, 1.0F);
		}

		--this.heightOffsetUpdateTime;

		if (this.heightOffsetUpdateTime <= 0) {
			this.heightOffsetUpdateTime = 100;
			this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
		}

		EntityLivingBase entitylivingbase = this.getAttackTarget();

		if (entitylivingbase != null && entitylivingbase.posY + entitylivingbase.getEyeHeight() > this.posY + this.getEyeHeight() + this.heightOffset) {
			this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
			this.isAirBorne = true;
		}

		super.updateAITasks();
	}

	@Override
	public void fall(float distance, float damageMultiplier) { }

	@Override
	public boolean isBurning() {
		return this.isFlying();
	}

	public boolean isFlying() {
		return (((Byte)this.dataManager.get(ON_FIRE)).byteValue() & 1) != 0;
	}

	public void setFlying(boolean b) {
		byte b0 = ((Byte)this.dataManager.get(ON_FIRE)).byteValue();
		if(b) b0 = (byte)(b0 | 1);
		else b0 &= -2;
		this.dataManager.set(ON_FIRE, Byte.valueOf(b0));
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.baseJourneyDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.boilHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return SoundEvents.ENTITY_BLAZE_AMBIENT;
	}

	@Override
	public SoundEvent setHurtSound() {
		return SoundEvents.ENTITY_BLAZE_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return SoundEvents.ENTITY_BLAZE_DEATH;
	}

	@Override
	public Item getItemDropped() {
		return Items.BLAZE_ROD;
	}

	class AIFireballAttack extends EntityAIBase {
		private EntityObserver entity = EntityObserver.this;
		private int x;
		private int y;

		public AIFireballAttack() {
			this.setMutexBits(3);
		}

		@Override
		public boolean shouldExecute() {
			EntityLivingBase entitylivingbase = this.entity.getAttackTarget();
			return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}

		@Override
		public void startExecuting() {
			this.x = 0;
		}

		@Override
		public void resetTask() {
			this.entity.setFlying(false);
		}

		@Override
		public void updateTask() {
			--this.y;
			EntityLivingBase entitylivingbase = this.entity.getAttackTarget();
			double d0 = this.entity.getDistanceSq(entitylivingbase);

			if (d0 < 4.0D) {
				if (this.y <= 0) {
					this.y = 20;
					this.entity.attackEntityAsMob(entitylivingbase);
				}

				this.entity.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
			}
			else if (d0 < 256.0D) {
				double d1 = entitylivingbase.posX - this.entity.posX;
				double d2 = entitylivingbase.getEntityBoundingBox().minY + entitylivingbase.height / 2.0F - (this.entity.posY + this.entity.height / 2.0F);
				double d3 = entitylivingbase.posZ - this.entity.posZ;

				if (this.y <= 0) {
					++this.x;

					if (this.x == 1) {
						this.y = 60;
						this.entity.setFlying(true);
					}
					else if (this.x <= 4) {
						this.y = 6;
					} else {
						this.y = 100;
						this.x = 0;
						this.entity.setFlying(false);
					}

					if (this.x > 1) {
						float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
						this.entity.world.playBroadcastSound(1009, new BlockPos((int)this.entity.posX, (int)this.entity.posY, (int)this.entity.posZ), 0);

						for (int i = 0; i < 1; ++i) {
							EntityMagmaFireball entitysmallfireball = new EntityMagmaFireball(this.entity.world, this.entity, d1 + this.entity.getRNG().nextGaussian() * f, d2, d3 + this.entity.getRNG().nextGaussian() * f);
							entitysmallfireball.posY = this.entity.posY + this.entity.height / 2.0F + 0.5D;
							this.entity.world.spawnEntity(entitysmallfireball);
						}
					}
				}

				this.entity.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
			} else {
				this.entity.getNavigator().clearPath();
				this.entity.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
			}

			super.updateTask();
		}
	}
}