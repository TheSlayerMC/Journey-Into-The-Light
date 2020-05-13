package net.journey.entity.mob.end;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.util.JourneyLootTables;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityEnderLeaper extends EntityModMob {

    public EntityEnderLeaper(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(0.7F, 1.2F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.EnderLeaperDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.EnderLeaperHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.SHIMMERER;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.SHIMMERER_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.SHIMMERER_DEATH;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.ENDER_LEAPER;
    }
}