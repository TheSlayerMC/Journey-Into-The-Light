package net.journey.entity.mob.depths;

import java.util.Random;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityShimmererProjectile;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModFlying;

public class EntityLightener extends EntityModFlying {

	public EntityLightener(World par1World) {
		super(par1World);
		this.moveHelper = new EntityLightener.MoveHelper();
		this.tasks.addTask(5, new EntityLightener.AIRandomFly());
		this.tasks.addTask(7, new EntityLightener.AIFireballAttack());
        this.tasks.addTask(7, new EntityLightener.AILookAround());
		this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
		setSize(0.7F, 1.2F);
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.flyingHealth;
	}
	
	@Override
	public void onUpdate() {
        super.onUpdate();
        if(!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL) this.setDead();
    }
	
	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.SHIMMERER;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.SHIMMERER_HURT;
	}

	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.SHIMMERER_DEATH;
	}
	
	@Override
	protected float getSoundVolume() {
        return 10.0F;
    }
	
	@Override
	public boolean getCanSpawnHere() {
        return this.rand.nextInt(15) == 0 && super.getCanSpawnHere() && this.worldObj.getDifficulty() != EnumDifficulty.EASY;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(1) == 0) dropItem(JourneyItems.depthsFlake, 2);
		if(rand.nextInt(1) == 0) dropItem(JourneyItems.scale, 2);
		super.dropFewItems(b, j);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

	@Override
	public Item getItemDropped() {
		return null;
	}

	public void setFire(boolean b) {
		this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b ? 1 : 0)));
	}

	private class AIRandomFly extends EntityAIBase {
		private EntityLightener e = EntityLightener.this;

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
		public boolean continueExecuting() {
			return false;
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
		private EntityLightener e = EntityLightener.this;
		private int height;

		public MoveHelper() {
			super(EntityLightener.this);
		}

		@Override
		public void onUpdateMoveHelper() {
			if(this.update) {
				double d0 = this.posX - this.e.posX;
				double d1 = this.posY - this.e.posY;
				double d2 = this.posZ - this.e.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if(this.height-- <= 0) {
					this.height += this.e.getRNG().nextInt(5) + 2;
					d3 = (double)MathHelper.sqrt_double(d3);
					if(this.canMove(this.posX, this.posY, this.posZ, d3)) {
						this.e.motionX += d0 / d3 * 0.1D;
						this.e.motionY += d1 / d3 * 0.1D;
						this.e.motionZ += d2 / d3 * 0.1D;
					} else {
						this.update = false;
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
				if(!this.e.worldObj.getCollidingBoundingBoxes(this.e, axisalignedbb).isEmpty()) {
					return false;
				}
			}
			return true;
		}
	}

	public class AILookAround extends EntityAIBase {
		private EntityLightener e = EntityLightener.this;

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

				if (entitylivingbase.getDistanceSqToEntity(this.e) < d0 * d0) {
					double d1 = entitylivingbase.posX - this.e.posX;
					double d2 = entitylivingbase.posZ - this.e.posZ;
					this.e.renderYawOffset = this.e.rotationYaw = -((float)Math.atan2(d1, d2)) * 180.0F / (float)Math.PI;
				}
			}
		}
	}

	public class AIFireballAttack extends EntityAIBase {
		private EntityLightener entity = EntityLightener.this;
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
		public void resetTask() {
			this.entity.setFire(false);
		}

		@Override
		public void updateTask() {
			EntityLivingBase entitylivingbase = this.entity.getAttackTarget();
			double d0 = 64.0D;

			if(entitylivingbase.getDistanceSqToEntity(this.entity) < d0 * d0 && this.entity.canEntityBeSeen(entitylivingbase)) {
				World world = this.entity.worldObj;
				counter++;

				if(this.counter == 20) {
					double d1 = 4.0D;
					Vec3 vec3 = this.entity.getLook(1.0F);
					double d2 = entitylivingbase.posX - (this.entity.posX + vec3.xCoord * d1);
					double d3 = entitylivingbase.getEntityBoundingBox().minY + entitylivingbase.height / 2.0F - (0.5D + this.entity.posY + this.entity.height / 2.0F);
					double d4 = entitylivingbase.posZ - (this.entity.posZ + vec3.zCoord * d1);
					world.playAuxSFXAtEntity((EntityPlayer)null, 1008, new BlockPos(this.entity), 0);
					EntityShimmererProjectile projectile = new EntityShimmererProjectile(world, this.entity, d2, d3, d4);
					projectile.posX = this.entity.posX + vec3.xCoord * d1;
					projectile.posY = this.entity.posY + this.entity.height / 2.0F + 0.5D;
					projectile.posZ = this.entity.posZ + vec3.zCoord * d1;
					world.spawnEntityInWorld(projectile);
					this.counter = -40;
				}
			}
			else if(this.counter > 0) counter--;
			this.entity.setFire(this.counter > 10);
		}
	}
}