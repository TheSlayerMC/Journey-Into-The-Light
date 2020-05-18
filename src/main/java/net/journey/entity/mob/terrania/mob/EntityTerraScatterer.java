package net.journey.entity.mob.terrania.mob;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityTerraScatterer extends JEntityMob {

    public EntityTerraScatterer(World w) {
        super(w);
        setSize(1.0F, 1.5F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.WRAITH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.WRAITH_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.WRAITH_DEATH;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.TERRA_SCATTERER;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.TERRA_SCATTERER;
    }
}