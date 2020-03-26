/**
 * An altered version of the Guardian Entity
 * Author ~ Ryan Bakody (Dizzlepop12), and Mojang
 */

package net.journey.entity.mob.depths;

import java.util.Random;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityMagmaFireball;
import net.journey.entity.projectile.EntityShimmererProjectile;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.EntityModFlying;

public class EntityLightener extends EntityModFlying {

	private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(EntityLightener.class, DataSerializers.BOOLEAN);
    private int explosionStrength = 1;
    
	public EntityLightener(World par1World) {
		super(par1World);
        this.moveHelper = new EntityLightener.LavasnakeMoveHelper(this);
        initEntityAI();
		this.isImmuneToFire = true;
		setSize(0.7F, 1.2F);
	}

	@Override
	protected void dropFewItems(boolean b, int j) {
		if(rand.nextInt(1) == 0) dropItem(JourneyItems.depthsFlake, rand.nextInt(2));
		if(rand.nextInt(1) == 0) dropItem(JourneyItems.scale, rand.nextInt(2));
		super.dropFewItems(b, j);
	}

	@Override
	public void onUpdate() {
        super.onUpdate();
        if(!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL) this.setDead();
    }

	@Override
	public boolean getCanSpawnHere() {
		return super.rand.nextInt(15) == 0 && super.getCanSpawnHere() && this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
	}		

	@Override
	public Item getItemDropped() {
		return JourneyItems.darkCrystal;
	}

    protected void initEntityAI()
    {
		this.tasks.addTask(5, new EntityLightener.AIRandomFly(this));
		this.tasks.addTask(7, new EntityLightener.AIFireballAttack(this));
		this.tasks.addTask(7, new EntityLightener.AILookAround(this));
		this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
    }

    @SideOnly(Side.CLIENT)
    public boolean isAttacking()
    {
        return ((Boolean)this.dataManager.get(ATTACKING)).booleanValue();
    }

    public void setAttacking(boolean attacking)
    {
        this.dataManager.set(ATTACKING, Boolean.valueOf(attacking));
    }

    public int getFireballStrength()
    {
        return this.explosionStrength;
    }
    
	@Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (source.getImmediateSource() instanceof EntityLargeFireball && source.getTrueSource() instanceof EntityPlayer)
        {
            super.attackEntityFrom(source, 1000.0F);
            return true;
        }
        else
        {
            return super.attackEntityFrom(source, amount);
        }
    }

	@Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(ATTACKING, Boolean.valueOf(false));
    }

	@Override
    public SoundCategory getSoundCategory()
    {
        return SoundCategory.HOSTILE;
    }

	@Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_GUARDIAN_AMBIENT;
    }

	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_GUARDIAN_HURT;
    }
	
	@Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_GUARDIAN_DEATH;
    }

	@Override
    protected float getSoundVolume()
    {
        return 10.0F;
    }

	@Override
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }

    public static void registerFixesLavasnake(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityLightener.class);
    }

	@Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("ExplosionPower", this.explosionStrength);
    }

	@Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("ExplosionPower", 99))
        {
            this.explosionStrength = compound.getInteger("ExplosionPower");
        }
    }

	@Override
    public float getEyeHeight()
    {
        return 2.6F;
    }

    static class AIFireballAttack extends EntityAIBase
        {
            private final EntityLightener parentEntity;
            public int attackTimer;

            public AIFireballAttack(EntityLightener ghast)
            {
                this.parentEntity = ghast;
            }

            public boolean shouldExecute()
            {
                return this.parentEntity.getAttackTarget() != null;
            }

            public void startExecuting()
            {
                this.attackTimer = 0;
            }

            public void resetTask()
            {
                this.parentEntity.setAttacking(false);
            }

            public void updateTask()
            {
                EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
                double d0 = 64.0D;

                if (entitylivingbase.getDistanceSq(this.parentEntity) < 4096.0D && this.parentEntity.canEntityBeSeen(entitylivingbase))
                {
                    World world = this.parentEntity.world;
                    ++this.attackTimer;

                    if (this.attackTimer == 2)
                    {
                        world.playEvent((EntityPlayer)null, 1015, new BlockPos(this.parentEntity), 0);
                    }

                    if (this.attackTimer == 1)
                    {
                        double d1 = 4.0D;
                        Vec3d vec3d = this.parentEntity.getLook(1.0F);
                        double d2 = entitylivingbase.posX - (this.parentEntity.posX + vec3d.x * 4.0D);
                        double d3 = entitylivingbase.getEntityBoundingBox().minY + (double)(entitylivingbase.height / 2.0F) - (0.5D + this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F));
                        double d4 = entitylivingbase.posZ - (this.parentEntity.posZ + vec3d.z * 4.0D);
                        world.playEvent((EntityPlayer)null, 1016, new BlockPos(this.parentEntity), 0);
                        EntityMagmaFireball entitylargefireball = new EntityMagmaFireball(world, this.parentEntity, d2, d3, d4);
                        entitylargefireball.posX = this.parentEntity.posX + vec3d.x * 4.0D;
                        entitylargefireball.posY = this.parentEntity.posY + (double)(this.parentEntity.height / 2.0F) + 0.5D;
                        entitylargefireball.posZ = this.parentEntity.posZ + vec3d.z * 4.0D;
                        world.spawnEntity(entitylargefireball);
                        this.attackTimer = -40;
                    }
                }
                else if (this.attackTimer > 0)
                {
                    --this.attackTimer;
                }

                this.parentEntity.setAttacking(this.attackTimer > 1);
            }
        }

    static class AILookAround extends EntityAIBase
        {
            private final EntityLightener parentEntity;

            public AILookAround(EntityLightener ghast)
            {
                this.parentEntity = ghast;
                this.setMutexBits(2);
            }

            public boolean shouldExecute()
            {
                return true;
            }

            public void updateTask()
            {
                if (this.parentEntity.getAttackTarget() == null)
                {
                    this.parentEntity.rotationYaw = -((float)MathHelper.atan2(this.parentEntity.motionX, this.parentEntity.motionZ)) * (180F / (float)Math.PI);
                    this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
                }
                else
                {
                    EntityLivingBase entitylivingbase = this.parentEntity.getAttackTarget();
                    double d0 = 64.0D;

                    if (entitylivingbase.getDistanceSq(this.parentEntity) < 4096.0D)
                    {
                        double d1 = entitylivingbase.posX - this.parentEntity.posX;
                        double d2 = entitylivingbase.posZ - this.parentEntity.posZ;
                        this.parentEntity.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                        this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
                    }
                }
            }
        }

    static class AIRandomFly extends EntityAIBase
        {
            private final EntityLightener parentEntity;

            public AIRandomFly(EntityLightener ghast)
            {
                this.parentEntity = ghast;
                this.setMutexBits(1);
            }

            public boolean shouldExecute()
            {
                EntityMoveHelper entitymovehelper = this.parentEntity.getMoveHelper();

                if (!entitymovehelper.isUpdating())
                {
                    return true;
                }
                else
                {
                    double d0 = entitymovehelper.getX() - this.parentEntity.posX;
                    double d1 = entitymovehelper.getY() - this.parentEntity.posY;
                    double d2 = entitymovehelper.getZ() - this.parentEntity.posZ;
                    double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                    return d3 < 1.0D || d3 > 3600.0D;
                }
            }

            public boolean shouldContinueExecuting()
            {
                return false;
            }

            public void startExecuting()
            {
                Random random = this.parentEntity.getRNG();
                double d0 = this.parentEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
                double d1 = this.parentEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
                double d2 = this.parentEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
                this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
            }
        }

    static class LavasnakeMoveHelper extends EntityMoveHelper
        {
            private final EntityLightener parentEntity;
            private int courseChangeCooldown;

            public LavasnakeMoveHelper(EntityLightener ghast)
            {
                super(ghast);
                this.parentEntity = ghast;
            }

            public void onUpdateMoveHelper()
            {
                if (this.action == EntityMoveHelper.Action.MOVE_TO)
                {
                    double d0 = this.posX - this.parentEntity.posX;
                    double d1 = this.posY - this.parentEntity.posY;
                    double d2 = this.posZ - this.parentEntity.posZ;
                    double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                    if (this.courseChangeCooldown-- <= 0)
                    {
                        this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                        d3 = (double)MathHelper.sqrt(d3);

                        if (this.isNotColliding(this.posX, this.posY, this.posZ, d3))
                        {
                            this.parentEntity.motionX += d0 / d3 * 0.1D;
                            this.parentEntity.motionY += d1 / d3 * 0.1D;
                            this.parentEntity.motionZ += d2 / d3 * 0.1D;
                        }
                        else
                        {
                            this.action = EntityMoveHelper.Action.WAIT;
                        }
                    }
                }
            }

            private boolean isNotColliding(double x, double y, double z, double p_179926_7_)
            {
                double d0 = (x - this.parentEntity.posX) / p_179926_7_;
                double d1 = (y - this.parentEntity.posY) / p_179926_7_;
                double d2 = (z - this.parentEntity.posZ) / p_179926_7_;
                AxisAlignedBB axisalignedbb = this.parentEntity.getEntityBoundingBox();

                for (int i = 1; (double)i < p_179926_7_; ++i)
                {
                    axisalignedbb = axisalignedbb.offset(d0, d1, d2);

                    if (!this.parentEntity.world.getCollisionBoxes(this.parentEntity, axisalignedbb).isEmpty())
                    {
                        return false;
                    }
                }

                return true;
            }
        }

	@Override
	public SoundEvent setLivingSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SoundEvent setHurtSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SoundEvent setDeathSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return s.LightenerHealth;
	}

}