package net.journey.entity.mob.end;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityEnderLeaper extends JEntityMob {

    public EntityEnderLeaper(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(0.7F, 1.2F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.SHIMMERER;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.SHIMMERER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.SHIMMERER_DEATH;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.ENDER_LEAPER;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.ENDER_LEAPER;
    }
}