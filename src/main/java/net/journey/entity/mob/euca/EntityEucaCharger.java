package net.journey.entity.mob.euca;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityEucaCharger extends JEntityMob {

    public EntityEucaCharger(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(0.7F, 1.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.HONGO;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.SAND_CRAWLER;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.SAND_CRAWLER;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.EUCA_CHARGER;
	}

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.EUCA_CHARGER;
    }
}