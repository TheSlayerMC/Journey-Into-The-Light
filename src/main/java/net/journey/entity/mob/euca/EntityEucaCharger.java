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

public class EntityEucaCharger extends EntityModMob {

    public EntityEucaCharger(World par1World) {
        super(par1World);
        addAttackingAI();
        this.setMovementSpeed();
        setSize(0.7F, 1.0F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.EucaChargerDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.EucaChargerHealth;
    }

    @Override
    public double setMovementSpeed() {
        return 0.5D;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.HONGO;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.SAND_CRAWLER;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.SAND_CRAWLER;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.EUCA_CHARGER;
    }
}