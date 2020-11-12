package net.journey.entity.mob.boss;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.util.EntityBossCrystal;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
//import net.journey.util.PotionEffects;
//import net.journey.entity.projectile.EntityEnchantedShot;
import net.journey.entity.projectile.EntityRealityShot;
//import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
//import net.minecraft.util.math.Vec3d;
//import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityCalcia extends EntityEssenceBoss {

    private int firetick;
    private final int firemax = 400;
    private final int firemax2 = 300;
    private boolean isInvi;
    private PossessionHandler[] possessions = new PossessionHandler[5];

    public EntityCalcia(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        this.setSize(1.6F, 3.2F);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        EntityAttributesHelper.setMaxHealth(this, MobStats.CALCIA_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.CALCIA_DAMAGE);
        EntityAttributesHelper.setKnockbackResistance(this, MobStats.CALCIA_KNOCKBACK_RESISTANCE);
        EntityAttributesHelper.setMovementSpeed(this, MobStats.CALCIA_MOVEMENT_SPEED);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.CALCIA;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.CALCIA_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.CALCIA_HURT;
    }

    public boolean isInv() {
        return isInvi;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (isInv()) {
            for (int i = 0; i < 5; i++) {
                this.world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
            }
        }
    }
    
    @Override
    protected void updateAITasks() {
    	if (this.getAttackTarget() != null && rand.nextInt((this.getHealth() / this.getMaxHealth() < 0.2) ? 40 : 120) == 0) {
    		EntityLivingBase teleportTarget = this.getAttackTarget();
    		double teleportX = teleportTarget.posX;
    		double teleportY;
    		double teleportZ = teleportTarget.posZ;
    		double targetDistance = this.getDistance(teleportTarget);
           	if (targetDistance > 20) { //teleport closer to player
           		teleportX += (posX - teleportTarget.posX) / 2;
           		teleportY = teleportTarget.posY;
           		teleportZ += (posZ - teleportTarget.posZ) / 2;
           	} else { //teleport somewhere around player
           		double rotation = Math.toRadians(rand.nextInt(360));
                teleportX += Math.cos(rotation) * targetDistance;
                teleportY = this.posY;
                teleportZ += Math.sin(rotation) * targetDistance;
           	}
            double preTeleportX = posX;
            double preTeleportY = posY;
            double preTeleportZ = posZ;
            if (this.calciaTeleport(teleportX, teleportY, teleportZ)) {
               	EntityRealityShot darkSoul = new EntityRealityShot(this.world, this, 4F);
               	darkSoul.posX = preTeleportX;
               	darkSoul.posY = preTeleportY + height / 2;
               	darkSoul.posZ = preTeleportZ;
               	world.spawnEntity(darkSoul);
                this.world.playSound((EntityPlayer)null, this.prevPosX, this.prevPosY, this.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
                this.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
            }
    	}
    }
    
    private boolean calciaTeleport(double teleportX, double teleportY, double teleportZ) {
    	double maxHeight = this.getAttackTarget().posY + 5;
    	boolean hasTeleported = false;
    	while (teleportY < maxHeight && hasTeleported == false) {
    		hasTeleported = attemptTeleport(teleportX, teleportY, teleportZ);
    		teleportY++;
    	}
    	return hasTeleported;
    }

    @Override
    public void onLivingUpdate() {
        if (firemax == firetick && firetick != 0) {
            this.isInvi = true;
            this.firetick = 0;
        } else {
            firetick++;
        }

        if (firemax2 == firetick && firetick != 0) {
            this.isInvi = false;
            this.firetick = 0;
        } else {
            firetick++;
        }
        for (int possessionNum = 0; possessionNum < possessions.length; possessionNum++) {
        	if (possessions[possessionNum] != null && !possessions[possessionNum].handlePossession(this)) {
        		possessions[possessionNum] = null;
        	}
        }
        super.onLivingUpdate();
    }

    @Override
    protected @Nullable ResourceLocation getLootTable() {
        return JourneyLootTables.CALCIA;
    }

    @Override
    protected @NotNull EntityBossCrystal.Type getDeathCrystalType() {
        return EntityBossCrystal.Type.NETHER;
    }
    
    public void possess(EntityLivingBase target, int possessionTime, int cooldown) {
    	boolean possessionExists = false;
    	for (int currentPossession = 0; currentPossession < possessions.length; currentPossession++) {
    		if (possessions[currentPossession] != null && possessions[currentPossession].possessedEntity == target) {
    			possessionExists = true;
    			break;
    		}
    	}
    	if (!possessionExists) {
    		for (int currentPossession = 0; currentPossession < possessions.length; currentPossession++) {
				if (possessions[currentPossession] == null) {
					possessions[currentPossession] = new PossessionHandler(target, possessionTime, cooldown);
					target.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 300, 1));
					break;
				}
			}
    	}
    	
    }
}

class PossessionHandler {
	EntityLivingBase possessedEntity;
	int possessionTimer;
	int possessionCooldown;
	
	public PossessionHandler(EntityLivingBase entity, int timer, int cooldown) {
		possessedEntity = entity;
		possessionTimer = timer;
		possessionCooldown = cooldown;
	}
	
	public boolean handlePossession(EntityCalcia master) {
		if (possessionTimer > 0) {
    		possessionTimer--;
			double x = master.posX - possessedEntity.posX;
			double y = master.posY - possessedEntity.posY + 5;
			double z = master.posZ - possessedEntity.posZ;
			double velocityCancel = Math.sqrt(x * x + y * y + z * z);
			x = x / velocityCancel * 0.1;
			y = y / velocityCancel * 0.1;
			z = z / velocityCancel * 0.1;
			possessedEntity.addVelocity(x, y, z);
			possessedEntity.velocityChanged = true;
			if (possessionTimer % 10 == 0) {
				double rotation = Math.toRadians(master.getRNG().nextInt(360));
        		EntityRealityShot shot = new EntityRealityShot(master.world, master, 2F);
        		shot.posX = Math.cos(rotation) * master.getRNG().nextInt(15) + 15 + possessedEntity.posX;
        		shot.posY = possessedEntity.posY + master.getRNG().nextDouble() * possessedEntity.height;
        		shot.posZ = Math.sin(rotation) * master.getRNG().nextInt(15) + 15 + possessedEntity.posZ;
                master.world.spawnEntity(shot);
			}
    	} else if (possessionCooldown > 0) {
    		possessionCooldown--;
    	} else {
    		return false;
    	}
		return true;
	}
}