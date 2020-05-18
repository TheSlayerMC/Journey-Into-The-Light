package net.journey.entity.mob.cloudia;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityStarlightWalker extends JEntityMob {

    public EntityStarlightWalker(World w) {
        super(w);
        addMeleeAttackingAI();
        setSize(1.0F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.EMPTY;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return SoundEvents.ENTITY_IRONGOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_IRONGOLEM_DEATH;
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.STARLIGHT_WALKER;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.STARLIGHT_WALKER;
    }
}