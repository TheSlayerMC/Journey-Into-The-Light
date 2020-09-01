package net.journey.entity.mob.frozen;

import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityShimmererProjectile;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityFlyingMob;

import java.util.Random;

public class EntityCrystalCluster extends JEntityFlyingMob {

    private static final DataParameter<Boolean> IS_FIRE = EntityDataManager.createKey(EntityCrystalCluster.class, DataSerializers.BOOLEAN);


    public EntityCrystalCluster(World par1World) {
        super(par1World);
        this.moveHelper = new EntityCrystalCluster.MoveHelper();
        initEntityAI();
        setSize(0.7F, 1.2F);
    }

    public void initEntityAI() {
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.tasks.addTask(5, new EntityCrystalCluster.AIRandomFly());
        this.tasks.addTask(7, new EntityCrystalCluster.AIFireballAttack());
        this.tasks.addTask(7, new EntityCrystalCluster.AILookAround());
    }

    @Override
    public double getEntityMaxHealth() {
        return MobStats.CrystalClusterHealth;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) this.setDead();
    }

    @Override
    public SoundEvent getAmbientSound() {
        return JourneySounds.SHIMMERER;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return JourneySounds.SHIMMERER_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return JourneySounds.SHIMMERER_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 10.0F;
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
    public ResourceLocation getLootTable() {
        return JourneyLootTables.CRYSTAL_CLUSTER;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(IS_FIRE, Boolean.valueOf(false));
    }

    public void setFire(boolean b) {
        this.dataManager.set(IS_FIRE, Boolean.valueOf(b));
    }

    private class AIRandomFly extends EntityAIBase {
        private final EntityCrystalCluster e = EntityCrystalCluster.this;

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
        private final EntityCrystalCluster e = EntityCrystalCluster.this;
        private int height;

        public MoveHelper() {
            super(EntityCrystalCluster.this);
        }

        @Override
        public void onUpdateMoveHelper() {
            if (this.action == EntityMoveHelper.Action.MOVE_TO) {
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
                        this.action = EntityMoveHelper.Action.WAIT;
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
        private final EntityCrystalCluster e = EntityCrystalCluster.this;

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
                this.e.renderYawOffset = this.e.rotationYaw = -((float) Math.atan2(this.e.motionX, this.e.motionZ)) * 180.0F / (float) Math.PI;
            } else {
                EntityLivingBase entitylivingbase = this.e.getAttackTarget();
                double d0 = 64.0D;

                if (entitylivingbase.getDistanceSq(this.e) < d0 * d0) {
                    double d1 = entitylivingbase.posX - this.e.posX;
                    double d2 = entitylivingbase.posZ - this.e.posZ;
                    this.e.renderYawOffset = this.e.rotationYaw = -((float) Math.atan2(d1, d2)) * 180.0F / (float) Math.PI;
                }
            }
        }
    }

    public class AIFireballAttack extends EntityAIBase {
        public int counter;
        private final EntityCrystalCluster entity = EntityCrystalCluster.this;

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

            if (entitylivingbase.getDistanceSq(this.entity) < d0 * d0 && this.entity.canEntityBeSeen(entitylivingbase)) {
                World world = this.entity.world;
                counter++;

                if (this.counter == 20) {
                    double d1 = 4.0D;
                    Vec3d vec3 = this.entity.getLook(1.0F);
                    double d2 = entitylivingbase.posX - (this.entity.posX + vec3.x * d1);
                    double d3 = entitylivingbase.getEntityBoundingBox().minY + entitylivingbase.height / 2.0F - (0.5D + this.entity.posY + this.entity.height / 2.0F);
                    double d4 = entitylivingbase.posZ - (this.entity.posZ + vec3.z * d1);
                    //world.playAuxSFXAtEntity((EntityPlayer)null, 1008, new BlockPos(this.entity), 0);
                    EntityShimmererProjectile projectile = new EntityShimmererProjectile(world, this.entity, d2, d3, d4);
                    projectile.posX = this.entity.posX + vec3.x * d1;
                    projectile.posY = this.entity.posY + this.entity.height / 2.0F + 0.5D;
                    projectile.posZ = this.entity.posZ + vec3.z * d1;
                    world.spawnEntity(projectile);
                    this.counter = -40;
                }
            } else if (this.counter > 0) counter--;
            this.entity.setFire(this.counter > 10);
        }
    }
}