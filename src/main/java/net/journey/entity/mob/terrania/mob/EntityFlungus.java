package net.journey.entity.mob.terrania.mob;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.init.JAnimations;
import net.journey.init.JourneySounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityMob;
import org.jetbrains.annotations.NotNull;
import ru.timeconqueror.timecore.animation.ActionManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationManagerBuilder;
import ru.timeconqueror.timecore.animation.AnimationStarter;
import ru.timeconqueror.timecore.api.animation.ActionManager;
import ru.timeconqueror.timecore.api.animation.AnimationProvider;
import ru.timeconqueror.timecore.api.animation.BlendType;

public class EntityFlungus extends JEntityMob implements AnimationProvider<EntityFlungus> {

	private final ActionManager<EntityFlungus> actionManager;

	private static final String LAYER_LIVING = "living";

	public EntityFlungus(World world) {
		super(world);
		this.setSize(1.0F, 0.3F);
		actionManager = ActionManagerBuilder.<EntityFlungus>create(
				AnimationManagerBuilder.create()
						.addLayer(LAYER_LIVING, BlendType.ADDING, 1F)
						.addWalkingAnimationHandling(new AnimationStarter(JAnimations.FLUNGUS_LIVING).setSpeed(0.75F), LAYER_LIVING))
				.build(this, world);
	}

	@Override
	public @NotNull ActionManager<EntityFlungus> getActionManager() {
		return actionManager;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		//TODO change
		EntityAttributesHelper.setMaxHealth(this, 50);
		EntityAttributesHelper.setMovementSpeed(this, 0);
		EntityAttributesHelper.setKnockbackResistance(this, 1);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAILookIdle(this));
	}

	@Override
	public void setPosition(double x, double y, double z) {
		super.setPosition(x, y, z);
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
	protected SoundEvent getAmbientSound() {
		return JourneySounds.TERRA_SLUG;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource d) {
		return JourneySounds.TERRA_SLUG_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return JourneySounds.TERRA_SLUG_DEATH;
	}
}
