package net.journey.entity.mob.terrania.mob;

import net.journey.entity.MobStats;
import net.journey.init.JourneySounds;
import net.journey.init.items.JourneyItems;
import net.journey.util.JourneyLootTables;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityModMob;

public class EntityTerraslug extends EntityModMob {

    public EntityTerraslug(World par1World) {
        super(par1World);
        addAttackingAI();
        setSize(0.4F, 0.4F);
    }

    @Override
    public double setAttackDamage(MobStats s) {
        return MobStats.TerraslugDamage;
    }

    @Override
    public double setMaxHealth(MobStats s) {
        return MobStats.TerraslugHealth;
    }

    @Override
    public SoundEvent setLivingSound() {
        return JourneySounds.TERRA_SLUG;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.TERRA_SLUG_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.TERRA_SLUG_DEATH;
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return JourneyLootTables.TERRA_SLUG;
    }
}