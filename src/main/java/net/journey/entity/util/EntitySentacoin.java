package net.journey.entity.util;

import net.journey.api.capability.JourneyPlayer;
import net.journey.api.capability.PlayerStats;
import net.journey.common.capability.JCapabilityManager;
import net.journey.init.JourneySounds;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntitySentacoin extends Entity {

	public int coinAge;
	public int delayBeforeCanPickup;
	private int coinHealth = 5;
	private EntityPlayer closestPlayer;

	public EntitySentacoin(World worldIn, double x, double y, double z) {
		super(worldIn);
		this.setSize(0.1F, 0.1F);
		this.setPosition(x, y, z);
		this.rotationYaw = (float)(Math.random() * 360.0D);
		this.motionX = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
		this.motionY = (double)((float)(Math.random() * 0.2D) * 2.0F);
		this.motionZ = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
	}

	public EntitySentacoin(World worldIn) {
		super(worldIn);
		this.setSize(0.15F, 0.15F);
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	protected void entityInit() { }

	@Override
	public void onUpdate() {
		super.onUpdate();

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if(!this.hasNoGravity()) this.motionY -= 0.029999999329447746D;

		this.pushOutOfBlocks(this.posX, (this.getEntityBoundingBox().minY + this.getEntityBoundingBox().maxY) / 2.0D, this.posZ);
		this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		float f = 0.98F;

		if(this.onGround) {
			BlockPos underPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
			net.minecraft.block.state.IBlockState underState = this.world.getBlockState(underPos);
			f = underState.getBlock().getSlipperiness(underState, this.world, underPos, this) * 0.98F;
		}

		this.motionX *= (double)f;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= (double)f;

		if(this.onGround)  this.motionY *= -0.8999999761581421D;

		this.coinAge++;
		if(this.coinAge >= 3000) this.setDead();
	}

	@Override
	public boolean handleWaterMovement() {
		return this.world.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.WATER, this);
	}

	@Override
	protected void dealFireDamage(int amount) {
		this.attackEntityFrom(DamageSource.IN_FIRE, (float)amount);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(this.world.isRemote || this.isDead) return false;

		if(this.isEntityInvulnerable(source)) {
			return false;
		} else {
			this.markVelocityChanged();
			this.coinHealth = (int)((float)this.coinHealth - amount);

			if(this.coinHealth <= 0) this.setDead();

			return false;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		compound.setShort("Health", (short)this.coinHealth);
		compound.setShort("Age", (short)this.coinAge);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		this.coinHealth = compound.getShort("Health");
		this.coinAge = compound.getShort("Age");
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityIn) {
		if(!this.world.isRemote) {
			JourneyPlayer journeyPlayer = JCapabilityManager.asJourneyPlayer(entityIn);
			PlayerStats stats = journeyPlayer.getPlayerStats();
			entityIn.onItemPickup(this, 1);
			stats.addSentacoin(1);
			this.playSound(JourneySounds.COIN_PICKUP, 1.0F, 1.0F + rand.nextFloat());
			journeyPlayer.sendUpdates();
			this.setDead();
		}
	}

	@Override
	public boolean canBeAttackedWithItem() {
		return false;
	}
}