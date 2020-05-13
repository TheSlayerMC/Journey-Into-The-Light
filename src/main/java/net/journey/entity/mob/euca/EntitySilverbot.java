package net.journey.entity.mob.euca;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.journey.util.JourneyLootTables;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntitySilverbot extends EntityModMob {

    public EntitySilverbot(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(0.7F, 1.2F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.SilverbotDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.SilverbotHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.ROBOT;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.ROBOT_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.ROBOT_DEATH;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.SILVERBOT;
    }
}