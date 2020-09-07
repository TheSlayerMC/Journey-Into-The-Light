package net.journey.entity.base;

import net.journey.api.capability.JourneyPlayer;
import net.journey.api.capability.PlayerStats;
import net.journey.api.entity.IJERCompatible;
import net.journey.common.capability.JCapabilityManager;
import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.entity.MobStats;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class JEntityMob extends EntityMob implements IJERCompatible {

	private EnumKnowledgeType knowledgeType;
	private int knowledgeAmount;

	public JEntityMob(World world) {
		super(world);
		setSize(1.0F, 2.0F);
	}

	@Override
	protected void initEntityAI() {
		//Basic tasks
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0F));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
	}

	protected void addMeleeAttackingAI() {
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.4D, false));

		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		EntityAttributesHelper.setMovementSpeed(this, MobStats.STANDARD_MOVEMENT_SPEED);
		EntityAttributesHelper.setFollowRange(this, MobStats.STANDARD_FOLLOW_RANGE);
		EntityAttributesHelper.setKnockbackResistance(this, MobStats.STANDARD_KNOCKBACK_RESISTANCE);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	/**
	 * Living sound
	 */
	@Override
	protected abstract SoundEvent getAmbientSound();

	@Override
	protected abstract SoundEvent getHurtSound(DamageSource d);

	@Override
	protected abstract SoundEvent getDeathSound();

	public final double getMoveSpeed() {
		return getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue();
	}

	public final double getAttackDamage() {
		return getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
	}

	public final double getFollowRange() {
		return getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).getAttributeValue();
	}

	public final double getKnockbackResistance() {
		return getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue();
	}

	@Override
	public boolean getCanSpawnHere() {
		return world.getDifficulty() != EnumDifficulty.PEACEFUL && this.world.checkNoEntityCollision(this.getEntityBoundingBox()) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox());
	}

	public JEntityMob applyKnowledge(EnumKnowledgeType type, int amount) {
		this.knowledgeType = type;
		this.knowledgeAmount = amount;
		return this;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if (cause.getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) cause.getTrueSource();
			PlayerStats stats = JCapabilityManager.asJourneyPlayer(player).getPlayerStats();
			JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(player);
			stats.addKnowledge(this.knowledgeType, this.knowledgeAmount);
			journeyPlayer.sendUpdates(((EntityPlayerMP) player));
		}
	}

	@Override
	public @Nullable ResourceLocation getJERLootLocation() {
		return getLootTable();
	}
}