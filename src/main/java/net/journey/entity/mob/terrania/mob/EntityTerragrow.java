package net.journey.entity.mob.terrania.mob;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityTerragrow extends JEntityMob {

    public EntityTerragrow(World w) {
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
    public ResourceLocation getLootTable() {
        return JourneyLootTables.TERRAGROW;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.TERRAGROW;
    }
}