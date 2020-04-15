package net.journey.entity.mob.boss;

import net.journey.JourneyArmory;
import net.journey.JourneyBlocks;
import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.JourneyWeapons;
import net.journey.blocks.tileentity.TileEntityJourneyChest;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityMagmaFireball;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
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

	private float heightOffset = 0.5F;
	private int heightOffsetUpdateTime;
	private int spawnTimer;
    private static final DataParameter<Byte> ON_FIRE = EntityDataManager.<Byte>createKey(EntityBlazier.class, DataSerializers.BYTE);

	public EntityBlazier(World w) {
		super(w);
		this.experienceValue = 10;
		//this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
        this.tasks.addTask(4, new EntityBlazier.AIFireballAttack());
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.isImmuneToFire = true;
		this.setSize(2.0F, 6.0F);
		spawnTimer = 0;
		
	}
	@Override
	protected void entityInit() {
		super.entityInit();
        this.dataManager.register(ON_FIRE, Byte.valueOf((byte)0));
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
                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }

        if(getHealth() <= 250) {
        	if(spawnTimer == 0 && !world.isRemote) {
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
                spawnTimer = 200;
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
	
	@Override
	public void onDeath(DamageSource damage) {
		/*if(damage.getEntity() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer)damage.getEntity();
			p.triggerAchievement(JourneyAchievements.achievementBlazier); {
			}
		}*/
		this.world.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 1)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.trophyBlaze.getStateFromMeta(5));
		this.world.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))), JourneyBlocks.journeyChest.getStateFromMeta(5));
		TileEntityJourneyChest te = (TileEntityJourneyChest)world.getTileEntity(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))));
		switch(rand.nextInt(2)) {
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
		return MobStats.BlazierDamage;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.BlazierHealth;
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
	public Item getItemDropped() {
		return Items.BLAZE_ROD;
	}
	
    class AIFireballAttack extends EntityAIBase {
        private EntityBlazier field_179469_a = EntityBlazier.this;
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
		public void resetTask() {
            this.field_179469_a.setFlying(false);
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
            }
            else if (d0 < 256.0D) {
                double d1 = entitylivingbase.posX - this.field_179469_a.posX;
                double d2 = entitylivingbase.getEntityBoundingBox().minY + entitylivingbase.height / 2.0F - (this.field_179469_a.posY + this.field_179469_a.height / 2.0F);
                double d3 = entitylivingbase.posZ - this.field_179469_a.posZ;

                if (this.field_179468_c <= 0) {
                    ++this.field_179467_b;

                    if (this.field_179467_b == 1) {
                        this.field_179468_c = 60;
                        this.field_179469_a.setFlying(true);
                    }
                    else if (this.field_179467_b <= 4) {
                        this.field_179468_c = 6;
                    } else {
                        this.field_179468_c = 100;
                        this.field_179467_b = 0;
                        this.field_179469_a.setFlying(false);
                    }

                    if (this.field_179467_b > 1) {
                        float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
                        this.field_179469_a.world.playBroadcastSound(1009, new BlockPos((int)this.field_179469_a.posX, (int)this.field_179469_a.posY, (int)this.field_179469_a.posZ), 0);

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
	public void setSwingingArms(boolean swingingArms) { }
}