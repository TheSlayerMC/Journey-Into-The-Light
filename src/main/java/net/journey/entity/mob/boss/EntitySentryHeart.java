package net.journey.entity.mob.boss;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import net.journey.entity.MobStats;
import net.journey.entity.mob.senterian.mob.EntitySentryBlock;
import net.journey.init.JourneySounds;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntitySentryHeart extends EntityEssenceBoss {

    protected static final DataParameter<Optional<BlockPos>> ATTACHED_BLOCK_POS = EntityDataManager.createKey(EntitySentryHeart.class, DataSerializers.OPTIONAL_BLOCK_POS);
    private BlockPos currentAttachmentPosition;
    
	public EntitySentryHeart(World par1World) {
		super(par1World);
        this.setSize(1.0F, 1.0F);
        this.currentAttachmentPosition = null;
	}

	@Override
	public double setAttackDamage(MobStats s) {
		return 0;
	}

	@Override
	public double setMaxHealth(MobStats s) {
		return 5000;
	}

	@Override
	public SoundEvent setLivingSound() {
		return JourneySounds.SENTRY_HEART_BEATING;
	}

	@Override
	public SoundEvent setHurtSound() {
		return SoundEvents.ENTITY_SLIME_HURT;
	}
	
    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.SENTRY_HEART_DEATH;
    }

	@Override
	public Item getItemDropped() {
		return null;
	}

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAILookIdle(this));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(ATTACHED_BLOCK_POS, Optional.absent());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
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
        BlockPos blockpos = this.getAttachmentPos();

        if (blockpos != null) {
            compound.setInteger("APX", blockpos.getX());
            compound.setInteger("APY", blockpos.getY());
            compound.setInteger("APZ", blockpos.getZ());
        }
    }

    public void setPosition(double x, double y, double z) {
        super.setPosition(x, y, z);

        if (this.dataManager != null && this.ticksExisted != 0) {
            Optional<BlockPos> optional = this.dataManager.get(ATTACHED_BLOCK_POS);
            Optional<BlockPos> optional1 = Optional.of(new BlockPos(x, y, z));

            if (!optional1.equals(optional)) {
                this.dataManager.set(ATTACHED_BLOCK_POS, optional1);
                this.isAirBorne = true;
            }
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

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        if (ATTACHED_BLOCK_POS.equals(key) && this.world.isRemote && !this.isRiding()) {
            BlockPos blockpos = this.getAttachmentPos();

            if (blockpos != null) {
                if (this.currentAttachmentPosition == null) {
                    this.currentAttachmentPosition = blockpos;
                }
                this.posX = (double) blockpos.getX() + 0.5D;
                this.posY = blockpos.getY();
                this.posZ = (double) blockpos.getZ() + 0.5D;
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

    @Nullable
    public BlockPos getAttachmentPos() {
        return (BlockPos) ((Optional) this.dataManager.get(ATTACHED_BLOCK_POS)).orNull();
    }

    public void setAttachmentPos(@Nullable BlockPos pos) {
        this.dataManager.set(ATTACHED_BLOCK_POS, Optional.fromNullable(pos));
    }

    @SideOnly(Side.CLIENT)
    public BlockPos getOldAttachPos() {
        return this.currentAttachmentPosition;
    }

    public float getEyeHeight() {
        return 1.0F;
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

    @SideOnly(Side.CLIENT)
    public boolean isAttachedToBlock() {
        return this.currentAttachmentPosition != null && this.getAttachmentPos() != null;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public double setMovementSpeed() {
        return 0;
    }
}
