package net.journey.entity.mob.nether;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.util.JourneyLootTables;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityWitherspine extends JEntityMob {

    public EntityWitherspine(World par1World) {
        super(par1World);
        addAttackingAI();
        this.isImmuneToFire = true;
        setSize(0.7F, 4.2F);
    }

    //@Override
    //public boolean getCanSpawnHere() {
    //	return super.worldObj.getBlockState(new BlockPos(this.posX, this.posY-1, this.posZ)).getBlock() == Blocks.nether_brick;
    //}

    @Override
    public boolean attackEntityAsMob(Entity e) {
        boolean attacked = super.attackEntityAsMob(e);
        if (attacked) {
            if (e instanceof EntityLivingBase)
                ((EntityLivingBase) e).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.wither, 50, 2)));
        }
        return attacked;
    }

    @Override
    public double getMoveSpeed() {
        return 20.0D;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WITHER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.WITHERSPINE;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.WITHERSPINE;
    }
}