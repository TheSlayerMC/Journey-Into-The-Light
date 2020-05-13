package net.journey.entity.mob.cloudia;

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

public class EntityStarlightWalker extends EntityModMob {

    public EntityStarlightWalker(World w) {
        super(w);
        addAttackingAI();
        setSize(1.0F, 1.0F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.StarlightWalkerDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.StarlightWalkerHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.EMPTY;
    }

    @Override
    public SoundEvent setHurtSound() {
        return SoundEvents.ENTITY_IRONGOLEM_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return SoundEvents.ENTITY_IRONGOLEM_DEATH;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.STARLIGHT_WALKER;
    }
}