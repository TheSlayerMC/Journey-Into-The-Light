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

public class EntityPsyollom extends JEntityMob {
    public static final int ENTITY_TYPE = 25;

    public EntityPsyollom(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(1.7F, 2.7F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.PSYOLLOM;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.PSYOLLOM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.PSYOLLOM_HURT;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.PSYOLLUM;
	}

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.PSYOLLOM;
    }
}