package net.journey.entity.mob.boss;

import net.journey.entity.MobStats;
import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMiniBoss;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRockiteSmasher extends JEntityMiniBoss {

    private int attackTimer;

    public EntityRockiteSmasher(World par1World) {
        super(par1World);
        initEntityAI();
        setSize(1.5F, 1.75F);
    }

    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.6D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, MobStats.ROCKITE_SMASHER_HEALTH);
        EntityAttributesHelper.setAttackDamage(this, MobStats.ROCKITE_SMASHER_DAMAGE);
        EntityAttributesHelper.setKnockbackResistance(this, MobStats.ROCKITE_SMASHER_KNOCKBACK_RESISTANCE);
    }
    
	public boolean attackEntityFrom(DamageSource source, float amount) {
        EntityPlayer player = (EntityPlayer) source.getImmediateSource();
        Item heldItem = player.inventory.getCurrentItem().getItem();

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
                } else if (!(heldItem instanceof ItemPickaxe) || !(heldItem.getRegistryName().toString().contains("pickaxe"))) {
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
    
    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        this.playSound(SoundEvents.ENTITY_IRONGOLEM_HURT, 1.0F, 0.5F);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRONGOLEM_DEATH;
    }

    @Override
    public void playLivingSound() {
        this.playSound(SoundEvents.ENTITY_IRONGOLEM_HURT, 1.0F, 0.5F);
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 1.0F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.EMPTY;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return SoundEvents.ENTITY_IRONGOLEM_HURT;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.ROCKITE;
    }
}