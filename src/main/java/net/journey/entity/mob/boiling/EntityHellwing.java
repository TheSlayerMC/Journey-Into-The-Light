package net.journey.entity.mob.boiling;

import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.entity.projectile.EntityMagmaFireball;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityHellwing extends JEntityMob {
	protected static final DataParameter<Byte> HELLWING_FLAGS = EntityDataManager.createKey(EntityHellwing.class,
			DataSerializers.BYTE);
	private EntityLiving owner;
	@Nullable
	private BlockPos boundOrigin;
	private boolean limitedLifespan;
	private int limitedLifeTicks;

	public EntityHellwing(World worldIn) {
		super(worldIn);
		this.isImmuneToFire = true;
		this.moveHelper = new EntityHellwing.AIMoveControl(this);
		this.setSize(1.0F, 1.3F);
		this.experienceValue = 3;
		this.setKnowledge(EnumKnowledgeType.BOIL, 5);
	}

	@Override
	public void move(MoverType type, double x, double y, double z) {
		super.move(type, x, y, z);
		this.doBlockCollisions();
	}

	@Override
	public void onUpdate() {
		this.noClip = true;
		super.onUpdate();
		this.noClip = false;
		this.setNoGravity(true);

		if (this.limitedLifespan && --this.limitedLifeTicks <= 0) {
			this.limitedLifeTicks = 20;
			this.attackEntityFrom(DamageSource.STARVE, 1.0F);
		}
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityHellwing.AIFireballAttack());
		this.tasks.addTask(2, new EntityHellwing.AIChargeAttack());
		this.tasks.addTask(8, new EntityHellwing.AIMoveRandom());
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityHellwing.class));
		this.targetTasks.addTask(2, new EntityHellwing.AICopyOwnerTarget(this));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		EntityAttributesHelper.setMaxHealth(this, MobStats.HELLWING_HEALTH);
		EntityAttributesHelper.setAttackDamage(this, MobStats.HELLWING_DAMAGE);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return JourneySounds.CAVE_MOB;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource d) {
		return JourneySounds.CAVE_MOB;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return JourneySounds.CAVE_MOB;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(HELLWING_FLAGS, Byte.valueOf((byte) 0));
	}

	public static void registerFixesHellwing(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, EntityHellwing.class);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);

		if (compound.hasKey("BoundX")) {
			this.boundOrigin = new BlockPos(compound.getInteger("BoundX"), compound.getInteger("BoundY"),
					compound.getInteger("BoundZ"));
		}

		if (compound.hasKey("LifeTicks")) {
			this.setLimitedLife(compound.getInteger("LifeTicks"));
		}
	}


	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);

		if (this.boundOrigin != null) {
			compound.setInteger("BoundX", this.boundOrigin.getX());
			compound.setInteger("BoundY", this.boundOrigin.getY());
			compound.setInteger("BoundZ", this.boundOrigin.getZ());
		}

		if (this.limitedLifespan) {
			compound.setInteger("LifeTicks", this.limitedLifeTicks);
		}
	}

	public EntityLiving getOwner() {
		return this.owner;
	}

	@Nullable
	public BlockPos getBoundOrigin() {
		return this.boundOrigin;
	}

	public void setBoundOrigin(@Nullable BlockPos boundOriginIn) {
		this.boundOrigin = boundOriginIn;
	}

	private boolean getHellwingFlag(int mask) {
		int i = this.dataManager.get(HELLWING_FLAGS).byteValue();
		return (i & mask) != 0;
	}

	private void setHellwingFlag(int mask, boolean value) {
		int i = this.dataManager.get(HELLWING_FLAGS).byteValue();

		if (value) {
			i = i | mask;
		} else {
			i = i & ~mask;
		}

		this.dataManager.set(HELLWING_FLAGS, Byte.valueOf((byte) (i & 255)));
	}

	public boolean isCharging() {
		return this.getHellwingFlag(1);
	}

	public void setCharging(boolean charging) {
		this.setHellwingFlag(1, charging);
	}

	public void setOwner(EntityLiving ownerIn) {
		this.owner = ownerIn;
	}

	public void setLimitedLife(int limitedLifeTicksIn) {
		this.limitedLifespan = true;
		this.limitedLifeTicks = limitedLifeTicksIn;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getBrightnessForRender() {
		return 15728880;
	}

	@Override
	public float getBrightness() {
		return 1.0F;
	}

	@Nullable
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		this.setEquipmentBasedOnDifficulty(difficulty);
		this.setEnchantmentBasedOnDifficulty(difficulty);
		return super.onInitialSpawn(difficulty, livingdata);
	}

	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
		this.setDropChance(EntityEquipmentSlot.MAINHAND, 0.0F);
	}

	class AIChargeAttack extends EntityAIBase {
		public AIChargeAttack() {
			this.setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			if (EntityHellwing.this.getAttackTarget() != null && !EntityHellwing.this.getMoveHelper().isUpdating()
					&& EntityHellwing.this.rand.nextInt(7) == 0) {
				return EntityHellwing.this.getDistanceSq(EntityHellwing.this.getAttackTarget()) > 4.0D;
			} else {
				return false;
			}
		}

		@Override
		public boolean shouldContinueExecuting() {
			return EntityHellwing.this.getMoveHelper().isUpdating() && EntityHellwing.this.isCharging()
					&& EntityHellwing.this.getAttackTarget() != null
					&& EntityHellwing.this.getAttackTarget().isEntityAlive();
		}

		@Override
		public void startExecuting() {
			EntityLivingBase entitylivingbase = EntityHellwing.this.getAttackTarget();
			Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
			EntityHellwing.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
			EntityHellwing.this.setCharging(true);
			EntityHellwing.this.playSound(SoundEvents.ENTITY_VEX_CHARGE, 1.0F, 1.0F);
		}

		@Override
		public void resetTask() {
			EntityHellwing.this.setCharging(false);
		}

		@Override
		public void updateTask() {
			EntityLivingBase entitylivingbase = EntityHellwing.this.getAttackTarget();

			if (EntityHellwing.this.getEntityBoundingBox().intersects(entitylivingbase.getEntityBoundingBox())) {
				EntityHellwing.this.attackEntityAsMob(entitylivingbase);
				EntityHellwing.this.setCharging(false);
			} else {
				double d0 = EntityHellwing.this.getDistanceSq(entitylivingbase);

				if (d0 < 9.0D) {
					Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
					EntityHellwing.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
				}
			}
		}
	}

	class AICopyOwnerTarget extends EntityAITarget {
		public AICopyOwnerTarget(EntityCreature creature) {
			super(creature, false);
		}

		@Override
		public boolean shouldExecute() {
			return EntityHellwing.this.owner != null && EntityHellwing.this.owner.getAttackTarget() != null
					&& this.isSuitableTarget(EntityHellwing.this.owner.getAttackTarget(), false);
		}

		@Override
		public void startExecuting() {
			EntityHellwing.this.setAttackTarget(EntityHellwing.this.owner.getAttackTarget());
			super.startExecuting();
		}
	}

	class AIMoveControl extends EntityMoveHelper {
		public AIMoveControl(EntityHellwing vex) {
			super(vex);
		}

		@Override
		public void onUpdateMoveHelper() {
			if (this.action == EntityMoveHelper.Action.MOVE_TO) {
				double d0 = this.posX - EntityHellwing.this.posX;
				double d1 = this.posY - EntityHellwing.this.posY;
				double d2 = this.posZ - EntityHellwing.this.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				d3 = MathHelper.sqrt(d3);

				if (d3 < EntityHellwing.this.getEntityBoundingBox().getAverageEdgeLength()) {
					this.action = EntityMoveHelper.Action.WAIT;
					EntityHellwing.this.motionX *= 0.5D;
					EntityHellwing.this.motionY *= 0.5D;
					EntityHellwing.this.motionZ *= 0.5D;
				} else {
					EntityHellwing.this.motionX += d0 / d3 * 0.05D * this.speed;
					EntityHellwing.this.motionY += d1 / d3 * 0.05D * this.speed;
					EntityHellwing.this.motionZ += d2 / d3 * 0.05D * this.speed;

					if (EntityHellwing.this.getAttackTarget() == null) {
						EntityHellwing.this.rotationYaw = -((float) MathHelper.atan2(EntityHellwing.this.motionX,
								EntityHellwing.this.motionZ)) * (180F / (float) Math.PI);
						EntityHellwing.this.renderYawOffset = EntityHellwing.this.rotationYaw;
					} else {
						double d4 = EntityHellwing.this.getAttackTarget().posX - EntityHellwing.this.posX;
						double d5 = EntityHellwing.this.getAttackTarget().posZ - EntityHellwing.this.posZ;
						EntityHellwing.this.rotationYaw = -((float) MathHelper.atan2(d4, d5))
								* (180F / (float) Math.PI);
						EntityHellwing.this.renderYawOffset = EntityHellwing.this.rotationYaw;
					}
				}
			}
		}
	}

	class AIMoveRandom extends EntityAIBase {
		public AIMoveRandom() {
			this.setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			return !EntityHellwing.this.getMoveHelper().isUpdating() && EntityHellwing.this.rand.nextInt(7) == 0;
		}

		@Override
		public boolean shouldContinueExecuting() {
			return false;
		}

		@Override
		public void updateTask() {
			BlockPos blockpos = EntityHellwing.this.getBoundOrigin();

			if (blockpos == null) {
				blockpos = new BlockPos(EntityHellwing.this);
			}

			for (int i = 0; i < 3; ++i) {
				BlockPos blockpos1 = blockpos.add(EntityHellwing.this.rand.nextInt(15) - 7,
						EntityHellwing.this.rand.nextInt(11) - 5, EntityHellwing.this.rand.nextInt(15) - 7);

				if (EntityHellwing.this.world.isAirBlock(blockpos1)) {
					EntityHellwing.this.moveHelper.setMoveTo((double) blockpos1.getX() + 0.5D,
							(double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 0.25D);

					if (EntityHellwing.this.getAttackTarget() == null) {
						EntityHellwing.this.getLookHelper().setLookPosition((double) blockpos1.getX() + 0.5D,
								(double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
					}

					break;
				}
			}
		}
	}

	class AIFireballAttack extends EntityAIBase {
		private static final String __OBFID = "CL_00002225";
		private final EntityHellwing field_179469_a = EntityHellwing.this;
		private int field_179467_b;
		private int field_179468_c;

		public AIFireballAttack() {
			this.setMutexBits(3);
		}

		@Override
		public boolean shouldExecute() {
			EntityLivingBase entitylivingbase = this.field_179469_a.getAttackTarget();
			return entitylivingbase != null && entitylivingbase.isEntityAlive();
		}

		@Override
		public void startExecuting() {
			this.field_179467_b = 0;
		}

		@Override
		public void updateTask() {
			--this.field_179468_c;
			EntityLivingBase entitylivingbase = this.field_179469_a.getAttackTarget();
			double d0 = this.field_179469_a.getDistanceSq(entitylivingbase);

			if (d0 < 4.0D) {
				if (this.field_179468_c <= 0) {
					this.field_179468_c = 20;
					this.field_179469_a.attackEntityAsMob(entitylivingbase);
				}

				this.field_179469_a.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
			} else if (d0 < 256.0D) {
				double d1 = entitylivingbase.posX - this.field_179469_a.posX;
				double d2 = entitylivingbase.getEntityBoundingBox().minY + entitylivingbase.height / 2.0F - (this.field_179469_a.posY + this.field_179469_a.height / 2.0F);
				double d3 = entitylivingbase.posZ - this.field_179469_a.posZ;

				if (this.field_179468_c <= 0) {
					++this.field_179467_b;

					if (this.field_179467_b == 1) {
						this.field_179468_c = 60;
					} else if (this.field_179467_b <= 4) {
						this.field_179468_c = 6;
					} else {
						this.field_179468_c = 100;
						this.field_179467_b = 0;
					}

					if (this.field_179467_b > 1) {
						float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
						this.field_179469_a.world.playEvent(null, 1018, new BlockPos((int) this.field_179469_a.posX, (int) this.field_179469_a.posY, (int) this.field_179469_a.posZ), 0);

						for (int i = 0; i < 1; ++i) {
							EntityMagmaFireball entitysmallfireball = new EntityMagmaFireball(this.field_179469_a.world, this.field_179469_a, d1 + this.field_179469_a.getRNG().nextGaussian() * f, d2, d3 + this.field_179469_a.getRNG().nextGaussian() * f);
							entitysmallfireball.posY = this.field_179469_a.posY + this.field_179469_a.height / 2.0F + 0.5D;
							this.field_179469_a.world.spawnEntity(entitysmallfireball);
						}
					}
				}

				this.field_179469_a.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
			} else {
				this.field_179469_a.getNavigator().clearPath();
				this.field_179469_a.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
			}

			super.updateTask();
		}
	}

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.HELLWING;
	}
}