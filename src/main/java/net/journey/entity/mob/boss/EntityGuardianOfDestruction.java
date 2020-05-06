package net.journey.entity.mob.boss;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntityGuardianOfDestruction extends EntityEssenceBoss {

	public int maxHealth = (int) MobStats.guardianofdestructionHealth;

	private int rolltimer;
	private int sountTimer;
	
	private boolean isMoving;

	public final int sleep = 0, alert = 1, lowhealth = 2;
	public int stage;

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
        addAttackingAI();
    }

	@Override
	protected void entityInit() {
		super.entityInit();
        this.prevRenderYawOffset = 180.0F;
        this.renderYawOffset = 180.0F;
        this.rotationYaw = 180.0F;
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
        if (health >= maxHealth) stage = sleep;
        else if (health <= maxHealth - 1 && health > maxHealth / 3) stage = alert;
        else if (health <= maxHealth / 3) stage = lowhealth;
		/*
		 * if the boss is at full health, set move speed to 0 and set attack target to null
		 * sets ismoving to false
		 */
        if (stage == sleep) {
	        this.motionX = 0.0D;
	        this.motionY = 0.0D;
	        this.motionZ = 0.0D;
	        this.prevRenderYawOffset = 180.0F;
	        this.renderYawOffset = 180.0F;
	        this.rotationYaw = 180.0F;
	        this.setAttackTarget(null);
	        this.isMoving = false;
        } else {
    		/*
    		 * if the boss isn't asleep, set attack target to the player that attacks it
    		 * sets ismoving to true
    		 */
        	if (stage == alert || stage == lowhealth) {
		        this.setAttackTarget(attackingPlayer);
		        this.isMoving = true;
		        /*
		         * sets move speed to 0 for specfic amount of ticks
		         * sets ismoving to false
		         */
		        if (rolltimer == 0) {
			        this.motionX = 0.0D;
			        this.motionY = 0.0D;
			        this.motionZ = 0.0D;
			        this.isMoving = false;
			        rolltimer = 60;
		        }
				if (rolltimer > 0) rolltimer--;
			}
        }
		/*
		 * if the player is absent, reset stage to sleeping (need to iron this one out)
		 */
		if (!this.world.isRemote) {
			if (this.getAttackTarget() == null) {
				stage = sleep;
				return;
			}

			if (isMoving) {
				/*
				 * plays sound while boss is moving
				 */
				if (sountTimer == 0) {
					JourneySounds.playSound(JourneySounds.SENTRY_DESTRUCTION_MOVING, world, this);
					sountTimer = 13;
				}
				if (sountTimer > 0) sountTimer--;
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
        return JourneySounds.EMPTY;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.SENTRY_AMBIENT_1;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.SENTRY_AMBIENT_1;
    }

    @Override
    public Item getItemDropped() {
        return null;
    }
}