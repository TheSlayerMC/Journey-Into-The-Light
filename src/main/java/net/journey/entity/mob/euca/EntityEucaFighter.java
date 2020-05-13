package net.journey.entity.mob.euca;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyConsumables;
import net.journey.init.items.JourneyItems;
import net.journey.util.JourneyLootTables;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityEucaFighter extends EntityModMob {


    public EntityEucaFighter(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(0.7F, 1.7F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.EucaFighterDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.EucaFighterHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.INSECTO;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.INSECTO_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.INSECTO_HURT;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.EUCA_FIGHTER;
    }
}