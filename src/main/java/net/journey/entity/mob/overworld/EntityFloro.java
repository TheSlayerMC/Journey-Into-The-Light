package net.journey.entity.mob.overworld;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityFloroWater;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityFloro extends JEntityMob implements IRangedAttackMob {
//	private AnimationManager animationManager = new AnimationManager();
//	private static final DataParameter<Byte> STATE = EntityDataManager.createKey(EntityFloro.class, DataSerializers.BYTE);

	public EntityFloro(World world) {
		super(world);
//		dataManager.register();
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();

		this.tasks.addTask(3, new EntityAIAvoidEntity<>(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(6, new EntityAILookIdle(this));

		this.tasks.addTask(4, new EntityAIAttackRanged(this, 0.27F, 50, 8.0F));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
		EntityFloroWater b = new EntityFloroWater(this.world, this, 1.0F);
		double d0 = target.posX - this.posX;
		double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - b.posY;
		double d2 = target.posZ - this.posZ;
		double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
		b.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
		JourneySounds.playSound(JourneySounds.MAGIC_SPARKLE, world, this);
		this.world.spawnEntity(b);
	}

	@Override
	public boolean getCanSpawnHere() {
		return
				dimension == 0
						&& (this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.GRASS ||
						this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.LEAVES ||
						this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.SAND ||
						this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == Blocks.DIRT);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return JourneySounds.HONGO;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return JourneySounds.HONGO_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return JourneySounds.HONGO_HURT;
	}

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.FLORO;
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
	}

	@Override
	public @NotNull EntitySettings getEntitySettings() {
		return MobStats.FLORO;
	}

//	@Override
//	public AnimationManager getAnimationManager() {
//		return animationManager;
//	}
}