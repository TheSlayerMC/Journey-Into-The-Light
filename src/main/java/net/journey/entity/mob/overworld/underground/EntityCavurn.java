package net.journey.entity.mob.overworld.underground;

import net.journey.JourneyItems;
import net.journey.JourneySounds;
import net.journey.entity.MobStats;
import net.journey.entity.projectile.EntityConjuring;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityCavurn extends EntityModMob implements IRangedAttackMob {


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
    public void attackEntityWithRangedAttack(EntityLivingBase target, float f) {
        EntityConjuring b = new EntityConjuring(this.world, this, 1.0F);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double) (target.height / 3.0F) - b.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
        b.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float) (14 - this.world.getDifficulty().getId() * 4));
        JourneySounds.playSound(JourneySounds.MAGIC_SPARKLE, world, this);
        this.world.spawnEntity(b);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return 0;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.CavurnHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.BASE_MOB_HURT;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.ROCK;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.CAVE_MOB;
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
    public Item getItemDropped() {
        return null;
    }

    @Override
    protected void dropFewItems(boolean b, int j) {
        if (rand.nextInt(10) == 0) dropItem(JourneyItems.caveCrystal, 1);
        super.dropFewItems(b, j);
        if (rand.nextInt(2) == 0) dropItem(JourneyItems.caveDust, rand.nextInt(3));
        super.dropFewItems(b, j);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
    }
}