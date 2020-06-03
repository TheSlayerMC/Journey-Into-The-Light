package net.journey.entity.mob.overworld.underground;

import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityMob;

import org.jetbrains.annotations.NotNull;

public class EntityCaveling extends JEntityMob {

    public EntityCaveling(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        this.setSize(1.0F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.CAVE_MOB;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.ROCK;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.BASE_MOB_HURT;
    }

    @Override
    public void onDeath(DamageSource d) {
        super.onDeath(d);
        if (d.getImmediateSource() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer) d.getImmediateSource();
            //p.triggerAchievement(JourneyAchievements.achievementCave);
        }
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 40.0D && super.getCanSpawnHere() &&
                this.world.getBlockState(new BlockPos(this.posX, this.posY - 1, this.posZ)).getMaterial() == Material.ROCK && this.dimension == 0;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.CAVELING;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.CAVELING;
    }
}