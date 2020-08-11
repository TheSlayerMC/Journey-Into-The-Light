package net.journey.entity.mob.boss;

import net.journey.blocks.BlockLament;
import net.journey.entity.MobStats;
import net.journey.entity.util.EntityBossCrystal;
import net.journey.init.JourneySounds;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityEssenceBoss;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntitySentryHeart extends EntityEssenceBoss {
    
	public int maxHealth = (int) MobStats.sentryHeartHealth;
	
	private boolean isActivated;
	
	public final int sleep = 0, alert = 1;
	public int stage;

	public EntitySentryHeart(World par1World) {
		super(par1World);
		this.setSize(4.0F, 17.0F);
		this.stage = sleep;
	}

	public int getStage() {
		return stage;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAILookIdle(this));
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return JourneySounds.SENTRY_HEART_BEATING;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource d) {
		return SoundEvents.ENTITY_SLIME_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return JourneySounds.SENTRY_HEART_DEATH;
	}

	@Override
	public void setPosition(double x, double y, double z) {
		super.setPosition(x, y, z);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
        this.prevRenderYawOffset = 180.0F;
        this.renderYawOffset = 180.0F;
        this.rotationYaw = 180.0F;
        int health = (int) getHealth();
        
        BlockPattern.PatternHelper blockpattern$patternhelper = BlockLament.getOrCreatepattern().match(world, this.getPosition().add(0, 0, 0));

        if (blockpattern$patternhelper == null) 
        	stage = sleep;
        else if (blockpattern$patternhelper != null) {
        	stage = alert;
        	this.isActivated = true;
        	if (this.isActivated = true) {
                BlockPos blockpos = blockpattern$patternhelper.getFrontTopLeft().add(-1, 0, -1);
                world.setBlockState(blockpos.add(0, 1, 0), Blocks.OBSIDIAN.getDefaultState(), 2);
        	}
        }
    }
    
	@Override
	protected void collideWithEntity(Entity entityIn) {
		double d0 = this.posX - entityIn.posX;
		double d1 = this.posZ - entityIn.posZ;
		double d2 = MathHelper.absMax(d0, d1);
		if (d2 >= 0.009999999776482582D) {
			d2 = MathHelper.sqrt(d2);
			d0 = d0 / d2;
			d1 = d1 / d2;
			double d3 = 1.0D / d2;
			if (d3 > 1.0D) {
				d3 = 1.0D;
			}
			d0 = d0 * d3;
			d1 = d1 * d3;
			d0 = d0 * 0.05D;
			d1 = d1 * 0.05D;
			d0 = d0 * (double) (1.0F - entityIn.entityCollisionReduction);
			d1 = d1 * (double) (1.0F - entityIn.entityCollisionReduction);
			entityIn.addVelocity(-d0, 0.0D, -d1);
		}
	}
	
	@Override
	public void knockBack(Entity entity, float strength, double xRatio, double zRatio) {
		if (this.getHealth() < 0.0F) {
			super.knockBack(entity, strength, xRatio, zRatio);
		}
	}

    public float getEyeHeight() {
        return 9.0F;
    }

    @Override
    public int getVerticalFaceSpeed() {
        return 0;
    }

    @Override
    public int getHorizontalFaceSpeed() {
        return 0;
    }

    @Override
    public void applyEntityCollision(Entity entityIn) {
    }

	@Override
	public float getCollisionBorderSize() {
		return 1.0F;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public @NotNull EntitySettings getEntitySettings() {
		return MobStats.SENTRY_HEART;
	}

	@Nullable
	@Override
	protected EntityBossCrystal.Type getDeathCrystalType() {//todo
		return null;
	}

	@Override
	protected @Nullable ResourceLocation getLootTable() {//todo
		return null;
	}
}
