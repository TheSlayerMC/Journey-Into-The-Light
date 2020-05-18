package net.journey.entity.mob.nether;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.blocks.JourneyBlocks;
import net.journey.util.JourneyLootTables;
import net.journey.util.PotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityHellTurtle extends JEntityMob {

    public EntityHellTurtle(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
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
    protected SoundEvent getAmbientSound() {
        return JourneySounds.SMALL_HONGO;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.SMALL_HONGO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.SMALL_HONGO_HURT;
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
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.HELL_TURTLE;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.HELL_TURTLE;
    }
}