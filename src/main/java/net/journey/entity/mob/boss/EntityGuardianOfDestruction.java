package net.journey.entity.mob.boss;

import net.journey.entity.MobStats;
import net.journey.entity.projectile.arrow.EntityDarknessArrow;
import net.journey.entity.projectile.arrow.EntityFlameArrow;
import net.journey.entity.projectile.arrow.EntityFrozenArrow;
import net.journey.entity.projectile.arrow.EntityPoisonArrow;
import net.journey.init.items.JourneyItems;
import net.journey.init.items.JourneyWeapons;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

import javax.annotation.Nullable;

public class EntityGuardianOfDestruction extends EntityEssenceBoss {


    public final int sleep = 0, alert = 1, lowhealth = 2;
    public int stage;
    public int rolltimer;

    public EntityGuardianOfDestruction(World par1World) {
        super(par1World);
        setSize(2.0F, 4.0F);
        this.stage = sleep;
        this.rotationYaw = this.rotationPitch = 0.0F;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.targetTasks.addTask(2, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(1, new EntityAILookIdle(this));
        addAttackingAI();
    }
    
	@Override
	protected void entityInit() {
		super.entityInit();
		this.posX = Math.floor(this.posX + 0.5D);
		this.posY = Math.floor(this.posY + 0.5D);
		this.posZ = Math.floor(this.posZ + 0.5D);
	}
	
	@Override
	public void move(MoverType type, double x, double y, double z) {
		if (this.stage == alert || this.stage == lowhealth) {
			super.move(type, x, y, z);
		}
		else if (this.stage == sleep) {
			super.move(type, 0, 0, 0);
		}
	}

    public int getStage() {
        return stage;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        int health = (int) getHealth();
        if (health >= 1500) stage = sleep;
        else if (health <= 1499 && health > 500) stage = alert;
        else if (health <= 500) stage = lowhealth;
        if (stage == sleep) {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
        	this.setAttackTarget(null);
        } else {
        	if (stage == alert) {
        		this.setAttackTarget(attackingPlayer);
        	}
			if (stage == lowhealth) {
				this.setAttackTarget(attackingPlayer);
			}
        }
    }

    @Override
    public double setKnockbackResistance() {
        return 3.0D;
    }
    
    @Override
    public double setMovementSpeed() {
    	if (stage == lowhealth) {
    		return 1.0D;
    	} else 
    		return MobStats.normalSpeed;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.guardianofdestructionHealth;
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.guardianofdestructionDamage;
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
        return SoundEvents.ENTITY_WITHER_DEATH;
    }

    @Override
    public Item getItemDropped() {
        return null;
    }
}