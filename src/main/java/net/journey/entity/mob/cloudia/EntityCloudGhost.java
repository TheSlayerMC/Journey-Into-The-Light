package net.journey.entity.mob.cloudia;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityCloudGhost extends JEntityMob {

    public EntityCloudGhost(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(0.7F, 2.4F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.SPIKED_BEAST;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.SPIKED_BEAST_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.SPIKED_BEAST_HURT;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.CLOUD_GHOST;
	}

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.CLOUD_GHOST;
    }
}