package net.journey.entity.mob.senterian.mob;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import net.journey.entity.MobStats;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.EntityModMob;

public class EntitySentryBlock extends EntityModMob {

	private static final UUID COVERED_ARMOR_BONUS_ID = UUID.fromString("7E0292F2-9434-48D5-A29F-9583AF7DF27F");
	private static final AttributeModifier COVERED_ARMOR_BONUS_MODIFIER = (new AttributeModifier(COVERED_ARMOR_BONUS_ID, "Covered armor bonus", 20.0D, 0)).setSaved(false);
	protected static final DataParameter<EnumFacing> ATTACHED_FACE = EntityDataManager.<EnumFacing>createKey(EntitySentryBlock.class, DataSerializers.FACING);
	protected static final DataParameter<Optional<BlockPos>> ATTACHED_BLOCK_POS = EntityDataManager.<Optional<BlockPos>>createKey(EntitySentryBlock.class, DataSerializers.OPTIONAL_BLOCK_POS);
	private BlockPos currentAttachmentPosition;
	protected static final DataParameter<Byte> PEEK_TICK = EntityDataManager.<Byte>createKey(EntitySentryBlock.class, DataSerializers.BYTE);
	private float prevPeekAmount;
	private float peekAmount;
	private int clientSideTeleportInterpolation;

	public EntitySentryBlock(World par1World) {
		super(par1World);
		this.setSize(1.0F, 1.0F);
		this.currentAttachmentPosition = null;
	}

	@Override
	public boolean getCanSpawnHere() {
		return this.posY < 20.0D && /**this.posY <  && */super.getCanSpawnHere();
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntitySentryBlock.AIPeek());
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[0]));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(ATTACHED_FACE, EnumFacing.DOWN);
		this.dataManager.register(ATTACHED_BLOCK_POS, Optional.absent());
		this.dataManager.register(PEEK_TICK, Byte.valueOf((byte)0));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.dataManager.set(ATTACHED_FACE, EnumFacing.byIndex(compound.getByte("AttachFace")));
		this.dataManager.set(PEEK_TICK, Byte.valueOf(compound.getByte("Peek")));

		if (compound.hasKey("APX")) {
			int i = compound.getInteger("APX");
			int j = compound.getInteger("APY");
			int k = compound.getInteger("APZ");
			this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(new BlockPos(i, j, k)));
		} else {
			this.dataManager.set(ATTACHED_BLOCK_POS, Optional.absent());
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setByte("AttachFace", (byte)((EnumFacing)this.dataManager.get(ATTACHED_FACE)).getIndex());
		compound.setByte("Peek", ((Byte)this.dataManager.get(PEEK_TICK)).byteValue());
		BlockPos blockpos = this.getAttachmentPos();

		if (blockpos != null) {
			compound.setInteger("APX", blockpos.getX());
			compound.setInteger("APY", blockpos.getY());
			compound.setInteger("APZ", blockpos.getZ());
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		BlockPos blockpos = (BlockPos)((Optional)this.dataManager.get(ATTACHED_BLOCK_POS)).orNull();

		if (blockpos == null && !this.world.isRemote) {
			blockpos = new BlockPos(this);
			this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
		}

		if (this.isRiding()) {
			blockpos = null;
			float f = this.getRidingEntity().rotationYaw;
			this.rotationYaw = f;
			this.renderYawOffset = f;
			this.prevRenderYawOffset = f;
			this.clientSideTeleportInterpolation = 0;
		}
		else if (!this.world.isRemote) {
			IBlockState iblockstate = this.world.getBlockState(blockpos);

			if (iblockstate.getMaterial() != Material.AIR) {
				if (iblockstate.getBlock() == Blocks.PISTON_EXTENSION) {
					EnumFacing enumfacing = (EnumFacing)iblockstate.getValue(BlockPistonBase.FACING);

					if (this.world.isAirBlock(blockpos.offset(enumfacing))) {
						blockpos = blockpos.offset(enumfacing);
						this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
					} else {
						this.tryTeleportToNewPosition();
					}
				}
				else if (iblockstate.getBlock() == Blocks.PISTON_HEAD) {
					EnumFacing enumfacing3 = (EnumFacing)iblockstate.getValue(BlockPistonExtension.FACING);

					if (this.world.isAirBlock(blockpos.offset(enumfacing3))) {
						blockpos = blockpos.offset(enumfacing3);
						this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos));
					} else {
						this.tryTeleportToNewPosition();
					}
				} else {
					this.tryTeleportToNewPosition();
				}
			}

			BlockPos blockpos1 = blockpos.offset(this.getAttachmentFacing());

			if (!this.world.isBlockNormalCube(blockpos1, false)) {
				boolean flag = false;

				for (EnumFacing enumfacing1 : EnumFacing.values()) {
					blockpos1 = blockpos.offset(enumfacing1);

					if (this.world.isBlockNormalCube(blockpos1, false)) {
						this.dataManager.set(ATTACHED_FACE, enumfacing1);
						flag = true;
						break;
					}
				}

				if (!flag) {
					this.tryTeleportToNewPosition();
				}
			}

			BlockPos blockpos2 = blockpos.offset(this.getAttachmentFacing().getOpposite());

			if (this.world.isBlockNormalCube(blockpos2, false)) {
				this.tryTeleportToNewPosition();
			}
		}

		float f1 = (float)this.getPeekTick() * 0.01F;
		this.prevPeekAmount = this.peekAmount;

		if (this.peekAmount > f1) {
			this.peekAmount = MathHelper.clamp(this.peekAmount - 0.05F, f1, 1.0F);
		}
		else if (this.peekAmount < f1) {
			this.peekAmount = MathHelper.clamp(this.peekAmount + 0.05F, 0.0F, f1);
		}

		if (blockpos != null)  {
			if (this.world.isRemote) {
				if (this.clientSideTeleportInterpolation > 0 && this.currentAttachmentPosition != null) {
					--this.clientSideTeleportInterpolation;
				} else {
					this.currentAttachmentPosition = blockpos;
				}
			}

			this.posX = (double)blockpos.getX() + 0.5D;
			this.posY = (double)blockpos.getY();
			this.posZ = (double)blockpos.getZ() + 0.5D;
			if (this.isAddedToWorld() && !this.world.isRemote) this.world.updateEntityWithOptionalForce(this, false); // Forge - Process chunk registration after moving.
			this.prevPosX = this.posX;
			this.prevPosY = this.posY;
			this.prevPosZ = this.posZ;
			this.lastTickPosX = this.posX;
			this.lastTickPosY = this.posY;
			this.lastTickPosZ = this.posZ;
			double d3 = 0.5D - (double)MathHelper.sin((0.5F + this.peekAmount) * (float)Math.PI) * 0.5D;
			double d4 = 0.5D - (double)MathHelper.sin((0.5F + this.prevPeekAmount) * (float)Math.PI) * 0.5D;
			double d5 = d3 - d4;
			double d0 = 0.0D;
			double d1 = 0.0D;
			double d2 = 0.0D;
			EnumFacing enumfacing2 = this.getAttachmentFacing();

			switch (enumfacing2) {
			case DOWN:
				this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5D, this.posY, this.posZ - 0.5D, this.posX + 0.5D, this.posY + 1.0D + d3, this.posZ + 0.5D));
				d1 = d5;
				break;
			case UP:
				this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5D, this.posY - d3, this.posZ - 0.5D, this.posX + 0.5D, this.posY + 1.0D, this.posZ + 0.5D));
				d1 = -d5;
				break;
			case NORTH:
				this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5D, this.posY, this.posZ - 0.5D, this.posX + 0.5D, this.posY + 1.0D, this.posZ + 0.5D + d3));
				d2 = d5;
				break;
			case SOUTH:
				this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5D, this.posY, this.posZ - 0.5D - d3, this.posX + 0.5D, this.posY + 1.0D, this.posZ + 0.5D));
				d2 = -d5;
				break;
			case WEST:
				this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5D, this.posY, this.posZ - 0.5D, this.posX + 0.5D + d3, this.posY + 1.0D, this.posZ + 0.5D));
				d0 = d5;
				break;
			case EAST:
				this.setEntityBoundingBox(new AxisAlignedBB(this.posX - 0.5D - d3, this.posY, this.posZ - 0.5D, this.posX + 0.5D, this.posY + 1.0D, this.posZ + 0.5D));
				d0 = -d5;
			}

			if (d5 > 0.0D) {
				List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());

				if (!list.isEmpty()) {
					for (Entity entity : list) {
						if (!(entity instanceof EntitySentryBlock) && !entity.noClip) {
							entity.move(MoverType.SHULKER, d0, d1, d2);
						}
					}
				}
			}
		}
	}

	public void move(MoverType type, double x, double y, double z) {
		if (type == MoverType.SHULKER_BOX) {
			this.tryTeleportToNewPosition();
		} else {
			super.move(type, x, y, z);
		}
	}

	public void setPosition(double x, double y, double z) {
		super.setPosition(x, y, z);

		if (this.dataManager != null && this.ticksExisted != 0) {
			Optional<BlockPos> optional = (Optional)this.dataManager.get(ATTACHED_BLOCK_POS);
			Optional<BlockPos> optional1 = Optional.<BlockPos>of(new BlockPos(x, y, z));

			if (!optional1.equals(optional)) {
				this.dataManager.set(ATTACHED_BLOCK_POS, optional1);
				this.dataManager.set(PEEK_TICK, Byte.valueOf((byte)0));
				this.isAirBorne = true;
			}
		}
	}

	protected boolean tryTeleportToNewPosition() {
		if (!this.isAIDisabled() && this.isEntityAlive()) {
			BlockPos blockpos = new BlockPos(this);

			for (int i = 0; i < 5; ++i) {
				BlockPos blockpos1 = blockpos.add(8 - this.rand.nextInt(17), 8 - this.rand.nextInt(17), 8 - this.rand.nextInt(17));

				if (blockpos1.getY() > 0 && this.world.isAirBlock(blockpos1) && this.world.isInsideWorldBorder(this) && this.world.getCollisionBoxes(this, new AxisAlignedBB(blockpos1)).isEmpty()) {
					boolean flag = false;

					for (EnumFacing enumfacing : EnumFacing.values()) {
						if (this.world.isBlockNormalCube(blockpos1.offset(enumfacing), false)) {
							this.dataManager.set(ATTACHED_FACE, enumfacing);
							flag = true;
							break;
						}
					}

					if (flag) {
						net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, blockpos1.getX(), blockpos1.getY(), blockpos1.getZ(), 0);
						if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) flag = false;
						blockpos1 = new BlockPos(event.getTargetX(), event.getTargetY(), event.getTargetZ());
					}

					if (flag) {
						this.playSound(SoundEvents.ENTITY_SHULKER_TELEPORT, 1.0F, 1.0F);
						this.dataManager.set(ATTACHED_BLOCK_POS, Optional.of(blockpos1));
						this.dataManager.set(PEEK_TICK, Byte.valueOf((byte)0));
						this.setAttackTarget((EntityLivingBase)null);
						return true;
					}
				}
			}
			return false;
		} else {
			return true;
		}
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
	}

	public void notifyDataManagerChange(DataParameter<?> key) {
		if (ATTACHED_BLOCK_POS.equals(key) && this.world.isRemote && !this.isRiding()) {
			BlockPos blockpos = this.getAttachmentPos();

			if (blockpos != null) {
				if (this.currentAttachmentPosition == null) {
					this.currentAttachmentPosition = blockpos;
				} else {
					this.clientSideTeleportInterpolation = 6;
				}

				this.posX = (double)blockpos.getX() + 0.5D;
				this.posY = (double)blockpos.getY();
				this.posZ = (double)blockpos.getZ() + 0.5D;
				this.prevPosX = this.posX;
				this.prevPosY = this.posY;
				this.prevPosZ = this.posZ;
				this.lastTickPosX = this.posX;
				this.lastTickPosY = this.posY;
				this.lastTickPosZ = this.posZ;
			}
		}

		super.notifyDataManagerChange(key);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
		this.newPosRotationIncrements = 0;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isClosed()) {
			Entity entity = source.getImmediateSource();

			if (entity instanceof EntityArrow) {
				return false;
			}
		}

		if (super.attackEntityFrom(source, amount)) {
			if ((double)this.getHealth() < (double)this.getMaxHealth() * 0.5D && this.rand.nextInt(4) == 0) {
				this.tryTeleportToNewPosition();
			}

			return true;
		} else {
			return false;
		}
	}

	private boolean isClosed() {
		return this.getPeekTick() == 0;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox() {
		return this.isEntityAlive() ? this.getEntityBoundingBox() : null;
	}

	public EnumFacing getAttachmentFacing() {
		return (EnumFacing)this.dataManager.get(ATTACHED_FACE);
	}

	@Nullable
	public BlockPos getAttachmentPos() {
		return (BlockPos)((Optional)this.dataManager.get(ATTACHED_BLOCK_POS)).orNull();
	}

	public void setAttachmentPos(@Nullable BlockPos pos) {
		this.dataManager.set(ATTACHED_BLOCK_POS, Optional.fromNullable(pos));
	}

	public int getPeekTick() {
		return ((Byte)this.dataManager.get(PEEK_TICK)).byteValue();
	}

	public void updateArmorModifier(int a) {
		if (!this.world.isRemote) {
			this.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(COVERED_ARMOR_BONUS_MODIFIER);

			if (a == 0) {
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).applyModifier(COVERED_ARMOR_BONUS_MODIFIER);
				this.playSound(SoundEvents.ENTITY_SHULKER_CLOSE, 1.0F, 1.0F);
			} else {
				this.playSound(SoundEvents.ENTITY_SHULKER_OPEN, 1.0F, 1.0F);
			}
		}
		this.dataManager.set(PEEK_TICK, Byte.valueOf((byte)a));
	}

	@SideOnly(Side.CLIENT)
	public float getClientPeekAmount(float p_184688_1_) {
		return this.prevPeekAmount + (this.peekAmount - this.prevPeekAmount) * p_184688_1_;
	}

	public float getPeekAmount() {
		return peekAmount;
	}

	@SideOnly(Side.CLIENT)
	public int getClientTeleportInterp() {
		return this.clientSideTeleportInterpolation;
	}

	@SideOnly(Side.CLIENT)
	public BlockPos getOldAttachPos() {
		return this.currentAttachmentPosition;
	}

	public float getEyeHeight() {
		return 0.5F;
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
	public void applyEntityCollision(Entity entityIn) { }

	@Override
	public float getCollisionBorderSize() {
		return 0.0F;
	}

	@SideOnly(Side.CLIENT)
	public boolean isAttachedToBlock() {
		return this.currentAttachmentPosition != null && this.getAttachmentPos() != null;
	}

	class AIPeek extends EntityAIBase
	{
		private int peekTime;

		private AIPeek()
		{
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute()
		{
			return EntitySentryBlock.this.getAttackTarget() == null && EntitySentryBlock.this.rand.nextInt(40) == 0;
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean shouldContinueExecuting()
		{
			return EntitySentryBlock.this.getAttackTarget() == null && this.peekTime > 0;
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting()
		{
			this.peekTime = 20 * (1 + EntitySentryBlock.this.rand.nextInt(3));
			EntitySentryBlock.this.updateArmorModifier(30);
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void resetTask()
		{
			if (EntitySentryBlock.this.getAttackTarget() == null)
			{
				EntitySentryBlock.this.updateArmorModifier(0);
			}
		}

		public void updateTask()
		{
			--this.peekTime;
		}
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public double setMovementSpeed() {
		return 0;
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return 0;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return 0;
	}

	@Override
	public SoundEvent setLivingSound() {
		return null;
	}

	@Override
	public SoundEvent setHurtSound() {
		return null;
	}

	@Override
	public SoundEvent setDeathSound() {
		return null;
	}

	@Override
	public Item getItemDropped() {
		return null;
	}
}