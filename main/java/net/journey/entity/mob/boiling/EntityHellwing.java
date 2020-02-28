package net.journey.entity.mob.boiling;

import java.util.Random;

import javax.annotation.Nullable;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityHellwing extends EntityModMob {

    protected static final DataParameter<Byte> HELLWING_FLAGS = EntityDataManager.<Byte>createKey(EntityHellwing.class, DataSerializers.BYTE);
    private BlockPos boundOrigin;
    
	public EntityHellwing(World par1World) {
		super(par1World);
        this.isImmuneToFire = true;
        this.moveHelper = new EntityHellwing.AIMoveControl(this);
        this.setSize(0.4F, 0.8F);
        this.experienceValue = 3;
		setSize(0.7F, 1.2F);
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.HellwingHealth;
	}
	
	@Override
    public void move(MoverType type, double x, double y, double z) {
        super.move(type, x, y, z);
        this.doBlockCollisions(); 
    }
	
	@Override
	public void onUpdate() {
        super.onUpdate();
        this.setNoGravity(true);
        this.motionY *= 0.6000000238418579D;
        if(!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) this.setDead();
    }
	
	@Override
    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityHellwing.AIChargeAttack());
        this.tasks.addTask(8, new EntityHellwing.AIMoveRandom());
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityCreature.class}));
        this.targetTasks.addTask(2, new EntityHellwing.AICopyOwnerTarget(this));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
    
	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.EMPTY;
	}

	@Override
	public SoundEvent setHurtSound() {
		return JourneySounds.OVERSEER_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.OVERSEER_DEATH;
	}
	
	@Override
	protected float getSoundVolume() {
        return 10.0F;
    }
	
    public static void registerFixesHellwing(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityHellwing.class);
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("BoundX")) {
            this.boundOrigin = new BlockPos(compound.getInteger("BoundX"), compound.getInteger("BoundY"), compound.getInteger("BoundZ"));
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound); 
           
            compound.setInteger("BoundX", this.boundOrigin.getX());
            compound.setInteger("BoundY", this.boundOrigin.getY());
            compound.setInteger("BoundZ", this.boundOrigin.getZ());
        
    }
    
    @Nullable
    public BlockPos getBoundOrigin()
    {
        return this.boundOrigin;
    }

    public void setBoundOrigin(@Nullable BlockPos boundOriginIn)
    {
        this.boundOrigin = boundOriginIn;
    }

    private boolean getHellwingflag(int mask)
    {
        int i = ((Byte)this.dataManager.get(HELLWING_FLAGS)).byteValue();
        return (i & mask) != 0;
    }

    private void setHellwingFlag(int mask, boolean value)
    {
        int i = ((Byte)this.dataManager.get(HELLWING_FLAGS)).byteValue();

        if (value)
        {
            i = i | mask;
        }
        else
        {
            i = i & ~mask;
        }

        this.dataManager.set(HELLWING_FLAGS, Byte.valueOf((byte)(i & 255)));
    }

    public boolean isCharging()
    {
        return this.getHellwingflag(1);
    }

    public void setCharging(boolean charging)
    {
        this.setHellwingFlag(1, charging);
    }
    
	@Override
	public boolean getCanSpawnHere() {
        return this.rand.nextInt(15) == 0 && super.getCanSpawnHere() && this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(5) == 0) dropItem(JourneyItems.boilingSkull, 1);
		super.dropFewItems(b, j);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
        this.dataManager.register(HELLWING_FLAGS, Byte.valueOf((byte)0));
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
	
	/*public void setFire(boolean b) {
		this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b ? 1 : 0)));
	}*/

	private class AIRandomFly extends EntityAIBase {
		private EntityHellwing e = EntityHellwing.this;

		public AIRandomFly() {
			this.setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			EntityMoveHelper entitymovehelper = this.e.getMoveHelper();
			if(!entitymovehelper.isUpdating()) {
				return true;
			} else {
				double d0 = entitymovehelper.getX() - this.e.posX;
				double d1 = entitymovehelper.getY() - this.e.posY;
				double d2 = entitymovehelper.getZ() - this.e.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0D || d3 > 3600.0D;
			}
		}

		@Override
		public void startExecuting() {
			Random random = this.e.getRNG();
			double d0 = this.e.posX + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			double d1 = this.e.posY + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			double d2 = this.e.posZ + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.e.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
		}
	}

	private class MoveHelper extends EntityMoveHelper {
		private EntityHellwing e = EntityHellwing.this;
		private int height;

		public MoveHelper() {
			super(EntityHellwing.this);
		}

		@Override
		public void onUpdateMoveHelper() {
            if (this.action == EntityMoveHelper.Action.MOVE_TO)
            {
                double d0 = this.posX - EntityHellwing.this.posX;
                double d1 = this.posY - EntityHellwing.this.posY;
                double d2 = this.posZ - EntityHellwing.this.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = (double)MathHelper.sqrt(d3);

                if (d3 < EntityHellwing.this.getEntityBoundingBox().getAverageEdgeLength())
                {
                    this.action = EntityMoveHelper.Action.WAIT;
                    EntityHellwing.this.motionX *= 0.5D;
                    EntityHellwing.this.motionY *= 0.5D;
                    EntityHellwing.this.motionZ *= 0.5D;
                }
                else
                {
                    EntityHellwing.this.motionX += d0 / d3 * 0.05D * this.speed;
                    EntityHellwing.this.motionY += d1 / d3 * 0.05D * this.speed;
                    EntityHellwing.this.motionZ += d2 / d3 * 0.05D * this.speed;

                    if (EntityHellwing.this.getAttackTarget() == null)
                    {
                        EntityHellwing.this.rotationYaw = -((float)MathHelper.atan2(EntityHellwing.this.motionX, EntityHellwing.this.motionZ)) * (180F / (float)Math.PI);
                        EntityHellwing.this.renderYawOffset = EntityHellwing.this.rotationYaw;
                    }
                    else
                    {
                        double d4 = EntityHellwing.this.getAttackTarget().posX - EntityHellwing.this.posX;
                        double d5 = EntityHellwing.this.getAttackTarget().posZ - EntityHellwing.this.posZ;
                        EntityHellwing.this.rotationYaw = -((float)MathHelper.atan2(d4, d5)) * (180F / (float)Math.PI);
                        EntityHellwing.this.renderYawOffset = EntityHellwing.this.rotationYaw;
                    }
                }
            }
        }

		private boolean canMove(double x, double y, double z, double h)  {
			double d4 = (x - this.e.posX) / h;
			double d5 = (y - this.e.posY) / h;
			double d6 = (z - this.e.posZ) / h;
			AxisAlignedBB axisalignedbb = this.e.getEntityBoundingBox();
			for(int i = 1; i < h; ++i) {
				axisalignedbb = axisalignedbb.offset(d4, d5, d6);
				if(!this.e.world.getCollisionBoxes(this.e, axisalignedbb).isEmpty()) {
					return false;
				}
			}
			return true;
		}
	}

	public class AILookAround extends EntityAIBase {
		private EntityHellwing e = EntityHellwing.this;

		public AILookAround() {
			this.setMutexBits(2);
		}

		@Override
		public boolean shouldExecute() {
			return true;
		}

		@Override
		public void updateTask() {
			if (this.e.getAttackTarget() == null) {
				this.e.renderYawOffset = this.e.rotationYaw = -((float)Math.atan2(this.e.motionX, this.e.motionZ)) * 180.0F / (float)Math.PI;
			} else {
				EntityLivingBase entitylivingbase = this.e.getAttackTarget();
				double d0 = 64.0D;

				if (entitylivingbase.getDistanceSq(this.e) < d0 * d0) {
					double d1 = entitylivingbase.posX - this.e.posX;
					double d2 = entitylivingbase.posZ - this.e.posZ;
					this.e.renderYawOffset = this.e.rotationYaw = -((float)Math.atan2(d1, d2)) * 180.0F / (float)Math.PI;
				}
			}
		}
	}

    class AIChargeAttack extends EntityAIBase
    {
        public AIChargeAttack()
        {
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute()
        {
            if (EntityHellwing.this.getAttackTarget() != null && !EntityHellwing.this.getMoveHelper().isUpdating() && EntityHellwing.this.rand.nextInt(7) == 0)
            {
                return EntityHellwing.this.getDistanceSq(EntityHellwing.this.getAttackTarget()) > 4.0D;
            }
            else
            {
                return false;
            }
        }

        @Override
        public boolean shouldContinueExecuting()
        {
            return EntityHellwing.this.getMoveHelper().isUpdating() && EntityHellwing.this.isCharging() && EntityHellwing.this.getAttackTarget() != null && EntityHellwing.this.getAttackTarget().isEntityAlive();
        }

        @Override
        public void startExecuting()
        {
            EntityLivingBase entitylivingbase = EntityHellwing.this.getAttackTarget();
            Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
            EntityHellwing.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
            EntityHellwing.this.setCharging(true);
        }

        @Override
        public void resetTask()
        {
            EntityHellwing.this.setCharging(false);
        }

        @Override
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = EntityHellwing.this.getAttackTarget();

            if (EntityHellwing.this.getEntityBoundingBox().intersects(entitylivingbase.getEntityBoundingBox()))
            {
                EntityHellwing.this.attackEntityAsMob(entitylivingbase);
                EntityHellwing.this.setCharging(false);
            }
            else
            {
                double d0 = EntityHellwing.this.getDistanceSq(entitylivingbase);

                if (d0 < 9.0D)
                {
                    Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
                    EntityHellwing.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
                }
            }
        }
    }

    class AICopyOwnerTarget extends EntityAITarget
    {
        public AICopyOwnerTarget(EntityCreature creature)
        {
            super(creature, false);
        }

        @Override
        public boolean shouldExecute()
        {
            return EntityHellwing.this.getAttackTarget() != null && this.isSuitableTarget(EntityHellwing.this.getAttackTarget(), false);
        }

        @Override
        public void startExecuting()
        {
            super.startExecuting();
        }
    }

    class AIMoveControl extends EntityMoveHelper
    {
        public AIMoveControl(EntityHellwing vex)
        {
            super(vex);
        }

        @Override
        public void onUpdateMoveHelper()
        {
            if (this.action == EntityMoveHelper.Action.MOVE_TO)
            {
                double d0 = this.posX - EntityHellwing.this.posX;
                double d1 = this.posY - EntityHellwing.this.posY;
                double d2 = this.posZ - EntityHellwing.this.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = (double)MathHelper.sqrt(d3);

                if (d3 < EntityHellwing.this.getEntityBoundingBox().getAverageEdgeLength())
                {
                    this.action = EntityMoveHelper.Action.WAIT;
                    EntityHellwing.this.motionX *= 0.5D;
                    EntityHellwing.this.motionY *= 0.5D;
                    EntityHellwing.this.motionZ *= 0.5D;
                }
                else
                {
                    EntityHellwing.this.motionX += d0 / d3 * 0.05D * this.speed;
                    EntityHellwing.this.motionY += d1 / d3 * 0.05D * this.speed;
                    EntityHellwing.this.motionZ += d2 / d3 * 0.05D * this.speed;

                    if (EntityHellwing.this.getAttackTarget() == null)
                    {
                        EntityHellwing.this.rotationYaw = -((float)MathHelper.atan2(EntityHellwing.this.motionX, EntityHellwing.this.motionZ)) * (180F / (float)Math.PI);
                        EntityHellwing.this.renderYawOffset = EntityHellwing.this.rotationYaw;
                    }
                    else
                    {
                        double d4 = EntityHellwing.this.getAttackTarget().posX - EntityHellwing.this.posX;
                        double d5 = EntityHellwing.this.getAttackTarget().posZ - EntityHellwing.this.posZ;
                        EntityHellwing.this.rotationYaw = -((float)MathHelper.atan2(d4, d5)) * (180F / (float)Math.PI);
                        EntityHellwing.this.renderYawOffset = EntityHellwing.this.rotationYaw;
                    }
                }
            }
        }
    }

    class AIMoveRandom extends EntityAIBase
    {
        public AIMoveRandom()
        {
            this.setMutexBits(1);
        }

        @Override
        public boolean shouldExecute()
        {
            return !EntityHellwing.this.getMoveHelper().isUpdating() && EntityHellwing.this.rand.nextInt(7) == 0;
        }

        @Override
        public boolean shouldContinueExecuting()
        {
            return false;
        }

        @Override
        public void updateTask()
        {
            BlockPos blockpos = EntityHellwing.this.getBoundOrigin();

            if (blockpos == null)
            {
                blockpos = new BlockPos(EntityHellwing.this);
            }

            for (int i = 0; i < 3; ++i)
            {
                BlockPos blockpos1 = blockpos.add(EntityHellwing.this.rand.nextInt(15) - 7, EntityHellwing.this.rand.nextInt(11) - 5, EntityHellwing.this.rand.nextInt(15) - 7);

                if (EntityHellwing.this.world.isAirBlock(blockpos1))
                {
                    EntityHellwing.this.moveHelper.setMoveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25D);

                    if (EntityHellwing.this.getAttackTarget() == null)
                    {
                        EntityHellwing.this.getLookHelper().setLookPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                    }

                    break;
                }
            }
        }
    }

	public class AIFireballAttack extends EntityAIBase {
		private EntityHellwing entity = EntityHellwing.this;
		public int counter;

		@Override
		public boolean shouldExecute() {
			return this.entity.getAttackTarget() != null;
		}

		@Override
		public void startExecuting() {
			this.counter = 0;
		}

		@Override
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = EntityHellwing.this.getAttackTarget();

            if (EntityHellwing.this.getEntityBoundingBox().intersects(entitylivingbase.getEntityBoundingBox()))
            {
                EntityHellwing.this.attackEntityAsMob(entitylivingbase);
                EntityHellwing.this.setCharging(false);
            }
            else
            {
                double d0 = EntityHellwing.this.getDistanceSq(entitylivingbase);

                if (d0 < 9.0D)
                {
                    Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
                    EntityHellwing.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
                }
            }
        }
    }

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.HellwingDamage;
	}
}