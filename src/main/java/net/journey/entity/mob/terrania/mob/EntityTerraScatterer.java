package net.journey.entity.mob.terrania.mob;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.journey.util.JourneyLootTables;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityTerraScatterer extends EntityModMob {

    public EntityTerraScatterer(World w) {
        super(w);
        setSize(1.0F, 1.5F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.TerraScattererDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.TerraScattererHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.WRAITH;
    }

    @Override
    public SoundEvent setHurtSound() {
        return SoundEvents.ENTITY_CREEPER_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.WRAITH_DEATH;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.TERRA_SCATTERER;
    }
}