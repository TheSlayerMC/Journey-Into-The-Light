package net.journey.entity.mob.terrania.mob;

import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityMob;

import org.jetbrains.annotations.NotNull;

public class EntityTerrashroom extends JEntityMob {

    public EntityTerrashroom(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        this.setSize(1.0F, 2.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.HONGO;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.HONGO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.HONGO_HURT;
    }

    @Override
    public ResourceLocation getLootTable() {
        return JourneyLootTables.TERRASHROOM;
    }

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.TERRASHROOM;
    }
}