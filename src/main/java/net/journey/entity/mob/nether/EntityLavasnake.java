/**
 * An altered version of the Guardian Entity
 * Author ~ Ryan Bakody (Dizzlepop12), and Mojang
 */

package net.journey.entity.mob.nether;

import net.journey.JITL;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityFireBall;
import net.journey.init.JAnimations;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.JEntityFlyingMob;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.ActionManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.animation.component.DelayedAction;
import ru.timeconqueror.timecore.animation.entityai.AnimatedRangedAttackAI;
import ru.timeconqueror.timecore.animation.util.StandardDelayPredicates;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.api.animation.BlendType;

import java.util.Random;

public class EntityLavasnake extends JEntityFlyingMob implements IRangedAttackMob, AnimationProvider<EntityLavasnake> {

    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(EntityLavasnake.class, DataSerializers.BOOLEAN);

    private static final DelayedAction<EntityLavasnake, AnimatedRangedAttackAI.ActionData> RANGED_ATTACK_ACTION;

    private static final String LAYER_LIVING = "living";
    private static final String LAYER_ATTACK = "attack";

    private int explosionStrength = 1;

    static {
        RANGED_ATTACK_ACTION = new DelayedAction<EntityLavasnake, AnimatedRangedAttackAI.ActionData>(JITL.rl("lavasnake/shoot"), new AnimationStarter(JAnimations.LAVASNAKE_SHOOT).setSpeed(1.5F), "attack")
                .setDelayPredicate(StandardDelayPredicates.whenPassed(0.5F))
                .setOnCall(AnimatedRangedAttackAI.STANDARD_RUNNER);
    }

    private final ActionManager<EntityLavasnake> actionManager;

    public EntityLavasnake(World par1World) {
        super(par1World);
        this.moveHelper = new EntityLavasnake.LavasnakeMoveHelper(this);
        initEntityAI();
        this.isImmuneToFire = true;
        setSize(1.0F, 1.2F);
        actionManager = ActionManagerBuilder.<EntityLavasnake>create(
                AnimationManagerBuilder.create()
                        .addLayer(LAYER_LIVING, BlendType.ADDING, 1F)
                        .addLayer(LAYER_ATTACK, BlendType.ADDING, 0.9F)
                        .addWalkingAnimationHandling(new AnimationStarter(JAnimations.LAVASNAKE_TAIL).setSpeed(2F), LAYER_LIVING)
        ).build(this, world);
    }

    public static void registerFixesLavasnake(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityLavasnake.class);
    }

    @Override
    public @NotNull ActionManager<EntityLavasnake> getActionManager() {
        return actionManager;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
            this.setDead();
    }

    @Override
    public boolean getCanSpawnHere() {
        return super.rand.nextInt(15) == 0 && super.getCanSpawnHere()
                && this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.LAVA_SNAKE;
	}

    protected void initEntityAI() {
        this.tasks.addTask(5, new EntityLavasnake.AIRandomFly(this));
        this.tasks.addTask(7, new EntityLavasnake.AILookAround(this));
        this.tasks.addTask(5, new AnimatedRangedAttackAI<>(this, RANGED_ATTACK_ACTION, 1.0F, 40, 16.0F));//mutex 3
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }

    @SideOnly(Side.CLIENT)
    public boolean isAttacking() {
        return this.dataManager.get(ATTACKING).booleanValue();
    }

    public void setAttacking(boolean attacking) {
        this.dataManager.set(ATTACKING, Boolean.valueOf(attacking));
    }

    public int getFireballStrength() {
        return this.explosionStrength;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else if (source.getImmediateSource() instanceof EntityLargeFireball
                && source.getTrueSource() instanceof EntityPlayer) {
            super.attackEntityFrom(source, 1000.0F);
            return true;
        } else {
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ATTACKING, Boolean.valueOf(false));
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.HOSTILE;
    }

    @Override
    protected float getSoundVolume() {
        return 10.0F;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 4;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("ExplosionPower", this.explosionStrength);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("ExplosionPower", 99)) {
            this.explosionStrength = compound.getInteger("ExplosionPower");
        }
    }

    @Override
    public float getEyeHeight() {
        return 0.5F;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return JourneySounds.LAVASNAKE_IDLE;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return JourneySounds.LAVASNAKE_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return JourneySounds.LAVASNAKE_HURT;
    }

    @Override
    public double getEntityMaxHealth() {
        return MobStats.LavaSnakeHealth;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        EntityFireBall projectile = new EntityFireBall(this.world, this, 1.0F);
        double dX = target.posX - this.posX;
        double dY = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - projectile.posY;
        double dZ = target.posZ - this.posZ;
        double distortion = MathHelper.sqrt(dX * dX + dZ * dZ);
        projectile.shoot(dX, dY + distortion * 0.20000000298023224D, dZ, 1.6F, (float) (7 - this.world.getDifficulty().getId()));

        world.playEvent(null, 1018, new BlockPos((int) this.posX, (int) this.posY, (int) this.posZ), 0);

        this.world.spawnEntity(projectile);
    }

    @Override
    public void setSwingingArms(boolean b) {

    }

    static class AILookAround extends EntityAIBase {
        private final EntityLavasnake parentEntity;

        public AILookAround(EntityLavasnake ghast) {
            this.parentEntity = ghast;
            this.setMutexBits(2);
        }

        public boolean shouldExecute() {
            return true;
        }

        public void updateTask() {
            if (this.parentEntity.getAttackTarget() == null) {
                this.parentEntity.rotationYaw = -((float) MathHelper.atan2(this.parentEntity.motionX,
                        this.parentEntity.motionZ)) * (180F / (float) Math.PI);
                this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
            } else {
                EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
                double d0 = 64.0D;

                if (entitylivingbase.getDistanceSq(this.parentEntity) < 4096.0D) {
                    double d1 = entitylivingbase.posX - this.parentEntity.posX;
                    double d2 = entitylivingbase.posZ - this.parentEntity.posZ;
                    this.parentEntity.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float) Math.PI);
                    this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
                }
            }
        }
    }

    static class AIRandomFly extends EntityAIBase {
        private final EntityLavasnake parentEntity;

        public AIRandomFly(EntityLavasnake ghast) {
            this.parentEntity = ghast;
            this.setMutexBits(1);
        }

        public boolean shouldExecute() {
            EntityMoveHelper entitymovehelper = this.parentEntity.getMoveHelper();

            if (!entitymovehelper.isUpdating()) {
                return true;
            } else {
                double d0 = entitymovehelper.getX() - this.parentEntity.posX;
                double d1 = entitymovehelper.getY() - this.parentEntity.posY;
                double d2 = entitymovehelper.getZ() - this.parentEntity.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        public boolean shouldContinueExecuting() {
            return false;
        }

        public void startExecuting() {
            Random random = this.parentEntity.getRNG();
            double d0 = this.parentEntity.posX + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.parentEntity.posY + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.parentEntity.posZ + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
        }
    }

    static class LavasnakeMoveHelper extends EntityMoveHelper {
        private final EntityLavasnake parentEntity;
        private int courseChangeCooldown;

        public LavasnakeMoveHelper(EntityLavasnake ghast) {
            super(ghast);
            this.parentEntity = ghast;
        }

        public void onUpdateMoveHelper() {
            if (this.action == EntityMoveHelper.Action.MOVE_TO) {
                double d0 = this.posX - this.parentEntity.posX;
                double d1 = this.posY - this.parentEntity.posY;
                double d2 = this.posZ - this.parentEntity.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.courseChangeCooldown-- <= 0) {
                    this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                    d3 = MathHelper.sqrt(d3);

                    if (this.isNotColliding(this.posX, this.posY, this.posZ, d3)) {
                        this.parentEntity.motionX += d0 / d3 * 0.1D;
                        this.parentEntity.motionY += d1 / d3 * 0.1D;
                        this.parentEntity.motionZ += d2 / d3 * 0.1D;
                    } else {
                        this.action = EntityMoveHelper.Action.WAIT;
                    }
                }
            }
        }

        private boolean isNotColliding(double x, double y, double z, double p_179926_7_) {
            double d0 = (x - this.parentEntity.posX) / p_179926_7_;
            double d1 = (y - this.parentEntity.posY) / p_179926_7_;
            double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
            AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();

            for (int i = 1; (double) i < p_179926_7_; ++i) {
                axisalignedbb = axisalignedbb.offset(d0, d1, d2);

                if (!this.parentEntity.world.getCollisionBoxes(this.parentEntity, axisalignedbb).isEmpty()) {
                    return false;
                }
            }

            return true;
        }
    }

}