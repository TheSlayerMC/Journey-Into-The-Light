package net.journey.entity.mob.boss;

import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityMagmaFireball;
import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyArmory;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityBlazier extends EntityEssenceBoss implements IRangedAttackMob {

    private static final DataParameter<Byte> ON_FIRE = EntityDataManager.createKey(EntityBlazier.class, DataSerializers.BYTE);
    private float heightOffset = 0.5F;
    private int heightOffsetUpdateTime;
    private int spawnTimer;

    public EntityBlazier(World w) {
        super(w);
        this.experienceValue = 10;
        //this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
        this.tasks.addTask(1, new EntityBlazier.AIFireballAttack());
        this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));
        //this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.isImmuneToFire = true;
        this.setSize(2.0F, 6.0F);
        spawnTimer = 0;

    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ON_FIRE, Byte.valueOf((byte) 0));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender() {
        return 15728880;
    }

    @Override
    public float getBrightness() {
        return 1.0F;
    }

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
                EntityBlaze z = new EntityBlaze(world);
                z.setLocationAndAngles(posX + 3, posY, posZ, this.rand.nextFloat() * 360.0F, 0.0F);
                EntityBlaze z1 = new EntityBlaze(world);
                z1.setLocationAndAngles(posX - 3, posY, posZ, this.rand.nextFloat() * 360.0F, 0.0F);
                EntityBlaze z2 = new EntityBlaze(world);
                z2.setLocationAndAngles(posX, posY, posZ + 3, this.rand.nextFloat() * 360.0F, 0.0F);
                EntityBlaze z3 = new EntityBlaze(world);
                z3.setLocationAndAngles(posX, posY, posZ - 3, this.rand.nextFloat() * 360.0F, 0.0F);
                this.world.spawnEntity(z);
                this.world.spawnEntity(z1);
                this.world.spawnEntity(z2);
                this.world.spawnEntity(z3);
                spawnTimer = 300;
            }
            spawnTimer--;
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
            this.heightOffset = 0.5F + (float) this.rand.nextGaussian() * 3.0F;
        }

        EntityLivingBase entitylivingbase = this.getAttackTarget();

        if (entitylivingbase != null && entitylivingbase.posY + entitylivingbase.getEyeHeight() > this.posY + this.getEyeHeight() + this.heightOffset) {
            this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
            this.isAirBorne = true;
        }

        super.updateAITasks();
    }

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    public boolean isBurning() {
        return this.isFlying();
    }

    @Override
    public void onDeath(DamageSource damage) {
		/*if(damage.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)damage.getEntity();
			p.triggerAchievement(JourneyAchievements.achievementBlazier); {
			}
		}*/
        this.world.setBlockState(new BlockPos((int) Math.floor(this.posX + 0), ((int) Math.floor(this.posY + 1)), ((int) Math.floor(this.posZ + 0))), JourneyBlocks.trophyBlaze.getStateFromMeta(5));
        this.world.setBlockState(new BlockPos((int) Math.floor(this.posX + 0), ((int) Math.floor(this.posY + 0)), ((int) Math.floor(this.posZ + 0))), JourneyBlocks.journeyChest.getStateFromMeta(5));
        TileEntityJourneyChest te = (TileEntityJourneyChest) world.getTileEntity(new BlockPos((int) Math.floor(this.posX + 0), ((int) Math.floor(this.posY + 0)), ((int) Math.floor(this.posZ + 0))));
        switch (rand.nextInt(2)) {
            case 0:
                te.setInventorySlotContents(2, new ItemStack(JourneyWeapons.blazingBow, 1));
                te.setInventorySlotContents(1, new ItemStack(JourneyWeapons.sizzlerSword, 1));
                te.setInventorySlotContents(7, new ItemStack(JourneyWeapons.sizzlingKnife, 128));
                te.setInventorySlotContents(8, new ItemStack(JourneyItems.hellShards, 3));
                te.setInventorySlotContents(15, new ItemStack(JourneyItems.hellShards, 3));
                te.setInventorySlotContents(3, new ItemStack(JourneyArmory.flameHelmet, 1));
                te.setInventorySlotContents(5, new ItemStack(JourneyArmory.flameLegs, 1));
                te.setInventorySlotContents(12, new ItemStack(JourneyArmory.flameChest, 1));
                te.setInventorySlotContents(14, new ItemStack(JourneyArmory.flameBoots, 1));
                break;
            case 1:
                te.setInventorySlotContents(1, new ItemStack(JourneyWeapons.blazingBow, 1));
                te.setInventorySlotContents(2, new ItemStack(JourneyItems.hellShards, 3));
                te.setInventorySlotContents(3, new ItemStack(JourneyArmory.flameHelmet, 1));
                te.setInventorySlotContents(5, new ItemStack(JourneyArmory.flameLegs, 1));
                te.setInventorySlotContents(8, new ItemStack(JourneyWeapons.sizzlingKnife, 128));
                te.setInventorySlotContents(11, new ItemStack(JourneyArmory.flameChest, 1));
                te.setInventorySlotContents(12, new ItemStack(JourneyItems.hellShards, 3));
                te.setInventorySlotContents(15, new ItemStack(JourneyWeapons.sizzlerSword, 1));
                te.setInventorySlotContents(17, new ItemStack(JourneyArmory.flameBoots, 1));
                break;
        }
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
    public double setAttackDamage(MobStats s) {
        return MobStats.BlazierDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.BlazierHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.BLAZIER_IDLE;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.BLAZIER_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.BLAZIER_DEATH;
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

    class AIFireballAttack extends EntityAIBase {
        private EntityBlazier blaze = EntityBlazier.this;
        private int attackStep;
        private int attackTime;

        public AIFireballAttack() {
            this.setMutexBits(3);
        }

        @Override
        public boolean shouldExecute() {
            EntityLivingBase entitylivingbase = this.blaze.getAttackTarget();
            return entitylivingbase != null && entitylivingbase.isEntityAlive();
        }

        @Override
        public void startExecuting() {
            this.attackStep = 0;
        }

        @Override
        public void resetTask() {
            this.blaze.setFlying(false);
        }

        @Override
        public void updateTask() {
            --this.attackTime;
            EntityLivingBase entitylivingbase = this.blaze.getAttackTarget();
            double d0 = this.blaze.getDistanceSq(entitylivingbase);

            if (d0 < 4.0D) {
                if (this.attackTime <= 0) {
                    this.attackTime = 20;
                    this.blaze.attackEntityAsMob(entitylivingbase);
                }

                this.blaze.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            } else if (d0 < 256.0D) {
                double d1 = entitylivingbase.posX - this.blaze.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + entitylivingbase.height / 2.0F - (this.blaze.posY + this.blaze.height / 2.0F);
                double d3 = entitylivingbase.posZ - this.blaze.posZ;

                if (this.attackTime <= 0) {
                    ++this.attackStep;

                    if (this.attackStep == 1) {
                        this.attackTime = 60;
                        this.blaze.setFlying(true);
                    } else if (this.attackStep <= 21) {
                        this.attackTime = 3;
                    } else {
                        this.attackTime = 300;
                        this.attackStep = 0;
                        this.blaze.setFlying(false);
                    }

                    if (this.attackStep > 1) {
                        float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                        this.blaze.world.playEvent(null, 1018, new BlockPos((int) this.blaze.posX, (int) this.blaze.posY, (int) this.blaze.posZ), 0);

                        for (int i = 0; i < 5; ++i) {
                            EntityMagmaFireball entitysmallfireball = new EntityMagmaFireball(this.blaze.world, this.blaze, d1 + this.blaze.getRNG().nextGaussian() * f, d2, d3 + this.blaze.getRNG().nextGaussian() * f);
                            entitysmallfireball.posY = this.blaze.posY + this.blaze.height / 2.0F + 0.5D;
                            this.blaze.world.spawnEntity(entitysmallfireball);
                        }
                    }
                }
                this.blaze.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
            } else {
                this.blaze.getNavigator().clearPath();
                this.blaze.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
            }

            super.updateTask();
        }
    }
}
