package net.journey.entity.mob.nether;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.JourneyLootTables;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityHellTurtle extends EntityModMob {

    public EntityHellTurtle(World par1World) {
        super(par1World);
        addAttackingAI();
        this.setSize(2.0F, 1.3F);
        this.isImmuneToFire = true;

    }

    @Override
    public boolean getCanSpawnHere() {
        return super.getCanSpawnHere() &&
                this.
                        world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == JourneyBlocks.heatSoil ||
                world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == JourneyBlocks.lavaRock ||
                world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getBlock() == JourneyBlocks.nethicanSludge;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
    }

    @Override
    public boolean attackEntityAsMob(Entity e) {
        boolean attacked = super.attackEntityAsMob(e);
        if (attacked) {
            if (e instanceof EntityLivingBase)
                ((EntityLivingBase) e).addPotionEffect(new PotionEffect(PotionEffects.setPotionEffect(PotionEffects.moveSlow, 100, 2)));
            e.setFire(10);
            e.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F)) * 2, 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F)) * 2);
        }
        return attacked;
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.HellTurtleDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.HellTurtleHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.SMALL_HONGO;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.SMALL_HONGO_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.SMALL_HONGO_HURT;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.HELL_TURTLE;
    }
}