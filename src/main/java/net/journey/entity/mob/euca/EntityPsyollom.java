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

public class EntityPsyollom extends EntityModMob {

    public static final int ENTITY_TYPE = 25;

    public EntityPsyollom(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(1.7F, 2.7F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.PsyollomDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.PsyollomHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.PSYOLLOM;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.PSYOLLOM_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.PSYOLLOM_HURT;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.PSYOLLUM;
    }
}