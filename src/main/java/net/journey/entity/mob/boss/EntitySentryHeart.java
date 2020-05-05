package net.journey.entity.mob.boss;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Optional;

import net.journey.blocks.BlockAncientCatalyst;
import net.journey.entity.MobStats;
import net.journey.entity.mob.senterian.mob.EntitySentryBlock;
import net.journey.init.JourneySounds;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.slayer.api.entity.EntityEssenceBoss;

public class EntitySentryHeart extends EntityEssenceBoss {
    
	public EntitySentryHeart(World par1World) {
		super(par1World);
        this.setSize(8.0F, 17.0F);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
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
        BlockPattern.PatternHelper blockpattern$patternhelper = BlockAncientCatalyst.getOrCreatepattern().match(world, this.getPosition().add(0, 0, 0));

        if (blockpattern$patternhelper != null) {
            BlockPos blockpos = blockpattern$patternhelper.getFrontTopLeft().add(-1, 0, -1);
            world.setBlockState(blockpos.add(0, 1, 0), Blocks.OBSIDIAN.getDefaultState(), 2);
            world.playSound(null, this.getPosition(), JourneySounds.OBELISK_OPEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }
    
	@Override
	protected void collideWithEntity(Entity entityIn) {
		double d0 = this.posX - entityIn.posX;
		double d1 = this.posZ - entityIn.posZ;
		double d2 = MathHelper.absMax(d0, d1);
		if (d2 >= 0.009999999776482582D) {
			d2 = (double) MathHelper.sqrt(d2);
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
    public double setMovementSpeed() {
        return 0;
    }
}
