package net.journey.entity.mob.senterian.mob;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.util.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntitySentryStalker extends JEntityMob {

    public EntitySentryStalker(World par1World) {
        super(par1World);
        this.setSize(1.0F, 2.5F);
        addMeleeAttackingAI();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 20.0D && /**this.posY <  && */super.getCanSpawnHere();
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.SENTRY_STALKER;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.SENTRY_STALKER;
    }
}