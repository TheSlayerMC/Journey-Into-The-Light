package net.journey.entity.mob.overworld.underground;

import net.journey.entity.base.EntityAttributesHelper;
import net.journey.entity.base.JEntityMob;
import net.journey.entity.projectile.staff.EntityConjuring;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityCavurn extends JEntityMob implements IRangedAttackMob {


    public EntityCavurn(World par1World) {
        super(par1World);
        setSize(0.7F, 2.0F);
    }

    @Override
    protected void initEntityAI() {
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.tasks.addTask(0, new EntityAIAttackRanged(this, 0.27F, 30, 10.0F));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        EntityAttributesHelper.setMaxHealth(this, 30);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.BASE_MOB_HURT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.ROCK;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.CAVE_MOB;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        EntityConjuring b = new EntityConjuring(this.world, this, 1.0F);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - b.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
        b.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
        JourneySounds.playSound(JourneySounds.MAGIC_SPARKLE, this);
        this.world.spawnEntity(b);
    }

    @Override
    public void onDeath(DamageSource d) {
        super.onDeath(d);
        if (d.getImmediateSource() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) d.getImmediateSource();
            //	p.triggerAchievement(JourneyAchievements.achievementCave);
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 40.0D && super.getCanSpawnHere() &&
                this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getMaterial() == Material.ROCK && this.dimension == 0;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.CAVURN;
    }


    @Override
    public void setSwingingArms(boolean swingingArms) {
    }
}