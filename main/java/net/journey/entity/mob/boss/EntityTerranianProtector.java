package net.journey.entity.mob.boss;

import java.util.Random;

import net.journey.JourneyAchievements;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityIceBall;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntityMoveHelper.Action;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityTerranianProtector extends EntityEssenceBoss implements IRangedAttackMob {
	
	private static final DataParameter<Byte> ON_FIRE = EntityDataManager.<Byte>createKey(EntityTerranianProtector.class, DataSerializers.BYTE);

	
	public EntityTerranianProtector(World par1World) {
		super(par1World);
		this.moveHelper = new EntityTerranianProtector.MoveHelper();
		this.tasks.addTask(5, new EntityTerranianProtector.AIRandomFly());
		this.tasks.addTask(7, new EntityTerranianProtector.AILookAround());
		this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
		addAttackingAI();
		setSize(2.0F, 10F);
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return MobStats.terranianDamage;
	}
	
	@Override
	public double setKnockbackResistance() {
		return 1.0D;
	}
	
    @Override
	public void attackEntityWithRangedAttack(EntityLivingBase e, float f1)
    {
        this.launchWitherSkullToEntity(0, e);
	}
    
    private void launchWitherSkullToEntity(int var1, EntityLivingBase e) {
        this.launchWitherSkullToCoords(var1, e.posX, e.posY + e.getEyeHeight() * 0.5D, e.posZ, var1 == 0 && this.rand.nextFloat() < 0.001F);
    }
    
    private void launchWitherSkullToCoords(int var1, double f2, double f4, double f6, boolean f8) {
        this.world.playBroadcastSound(1014, new BlockPos(this), 0);
        double d3 = this.coordX(var1);
        double d4 = this.coordY(var1);
        double d5 = this.coordZ(var1);
        double d6 = f2 - d3;
        double d7 = f4 - d4;
        double d8 = f6 - d5;
        EntityIceBall entitydeathskull = new EntityIceBall(this.world);

        entitydeathskull.posY = d4;
        entitydeathskull.posX = d3;
        entitydeathskull.posZ = d5;
        this.world.spawnEntity(entitydeathskull);
	}
    
    private double coordX(int par1) {
        if (par1 <= 0) {  
            return this.posX;
        } else {
            float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float)Math.PI;
            float f1 = MathHelper.cos(f);
            return this.posX + f1 * 1.3D;
        }
    }

    private double coordY(int par1) {
        return par1 <= 0 ? this.posY + 3.0D : this.posY + 2.2D;
    }

    private double coordZ(int par1) {
        if (par1 <= 0) {
            return this.posZ;
        } else {
            float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float)Math.PI;
            float f1 = MathHelper.sin(f);
            return this.posZ + f1 * 1.3D;
        }
    }

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.terranianHealth;
	}

	@Override
	public SoundEvent setLivingSound() {
		return SoundEvents.ENTITY_WITHER_AMBIENT;
	}

	@Override
	public SoundEvent setHurtSound() {
		return SoundEvents.ENTITY_WITHER_HURT;
	}

	@Override
	public SoundEvent setDeathSound() {
		return JourneySounds.BOSS_DEATH;
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return this.rand.nextInt(15) == 0 && super.getCanSpawnHere()
				&& this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(ON_FIRE, Byte.valueOf((byte)0));
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
	public void fall(float distance, float damageMultiplier) {
	}

	/*@Override
	public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
		if (this.isInWater()) {
			this.moveFlying(p_70612_1_, p_70612_2_, 0.02F);
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.800000011920929D;
			this.motionY *= 0.800000011920929D;
			this.motionZ *= 0.800000011920929D;
		} else if (this.isInLava()) {
			this.moveFlying(p_70612_1_, p_70612_2_, 0.02F);
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.5D;
			this.motionY *= 0.5D;
			this.motionZ *= 0.5D;
		} else {
			float f2 = 0.91F;

			if (this.onGround) {
				f2 = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX),
						MathHelper.floor(this.getEntityBoundingBox().minY) - 1,
						MathHelper.floor(this.posZ))).getBlock().slipperiness * 0.91F;
			}

			float f3 = 0.16277136F / (f2 * f2 * f2);
			this.moveFlying(p_70612_1_, p_70612_2_, this.onGround ? 0.1F * f3 : 0.02F);
			f2 = 0.91F;

			if (this.onGround) {
				f2 = this.world.getBlockState(new BlockPos(MathHelper.floor(this.posX),
						MathHelper.floor(this.getEntityBoundingBox().minY) - 1,
						MathHelper.floor(this.posZ))).getBlock().slipperiness * 0.91F;
			}

			this.moveEntity(this.motionX, this.motionY, this.motionZ);
			this.motionX *= f2;
			this.motionY *= f2;
			this.motionZ *= f2;
		}

	}*/

	private class AIRandomFly extends EntityAIBase {
		private EntityTerranianProtector e = EntityTerranianProtector.this;

		public AIRandomFly() {
			this.setMutexBits(1);
		}

		@Override
		public boolean shouldExecute() {
			EntityMoveHelper entitymovehelper = this.e.getMoveHelper();
			if (!entitymovehelper.isUpdating()) {
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
		public boolean shouldContinueExecuting() {
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
		private EntityTerranianProtector e = EntityTerranianProtector.this;
		private int height;

		public MoveHelper() {
			super(EntityTerranianProtector.this);
		}

		@Override
		public void onUpdateMoveHelper() {
			if (this.action == Action.MOVE_TO) {
				double d0 = this.posX - this.e.posX;
				double d1 = this.posY - this.e.posY;
				double d2 = this.posZ - this.e.posZ;
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (this.height-- <= 0) {
					this.height += this.e.getRNG().nextInt(5) + 2;
					d3 = (double) MathHelper.sqrt(d3);
					if (this.canMove(this.posX, this.posY, this.posZ, d3)) {
						this.e.motionX += d0 / d3 * 0.1D;
						this.e.motionY += d1 / d3 * 0.1D;
						this.e.motionZ += d2 / d3 * 0.1D;
					} else {
						this.action = Action.WAIT;
					}
				}
			}
		}

		private boolean canMove(double x, double y, double z, double h) {
			double d4 = (x - this.e.posX) / h;
			double d5 = (y - this.e.posY) / h;
			double d6 = (z - this.e.posZ) / h;
			AxisAlignedBB axisalignedbb = this.e.getEntityBoundingBox();
			for (int i = 1; i < h; ++i) {
				axisalignedbb = axisalignedbb.offset(d4, d5, d6);
				if (!this.e.world.getCollisionBoxes(this.e, axisalignedbb).isEmpty()) {
					return false;
				}
			}
			return true;
		}
	}

	public class AILookAround extends EntityAIBase {
		private EntityTerranianProtector e = EntityTerranianProtector.this;

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
				this.e.renderYawOffset = this.e.rotationYaw = -((float) Math.atan2(this.e.motionX, this.e.motionZ))
						* 180.0F / (float) Math.PI;
			} else {
				EntityLivingBase entitylivingbase = this.e.getAttackTarget();
				double d0 = 64.0D;

				if (entitylivingbase.getDistanceSq(this.e) < d0 * d0) {
					double d1 = entitylivingbase.posX - this.e.posX;
					double d2 = entitylivingbase.posZ - this.e.posZ;
					this.e.renderYawOffset = this.e.rotationYaw = -((float) Math.atan2(d1, d2)) * 180.0F
							/ (float) Math.PI;
				}
			}
		}
	}

	@Override
	public void onDeath(DamageSource damage) {
		/*if(damage.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)damage.getEntity();
			p.triggerAchievement(JourneyAchievements.achievementTerra); {
			}
		}*/
		this.world.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 1)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.trophyTerra.getStateFromMeta(5));
		this.world.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.journeyChest.getStateFromMeta(5));
		TileEntityJourneyChest te = (TileEntityJourneyChest)world.getTileEntity(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))));
		switch(rand.nextInt(2)) {
		case 0:
			te.setInventorySlotContents(2, new ItemStack(JourneyItems.terronicBlade, 1));
			te.setInventorySlotContents(11, new ItemStack(JourneyItems.cloudiaPortalGem, 9));
			break;
		case 1:
			te.setInventorySlotContents(1, new ItemStack(JourneyItems.terralightBlade, 1));
			te.setInventorySlotContents(14, new ItemStack(JourneyItems.cloudiaPortalGem, 12));
			break;
		}
	}
	
	@Override
	public void setSwingingArms(boolean swingingArms) { }
}
