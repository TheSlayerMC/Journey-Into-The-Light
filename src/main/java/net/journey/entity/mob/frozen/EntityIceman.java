package net.journey.entity.mob.frozen;

import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityFrozenSnowball;
import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.init.items.JourneyWeapons;
import net.journey.util.Config;
import net.journey.util.JourneyLootTables;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

import javax.annotation.Nullable;

public class EntityIceman extends EntityModMob implements IRangedAttackMob {

    public EntityIceman(World par1World) {
        super(par1World);
    }

    @Override
    protected void initEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.tasks.addTask(0, new EntityAIAttackRanged(this, 0.27F, 30, 10.0F));
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        super.setEquipmentBasedOnDifficulty(difficulty);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(JourneyWeapons.iceWand));
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setEquipmentBasedOnDifficulty(difficulty);
        return livingdata;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        EntityFrozenSnowball b = new EntityFrozenSnowball(this.world, this);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - b.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
        b.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
        this.playSound(SoundEvents.ENTITY_SNOWMAN_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(b);
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.world.isRemote) {
            int i = MathHelper.floor(this.posX);
            int j = MathHelper.floor(this.posY);
            int k = MathHelper.floor(this.posZ);

            if (this.isWet()) {
                this.attackEntityFrom(DamageSource.DROWN, 1.0F);
            }

            if (this.world.getBiome(new BlockPos(i, 0, k)).getTemperature(new BlockPos(i, j, k)) > 1.0F) {
                this.attackEntityFrom(DamageSource.ON_FIRE, 1.0F);
            }

            if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
                return;
            }

            for (int l = 0; l < 4; ++l) {
                i = MathHelper.floor(this.posX + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
                j = MathHelper.floor(this.posY);
                k = MathHelper.floor(this.posZ + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockpos = new BlockPos(i, j, k);

                if (this.world.getBlockState(blockpos).getMaterial() == Material.AIR
                        && this.world.getBiome(blockpos).getTemperature(blockpos) < 0.8F
                        && Blocks.SNOW_LAYER.canPlaceBlockAt(this.world, blockpos)) {
                    this.world.setBlockState(blockpos, Blocks.SNOW_LAYER.getDefaultState());
                }
            }
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        return
                this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == JourneyBlocks.frozenGrass ||
                        this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == JourneyBlocks.brittleIce && this.dimension == Config.frozen;
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return 0;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.IceManHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.INSECTO;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.INSECTO_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.INSECTO_HURT;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.ICEMAN;
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {

    }
}