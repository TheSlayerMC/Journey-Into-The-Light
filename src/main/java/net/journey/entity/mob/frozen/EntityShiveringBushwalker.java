package net.journey.entity.mob.frozen;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityShiveringBushwalker extends JEntityMob {

    public EntityShiveringBushwalker(World par1World) {
        super(par1World);
        addAttackingAI();
        this.setSize(0.65F, 1F);
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
    public boolean getCanSpawnHere() {
        return this.posY < 60.0D && super.getCanSpawnHere();
    }

    @Override
    protected ResourceLocation getLootTable() {
        return JourneyLootTables.SHIVERING_BUSHWALKER;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.SHIVERING_BUSHWALKER;
    }
}