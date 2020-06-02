package net.journey.entity.mob.pet;

import org.jetbrains.annotations.NotNull;

import net.journey.api.entity.ISettingsConsumer.EntitySettings;
import net.journey.init.JourneySounds;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.slayer.api.entity.EntityJourneyPet;

public class EntityTameRoc extends EntityJourneyPet {

	public EntityTameRoc(World worldIn) {
		super(worldIn);
	}

    public EntityTameRoc(World worldIn, EntityPlayer owner) {
		super(worldIn, owner);
	}

	@Override
    public SoundEvent setLivingSound() {
        return JourneySounds.BIRD;
    }

    @Override
    public SoundEvent setHurtSound() {
        return JourneySounds.BIRD_HURT;
    }

    @Override
    public SoundEvent setDeathSound() {
        return JourneySounds.BIRD_DEATH;
    }


	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
}