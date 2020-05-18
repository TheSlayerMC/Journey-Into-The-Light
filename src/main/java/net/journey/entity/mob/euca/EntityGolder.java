package net.journey.entity.mob.euca;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityGolder extends JEntityMob {


    public EntityGolder(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(1.2F, 1.7F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.REAPER;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.REAPER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.REAPER_HURT;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.GOLDER;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.GOLDER;
    }
}