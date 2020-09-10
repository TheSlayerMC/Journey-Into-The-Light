package net.journey.entity.mob.corba;

import net.journey.common.knowledge.EnumKnowledgeType;
import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.init.JourneySounds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySmelly extends JEntityMob {

	private int attackTimer;

	public EntitySmelly(World world) {
		super(world);
		addMeleeAttackingAI();
		setSize(1.5F, 3.0F);
		setKnowledge(EnumKnowledgeType.CORBA, 6);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		EntityAttributesHelper.setMovementSpeed(this, MobStats.SMELLY_SPEED);
		EntityAttributesHelper.setMaxHealth(this, MobStats.SMELLY_HEALTH);
		EntityAttributesHelper.setAttackDamage(this, MobStats.SMELLY_DAMAGE);
	}

	@Override
	public float getSoundPitch() {
		return 0.5F;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return JourneySounds.STINKY_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource d) {
		return JourneySounds.STINKY_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return JourneySounds.STINKY_DEATH;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source)) {
			return false;
		} else if (source == DamageSource.DROWN) {
			return false;
		} else {
			Entity entity;
			{
				entity = source.getImmediateSource();
				if (entity instanceof EntityArrow) {
					return false;
				} else if (entity instanceof EntityThrowable) {
					return false;
				}
			}
		}
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (this.attackTimer > 0) {
			--this.attackTimer;
		}

		if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D
				&& this.rand.nextInt(5) == 0) {
			int i = MathHelper.floor(this.posX);
			int j = MathHelper.floor(this.posY - 0.20000000298023224D);
			int k = MathHelper.floor(this.posZ);
			IBlockState iblockstate = this.world.getBlockState(new BlockPos(i, j, k));

			if (iblockstate.getMaterial() != Material.AIR) {
				this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK,
						this.posX + ((double) this.rand.nextFloat() - 0.5D) * (double) this.width,
						this.getEntityBoundingBox().minY + 0.1D,
						this.posZ + ((double) this.rand.nextFloat() - 0.5D) * (double) this.width,
						4.0D * ((double) this.rand.nextFloat() - 0.5D), 0.5D,
						((double) this.rand.nextFloat() - 0.5D) * 4.0D, Block.getStateId(iblockstate));
			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		this.attackTimer = 10;
		this.world.setEntityState(this, (byte) 4);
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this),
				(float) (7 + this.rand.nextInt(15)));

		if (flag) {
			entityIn.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F)) * 4, 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F)) * 4);
			this.applyEnchantments(this, entityIn);
		}

		this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 0.5F);
		return flag;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void handleStatusUpdate(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
			this.playSound(SoundEvents.ENTITY_IRONGOLEM_ATTACK, 1.0F, 0.5F);
		} else {
			super.handleStatusUpdate(id);
		}
	}

	@SideOnly(Side.CLIENT)
	public int getAttackTimer() {
		return this.attackTimer;
	}
}
