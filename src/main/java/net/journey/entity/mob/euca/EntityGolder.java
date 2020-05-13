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

public class EntityGolder extends EntityModMob {


    public EntityGolder(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(1.2F, 1.7F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.GolderDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.GolderHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.REAPER;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.REAPER_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.REAPER_HURT;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.GOLDER;
    }
}