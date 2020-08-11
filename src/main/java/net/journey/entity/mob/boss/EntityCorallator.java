package net.journey.entity.mob.boss;

import net.journey.entity.MobStats;
import net.journey.entity.mob.euca.EntityShimmerer;
import net.journey.entity.projectile.EntityMagmaFireball;
import net.journey.entity.util.EntityBossCrystal;
import net.journey.init.JourneyLootTables;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class EntityCorallator extends EntityEssenceBoss implements IRangedAttackMob {
    private static final DataParameter<Byte> ON_FIRE = EntityDataManager.createKey(EntityCorallator.class, DataSerializers.BYTE);
    private int spawnTimer;

    public EntityCorallator(World par1World) {
        super(par1World);
        this.moveHelper = new EntityCorallator.MoveHelper();
        this.tasks.addTask(5, new EntityCorallator.AIRandomFly());
        this.tasks.addTask(7, new EntityCorallator.AILookAround());
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        //this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
        addMeleeAttackingAI();
        setSize(3.0F, 4.0F);
        spawnTimer = 0;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WITHER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return SoundEvents.ENTITY_WITHER_HURT;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.rand.nextInt(15) == 0 && super.getCanSpawnHere()
                && this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
    }

    @Override
    protected @Nullable ResourceLocation getLootTable() {
        return JourneyLootTables.CORALLATOR;
    }

    @Override
    protected @Nullable EntityBossCrystal.Type getDeathCrystalType() {
        return EntityBossCrystal.Type.EUCA;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ON_FIRE, Byte.valueOf((byte) 0));
    }

    public boolean isFlying() {
        return (this.dataManager.get(ON_FIRE).byteValue() & 1) != 0;
    }

    public void setFlying(boolean b) {
        byte b0 = this.dataManager.get(ON_FIRE).byteValue();
        if (b) b0 = (byte) (b0 | 1);
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

    @Override
    public void onLivingUpdate() {

        if (!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }

        if (this.world.isRemote) {
            if (this.rand.nextInt(24) == 0 && !this.isSilent()) {
                this.world.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.AMBIENT, 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
            }

            for (int i = 0; i < 2; ++i) {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D);
            }
        }

        if (getHealth() <= 250) {
            if (spawnTimer == 0 && !world.isRemote) {
                EntityShimmerer z = new EntityShimmerer(world);
                z.setLocationAndAngles(posX + 3, posY, posZ, this.rand.nextFloat() * 360.0F, 0.0F);
                EntityShimmerer z1 = new EntityShimmerer(world);
                z1.setLocationAndAngles(posX - 3, posY, posZ, this.rand.nextFloat() * 360.0F, 0.0F);
                EntityShimmerer z2 = new EntityShimmerer(world);
                z2.setLocationAndAngles(posX, posY, posZ + 3, this.rand.nextFloat() * 360.0F, 0.0F);
                EntityShimmerer z3 = new EntityShimmerer(world);
                z3.setLocationAndAngles(posX, posY, posZ - 3, this.rand.nextFloat() * 360.0F, 0.0F);
                this.world.spawnEntity(z);
                this.world.spawnEntity(z1);
                this.world.spawnEntity(z2);
                this.world.spawnEntity(z3);
                spawnTimer = 200;
            }
            spawnTimer--;
        }
        super.onLivingUpdate();
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase e, float f1) {
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
        EntityMagmaFireball entitydeathskull = new EntityMagmaFireball(this.world, this, d6, d7, d8);
        entitydeathskull.posY = d4;
        entitydeathskull.posX = d3;
        entitydeathskull.posZ = d5;
        this.world.spawnEntity(entitydeathskull);
    }

    private double coordX(int par1) {
        if (par1 <= 0) {
            return this.posX;
        } else {
            float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float) Math.PI;
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
            float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float) Math.PI;
            float f1 = MathHelper.sin(f);
            return this.posZ + f1 * 1.3D;
        }
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.CORALLATOR;
    }

    private class AIRandomFly extends EntityAIBase {
        private final EntityCorallator e = EntityCorallator.this;

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
        private final EntityCorallator e = EntityCorallator.this;
        private int height;

        public MoveHelper() {
            super(EntityCorallator.this);
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
                    d3 = MathHelper.sqrt(d3);
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
        private final EntityCorallator e = EntityCorallator.this;

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
}