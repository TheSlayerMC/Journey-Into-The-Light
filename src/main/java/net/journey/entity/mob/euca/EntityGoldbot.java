package net.journey.entity.mob.euca;

import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.journey.init.JourneySounds;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.JEntityMob;

import org.jetbrains.annotations.NotNull;

public class EntityGoldbot extends JEntityMob {

    public EntityGoldbot(World par1World) {
        super(par1World);
        addMeleeAttackingAI();
        setSize(0.7F, 1.2F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return JourneySounds.ROBOT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return JourneySounds.ROBOT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return JourneySounds.ROBOT_DEATH;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.GOLDBOT;
	}

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.GOLDBOT;
    }
}