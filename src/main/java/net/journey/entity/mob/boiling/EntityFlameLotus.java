package net.journey.entity.mob.boiling;

import net.journey.api.entity.JEntityMob;
import net.journey.entity.MobStats;
import net.journey.init.JourneyLootTables;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class EntityFlameLotus extends JEntityMob {

    public EntityFlameLotus(World par1World) {
        super(par1World);
        this.setSize(2.0F, 0.4F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource d) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

	@Override
	public ResourceLocation getLootTable() {
		return JourneyLootTables.FLAME_LOTUS;
	}

    @Override
    public @NotNull EntitySettings getEntitySettings() {
        return MobStats.FLAME_LOTUS;
    }
}