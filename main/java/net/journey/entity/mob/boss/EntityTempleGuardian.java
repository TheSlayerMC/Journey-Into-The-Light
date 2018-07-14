package net.journey.entity.mob.boss;

import javax.annotation.Nullable;

import net.journey.JourneyItems;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityFireBall;
import net.journey.entity.projectile.EntityFloroWater;
import net.journey.entity.projectile.EntityMagmaFireball;
import net.journey.enums.EnumSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityTempleGuardian extends EntityEssenceBoss implements IRangedAttackMob {

	private int ticks;
	private final EntityAIAttackRangedBow<EntityTempleGuardian> aiArrowAttack = new EntityAIAttackRangedBow<EntityTempleGuardian>(this, 1.0D, 20, 15.0F);
	
	public EntityTempleGuardian(World par1World) {
		super(par1World);
		addAttackingAI();
		setSize(2.0F, 3.8F);
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
		if(par1World != null && !par1World.isRemote) {
			this.setCombatTask();
		}
	}
	
    @Override
    public float getSoundVolume() {
    	return 1.0F;
    }
	
	public void setCombatTask() {
		if(this.world != null && !this.world.isRemote) {
			this.tasks.removeTask(this.aiArrowAttack);
			ItemStack itemstack = this.getHeldItemMainhand();
			if(itemstack.getItem() == JourneyItems.staffOfHellstone) {
				int i = 20;
				if(this.world.getDifficulty() != EnumDifficulty.HARD) {
					i = 40;
				}
				this.aiArrowAttack.setAttackCooldown(i);
				this.tasks.addTask(4, this.aiArrowAttack);
			}
		}
	}
	
	@Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(JourneyItems.staffOfHellstone));
    }

	@Override
	@Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        this.setCombatTask();
        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());
        return livingdata;
    }
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setCombatTask();
    }
	
	@Override
	public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
        super.setItemStackToSlot(slotIn, stack);
        if (!this.world.isRemote && slotIn == EntityEquipmentSlot.MAINHAND) {
            this.setCombatTask();
        }
    }

	@Override
	public ItemStack getHeldItem(EnumHand hand) {
		return new ItemStack(JourneyItems.staffOfHellstone);
	}
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		EntityFloroWater b = new EntityFloroWater(this.world, this, 10F);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - b.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
        b.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
		EnumSounds.playSound(EnumSounds.HAMMER, world, this);
        this.world.spawnEntity(b);
    }

	
	
	@Override
	public void onDeath(DamageSource damage){
		this.world.setBlockState(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))), Blocks.CHEST.getStateFromMeta(5));
		TileEntityChest te = (TileEntityChest)world.getTileEntity(new BlockPos((int)Math.floor(this.posX + 0), ((int)Math.floor(this.posY + 0)), ((int)Math.floor(this.posZ + 0))));
		switch(rand.nextInt(4)) {
		case 0:
			te.setInventorySlotContents(2, new ItemStack(JourneyItems.yellowGem, 3));
			te.setInventorySlotContents(11, new ItemStack(JourneyItems.blueGem, 2));
			te.setInventorySlotContents(16, new ItemStack(JourneyItems.greenGem, 5));
			te.setInventorySlotContents(5, new ItemStack(JourneyItems.purpleGem, 2));
			break;
		case 1:
			te.setInventorySlotContents(1, new ItemStack(JourneyItems.yellowGem, 4));
			te.setInventorySlotContents(14, new ItemStack(JourneyItems.blueGem, 5));
			te.setInventorySlotContents(12, new ItemStack(JourneyItems.greenGem, 2));
			te.setInventorySlotContents(4, new ItemStack(JourneyItems.purpleGem, 6));
			break;
		case 2:
			te.setInventorySlotContents(14, new ItemStack(JourneyItems.yellowGem, 2));
			te.setInventorySlotContents(1, new ItemStack(JourneyItems.blueGem, 4));
			te.setInventorySlotContents(4, new ItemStack(JourneyItems.greenGem, 6));
			te.setInventorySlotContents(12, new ItemStack(JourneyItems.purpleGem, 2));
			break;
		case 3:
			te.setInventorySlotContents(4, new ItemStack(JourneyItems.yellowGem, 5));
			te.setInventorySlotContents(12, new ItemStack(JourneyItems.blueGem, 1));
			te.setInventorySlotContents(14, new ItemStack(JourneyItems.greenGem, 4));
			te.setInventorySlotContents(1, new ItemStack(JourneyItems.purpleGem, 2));
			break;
		}
	}
	
	@Override
	public void fall(float distance, float damageMultiplier) { }
	
	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return MobStats.templeGuardianHealth;
	}
	
	@Override
	public EnumSounds setDeathSound() {
		return EnumSounds.BOSS_DEATH;
	}
	
	@Override
	public EnumSounds setLivingSound() {
		return EnumSounds.BLAZE;
	}

	@Override
	public Item getItemDropped() {
		return null;
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return 0;
	}

	@Override
	public EnumSounds setHurtSound() {
		return EnumSounds.BLAZE_HURT;
	}
    
    private void launchWitherSkullToEntity(int var1, EntityLivingBase e) {
        this.launchWitherSkullToCoords(var1, e.posX, e.posY + e.getEyeHeight() * 0.5D, e.posZ, var1 == 0 && this.rand.nextFloat() < 0.001F);
        
    }
    
    private void launchWitherSkullToCoords(int var1, double f2, double f4, double f6, boolean f8) {
       // this.world.playAuxSFXAtEntity((EntityPlayer)null, 1014, new BlockPos(this), 0);
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
        }
        else {
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
        }
        else {
            float f = (this.renderYawOffset + 180 * (par1 - 1)) / 180.0F * (float)Math.PI;
            float f1 = MathHelper.sin(f);
            return this.posZ + f1 * 1.3D;
        }
    }

	@Override
	public void setSwingingArms(boolean swingingArms) { }
}